/**
 * 
 */
package me.acf.clan;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.site.account.AccountWeb;

/**
 * Criado pelo DEVS CH
 * @author AdriaŋCF
 *
 * 
 */
 
public class Clan {

	public static HashMap clan = new HashMap<Player, String>();
	

	
	public static boolean TemClan(Player p)
	{
		if (clan.get(p) == "NENHUM")
			return true;
		
		return false;
	}
	
	public static String NomeDoClan(Player p)
	{
		String nome = (String)clan.get(p);
		if (!nome.contains("NENHUM"))
		{
			nome = "["+nome+"] ";
		}
		return nome.replace("NENHUM", "");
		
	}
   
}
