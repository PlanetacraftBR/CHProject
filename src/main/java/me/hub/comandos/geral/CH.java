package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CH implements CommandExecutor {

public String[] atalhos = new String[] { "plugins","pl","criador","creator","author","webapi","CHDevs" };
    public String desc = "Ver o criado";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
				sender.sendMessage("§f§lPlugins criado pelo grupo §a§lCHDevs");
				sender.sendMessage("§f§l@Author§7§l adriancf , §f§l@WebAPI§7§l Jotinha_BR , §f§l@MiniGames§7§l Jotinha_BR");	
		return false;
	  }
	

	

}

