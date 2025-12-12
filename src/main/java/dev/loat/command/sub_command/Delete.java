package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Delete {
    public static final String COMMAND = "delete";
    public static final String ARGUMENT = "name";


    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String name = StringArgumentType.getString(context, Restore.ARGUMENT);
        
        Logger.info("Deleting existing backup \"%s\"".formatted(name));
        
        context.getSource().sendSuccess(() -> Component.literal("/backup delete \"%s\"".formatted(name)), false);
        return 1;
    }
}
