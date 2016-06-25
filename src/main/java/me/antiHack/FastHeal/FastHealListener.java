package me.antiHack.FastHeal;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class FastHealListener
{
  public static HashMap<Player, FastHealListener> log = new HashMap();
  public long time;
  public Player player;
  
  public static FastHealListener getData(Player p)
  {
    if (log.get(p) != null)
    {
      FastHealListener fh = (FastHealListener)log.get(p);
      return fh;
    }
    FastHealListener fh = new FastHealListener(p);
    return fh;
  }
  
  public FastHealListener(Player p)
  {
    this.player = p;
    this.time = System.currentTimeMillis();
    log.put(p, this);
  }
  
  public static double check(Player p)
  {
    FastHealListener fh = getData(p);
    long time = System.currentTimeMillis();
    return (time - fh.time) / 1000.0D;
  }
}
