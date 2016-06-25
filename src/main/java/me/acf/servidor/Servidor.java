package me.acf.servidor;

import org.bukkit.Bukkit;

import me.hub.Main;
import me.site.account.AccountWeb;

public class Servidor {
	
	public Servidor()
	{
		System.out.print(AccountWeb.Conectar(Main.site + "/API/sala.php?modo=ADD&nome=" + Bukkit.getServerName()+"&status=ON&quantidade=" + Bukkit.getMaxPlayers() + "&onlines=" + Bukkit.getOnlinePlayers().size()));
	}
	
	public static void OFFServidor()
	{
		System.out.print(AccountWeb.Conectar(Main.site + "/API/sala.php?modo=ADD&nome=" + Bukkit.getServerName()+"&status=OFF&quantidade=" + Bukkit.getMaxPlayers() + "&onlines=0"));
	}

	public static void AddJoin()
	{
		System.out.print(AccountWeb.Conectar(Main.site + "/API/sala.php?modo=JOIN&nome=" + Bukkit.getServerName() + "&onlines=" + Bukkit.getOnlinePlayers().size()));
	}
	
	public static void AddLeave()
	{
		System.out.print(AccountWeb.Conectar(Main.site + "/API/sala.php?modo=LEAVE&nome=" + Bukkit.getServerName() + "&onlines=" + Bukkit.getOnlinePlayers().size()));
	}
	
	public static void ModoGame(String modo)
	{
		System.out.print(AccountWeb.Conectar(Main.site + "/API/sala.php?modo=STATUS&nome=" + Bukkit.getServerName()+"&status=" + modo + "&onlines=" + Bukkit.getOnlinePlayers().size()));
    }
	public static boolean GetMain()
	{

		try {
		String mai = AccountWeb.Conectar(Main.site + "/API/sala.php?modo=CONSUTAR&nome=" + Bukkit.getServerName(), "status");
        if (mai.equals("MANUTENCAO"))
        return true;
		}
		  catch (Exception exception)
	    {
			  System.out.print("Não foi possivel verificar o modo manutenção do servidor!");
	    }
		
		return false;
	}


}
