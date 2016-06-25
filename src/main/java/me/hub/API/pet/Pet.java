/**
 * 
 */
package me.hub.API.pet;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.Main;
import me.hub.MiniPlugin;
/**
 * @author adriancf
 *
 */
public class Pet extends MiniPlugin{

	    
	 /**
	 * @param moduleName
	 * @param plugin
	 */
	public Pet(JavaPlugin plugin) {
		super("Pet", plugin);
		// TODO Auto-generated constructor stub
	}

	public static HashMap<String,Entity> Pets = new HashMap<String, Entity>();
	
    @EventHandler
   public void onTarget(EntityTargetEvent event){
   if(event.getTarget() instanceof Player){
    if (event.getEntity().hasMetadata("PET"))
    {
    	event.setCancelled(true);
    }

   }
   }
	  @EventHandler
	  public void Leave(PlayerQuitEvent e)
	  {
		  try {
		  Pets.get(e.getPlayer().getName()).remove();
		  Bukkit.getWorld("world").playSound(e.getPlayer().getLocation(), Sound.HORSE_DEATH, 0.2F, 0.2F);
		  } catch (Exception e2) {
			  
		  }
		  }
	  
	  public static void createPet(Player player,Entity entity){
         try {
	      
            
          entity.setCustomName(player.getCustomName());
          entity.setCustomNameVisible(true);
          entity.teleport(player.getLocation());
          
          entity.setMetadata("PET", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
          Pets.put(player.getName(), entity);
         } catch (Exception e) {
             e.printStackTrace();
         }
         }

 
  public void followPlayer(Creature creature,Player player,double Speed){
          Location location = player.getLocation();
         
         
          Random rnd = new Random();
          int zufall = rnd.nextInt(6);
          switch(zufall){
          case 0:
                  location.add(1.5,0,1.5);
                  break;
          case 1:
                  location.add(0,0,1.5);
                  break;
          case 2:
                  location.add(1.5,0,0);
                  break;
          case 3:
                  location.subtract(1.5,0,1.5);
                  break;
          case 4:
                  location.subtract(0,0,1.5);
                  break;
          case 5:
                  location.subtract(1.5,0,0);
                  break;
          }
         
         
          if(location.distanceSquared(creature.getLocation()) > 100){
                  if(!player.isOnGround()){
           return;
                  }
                  creature.teleport(location);
          }else{
          ((CraftCreature)creature).getHandle().getNavigation().a(location.getX(),location.getY(),location.getZ(),Speed);
          }
  }
  

  
  @EventHandler
  public void onDmg(EntityDamageByEntityEvent event){
          Entity entity = (Entity)event.getEntity();
      if(Pets.containsValue(entity)){
          event.setCancelled(true);
      }
      if(event.getDamager() != null){
          Entity damager = (Entity)event.getDamager();
           if(Pets.containsValue(damager)){
                  event.setCancelled(true);
              }
      }
      }
      
  @EventHandler
  public void onMove(PlayerMoveEvent event){
          Player player = (Player)event.getPlayer();
          if(Pets.containsKey(player.getName())){
        	  try {
          followPlayer((Creature)Pets.get(player.getName()), player, 1.6);
        	  }
  	     catch (Exception e) {
  	     }}
  }
  
}
