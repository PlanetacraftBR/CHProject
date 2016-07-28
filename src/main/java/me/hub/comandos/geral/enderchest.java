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

public class enderchest implements CommandExecutor {

	public String[] atalhos = new String[] { "ec" };
    public String desc = "Ver o endercehst de um jogador";
    
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
					Format.Comando("VER", "/enderchest §ajogador", jogador);
			
					return true;
				}
		          Player player = Bukkit.getPlayer(args[0]);
		          if (player == null)
		          {
		        	  Format.Comando("EnderChest", "jogador " + args[0] + " não esta no servidor!", jogador);
		            return true;
		          }
		         
		          jogador.openInventory(player.getEnderChest());
			      UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o abriu o enderchest do §a§o" + args[0]);
		          
		   }
		
		return false;
	  }
	

	

}

