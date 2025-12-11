package dev.loat.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;

import dev.loat.command.sub_command.*;

public class CommandManager {
    @SuppressWarnings("null")
    public static void register() {
        CommandRegistrationCallback.EVENT.register((
            dispatcher,
            registryAccess,
            environment
        ) -> dispatcher.register(
            Commands.literal("backup")
                .then(Commands.literal(Help.COMMAND).executes(Help::execute))
                .then(Commands.literal(Reload.COMMAND).executes(Reload::execute))
                .then(Commands.literal(Create.COMMAND).executes(Create::execute))
                .then(Commands.literal(Restore.COMMAND).executes(Restore::execute))
                .then(Commands.literal(Abort.COMMAND).executes(Abort::execute))
                .then(Commands.literal(List.COMMAND).executes(List::execute))
                .then(Commands.literal(Info.COMMAND).executes(Info::execute))
                .then(Commands.literal(Cleanup.COMMAND).executes(Cleanup::execute))
                .then(Commands.literal(Delete.COMMAND).executes(Delete::execute))
                .executes(Help::execute)
        ));
    }
}
