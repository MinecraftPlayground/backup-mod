package dev.loat.command;

import java.util.function.Supplier;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Command {
    protected static boolean hasArgument(
        CommandContext<CommandSourceStack> context,
        String argument
    ) {
        return context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals(argument));
    }

    @SuppressWarnings("null")
    protected static void sendSuccess(
        CommandContext<CommandSourceStack> context,
        Supplier<Component> supplier
    ) {
        if (context.getSource().getEntity() instanceof Player) {
            context.getSource().sendSuccess(supplier, false);
        }
    }
}
