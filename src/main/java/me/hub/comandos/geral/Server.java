package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.message.Message;
import me.hub.Bungee.Bungee;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Server implements CommandExecutor {

public String[] atalhos = new String[] { "send", "sv", "servers", "snd" , "servidor","server" };
    public String desc = "Teleporte de servidor";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		    if (Account.getRank(jogador).Has(jogador, Rank.STAFF, true)) {
		   if (args.length < 1)
		   {
				 Format.Comando("SERVER", "Use /sv §aNome do servidor", jogador);
			   return true;
		   }
		   if (args.length >= 1)
		   Bungee.SendPlayerToServer(jogador, args[0]);
		   
		    }
		   
		
		return false;
	  }
	

	

}

