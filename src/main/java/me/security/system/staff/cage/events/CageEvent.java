package me.security.system.staff.cage.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;
import me.security.system.staff.cage.CageCreate;

public class CageEvent extends MiniPlugin {

	public CageEvent(JavaPlugin plugin) {
		super("CageEvent", plugin);
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void Sair(PlayerQuitEvent event)
	{
		CageCreate.RemoverCage(event.getPlayer());
	}

	@EventHandler
	public void Teleport(PlayerInteractEvent event)
	{
		if (CageCreate.cages.containsKey(event.getPlayer()))
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Drop(PlayerDropItemEvent event)
	{
		if (CageCreate.cages.containsKey(event.getPlayer()))
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Feed(PlayerItemConsumeEvent event)
	{
		if (CageCreate.cages.containsKey(event.getPlayer()))
		{
			if (event.getItem().equals(Material.MUSHROOM_SOUP))
				return;
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Breack(BlockPlaceEvent event)
	{
		if (CageCreate.cages.containsKey(event.getPlayer()))
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Breack(BlockBreakEvent event)
	{
		if (CageCreate.cages.containsKey(event.getPlayer()))
		{
			event.setCancelled(true);
		}
	}
	
	
	
}
