package com.gmail.edziu1996.nameapi;

import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandsManager
{
	static CommandSpec cmdNick = CommandSpec.builder()
			.description(Text.of("This command change display name"))
			.permission("nameapi.nick.use")
			.arguments(
					GenericArguments.string(Text.of("name")),
					GenericArguments.optional(GenericArguments.string(Text.of("player")))
					)
			.executor(new CmdNickExecutor())
			.build();

	public static void load(Game game)
	{
		game.getCommandManager().register(NameAPI.getPlugin(), cmdNick, "nick");
	}

}
