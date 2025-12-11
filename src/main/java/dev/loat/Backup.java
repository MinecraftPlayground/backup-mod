package dev.loat;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.loat.command.CommandManager;

public class Backup implements ModInitializer {

  public static final Logger LOGGER = LoggerFactory.getLogger("Backup");

  @Override
  public void onInitialize() {
    CommandManager.register();

    Backup.LOGGER.info("Hello Fabric world!");
  }
}
