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

public class Ban implements CommandExecutor {

	public String[] atalhos = new String[] { "punish" };
    public String desc = "Banir um jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   if (args.length  <= 1)
				{
				   sender.sendMessage("Punir Use /ban jogador motivo");
				}
			   if (args.length  >= 2)
				{
		          PunishMananger.AddPunish(args[0], args[1], "Console");
				}
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
	
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, false))
		   {
		   if (args.length  <= 1)
			{
			   Format.Comando("Punir", "Use /ban §ajogador §amotivo", jogador);
			}
		
			   if (args.length  >= 2)
				{
		          PunishMananger.AddPunish(args[0], args[1], jogador.getName());
				}
		   }
			

		
		return false;
	  }
	

	

}

