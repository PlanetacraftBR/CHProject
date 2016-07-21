package me.hub;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.Magic_Chest.MagicMananger;
import me.hub.API.Util.UtilTablist;
import me.hub.API.Util.UtilTitle;
import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.recharge.Recharge;
import me.security.Accout.AccountAPI;
import me.security.Accout.buffer.AccountBuffer;

public class Test extends MiniPlugin {

	
	public static String texto = "§a§lPlanetaCraft§f§l_§e§lBR";
	
	public Test (JavaPlugin plugin) {
		super("Tests", plugin);
		MagicMananger magic = new MagicMananger(plugin);
		System.out.print(texto);
		Recharge.Initialize(plugin);
	}
	
	 public static void CriarScoreboard(Player j)  {
		   ScoreboardAPI scoreboard = new ScoreboardAPI();
		   scoreboard.send(j);     
	    }
	 
	 
	 
	 @EventHandler
	 public void Test(PlayerInteractEvent event)
	 {
		if (event.getClickedBlock().getType() == Material.CHEST)
		{
			event.setCancelled(true);
			 if (!Recharge.Instance.use(event.getPlayer(),"chest", 1900L, true, false,false,true)) {
			        return;
			} 
			 event.getPlayer().sendMessage("ok");
			
		}
	 }
	 
	
	 @EventHandler
	 public void test2(PlayerJoinEvent event)
	 {
		 UtilTitle.sendTitle(event.getPlayer(),20,20,20,"Seja Bem-Vindo ao servidor de espera!","Você deve se registrar");
	     UtilTablist.setFooter(event.getPlayer(), "Testando......","Testando.....");
	     UtilTablist.setHeader(event.getPlayer(), "Testando......","Testando.....");
	     UtilTablist.AddItem(event.getPlayer(), "Jotinha_BR");
	     UtilTablist.RemoveItem(event.getPlayer(), event.getPlayer().getName());
   	 }
	 

	public void test(PlayerJoinEvent event)
	{
		AccountAPI conta = AccountBuffer.Return(event.getPlayer());
		CriarScoreboard(event.getPlayer());
	    ScoreboardAPI.add("§c"); //15
        ScoreboardAPI.add("Grupo: " + conta.rank.GetTag(true),500); //15
        ScoreboardAPI.add("Cash: §a" + conta.Cash()); //14
        ScoreboardAPI.add("Planets: §a" + conta.Planets()); //13
        ScoreboardAPI.add("§5"); //12
        ScoreboardAPI.add("§7"); //11
        ScoreboardAPI.add("Nivel:§a " + conta.nivel); //10
        ScoreboardAPI.add("Patente: §e" + conta.patente.GetTag(true)); //9
        ScoreboardAPI.add("§f"); //8    
        ScoreboardAPI.add("Chaves: §c" + conta.Chave()); //7
        ScoreboardAPI.add("Servidor Lobby: §aCentral"); //6
        ScoreboardAPI.add("§2"); //5
        
        ScoreboardAPI.build(event.getPlayer(), texto);
	}

}
