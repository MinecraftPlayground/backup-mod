package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;


public final class Abort {
    public static final String COMMAND = "abort";

    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName;

        if (context.getSource().getEntity() instanceof Player) {
            playerName = context.getSource().getPlayer().getName().getString();
        } else {
            playerName = "Console";
        }
        
        Logger.info("[%s] Aborting current action".formatted(playerName));

        context.getSource().sendSuccess(() -> Component.literal("/backup abort"), false);
        return 1;
    }
}
