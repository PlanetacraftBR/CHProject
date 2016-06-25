package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Say implements CommandExecutor {

	public String[] atalhos = new String[] { "bc", "broadcast", "s", "anuncio" };
    public String desc = "Anunciar no servidor";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   if (args.length < 1)
			   {
					 Bukkit.getServer().getConsoleSender().sendMessage("§c§lMSG §7Use /say §atexto");
				   return true;
			   }
		
				 StringBuilder s = new StringBuilder();
		            for (int i = 0; i < args.length; i++) {
	                 s.append(args[i]).append(" ");
	             }
		            Bukkit.broadcastMessage("§9§lANUNCIO§e " + s.toString().replace("&", "§"));
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		    if (Account.getRank(jogador).Has(jogador, Rank.STAFFM, true)) {
		   if (args.length < 1)
		   {
				 Format.Comando("MSG", "Use /say §atexto", jogador);
			   return true;
		   }
	
			 StringBuilder s = new StringBuilder();
	            for (int i = 0; i < args.length; i++) {
                 s.append(args[i]).append(" ");
             }
	            Bukkit.broadcastMessage("§9§lANUNCIO§e " + s.toString().replace("&", "§"));
		    }
	  


		   
		
		return false;
	  }
	

	

}

