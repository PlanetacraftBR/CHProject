package me.security.registrar.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hub.API.Util.message.Message;
import me.hub.Bungee.Bungee;
import me.hub.comandos.ComandosAPI;
import me.security.LoginManager;

public class limbo implements CommandExecutor {

	public String[] atalhos = new String[] { "limbos", "lmb", "bug", "limpar" };
    public String desc = "Limbo";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if (!LoginManager.Original.contains(jogador.getUniqueId().toString()))
		   {
			   return true;
		   }
		   if (!Bukkit.getServerName().equals("Logando"))
			   return true;
		  Bungee.SendPlayerToServer(jogador, "lobby");
		   
		
		return false;
	  }
	

	

}

