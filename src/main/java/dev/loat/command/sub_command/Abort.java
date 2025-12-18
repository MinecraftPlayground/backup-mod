package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Abort extends Command {
    public static final String COMMAND = "abort";

    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName = context.getSource().getTextName();
        
        Logger.info("[%s] Aborting current action".formatted(playerName));

        Abort.sendSuccess(context, () -> Component.literal("/backup abort"));

        return 1;
    }
}
