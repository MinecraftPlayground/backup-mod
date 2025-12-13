package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Info {
    public static final String COMMAND = "info";
    public static final String ARGUMENT = "name";

    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String name = StringArgumentType.getString(context, Info.ARGUMENT);

        Logger.info("Getting info about existing backup \"%s\"".formatted(name));
        
        context.getSource().sendSuccess(() -> Component.literal("/backup info \"%s\"".formatted(name)), false);
        return 1;
    }
}
