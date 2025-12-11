package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Delete {
    public static final String COMMAND = "delete";

    public static int execute(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("/backup delete"), false);
        return 1;
    }
}
