package dev.loat;

import net.fabricmc.api.ModInitializer;

import dev.loat.logging.Logger;

import dev.loat.command.CommandManager;
import dev.loat.config.Config;
import dev.loat.config.ConfigManager;
import dev.loat.config.files.BackupConfigFile;
import dev.loat.config.files.BackupTextsFile;


public class Backup implements ModInitializer {
    @Override
    public void onInitialize() {
        Logger.setLoggerClass(Backup.class);

        ConfigManager.addConfig(new Config<>(
            ConfigManager.resolve("config.yml"),
            BackupConfigFile.class
        ));

        ConfigManager.addConfig(new Config<>(
            ConfigManager.resolve("texts.yml"),
            BackupTextsFile.class
        ));

        CommandManager.register();

        Logger.info("Backup initialized.");
    }
}
