package me.antiHack.FastHeal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;

public class FastHeal  extends MiniPlugin
implements Detector
{
private AntiHack Host;


public FastHeal(AntiHack host)
{
  super("FastHeal Detector", host.GetPlugin());
  this.Host = host;
}

@EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
public void onEntityRegainHealthLow(EntityRegainHealthEvent event)
  throws IOException
{
  Entity entity = event.getEntity();
  if (!(entity instanceof Player)) {
    return;
  }
  Player player = (Player)entity;
  if (event.getRegainReason() != EntityRegainHealthEvent.RegainReason.SATIATED) {
    return;
  }
  if (FastHealListener.check(player) == 0.0D) {
    return;
  }
  if (FastHealListener.check(player) < 2.0D) {
	  this.Host.addSuspicion(player, "FastHeal (" + FastHealListener.check(player) + ")");
      event.setCancelled(true);
  } 
}

@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
public void onEntityRegainHealth(EntityRegainHealthEvent event)
{
  Entity entity = event.getEntity();
  if (!(entity instanceof Player)) {
    return;
  }
  Player player = (Player)entity;
  FastHealListener data = FastHealListener.getData(player);
  data.time = System.currentTimeMillis();
}

	public void Reset(Player paramPlayer) {
		
		
	}
}
