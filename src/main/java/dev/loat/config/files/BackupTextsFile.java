package dev.loat.config.files;

import dev.loat.config.annotation.Comment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;

public final class BackupTextsFile {
    @Comment("/backup help")
    public Component commandHelp = Component.empty()
        .append(Component.literal("/backup help "))
        .append(Component.literal("Displays this help message.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup reload "))
        .append(Component.literal("Reloads the backup configuration and texts.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup create [<comment>] "))
        .append(Component.literal("Creates a new backup.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup restore (latest|<name>) "))
        .append(Component.literal("Restores an existing backup.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup delete <name> "))
        .append(Component.literal("Deletes an existing backup.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup list "))
        .append(Component.literal("Lists all existing backups.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup info <name> "))
        .append(Component.literal("Displays information about an existing backup.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        )
        .append(Component.literal("\n"))
        .append(Component.literal("/backup cleanup "))
        .append(Component.literal("Cleans up old backups based on the configuration.")
            .withStyle(style -> style
                .withItalic(true)
                .withColor(TextColor.parseColor("#AAAAAA").getOrThrow())
            )
        );

    @Comment("/backup reload")
    public Component commandReload = Component.literal("Config reloaded successfully.");
}
