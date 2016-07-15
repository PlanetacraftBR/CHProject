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

public class Matar implements CommandExecutor {

	public String[] atalhos = new String[] { "kill","suicide" };
    public String desc = "Ver o inventario de um jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   Player jogador = (Player) sender;
			if (args.length  <= 0)
			{
			    if(jogador.getGameMode() == org.bukkit.GameMode.ADVENTURE){
			    	Format.Comando("§a§lKILL", "§f§lVocê não pode se matar neste momente!", jogador);
			    	return true;
			    }
			    jogador.damage(99999999D);
			    Format.Comando("§a§lKILL", "§f§lVocê acaba de se matar!", jogador);
				return true;
			}
		   if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, true))
		   {
		          Player player = Bukkit.getPlayer(args[0]);
		          if (player == null)
		          {
		        	  Format.Comando("KILL", "jogador " + args[0] + " não esta no servidor!", jogador);
		            return true;
		          }
		         
		          player.damage(99999999D);
			      UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o matou o §a§o" + args[0]);
		          
		   }
		
		return false;
	  }
	

	

}

