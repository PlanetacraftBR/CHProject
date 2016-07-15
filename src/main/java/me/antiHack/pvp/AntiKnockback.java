package me.antiHack.pvp;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;

public class AntiKnockback  extends MiniPlugin
implements Detector
{
public static AntiHack Host;

public AntiKnockback(AntiHack host)
{
  super("AntiKnockback Detector", host.GetPlugin());
  this.Host = host;
}
     Plugin plugin;
	 
     
     @EventHandler
     public void onVelocityChange(final PlayerVelocityEvent e)
     {
       final Player p = e.getPlayer();
       Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
       if ((p.getLocation().getBlock().getRelative(-1, 0, 0).getType() != Material.AIR) || 
         (p.getLocation().getBlock().getRelative(1, 0, 0).getType() != Material.AIR) || 
         (p.getLocation().getBlock().getRelative(0, 0, -1).getType() != Material.AIR) || 
         (p.getLocation().getBlock().getRelative(0, 0, 1).getType() != Material.AIR)) {
         return;
       }
       final Location hit = p.getLocation();

       if (canBypass(p)) {
         return;
       }

       double force = Math.abs(e.getVelocity().getX() + e.getVelocity().getY() + e.getVelocity().getZ());
       if ((force == 0.0D) || (e.getVelocity().getX() == 0.0D) || (e.getVelocity().getZ() == 0.0D)) {
         return;
       }
       Bukkit.getScheduler().runTaskLater(Main.plugin,new Runnable()
       {
         public void run()
         {
           if ((p == null) || (!p.isValid())) {
             return;
           }
           if (p.getLocation().distanceSquared(hit) < 1.0E-4D)
           {
             e.setCancelled(true);
          Host.addSuspicion(p, "Suspeito de AntiKnockBack");
           }
         }
       }, 3L);
     }
     
     private boolean canBypass(Player p)
     {
       Boolean result = Boolean.valueOf(false);
       int ping = ((CraftPlayer)p).getHandle().ping;
       if (ping > 300) {
         result = Boolean.valueOf(true);
       }
       if (p.getVehicle() != null) {
         result = Boolean.valueOf(true);
       }
       return result.booleanValue();
     }
	  
	  
	 
	
	public void Reset(Player paramPlayer) {
		
		
	}
}
