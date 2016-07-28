package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class inventario implements CommandExecutor {

	public String[] atalhos = new String[] { "ver" };
    public String desc = "Ver o inventario de um jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, true))
		   {
				if (args.length  <= 0)
				{
					Format.Comando("VER", "/ver §ajogador", jogador);
			
					return true;
				}
		          Player player = Bukkit.getPlayer(args[0]);
		          if (player == null)
		          {
		        	  Format.Comando("VER", "jogador " + args[0] + " não esta no servidor!", jogador);
		            return true;
		          }
		         
		          jogador.openInventory(player.getInventory());
			      UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o abriu o inventario do §a§o" + args[0]);
		          
		   }
		
		return false;
	  }
	

	

}

