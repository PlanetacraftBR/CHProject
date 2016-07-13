package me.hub;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.Magic_Chest.MagicEvent;
import me.acf.Magic_Chest.MagicMananger;
import me.antiHack.AntiHack;
import me.hub.API.BlockRestore;
import me.hub.API.Explosion.Explosion;
import me.hub.Bungee.Bungee;
import me.hub.Scoreboard.ScoreboardAPI;
import me.security.Accout.Account;
import me.security.Accout.AccountAPI;
import me.security.Accout.buffer.AccountBuffer;

public class Test extends MiniPlugin {

	
	public static String texto = "§a§lPlanetaCraft§f§l_§e§lBR";
	
	public Test (JavaPlugin plugin) {
		super("Tests", plugin);
		Bungee bunge = new Bungee(plugin);
		AntiHack hacks = new AntiHack(plugin,bunge);
		BlockRestore block = new BlockRestore(plugin);
		Explosion explo = new Explosion(plugin,block);
		Account account = new Account(plugin);
		MagicMananger magic = new MagicMananger(plugin);
		System.out.print(texto);
	}
	
	 public static void CriarScoreboard(Player j)  {
		   ScoreboardAPI scoreboard = new ScoreboardAPI();
		   scoreboard.send(j);     
	    }
	 
	
	@EventHandler
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
        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(10);
	}

}
