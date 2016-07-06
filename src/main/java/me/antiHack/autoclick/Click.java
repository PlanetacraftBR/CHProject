package me.antiHack.autoclick;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;

public class Click  extends MiniPlugin
implements Detector
{
public static AntiHack Host;

public Click(JavaPlugin plugin) {
	super("KillAura", plugin);
	  this.plugin = plugin;
}
Plugin plugin;
	  public static HashMap<Player, String> Click = new HashMap();
	  
	  
	  @EventHandler
	  public void onClick(InventoryClickEvent event)
	  {
	    if ((event.getWhoClicked() instanceof Player))
	    {
	      Player p = (Player)event.getWhoClicked();
	      if (Click.containsKey(p))
			{
	    	     if (event.isShiftClick())
	    	      {
				int clicks = Integer.parseInt(Click.get(p));
				clicks++;
			    Click.remove(p);
			    Click.put(p, "" + clicks);
			    if (clicks > 80) {
			    	 this.Host.addSuspicion(p, "Clicks no inv p/s (" + clicks + ")");
			    event.setCancelled(true);
			    }
	    	      }
			}
	  	else
		{
			Click.put(p, "1");
		}
	    }
	  }
		@EventHandler
		public void HitMob(EntityDamageByEntityEvent e)
		{

			Player p = (Player) e.getDamager();
			
		  if (e.getDamager().getType() == EntityType.PLAYER){
			
		      if (Click.containsKey(p))
				{
		    	  
					int clicks = Integer.parseInt(Click.get(p));
					clicks++;
				    Click.remove(p);
				    Click.put(p, "" + clicks);
				    
			if (clicks >= 4){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "f "+p.getName());
			}
			if (clicks == 13){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "f "+p.getName());
				this.Host.addSuspicion(p, "Esta Clickando muito rapido (Extremo)");
			}
			if (clicks >= 14){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "f "+p.getName());
				UtilPlayer.Kick(p, "Você esta muito suspeito de ser algum Cheat");
				this.Host.addSuspicion(p, "Esta Clickando muito rapido (Extremo)");
			}
			if (clicks >= 4){
			this.Host.addSuspicion(p, "Esta Clickando muito rapido ("+clicks+")");
			}
				}
			  	else
				{
					Click.put(p, "1");
				}
			}
			
		}

		  @EventHandler
		  public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
		  {
		    if (!(event.getDamager() instanceof Player)) {
		      return;
		    }
		    Player player = (Player)event.getDamager();
		    
		    AntiHack.setLastAttackTime(player.getUniqueId());
		  }
		  
		  @EventHandler
		  public void onEntityDamage(EntityDamageEvent event)
		  {
		    if (!(event.getEntity() instanceof Player)) {
		      return;
		    }
		    if (event.getCause() == EntityDamageEvent.DamageCause.SUICIDE) {
		      return;
		    }
		    Player player = (Player)event.getEntity();
		    
		   AntiHack.setLastAttackTime(player.getUniqueId());
		  }
	
	public void Reset(Player paramPlayer) {
		
		
	}
}
