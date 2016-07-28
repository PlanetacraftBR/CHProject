package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.acf.punish.PunishMananger;
import me.hub.Main;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor {

	public String[] atalhos = new String[] { "pardon", "desban" };
    public String desc = "Banir um jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   if (args.length  <= 0)
				{
				   sender.sendMessage("Punir Use /unban jogador");
				}
			   if (args.length  >= 1)
				{
		          PunishMananger.DelPunish(args[0], "Console");
				}
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
	

		   if (args.length  <= 0)
			{
			   Format.Comando("Punir", "Use /unban §ajogador", jogador);
			}
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFFM, true))
		   {
			   if (args.length  >= 1)
				{
		          PunishMananger.DelPunish(args[0], jogador.getName());
				}
		   }
			

		
		return false;
	  }
	

	

}

