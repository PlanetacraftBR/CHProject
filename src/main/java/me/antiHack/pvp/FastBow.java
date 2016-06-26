package me.antiHack.pvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.comphenix.protocol.ProtocolManager;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.Main;
import me.hub.MiniPlugin;

public class FastBow
extends MiniPlugin
implements Detector
{
	public FastBow(AntiHack host)
	{
	  super("FastBow Detector", host.GetPlugin());
	  this.Host = host;
	}
	
  private AntiHack Host;
  ArrayList<String> sur = new ArrayList();
  
  @EventHandler
  public void onlaunch(ProjectileLaunchEvent e)
  {
    Projectile prj = e.getEntity();
    if ((prj.getShooter() instanceof Player))
    {
      Player p = (Player)prj.getShooter();
      if (hasBow(p))
      {
        Vector v = prj.getVelocity();
        Double x = Double.valueOf(v.getX());
        Double y = Double.valueOf(v.getY() * 1.15D);
        Double z = Double.valueOf(v.getZ());
        if (x.toString().contains("-")) {
          x = Double.valueOf(x.doubleValue() * -1.0D);
        }
        if (y.toString().contains("-")) {
          y = Double.valueOf(y.doubleValue() * -1.0D);
        }
        if (z.toString().contains("-")) {
          z = Double.valueOf(z.doubleValue() * -1.0D);
        }
        Double tout = Double.valueOf(x.doubleValue() + y.doubleValue() + z.doubleValue());
        if (tout.doubleValue() >= 2.8D)
        {
       	 this.Host.addSuspicion(p, "Esta disparando Projectiles(FastBow) muito rapido");
        }
      }
    }
  }
  
  ProtocolManager pr = Main.getProtocolManager();
  public HashMap<String, Long> bow = new HashMap();
  
  public void addBow(Player p)
  {
    this.bow.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
  }
  
  public boolean hasBow(Player p)
  {
    if ((this.bow.containsKey(p.getName())) && 
      (getbow(p) <= 0.0D))
    {
      removebow(p);
      return false;
    }
    return this.bow.containsKey(p.getName());
  }
  
  public void removebow(Player p)
  {
    this.bow.remove(p.getName());
  }
  
  public double getbow(Player p)
  {
    double leftcooldown = ((Long)this.bow.get(p.getName())).longValue() + 100L - System.currentTimeMillis();
    return leftcooldown;
  }
  
  @EventHandler
  public void Interraction(PlayerInteractEvent e)
  {
    if ((e.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_AIR")) || (e.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_BLOCK")))
    {
      Player p = e.getPlayer();
      if (p.getItemInHand().getType() != Material.BOW) {
        return;
      }
      if (hasBow(p))
      {
        e.setCancelled(true);
        return;
      }
      addBow(p);
    }
  }

/* (non-Javadoc)
 * @see me.antiHack.Detector#Reset(org.bukkit.entity.Player)
 */
@Override
public void Reset(Player paramPlayer) {
	// TODO Auto-generated method stub
	
}
}
