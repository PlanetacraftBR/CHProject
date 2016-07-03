/**
 * 
 */
package me.acf.lobby.MagicChest;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.acf.lobby.patentes.Patente;
import me.hub.API.Util.UtilServer;
import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.config.Config;
import me.site.account.Account;

/**
 * @author adriancf
 *
 */
public class Premio {

	
	private static void Sound(Location loc, org.bukkit.Sound sound)
	{
		for (Player p : UtilServer.Jogadores())
		p.playSound(loc, sound, 10F, 1.2F);
	}
	
	public static void Verificar(String premio, Player p)
	{
		premio = premio.replace("§a§l", "");
		if (premio.contains("§f§lArmadura"))
		{
		
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_ENDERMEN_HURT);
	        Config.add(p, "Armor." + premio.replace("§f§lArmadura ", ""), "Sim");
		 return;
		}
		if (premio.contains("§aDisco"))
		{
		
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
	        Config.add(p, "Music." + premio, "Sim");
		 return;
		}
		if (premio.contains("§f§lMorph"))
		{
		
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_WITHER_SPAWN);
	        Config.add(p, "Morph." + premio.replace("§f§lMorph ", ""), "Sim");
		 return;
		}
		if (premio.contains("§f§lPets"))
		{
		
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_WITHER_SPAWN);
	        Config.add(p, "Pet." + premio.replace("§f§lPets ", ""), "Sim");
		 return;
		}
		if (premio.contains("§5Cash"))
		{
			int valor = Integer.parseInt(premio.replace("x §5Cash", ""));
			 Account.AddCash(p, valor);
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_WITHER_SPAWN);
			ScoreboardAPI.remover(p, "Cash");
		    Account.UpdateAccount(p);
		 return;
		}
		if (premio.contains("§e§lPatente §6§lUP"))
		{
			UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_WITHER_SPAWN);
			Patente.UpPatende(p);
		    Account.UpdateAccount(p);
			ScoreboardAPI.remover(p, "Patente:");
		 return;
		}
		if (premio.contains("§b§lMagic Chest!"))
		{
			  int numero = Integer.parseInt("" + premio.charAt(0));
			   Account.AddChave(p, numero); 
			   UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
		    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_ENDERDRAGON_DEATH);
			ScoreboardAPI.remover(p, "Chaves");
		    Account.UpdateAccount(p); 
		 return;
		}
		if (premio.contains("Planets"))
		{
			int valor = Integer.parseInt(premio.replace("x Planets", ""));
		 Account.AddCoins(p, valor);
			ScoreboardAPI.remover(p, "Planets");
			if (p.isOnline())
				Account.UpdateAccount(p);
		}
		if (premio.contains("Exp"))
		{
			int valor = Integer.parseInt(premio.replace("x Exp", ""));
		 Account.AddExp(p, "" + valor);
			ScoreboardAPI.remover(p, "Nivel");
			if (p.isOnline())
				Account.UpdateAccount(p);
		}
		if (premio.contains("§6§l"))
		{
		
			
		   String numero = "" + premio.charAt(0) + premio.charAt(1) + premio.charAt(2);
		   numero = numero.replace(" ", "").replace("x", "");
		   String nome = premio.replace("x §6§l", "").replace(numero, "");
		   String total = Config.GetGadgets(p, "§a"+nome);
	       int soma = Integer.parseInt(total) + Integer.parseInt(numero);
	       Config.SetGadgets(p, "§a"+nome, "" + soma);
	       if (premio.contains("PlanetsBomb"))
			{
	    	   UtilServer.AnuncioServidor("§5§lMagic §6§l" + p.getCustomName() + " §7ganhou§a§l " + premio);
			    Sound(p.getLocation(), org.bukkit.Sound.ENTITY_BLAZE_DEATH);
			}
		}
		p.sendMessage("§f§lMagic §7Você ganhou§a§l " + premio);
	}
}
