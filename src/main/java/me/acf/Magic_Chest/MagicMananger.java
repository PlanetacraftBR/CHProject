package me.acf.Magic_Chest;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.Magic_Chest.modulo.MenuMagic;
import me.hub.MiniPlugin;

public class MagicMananger extends MiniPlugin {
	
	MenuMagic magic = null;
	public MagicMananger(JavaPlugin plugin)
	{
		super("Magic Chest",plugin);
		magic = new MenuMagic(null);
	}

	
	 @EventHandler
	   public void EnderChest(final PlayerInteractEvent event)
	   {
	     Player jogador = event.getPlayer();
	     if (jogador.getGameMode() == GameMode.CREATIVE)
	       return;
	     if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	       if (event.getClickedBlock().getType() == null) {
	         return;
	       }
	       if (event.getClickedBlock().getType() == Material.CHEST) {
	    	   event.setCancelled(true);
	       }
	       if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
	    	   jogador.openInventory(magic.inv);
	    	   // MagicEvent.Magic(jogador, event.getClickedBlock().getLocation());
	       }
	     }
	   }
	 
	 
	
}
