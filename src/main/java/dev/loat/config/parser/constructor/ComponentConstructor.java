package dev.loat.config.parser.constructor;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.Map;
import java.util.stream.Collectors;

public class ComponentConstructor extends Constructor {

    public ComponentConstructor(Class<?> rootClass, LoaderOptions loaderOptions) {
        super(rootClass, loaderOptions);
        this.yamlClassConstructors.put(NodeId.mapping, new ConstructComponentMapping());
        // Optional: Für Scalar (einfache Strings) direkt Component.literal machen
        this.yamlClassConstructors.put(NodeId.scalar, new ConstructScalarComponent());
    }

    private class ConstructComponentMapping extends ConstructMapping {

        @SuppressWarnings("null")
        @Override
        protected Object constructJavaBean2ndStep(MappingNode node, Object object) {
            for (NodeTuple tuple : node.getValue()) {
                Node keyNode = tuple.getKeyNode();
                Node valueNode = tuple.getValueNode();

                if (keyNode instanceof ScalarNode keyScalar) {
                    String propertyName = constructScalar(keyScalar).toString();

                    if (isComponentField(propertyName)) {
                        Component component = null;

                        if (valueNode instanceof MappingNode mapping) {
                            // Normaler Component mit Feldern (text, extra, color, ...)
                            Map<String, Object> map = constructMapping(mapping)
                                .entrySet().stream()
                                .collect(Collectors.toMap(
                                    e -> String.valueOf(e.getKey()),   // Schlüssel immer String
                                    Map.Entry::getValue
                                ));
                            JsonObject json = mapToJsonObject(map);
                            component = ComponentSerialization.CODEC
                                    .parse(JsonOps.INSTANCE, json)
                                    .getOrThrow(err -> new IllegalStateException("Failed to decode Component: " + err));

                        } else if (valueNode instanceof ScalarNode scalar) {
                            // Einfacher String → Component.literal
                            String text = constructScalar(scalar).toString();
                            // Entferne ggf. umschließende Quotes, die SnakeYAML manchmal hinzufügt
                            text = text.replaceAll("^\"|\"$", "");
                            component = Component.literal(text);
                        }

                        if (component != null) {
                            try {
                                java.lang.reflect.Field field = object.getClass().getDeclaredField(propertyName);
                                field.setAccessible(true);
                                field.set(object, component);
                            } catch (Exception e) {
                                throw new RuntimeException("Failed to set field: " + propertyName, e);
                            }
                            continue; // Standard-Zuweisung überspringen
                        }
                    }
                }
            }

            return super.constructJavaBean2ndStep(node, object);
        }
    }

    /**
     * Für reine Scalar-Werte im YAML, die direkt als Component interpretiert werden sollen
     */
    private class ConstructScalarComponent extends ConstructScalar {

        @SuppressWarnings("null")
        @Override
        public Object construct(Node node) {
            String value = constructScalar((ScalarNode) node).toString();
            return Component.literal(value);
        }
    }

    private boolean isComponentField(String name) {
        return "commandHelp".equals(name) || "commandReload".equals(name);
        // Füge weitere Felder hinzu, wenn nötig
    }

    private JsonObject mapToJsonObject(Map<?, ?> map) {
        JsonObject obj = new JsonObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = String.valueOf(entry.getKey());
            Object value = entry.getValue();

            if (value == null) {
                obj.add(key, JsonNull.INSTANCE);
            } else if (value instanceof Map) {
                obj.add(key, mapToJsonObject((Map<?, ?>) value));
            } else if (value instanceof Iterable<?> list) {
                com.google.gson.JsonArray array = new com.google.gson.JsonArray();
                for (Object item : list) {
                    if (item instanceof Map) {
                        array.add(mapToJsonObject((Map<?, ?>) item));
                    } else if (item instanceof String) {
                        array.add((Boolean) item);
                    } else {
                        array.add(JsonParser.parseString(String.valueOf(item)));
                    }
                }
                obj.add(key, array);
            } else {
                obj.add(key, JsonParser.parseString(String.valueOf(value)));
            }
        }
        return obj;
    }
}
