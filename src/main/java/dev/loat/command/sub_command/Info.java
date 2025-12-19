package dev.loat.command.sub_command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Info extends Command {
    public static final String COMMAND = "info";
    public static final String ARGUMENT = "name";

    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName = context.getSource().getTextName();
        String name = StringArgumentType.getString(context, Info.ARGUMENT);

        Logger.info("[%s] Getting info about existing backup \"%s\"".formatted(playerName, name));
        
        Info.sendSuccess(context, () -> Component.literal("/backup info \"%s\"".formatted(name)));
        return 1;
    }
}
