package dev.loat.command;

import java.util.function.Supplier;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Represents a command.
 */
public class Command {
    /**
     * Checks if the given command context contains the given argument.
     *
     * @param context The command context to check.
     * @param argument The argument to check for.
     * @return True if the argument exists, false otherwise.
     */
    protected static boolean hasArgument(
        CommandContext<CommandSourceStack> context,
        String argument
    ) {
        return context.getNodes().stream().anyMatch(node -> node.getNode().getName().equals(argument));
    }

    /**
     * Sends a success message to the player executing the command, if the command source is a player.
     *
     * @param context The command context to send the success message to.
     * @param supplier The supplier of the success message component.
     */
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
