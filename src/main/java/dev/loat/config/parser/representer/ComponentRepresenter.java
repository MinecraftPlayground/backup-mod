package dev.loat.config.parser.representer;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.*;
import org.yaml.snakeyaml.representer.Representer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.JsonOps;

import dev.loat.logging.Logger;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComponentRepresenter extends Representer {

    public ComponentRepresenter(DumperOptions options) {
        super(options);

        this.representers.put(Component.class, data -> representComponent((Component) data));
        this.representers.put(MutableComponent.class, data -> representComponent((Component) data));
    }

    private Node representComponent(Component component) {
        JsonElement json = ComponentSerialization.CODEC
            .encodeStart(JsonOps.INSTANCE, component)
            .getOrThrow(err -> new IllegalStateException("Failed to encode Component: " + err));

        if (!json.isJsonObject()) {
            Logger.warning("Component encoded to non-object JSON: " + json);
            return representScalar(Tag.STR, json.toString());
        }

        JsonObject obj = json.getAsJsonObject();

        List<NodeTuple> nodes = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            Node keyNode = representScalar(Tag.STR, key);
            Node valueNode = representJsonElement(value);

            nodes.add(new NodeTuple(keyNode, valueNode));
        }

        MappingNode mapping = new MappingNode(Tag.MAP, nodes, DumperOptions.FlowStyle.BLOCK);

        // Optional: wenn du flow style willst (kompakter)
        // mapping.setFlowStyle(DumperOptions.FlowStyle.FLOW);

        return mapping;
    }

    private Node representJsonElement(JsonElement element) {
        if (element.isJsonNull()) {
            return representScalar(Tag.NULL, null);
        }
        if (element.isJsonPrimitive()) {
            JsonPrimitive prim = element.getAsJsonPrimitive();
            if (prim.isBoolean()) {
                return representScalar(Tag.BOOL, String.valueOf(prim.getAsBoolean()));
            }
            if (prim.isNumber()) {
                return representScalar(Tag.INT, prim.getAsNumber().toString());
            }
            return representScalar(Tag.STR, prim.getAsString(), DumperOptions.ScalarStyle.SINGLE_QUOTED);
        }
        if (element.isJsonArray()) {
            List<Node> list = new ArrayList<>();
            for (JsonElement e : element.getAsJsonArray()) {
                list.add(representJsonElement(e));
            }
            return new SequenceNode(Tag.SEQ, list, DumperOptions.FlowStyle.BLOCK);
        }
        if (element.isJsonObject()) {
            List<NodeTuple> tuples = new ArrayList<>();
            for (Map.Entry<String, JsonElement> e : element.getAsJsonObject().entrySet()) {
                Node k = representScalar(Tag.STR, e.getKey());
                Node v = representJsonElement(e.getValue());
                tuples.add(new NodeTuple(k, v));
            }
            return new MappingNode(Tag.MAP, tuples, DumperOptions.FlowStyle.BLOCK);
        }
        return representScalar(Tag.STR, element.toString(), DumperOptions.ScalarStyle.SINGLE_QUOTED);
    }
}
