package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Delete extends Command {
    public static final String COMMAND = "delete";
    public static final String ARGUMENT = "name";


    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName = context.getSource().getTextName();
        String name = StringArgumentType.getString(context, Restore.ARGUMENT);
        
        Logger.info("[%s] Deleting existing backup \"%s\"".formatted(playerName, name));
        
        Delete.sendSuccess(context, () -> Component.literal("/backup delete \"%s\"".formatted(name)));
        return 1;
    }
}
