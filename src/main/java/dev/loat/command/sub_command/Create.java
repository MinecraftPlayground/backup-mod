package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Create extends Command {
    public static final String COMMAND = "create";
    public static final String ARGUMENT = "comment";

    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String comment;

        if(Create.hasArgument(context, Create.ARGUMENT)) {
            comment = StringArgumentType.getString(context, Create.ARGUMENT);
            Logger.info("Creating new backup with comment \"%s\"".formatted(comment));
        } else {
            comment = "";
            Logger.info("Creating new backup");
        }

        context.getSource().sendSuccess(() -> Component.literal("/backup create \"%s\"".formatted(comment)), false);
        return 1;
    }
}
