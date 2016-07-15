package me.hub.Admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;
import me.hub.API.Util.UtilMath;
import me.hub.API.Util.UtilServer;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class Admin extends MiniPlugin {

	public Admin(JavaPlugin pl)
	{
		super("Admin", pl);
		Staff.Load();
	}

 public static void SumirStaff() {
	      for (Player p : me.hub.comandos.geral.Admin.admin)
	      {
	    	  Sumir(p);
	      }
 }
	
 public static void Sumir(Player p)
 {
	 for (Player all : UtilServer.Jogadores())
	 {
		 if (!(Account.getRank(all.getPlayer())).Has(all, Rank.STAFF, false))
		   {
		 all.hidePlayer(p);
		   }
	 }
 }
 
 public static void Revelar(Player p)
 {
	 for (Player all : UtilServer.Jogadores())
	 {
		 all.showPlayer(p);
	 }
 }

     @EventHandler
     public void StaffPRO(PlayerLoginEvent event)
     {
    	 SumirStaff();
     }

 	
 	
	@EventHandler
	public void Comandos (PlayerCommandPreprocessEvent event)
	{
		 if (!event.isCancelled())
		    {
		   
		      String cmd = event.getMessage().split(" ")[0];
		      HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
		      if (topic == null)
		    	  event.setCancelled(true);
		    }
		      
		 if (!(Account.getRank(event.getPlayer())).Has(event.getPlayer(), Rank.DEV, false))
		   {
		
			 UtilServer.AnuncioDono("§c" + event.getPlayer().getName() + " digitou§7: " + event.getMessage());
			 
		   }
	}
	
	  @EventHandler
	  public void PlacaColor(SignChangeEvent e)
	  {
			 if ((Account.getRank(e.getPlayer())).Has(e.getPlayer(), Rank.VIP, false))
			   {
	      if (e.getLine(0).contains("&")) {
	        e.setLine(0, e.getLine(0).replace("&", "§"));
	      } 
	      if (e.getLine(1).contains("&")) {
	        e.setLine(1, e.getLine(1).replace("&", "§"));
	      }
	      if (e.getLine(2).contains("&")) {
	        e.setLine(2, e.getLine(2).replace("&", "§"));
	      }
	      if (e.getLine(3).contains("&")) {
	        e.setLine(3, e.getLine(3).replace("&", "§"));
	      }
	    }
	  }
}
