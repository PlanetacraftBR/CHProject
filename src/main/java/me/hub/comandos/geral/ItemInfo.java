package me.hub.comandos.geral;

import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemInfo implements CommandExecutor {

	public String[] atalhos = new String[] { "id" };
    public String desc = "Ver id deste servidor";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
		    jogador.sendMessage("§a§lID do iten da sua mão: §f" + jogador.getItemInHand().getTypeId());
		   
			
		   
		
		return false;
	  }
	

	

}

