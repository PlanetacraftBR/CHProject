package me.antiHack.Move;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.Main;
import me.hub.MiniPlugin;

public class NoFall
extends MiniPlugin
implements Detector
{
	  public NoFall(AntiHack host)
	  {
	    super("NoFall Detector", host.GetPlugin());
	    this.Host = host;
	  }
	 private AntiHack Host;
	 
  @EventHandler
  public void testFall(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {

        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
        	if (Main.plugin.getConfig().getString("Carregar").equals("OneInTheChamber")) {
        		
        	}else{
        	if ((me.acf.KitPvP.kitAPI.Kit.PoderUsar(p, "stomper")) || (me.acf.KitPvP.kitAPI.Kit.PoderUsar(p, "kangaroo"))) {
            if (e.getDamage() < 2.0D)
            {
            	this.Host.addSuspicion(p, "Suspeito de NoFall");
            }
           }
        	}
          }
        
      }
    }
  }


@Override
public void Reset(Player paramPlayer) {
	// TODO Auto-generated method stub
	
}
}
