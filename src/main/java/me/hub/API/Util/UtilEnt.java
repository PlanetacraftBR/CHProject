package me.hub.API.Util;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class UtilEnt
{
  private static HashMap<org.bukkit.entity.Entity, String> _nameMap = new HashMap();

  
  public static HashMap<org.bukkit.entity.Entity, String> GetEntityNames()
  {
    return _nameMap;
  }
  

  public static boolean onBlock(Player player)
  {
    double xMod = player.getLocation().getX() % 1.0D;
    if (player.getLocation().getX() < 0.0D) {
      xMod += 1.0D;
    }
    double zMod = player.getLocation().getZ() % 1.0D;
    if (player.getLocation().getZ() < 0.0D) {
      zMod += 1.0D;
    }
    int xMin = 0;
    int xMax = 0;
    int zMin = 0;
    int zMax = 0;
    if (xMod < 0.3D) {
      xMin = -1;
    }
    if (xMod > 0.7D) {
      xMax = 1;
    }
    if (zMod < 0.3D) {
      zMin = -1;
    }
    if (zMod > 0.7D) {
      zMax = 1;
    }
    for (int x = xMin; x <= xMax; x++) {
      for (int z = zMin; z <= zMax; z++)
      {
        if ((player.getLocation().add(x, -0.5D, z).getBlock().getType() != Material.AIR) && (!player.getLocation().add(x, -0.5D, z).getBlock().isLiquid())) {
          return true;
        }
        Material beneath = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        if ((player.getLocation().getY() % 0.5D == 0.0D) && (
          (beneath == Material.FENCE) || 
          (beneath == Material.FENCE_GATE) || 
          (beneath == Material.NETHER_FENCE) || 
          (beneath == Material.SPRUCE_FENCE) || 
          (beneath == Material.SPRUCE_FENCE_GATE) || 
          (beneath == Material.COBBLE_WALL))) {
          return true;
        }
        String bug = "" + beneath;
        if ((bug.contains("FENCE")) || (bug.contains("fence")))
        	return true;
      }
    }
    return false;
  }
  
  public static boolean onBlock_JESUS(Player player)
  {
    double xMod = player.getLocation().getX() % 1.0D;
    if (player.getLocation().getX() < 0.0D) {
      xMod += 1.0D;
    }
    double zMod = player.getLocation().getZ() % 1.0D;
    if (player.getLocation().getZ() < 0.0D) {
      zMod += 1.0D;
    }
    int xMin = 0;
    int xMax = 0;
    int zMin = 0;
    int zMax = 0;
    if (xMod < 0.3D) {
      xMin = -1;
    }
    if (xMod > 0.7D) {
      xMax = 1;
    }
    if (zMod < 0.3D) {
      zMin = -1;
    }
    if (zMod > 0.7D) {
      zMax = 1;
    }
    for (int x = xMin; x <= xMax; x++) {
      for (int z = zMin; z <= zMax; z++)
      {
        
        Material beneath = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        if (
          (beneath == Material.WATER) || 
          (beneath == Material.STATIONARY_WATER) || 
          (beneath == Material.LAVA) || 
          (beneath == Material.STATIONARY_LAVA)) {
          return true;
        }
      
  
      }
    }
    return false;
  }
  
	public static void PlayDamageSound(Entity damagee) 
	{
		Sound sound = Sound.ENTITY_CAT_HURT;
		
		if (damagee.getType() == EntityType.BAT)				sound = Sound.ENTITY_BAT_HURT;
		else if (damagee.getType() == EntityType.BLAZE)			sound = Sound.ENTITY_BLAZE_HURT;
		
		else if (damagee.getType() == EntityType.CHICKEN)		sound = Sound.ENTITY_CHICKEN_HURT;
		else if (damagee.getType() == EntityType.COW)			sound = Sound.ENTITY_COW_HURT;

		else if (damagee.getType() == EntityType.ENDER_DRAGON)	sound = Sound.ENTITY_ENDERDRAGON_GROWL;
		
		else if (damagee.getType() == EntityType.GHAST)			sound = Sound.ENTITY_GHAST_SCREAM;
		else if (damagee.getType() == EntityType.GIANT)			sound = Sound.ENTITY_ZOMBIE_HURT;
		
		else if (damagee.getType() == EntityType.MAGMA_CUBE)	sound = Sound.ENTITY_MAGMACUBE_JUMP;

		else if (damagee.getType() == EntityType.PIG_ZOMBIE)	sound = Sound.ENTITY_ZOMBIE_HURT;
		
		else if (damagee.getType() == EntityType.SKELETON)		sound = Sound.ENTITY_SKELETON_HURT;
		else if (damagee.getType() == EntityType.SLIME)			sound = Sound.ENTITY_SLIME_ATTACK;
		//else if (damagee.getType() == EntityType.SQUID)		sound = Sound;
		else if (damagee.getType() == EntityType.VILLAGER)	    sound = Sound.ENTITY_VILLAGER_HURT;
		//else if (damagee.getType() == EntityType.WITCH)		sound = Sound;
		else if (damagee.getType() == EntityType.WITHER)		sound = Sound.ENTITY_WITHER_HURT;
		else if (damagee.getType() == EntityType.WOLF)			sound = Sound.ENTITY_WOLF_HURT;
		else if (damagee.getType() == EntityType.ZOMBIE)		sound = Sound.ENTITY_ZOMBIE_HURT;	

		damagee.getWorld().playSound(damagee.getLocation(), sound, 1.5f + (float)(0.5f * Math.random()), 0.8f + (float)(0.4f * Math.random()));
	}
  public static boolean isGrounded(org.bukkit.entity.Entity ent)
  {
    if ((ent instanceof CraftEntity)) {
      return ((CraftEntity)ent).getHandle().onGround;
    }
    return UtilBlock.solid(ent.getLocation().getBlock().getRelative(BlockFace.DOWN));
  }
}