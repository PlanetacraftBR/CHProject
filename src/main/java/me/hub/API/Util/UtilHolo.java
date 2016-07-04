package me.hub.API.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.EntityWither;
import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_10_R1.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_10_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_10_R1.PlayerConnection;
import net.minecraft.server.v1_10_R1.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import com.comphenix.protocol.events.PacketContainer;

import me.hub.API.Util.Holo1_7.UtilHolo1_7;

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
		    UtilHolo1_7 holo1_7 = new UtilHolo1_7(ent.getCustomName());
		    holo1_7.Holo1_7.add(add);
		    holo1_7.show(ent.getLocation());
		}
	}
	public static void ArmoStand()
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
    		  if (entity.getType() == EntityType.ARMOR_STAND)
    		  {
    			  if (entity.getCustomName() != null)
    				  holograns.add(entity);
    				  }
    	  }
	}
	
	public static void RemoveAllHolo()
	{
	      List<Entity> entities = Bukkit.getWorld("world").getEntities();
    	  for (Entity entity : entities){
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
    UtilHolo1_7 holo1_7 = new UtilHolo1_7(message);
    holo1_7.Holo1_7.add(p);
    holo1_7.show(loc);
    }
    }
    

}
