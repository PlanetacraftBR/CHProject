package me.hub.API.module;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SpawnNPC {

	 public static final Random RANDOM = new Random();
	
	 private static Vector[] vectors = { new Vector(0.0D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, 0.0D), new Vector(1.5D, 0.0D, 0.0D), new Vector(0.0D, 0.0D, -1.5D), new Vector(1.5D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, -1.5D) };
	 
	 
	public static void spawnNPC(Player player, Location loc, String name) {
	   
		 int numPlayers = 5;
		    for (int i = 1; i <= numPlayers; i++)
		    {
		      int degrees = 360 / (numPlayers - 1) * i;
		      double radians = Math.toRadians(degrees);
			    if (i == 1) {
			    Vector vec =  player.getLocation().add(0.0D, 2.0D, 0.0D).toVector();
			    }
			    else
			    {
			    Vector vec = player.getLocation().add(2.0D * Math.cos(radians), 0.2D, 2.0D * Math.sin(radians)).toVector();
			    
			    }
		    }

	}
	
	

}
