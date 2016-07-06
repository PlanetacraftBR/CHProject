package me.antiHack.pvp;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.MiniPlugin;

public class AutoSoup
extends MiniPlugin
implements Detector
{
	public AutoSoup(AntiHack host)
	{
	  super("AutoSoup Detector", host.GetPlugin());
	  this.Host = host;
	}
	
  private Map<Player, Integer> a = new HashMap();
  private AntiHack Host;
  
  
  @EventHandler
  public void autosoup(InventoryClickEvent e)
  {
	    Player p = (Player)e.getWhoClicked();
	    if (p.getInventory().getName().contains("Sopas")){
	        
	    }else{
	    	
    if (e.isCancelled()) {
      return;
    }
    if (e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
      return;
    }
    if (e.getSlot() == -1) {
      return;
    }
    long calculatedTime = System.currentTimeMillis() - AntiHack.getLastAttackTime(p.getUniqueId());
    int bowls = 0;
    ItemStack[] arrayOfItemStack;
    int j = (arrayOfItemStack = p.getInventory().getContents()).length;
    for (int i = 0; i < j; i++)
    {
      ItemStack itemStack = arrayOfItemStack[i];
      if ((itemStack != null) && (itemStack.getType() == Material.BOWL)) {
        bowls += itemStack.getAmount();
      }
    }
    if (calculatedTime > 140L) {
      return;
    }
    Integer level = aumentarlevel(p);
	 this.Host.addSuspicion(p, "Esta usando AutoSoup nivel (" + getLevel(p).intValue() + ")");
    }
  }
  
  private Integer aumentarlevel(Player p)
  {
    if (this.a.containsKey(p)) {
      this.a.put(p, Integer.valueOf(((Integer)this.a.get(p)).intValue() + 1));
    } else {
      this.a.put(p, Integer.valueOf(1));
    }
    return (Integer)this.a.get(p);
  }
  
  private Integer getLevel(Player p)
  {
    return (Integer)this.a.get(p);
  }


  
	public void Reset(Player paramPlayer) {
		
		
	}
}
