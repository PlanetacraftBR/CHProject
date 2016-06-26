package me.hub.npc;

import me.hub.NMS.NPC;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpawnNPC {
	
    private static Location skywars = new Location(Bukkit.getWorld("world"), -50.3, 164.5,-67.5);
    private static NPC npc = new NPC("zSky_", "§e[GAME] SkyWars",91, skywars, false);
    
	public static void SpawnNPC(Player online)
	{
	    npc.setItemInHand(Material.GOLDEN_APPLE, online);
	    npc.spawn(online);
	    	
	    
	}
	public static void removeNPC(Player online)
	{
	    npc.despawn(online);
	    	
	    
	}


}
