package dev.loat.command;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;

public class Command {
    protected static boolean hasArgument(
        CommandContext<CommandSourceStack> context,
        String argument
    ) {
        return context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals(argument));
    }
}
