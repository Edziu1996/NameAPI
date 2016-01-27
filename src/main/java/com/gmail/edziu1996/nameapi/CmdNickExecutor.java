package com.gmail.edziu1996.nameapi;

import org.spongepowered.api.Game;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class CmdNickExecutor implements CommandExecutor
{
	Game game = NameAPI.getPlugin().getGame();
	ConfigPlayerName plName = NameAPI.getPlugin().playerName;
	
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		if (!(src instanceof ConsoleSource))
		{
			String name = args.<String>getOne("name").get();
			
			if (args.hasAny("player"))
			{
				Player p = game.getServer().getPlayer(args.<String>getOne("player").get()).orElse(null);
				if (p != null)
				{
					NameAPI.getPlugin().setDisplayName(p, name);
				}
				else
				{
					src.sendMessage(Text.of("Player must be online!"));
				}
			}
			else
			{
				NameAPI.getPlugin().setDisplayName((Player) src, name);
			}
		}
		else
		{
			String name = args.<String>getOne("name").get();
			
			if (args.hasAny("player"))
			{
				Player p = game.getServer().getPlayer(args.<String>getOne("player").get()).orElse(null);
				
				if (p != null)
				{
					NameAPI.getPlugin().setDisplayName(p, name);
				}
				else
				{
					src.sendMessage(Text.of("Player must be online!"));
				}
			}
			else
			{
				src.sendMessage(Text.of("Usage in console: /nick <name> <player>"));
			}
		}
		
		return CommandResult.success();
	}

}
