package dev.loat.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;

import com.mojang.brigadier.arguments.StringArgumentType;

import dev.loat.command.sub_command.*;
import dev.loat.command.suggestion_provider.BackupFiles;

public class CommandManager {
    @SuppressWarnings("null")
    public static void register() {
        CommandRegistrationCallback.EVENT.register((
            dispatcher,
            registryAccess,
            environment
        ) -> dispatcher.register(
            Commands.literal("backup").executes(Help::execute)
                .then(Commands.literal(Help.COMMAND).executes(Help::execute))
                .then(Commands.literal(Reload.COMMAND).executes(Reload::execute))
                .then(Commands.literal(Create.COMMAND).executes(Create::execute)
                    .then(Commands.argument(Create.ARGUMENT, StringArgumentType.greedyString()).executes(Create::execute))
                )
                .then(Commands.literal(Restore.COMMAND)
                    .then(Commands.literal(Restore.Argument.LATEST).executes(Restore::execute))
                    .then(Commands.argument(Restore.ARGUMENT, StringArgumentType.string()).suggests(new BackupFiles()).executes(Restore::execute))
                )
                .then(Commands.literal(Delete.COMMAND)
                    .then(Commands.argument(Delete.ARGUMENT, StringArgumentType.string()).suggests(new BackupFiles()).executes(Delete::execute))
                )
                .then(Commands.literal(Abort.COMMAND).executes(Abort::execute))
                .then(Commands.literal(List.COMMAND).executes(List::execute))
                .then(Commands.literal(Info.COMMAND)
                    .then(Commands.argument(Info.ARGUMENT, StringArgumentType.string()).suggests(new BackupFiles()).executes(Info::execute))
                )
                .then(Commands.literal(Cleanup.COMMAND).executes(Cleanup::execute))
        ));
    }
}
