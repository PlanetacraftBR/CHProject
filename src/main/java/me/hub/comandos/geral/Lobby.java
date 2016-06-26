/**
 * 
 */
package me.hub.comandos.geral;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hub.API.Util.message.Message;
import me.hub.Bungee.Bungee;
import me.hub.comandos.ComandosAPI;

/**
 * @author adriancf
 *
 */
public class Lobby implements CommandExecutor{
	public String[] atalhos = new String[] { "planetacraft_br","pc-br","planetacraft","hub","main","inicio" };
    public String desc = "Ir para o lobby";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   Bungee.SendPlayerToServer(jogador, "lobby");
		
		return false;
	  }
}
