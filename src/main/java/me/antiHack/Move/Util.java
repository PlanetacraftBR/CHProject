package me.antiHack.Move;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Util {
	public static boolean b(Player p)
	{
	  Location loc1 = new Location(p.getWorld(), p.getLocation().getX() + 1.0D, p.getLocation().getY() - 1.0D, p.getLocation().getZ());
	  Location loc2 = new Location(p.getWorld(), p.getLocation().getX() - 1.0D, p.getLocation().getY() - 1.0D, p.getLocation().getZ());
	  Location loc3 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0D, p.getLocation().getZ() + 1.0D);
	  Location loc4 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0D, p.getLocation().getZ() - 1.0D);
	  
	  Material b1 = loc1.getBlock().getType();
	  Material b2 = loc2.getBlock().getType();
	  Material b3 = loc3.getBlock().getType();
	  Material b4 = loc4.getBlock().getType();
	  
	  Material wt = Material.STATIONARY_WATER;
	  if ((b1 != wt) || (b2 != wt) || 
	    (b3 != wt) || (b4 != wt)) {
	    return true;
	  }
	  Location loc1x = new Location(p.getWorld(), p.getLocation().getX() + 2.0D, p.getLocation().getY() - 1.0D, p.getLocation().getZ());
	  Location loc2x = new Location(p.getWorld(), p.getLocation().getX() - 2.0D, p.getLocation().getY() - 1.0D, p.getLocation().getZ());
	  Location loc3x = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0D, p.getLocation().getZ() + 2.0D);
	  Location loc4x = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0D, p.getLocation().getZ() - 2.0D);
	  
	  Material b1x = loc1x.getBlock().getType();
	  Material b2x = loc2x.getBlock().getType();
	  Material b3x = loc3x.getBlock().getType();
	  Material b4x = loc4x.getBlock().getType();
	  if ((b1x != wt) || (b2x != wt) || 
	    (b3x != wt) || (b4x != wt)) {
	    return true;
	  }
	  Location loc1xb = new Location(p.getWorld(), p.getLocation().getX() + 1.0D, p.getLocation().getY(), p.getLocation().getZ());
	  Location loc2xb = new Location(p.getWorld(), p.getLocation().getX() - 1.0D, p.getLocation().getY(), p.getLocation().getZ());
	  Location loc3xb = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() + 1.0D);
	  Location loc4xb = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() - 1.0D);
	  
	  Material lilyB1 = loc1xb.getBlock().getType();
	  Material lilyB2 = loc2xb.getBlock().getType();
	  Material lilyB3 = loc3xb.getBlock().getType();
	  Material lilyB4 = loc4xb.getBlock().getType();
	  if ((lilyB1 == Material.WATER_LILY) || 
	    (lilyB2 == Material.WATER_LILY) || 
	    (lilyB3 == Material.WATER_LILY) || 
	    (lilyB4 == Material.WATER_LILY)) {
	    return true;
	  }
	  return false;
	}
}
