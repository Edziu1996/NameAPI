package com.gmail.edziu1996.nameapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.api.entity.living.player.Player;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class ConfigPlayerName extends ConfigManager
{
	Map<String, Map<Object, ? extends CommentedConfigurationNode>> map = new HashMap<String, Map<Object,? extends CommentedConfigurationNode>>();

	public ConfigPlayerName(String name)
	{
		super(name);
	}
	
	@Override
	public void init()
	{
		if (get().hasMapChildren())
		{
			for (Entry<Object, ? extends CommentedConfigurationNode> e : get().getChildrenMap().entrySet())
			{
				map.put(e.getKey().toString(), e.getValue().getChildrenMap());
			}
		}
	}
	
	public void loadByPlayer(Player p)
	{
		String sid = p.getUniqueId().toString();
		
		if (get().hasMapChildren())
		{
			if (get().getChildrenMap().containsKey(sid))
			{
				map.remove(sid);
				map.put(sid, get().getNode(sid).getChildrenMap());
			}
			else
			{
				map.remove(sid);
			}
		}
	}

}
