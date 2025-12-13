package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Reload {
    public static final String COMMAND = "reload";

    public static int execute(CommandContext<CommandSourceStack> context) {
        Logger.info("Reloading config");
       
        context.getSource().sendSuccess(() -> Component.literal("/backup reload"), false);
        return 1;
    }
}
