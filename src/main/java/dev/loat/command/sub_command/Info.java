package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Info {
    public static final String COMMAND = "info";

    public static int execute(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("/backup info"), false);
        return 1;
    }
}
