package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Restore extends Command {
    public static final String COMMAND = "restore";
    public static final String ARGUMENT = "name";
    
    public static final class Argument {
        public static final String LATEST = "latest";
    }

    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String name;
        
        if(Create.hasArgument(context, Restore.ARGUMENT)) {
            name = StringArgumentType.getString(context, Restore.ARGUMENT);
        } else {
            name = Restore.Argument.LATEST;
        }

        Logger.info("Restoring existing backup \"%s\"".formatted(name));

        Restore.sendSuccess(context, () -> Component.literal("/backup restore \"%s\"".formatted(name)));
        return 1;
    }
}
