package dev.loat.command.sub_command;

import com.mojang.brigadier.context.CommandContext;

import dev.loat.command.Command;
import dev.loat.config.ConfigManager;
import dev.loat.config.files.BackupTextsFile;
import dev.loat.logging.Logger;
import net.minecraft.commands.CommandSourceStack;


public final class Reload extends Command {
    public static final String COMMAND = "reload";

    @SuppressWarnings("null")
    public static int execute(CommandContext<CommandSourceStack> context) {
        String playerName = context.getSource().getTextName();

        Logger.info("[%s] Reloading config".formatted(playerName));
        
        var textCommandReload = ConfigManager.getConfig(BackupTextsFile.class).get().commandReload;
        ConfigManager.loadAll();
       
        Reload.sendSuccess(context, () -> textCommandReload);
        return 1;
    }
}
