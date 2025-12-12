package dev.loat.command.suggestion_provider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class BackupFiles implements SuggestionProvider<CommandSourceStack> {
    @Override
	public CompletableFuture<Suggestions> getSuggestions(
        CommandContext<CommandSourceStack> context,
        SuggestionsBuilder builder
    ) throws CommandSyntaxException {
		// CommandSourceStack source = context.getSource();

		List<String> fileNames = List.of("file1", "file2", "file3");

		for (String fileName : fileNames) {
			builder.suggest(
                "\"%s.zip\"".formatted(fileName),
                Component.literal("Tooltip").withStyle(style -> style.withItalic(true))
            );
		}

		return builder.buildFuture();
	}
}
