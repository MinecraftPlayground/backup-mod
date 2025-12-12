package dev.loat;

import net.fabricmc.api.ModInitializer;

import dev.loat.logging.Logger;
import dev.loat.command.CommandManager;


public class Backup implements ModInitializer {

  
  @Override
  public void onInitialize() {
    Logger.setLoggerClass(Backup.class);
    CommandManager.register();

    Logger.info("Hello Fabric world!");
  }
}
