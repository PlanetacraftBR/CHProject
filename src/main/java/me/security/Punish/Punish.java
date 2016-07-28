package me.security.Punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilPlayer;
import me.site.account.AccountWeb;

public class Punish {

	
	public static void DelPunish(String jogador, String staff)
	{
		
		Player st = Bukkit.getPlayerExact(staff);
		try {
	    JSONObject obj = new JSONObject(AccountWeb.Conectar(Main.site + "/API/conta.php?nick=" + jogador));
	        
	    System.out.print(AccountWeb.Conectar(Main.site + "/API/pontos_staff.php?modo=REMOVE&nick=" + obj.getString("ban_st") + "&quantidade=6"));
		    
		Player banned = Bukkit.getPlayerExact(jogador);
		String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=REMOVE&nick=" + jogador, "OK");
	    if (!staff.contains("Console")) {
		if (st.isOnline())
		    Format.Comando("Punir", buscar.replace("&", "§").replace("pular", "\n"), st);
	    }
	    else {
	    	System.out.print(buscar);
	    }
	    }
	    catch (Exception exception)
	    {
			String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=REMOVE&nick=" + jogador, "ERRO");
		    if (!staff.contains("Console")) {
				if (st.isOnline())
				    Format.Comando("Punir", buscar.replace("&", "§").replace("pular", "\n"), st);
			    }
			    else
			    	System.out.print(buscar);
	    }
	}
	
	public static void Add(String jogador, String Motivo, String staff)
	{
		
		Player st = Bukkit.getPlayerExact(staff);
		try {
	    Player banned = Bukkit.getPlayerExact(jogador);
		String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=ADD&nick=" + jogador + "&staff="+ staff + "&motivo=" + Motivo +"", "OK");
	  
		Bukkit.broadcastMessage(buscar.replace("&", "§").replace("pular", "\n"));
		UtilPlayer.Kick(banned, "Você vai ser banido cuidado!");
		
		
		if (staff.contains("Console"))
			return;
		
		
   	    Format.Comando("Punir","Você baniu o jogador §a" + jogador, st);
	    System.out.print(AccountWeb.Conectar(Main.site + "/API/pontos_staff.php?modo=ADD&nick=" + st.getName() + "&quantidade=1"));
	
	   
	    }
	    catch (Exception exception)
	    {
			String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=ADD&nick=" + jogador + "&staff="+ staff + "&motivo=" + Motivo +"", "ERRO");
		    if (!staff.contains("Console")) {
				if (st.isOnline())
				    Format.Comando("Punir", buscar.replace("&", "§").replace("pular", "\n"), st);
			    }
			    else
			    	System.out.print(buscar);
	    }
	}
}
