package com.gmail.edziu1996.nameapi;

import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id="NameAPI", version="0.2", name="NameAPI")
public class NameAPI
{
	@Inject
	private Logger logger;
	
	private static NameAPI plugin;
	
	@Inject
	@ConfigDir(sharedRoot=false)
	private Path configDir;
	
	Game game = Sponge.getGame();
	
	ConfigPlayerName playerName;
	
	@Listener
	public void preInit(GamePreInitializationEvent event)
	{
		plugin = this;
		
		playerName = new ConfigPlayerName("playerName");
		playerName.setup();
	}
	
	@Listener
	public void init(GameInitializationEvent event)
	{
		CommandsManager.load(game);
	}
	
	@Listener
	public void postInit(GamePostInitializationEvent event) {}
	
	@Listener
	public void onServerStart(GameStartedServerEvent event)
	{
		logger.info("This plugin has started!");
	}
	
	@Listener
	public void onServerStop(GameStoppedServerEvent event)
	{
		logger.info("This plugin has stopped!");
	}
	
	public Path getConfigDir()
	{
		return configDir;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public Logger getLogger()
	{
		return logger;
	}
	
	public static NameAPI getPlugin()
	{
		return plugin;
	}
	
	public String getName(Player p)
	{
		return p.getName();
	}
	
	public UUID getUUID(Player p)
	{
		return p.getUniqueId();
	}
	
	public boolean hasDisplayName(Player p)
	{
		boolean has = false;
		String sid = getUUID(p).toString();
		
		if (playerName.map.containsKey(sid))
		{
			has = true;
		}
		
		return has;
	}
	
	public String getDisplayName(Player p)
	{
		String name = null;
		String sid = getUUID(p).toString();
		
		if(hasDisplayName(p))
		{
			name = playerName.map.get(sid);
		}
		
		return name;
	}
	
	public void setDisplayName(Player p, String name)
	{
		playerName.get().getNode(getUUID(p).toString()).setValue(name);
		playerName.save();
		playerName.loadByPlayer(p);
	}
	
	public void removeDisplayName(Player p)
	{
		playerName.get().removeChild(getUUID(p).toString());
		playerName.save();
		playerName.loadByPlayer(p);
	}
	
	
}
