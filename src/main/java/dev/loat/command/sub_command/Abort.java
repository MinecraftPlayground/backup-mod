package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Abort {
    public static final String COMMAND = "abort";

    public static int execute(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("/backup abort"), false);
        return 1;
    }
}
