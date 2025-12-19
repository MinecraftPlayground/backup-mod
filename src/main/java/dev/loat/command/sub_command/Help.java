package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.config.ConfigManager;
import dev.loat.config.files.BackupTextsFile;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


public final class Help extends Command {
    public static final String COMMAND = "help";
    
    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName = context.getSource().getTextName();

        Logger.info("[%s] Getting help".formatted(playerName));

        String TextCommandHelp = ConfigManager.getConfig(BackupTextsFile.class).get().commandHelp;

        Help.sendSuccess(context, () -> Component.literal(TextCommandHelp));
        return 1;
    }
}
