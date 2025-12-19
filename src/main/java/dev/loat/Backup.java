package dev.loat;

import net.fabricmc.api.ModInitializer;

import dev.loat.logging.Logger;

import dev.loat.command.CommandManager;
import dev.loat.config.Config;
import dev.loat.config.ConfigManager;
import dev.loat.config.files.BackupConfigFile;


public class Backup implements ModInitializer {

  
    private static Config<BackupConfigFile> config;

    @SuppressWarnings("null")
    @Override
    public void onInitialize() {
        Logger.setLoggerClass(Backup.class);

        Backup.config = ConfigManager.CONFIG;
        CommandManager.register();

        Logger.info("Backup loaded.");
    }
}
