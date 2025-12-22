package dev.loat.config.parser.constructor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.JsonOps;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

public class ComponentConstructor extends Constructor {

    /**
     * Constructs a new component constructor for parsing components (YAML -> components).
     */
    public ComponentConstructor(
        Class<?> configFileClass,
        LoaderOptions options
    ) {
        super(configFileClass, options);
    }

    @Override
    protected Object constructObject(Node node) {
        if (Component.class.isAssignableFrom(node.getType())) {
            return this.constructComponent(node);
        }
        return super.constructObject(node);
    }

    private Component constructComponent(Node node) {
        JsonElement json = nodeToJson(node);
        return ComponentSerialization.CODEC
            .decode(JsonOps.INSTANCE, json)
            .getOrThrow(err -> new IllegalStateException("Failed to decode Component: " + err))
            .getFirst();
    }

    private JsonElement nodeToJson(Node node) {
        if (node instanceof ScalarNode scalar) {
            Object value = constructScalar(scalar);
            if (value == null) {
                return JsonNull.INSTANCE;
            } else if (node.getTag().equals(Tag.BOOL)) {
                return new JsonPrimitive(Boolean.parseBoolean(value.toString()));
            } else if (node.getTag().equals(Tag.INT)) {
                return new JsonPrimitive(Integer.parseInt(value.toString()));
            } else if (node.getTag().equals(Tag.FLOAT)) {
                return new JsonPrimitive(Double.parseDouble(value.toString()));
            } else {
                return new JsonPrimitive(value.toString());
            }
        } else if (node instanceof SequenceNode sequence) {
            JsonArray array = new JsonArray();
            for (Node child : sequence.getValue()) {
                array.add(nodeToJson(child));
            }
            return array;
        } else if (node instanceof MappingNode mapping) {
            JsonObject object = new JsonObject();
            for (NodeTuple tuple : mapping.getValue()) {
                ScalarNode keyNode = (ScalarNode) tuple.getKeyNode();
                String key = constructScalar(keyNode);
                JsonElement value = nodeToJson(tuple.getValueNode());
                object.add(key, value);
            }
            return object;
        }
        throw new IllegalArgumentException("Unexpected node type: " + node.getClass().getName());
    }
}
