package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class List {
    public static final String COMMAND = "list";
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        Logger.info("Listing all backups");
        
        context.getSource().sendSuccess(() -> Component.literal("/backup list"), false);
        return 1;
    }
}
