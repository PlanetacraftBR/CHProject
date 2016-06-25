package me.site.account;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import me.acf.lobby.patentes.Patente;
import me.acf.punish.PunishMananger;
import me.donate.DonateMananger;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.site.account.rank.Rank;



public class AccountOFF extends MiniPlugin
{

	public AccountOFF(JavaPlugin plugin) {
		super("Account", plugin);
	}


	  public static String loadAccount(String p)
	  {
		  String obj = AccountWeb.Conectar(Main.site + "/API/conta.php?nick=" + p);
		  
	     return obj;
	  }
	  
}




