package me.hub.API.Util;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import me.acf.lobby.perfil.Perfil;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class UtilServer
{

	  public static Server getServer()
	  {
	    return Bukkit.getServer();
	  }

	  public static Collection<? extends Player> Jogadores()
	  {
		  return Bukkit.getOnlinePlayers();
	  }
	  
	  public static void AnuncioDono(String msg)
	  {
		  for (Player p : Jogadores())
		  {
			  if ((Account.getRank(p)).Has(p, Rank.DONO, false))
			   {
				  p.sendMessage(msg);
			   }
		  }
	  }
	  
	  public static void AnuncioStaff(String msg)
	  {
		  for (Player p : Jogadores())
		  {
			  if ((Account.getRank(p)).Has(p, Rank.STAFF, false))
			   {
				  p.sendMessage(msg);
			   }
		  }
	  }
	  public static void AnuncioServidor(String msg)
	  {
		  for (Player p : Jogadores())
		  {
				if (!Perfil.Notification.contains(p))
					p.sendMessage(msg);
		  }
	  }

}