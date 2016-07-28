package me.security.move;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;

public class AntiMove extends MiniPlugin {

	public AntiMove(JavaPlugin plugin) {
		super("AntiMove", plugin);
		// TODO Auto-generated constructor stub
	}
	
	public static Map<Player, Location> loc = new HashMap<>();
	
	@EventHandler
	public void Move(PlayerMoveEvent event)
	{
		if (loc.containsKey(event.getPlayer()))
		{
			event.getPlayer().teleport(loc.get(event.getPlayer()));
		}
	}

}
