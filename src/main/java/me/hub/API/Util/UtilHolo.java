package me.hub.API.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.acf.Magic_Chest.MagicEvent;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.EntityBat;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.IBlockData;
import net.minecraft.server.v1_10_R1.PacketPlayOutBlockChange;
import net.minecraft.server.v1_10_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_10_R1.World;

public class UtilHolo {

	private static HashSet<Entity> holo = new HashSet();
	public static ArrayList<Player> Player1_7 = new ArrayList<>();
	
	
	
	public static void Holo(Location loc, String name)
	{
	     List<Entity> entities = Bukkit.getWorld(loc.getWorld().getName()).getEntities();
   	  for (Entity entity : entities){
   	  if (entity.getType() == EntityType.ARMOR_STAND)
   	  {
   		  if (entity.getLocation().equals(loc))
   			  entity.remove();
   	  }
   	  }
        ArmorStand hologram = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        hologram.setCustomName("§7§o" + name);
        hologram.setCustomNameVisible(true);
        hologram.setBasePlate(false);
        hologram.setVisible(false);
        hologram.setGravity(false);
        
        holo.add(hologram);
       
	}
	
	public static void HoloPerm(Location loc, String name)
	{
        ArmorStand hologram = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        hologram.setCustomName("§7§o" + name.replace("&", "§"));
        hologram.setCustomNameVisible(true);
        hologram.setBasePlate(false);
        hologram.setVisible(false);
        hologram.setGravity(false);
	}
	
	public static void RemoveHoloPerm(String holo2)
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
    		  if (entity.getName().contains(holo2))
    		  {
    			  entity.remove();
    		  }
    	  }
	}
	
	public static void RemoveHolo(Entity holo2)
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
    		  if (entity.equals(holo2))
    		  {
    			  entity.remove();
    		  }
    	  }
	}
	
	private static ArrayList<Entity> holograns = new ArrayList<>();
	
	public static void AddPlayer1_7(Player add)
	{
		Player1_7.add(add);
		for (Entity ent : holograns)
		{
		    final EntityPlayer nmsPlayer = ((CraftPlayer) add).getHandle();
		    World world = nmsPlayer.getWorld(); 
		    final EntityBat holo = new EntityBat(world);
		    holo.setCustomName(ent.getCustomName());
		    holo.setNoGravity(true);
		    holo.setInvulnerable(false);
		    holo.setCustomNameVisible(true);
		    holo.setLocation(ent.getLocation().getX(), ent.getLocation().getY(), ent.getLocation().getZ(), 0, 0);
		    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(holo);
		    nmsPlayer.playerConnection.sendPacket(packet);
		}
	}
	public static void ArmoStand()
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
    		  if (entity.getType() == EntityType.ARMOR_STAND)
    		  {
    			  if (entity.getCustomName() != null) {
    				if (!holograns.contains(entity)) {
    				 if (!MagicEvent.magics.contains(entity))
    					holograns.add(entity);
    				}
    			  }
    				  }
    	  }
	}
	
	public static void RemoveAllHolo()
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
    		  if (MagicEvent.magics.contains(entity))
    			  entity.remove();
    		  if (holo.equals(entity))
    		  {
    			  entity.remove();
    		  }
    	  }
	}
	
    public static void showHolo(Player p, String message, Location loc) {
    final EntityPlayer nmsPlayer = ((CraftPlayer) p).getHandle();
    World world = nmsPlayer.getWorld(); 
    final EntityArmorStand holo = new EntityArmorStand(world);
    holo.setCustomName(message);
    holo.setBasePlate(false);
    holo.setInvisible(true);
    holo.setNoGravity(true);
    holo.setCustomNameVisible(true);
    holo.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(holo);
    nmsPlayer.playerConnection.sendPacket(packet);
    if (Player1_7.contains(p)) {
    	Holo1_7(p,message,loc);
    }
    }
    
    public static void Holo1_7(Player p, String message, Location loc)
    {
    	final EntityPlayer nmsPlayer = ((CraftPlayer) p).getHandle();
	    World world = nmsPlayer.getWorld(); 
	    final EntityBat holo = new EntityBat(world);
	    holo.setCustomName(message);
	    holo.setAI(true);
	    holo.setNoGravity(true);
	    holo.setCustomNameVisible(true);
	    holo.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
	    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(holo);
	    nmsPlayer.playerConnection.sendPacket(packet);
    }
    
}
