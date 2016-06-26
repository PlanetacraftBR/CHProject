package me.hub.comandos.geral;

import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Patente implements CommandExecutor {

	public String[] atalhos = new String[] { "pat" };
    public String desc = "Ver sua patente";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		    
		   jogador.sendMessage("§aPatente:§f " + Account.getPatente(jogador).GetName(false));
		   jogador.sendMessage("");
		   jogador.sendMessage("§aProxima Patente:§f§o " +  me.acf.lobby.patentes.Patente.ProxPatende(jogador));
	

			
		   
		
		return false;
	  }
	

	

}

