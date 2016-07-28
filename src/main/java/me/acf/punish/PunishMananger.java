package me.acf.punish;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilTime;
import me.hub.Bungee.Bungee;
import me.site.account.AccountWeb;

public class PunishMananger extends MiniPlugin{

	  public static HashMap<Player, String> Ban_Motivo = new HashMap();
	  public static HashMap<Player, String> Ban_Staff = new HashMap();
	  public static HashMap<Player, String> Dia_Ban = new HashMap();
	  public static HashMap<Player, String> Exp_Ban = new HashMap();
	  
	 public PunishMananger(JavaPlugin plugin) {
		super("Punish", plugin);
		
	}

	@EventHandler
	public void Entrar(PlayerJoinEvent e)
	{

	    Calendar now = Calendar.getInstance();
	   String tempo = "";

	    try {
			if (getMotivo(e.getPlayer()).equals("")){
		    	String buscarip = AccountWeb.Conectar(Main.site + "/API/ban_ip.php?nick=" + e.getPlayer().getName(), "OK");
			    if (buscarip != null){
			    	e.getPlayer().kickPlayer("§6" + Main.NomeDoServidor + " - Kickado \n§f"+buscarip.toString());
			    }
				return;
			}
			
	    	if (getExpData(e.getPlayer()).contains("Vitalico"))
	    	{
	    		tempo = "§aVitalico";
	    	}
	    	else
	    	{
	    	Calendar calendar = new GregorianCalendar();
	    	SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	    	Date date = new Date();
	    	calendar.setTime(date);
	        
	        String s1 = out.format(calendar.getTime());
	        String s2 = getExpData(e.getPlayer());
	        
	        Date d1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s1);
	        Date d2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s2);

	        long sec = d2.getTime() - d1.getTime();
	        if (sec < 0)
	        {
	        	String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=REMOVE&nick=" + e.getPlayer().getName(), "OK");
        	    System.out.print(buscar);
	        	return;
	        }
	        tempo = UtilTime.convertString(sec, 0, UtilTime.TimeUnit.FIT);
	    	}
	    
	    	e.getPlayer().kickPlayer("§6" + Main.NomeDoServidor + " - Kickado \n§fVocê foi banido do servidor\n§f§o" + getMotivo(e.getPlayer()) + "\n\n§fInformações (" + e.getPlayer().getName() + ")\n§fTempo: §a" + tempo + "\n§fStaff que baniu:§6 " + getStaff(e.getPlayer()) + "\n§fDia do ban:§7 " + getDiaData(e.getPlayer()));
	    	
	    }    
	    catch (Exception exception)
	    {
	    	e.getPlayer().kickPlayer("§7Erro ao recuperar informações da web, por favor, tente novamente em um minuto.");
		    }

	
	}
	
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
	
	public static void AddPunish(String jogador, String Motivo, String staff)
	{
		
		Player st = Bukkit.getPlayerExact(staff);
		try {
		Player banned = Bukkit.getPlayerExact(jogador);
		String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=ADD&nick=" + jogador + "&staff="+ staff + "&motivo=" + Motivo +"", "OK");
	    if (!staff.contains("Console")) {
	    	Bukkit.broadcastMessage(buscar.replace("&", "§").replace("pular", "\n"));
		if (st.isOnline()) {
			  Format.Comando("Punir","Você baniu o jogador §a" + jogador, st);
	          System.out.print(AccountWeb.Conectar(Main.site + "/API/pontos_staff.php?modo=ADD&nick=" + st.getName() + "&quantidade=1"));
		}
	    }
	    else {
	    	System.out.print(buscar);
	    	Bukkit.broadcastMessage(buscar.replace("&", "§").replace("pular", "\n"));
	    }
	    if (banned != null) { 
	    if (banned.isOnline()) 
	    	Bungee.KickPlayer(banned.getName(), Main.NomeDoServidor + " - Kickado \n§fVocê foi banido do servidor");
	    	banned.kickPlayer("§6" + Main.NomeDoServidor + " - Kickado \n§fVocê foi banido do servidor");
	    }
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
	
	
	public static String getStaff(Player p)
	{
		  return (String)Ban_Staff.get(p);
	}
	
public static String getMotivo(Player p)
{
	  return (String)Ban_Motivo.get(p);
}
public static String getExpData(Player p)
{
	  return (String)Exp_Ban.get(p);
}
public static String getDiaData(Player p)
{
	  return (String)Dia_Ban.get(p);
}
}
