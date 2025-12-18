package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class List extends Command {
    public static final String COMMAND = "list";
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        Logger.info("Listing all backups");
        
        List.sendSuccess(context, () -> Component.literal("/backup list"));
        return 1;
    }
}
