package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Cleanup extends Command {
    public static final String COMMAND = "cleanup";

    public static int execute(CommandContext<CommandSourceStack> context) {
        Logger.info("Running cleanup process");

        Cleanup.sendSuccess(context, () -> Component.literal("/backup cleanup"));

        return 1;
    }
}
