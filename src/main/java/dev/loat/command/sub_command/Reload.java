package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Reload extends Command {
    public static final String COMMAND = "reload";

    public static int execute(CommandContext<CommandSourceStack> context) {
        Logger.info("Reloading config");
       
        Reload.sendSuccess(context, () -> Component.literal("/backup reload"));
        return 1;
    }
}
