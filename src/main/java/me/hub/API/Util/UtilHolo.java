package me.hub.API.Util;

import java.util.HashSet;
import java.util.List;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import com.comphenix.protocol.events.PacketContainer;

public class UtilHolo {

	private static HashSet<Entity> holo = new HashSet();
	
	public static void Holo(Location loc, String name)
	{
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
    holo.setGravity(false);
    holo.setCustomNameVisible(true);
    holo.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(holo);
    nmsPlayer.playerConnection.sendPacket(packet);
    }
    

}
