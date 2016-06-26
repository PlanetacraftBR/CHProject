package me.antiHack.Move;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilBlock;
import me.hub.API.Util.UtilMath;

public class Fly
  extends MiniPlugin
  implements Detector
{
  private AntiHack Host;
  private HashMap<Player, Map.Entry<Integer, Double>> _floatTicks = new HashMap();
  private HashMap<Player, Map.Entry<Integer, Double>> _hoverTicks = new HashMap();
  private HashMap<Player, Map.Entry<Integer, Double>> _riseTicks = new HashMap();
  private HashMap<Player, Location> _teleport = new HashMap();
  
  public Fly(AntiHack host)
  {
    super("Fly Detector", host.GetPlugin());
    this.Host = host;
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void updateFlyhack(PlayerMoveEvent event)
  {
    Player player = event.getPlayer();
    
    if (this.Host.isValid(player, true)) {
      return;
    }
    if (UtilMath.offset(event.getFrom(), event.getTo()) <= 0.0D)
    {
      updateFloat(player);
      return;
    }
    updateHover(player);
    updateRise(player);
    _teleport.put(player, player.getLocation());
  }
  
  private void updateFloat(Player player)
  {
    int count = 0;
    if (this._floatTicks.containsKey(player)) {
      if (player.getLocation().getY() == ((Double)((Map.Entry)this._floatTicks.get(player)).getValue()).doubleValue()) {
        count = ((Integer)((Map.Entry)this._floatTicks.get(player)).getKey()).intValue() + 1;
      } else {
        count = 0;
      }
    }
    if (count > this.Host.FloatHackTicks)
    {
      this.Host.addSuspicion(player, "Fly (Flutuando)");
      count = 0;
      player.teleport(_teleport.get(player));
    
    }
    this._floatTicks.put(player, new AbstractMap.SimpleEntry(Integer.valueOf(count), Double.valueOf(player.getLocation().getY())));
  }
  
  private void updateHover(Player player)
  {
    int count = 0;

    if (this._hoverTicks.containsKey(player)) {
      if (player.getLocation().getY() == ((Double)((Map.Entry)this._hoverTicks.get(player)).getValue()).doubleValue()) {
        count = ((Integer)((Map.Entry)this._hoverTicks.get(player)).getKey()).intValue() + 1;
      } else {
        count = 0;
      }
    }
    if (count > this.Host.HoverHackTicks)
    {
      this.Host.addSuspicion(player, "Fly (Voando)");
      player.teleport(_teleport.get(player));
      count = 0;
    }
    this._hoverTicks.put(player, new AbstractMap.SimpleEntry(Integer.valueOf(count), Double.valueOf(player.getLocation().getY())));
  }
  
  private void updateRise(Player player)
  {
    int count = 0;

    if (this._riseTicks.containsKey(player)) {
      if (player.getLocation().getY() > ((Double)((Map.Entry)this._riseTicks.get(player)).getValue()).doubleValue())
      {
        boolean nearBlocks = false;
        for (Block block : UtilBlock.getSurrounding(player.getLocation().getBlock(), true)) {
          if (block.getType() != Material.AIR)
          {
            nearBlocks = true;
            break;
          }
        }
        if (nearBlocks) {
          count = 0;
        } else {
          count = ((Integer)((Map.Entry)this._riseTicks.get(player)).getKey()).intValue() + 1;
        }
      }
      else
      {
        count = 0;
      }
    }
    if (count > this.Host.RiseHackTicks)
    {
      this.Host.addSuspicion(player, "Fly (Levantando)");
      player.teleport(_teleport.get(player));
      count = 0;
    }
    this._riseTicks.put(player, new AbstractMap.SimpleEntry(Integer.valueOf(count), Double.valueOf(player.getLocation().getY())));
  }
  
  public void Reset(Player player)
  {
    this._floatTicks.remove(player);
    this._hoverTicks.remove(player);
    this._riseTicks.remove(player);
  }
}
