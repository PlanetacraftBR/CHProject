package me.antiHack.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import me.antiHack.AntiHack;
import me.antiHack.Detector;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;

public class ToggleSneak  
	  extends MiniPlugin
	  implements Detector
	  {
	  private AntiHack Host;
	  public static ArrayList<Player> inv = new ArrayList();
	  
	  public ToggleSneak(AntiHack host)
	  {
	    super("Sneak Detector", host.GetPlugin());
	    this.Host = host;
	  }
	  
	  @EventHandler
	  public void Click(InventoryClickEvent event)
	  {
		  Player player = (Player) event.getWhoClicked();
		  if (player.isSneaking()) {
		  this.Host.addSuspicion(player, "Suspeito de ToggleSneak");
		  UtilPlayer.Kick(player, "Você esta muito suspeito de ser algum Cheat");
		  event.setCancelled(true);
		 ((Player) player).setSneaking(false);
		  }
		  
	  }  

	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		((Player) event.getPlayer()).setSneaking(false);
		inv.add((Player) event.getPlayer());
		
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		inv.remove((Player) event.getPlayer());
		((Player) event.getPlayer()).setSneaking(false);
	}

	@Override
	public void Reset(Player paramPlayer) {
		// TODO Auto-generated method stub
		
	}
}
