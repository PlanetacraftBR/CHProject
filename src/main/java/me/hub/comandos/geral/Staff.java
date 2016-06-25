package me.hub.comandos.geral;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class Staff implements CommandExecutor {

	public String[] atalhos = new String[] { "as", "cs", "chatStaff", "cStaff" };
    public String desc = "Anunciar no servidor em modo staff";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		    if (Account.getRank(jogador).Has(jogador, Rank.STAFF, true)) {
		   if (args.length < 1)
		   {
				 Format.Comando("MSG", "Use /staff §atexto", jogador);
			   return true;
		   }

			
			 StringBuilder s = new StringBuilder();
	            for (int i = 0; i < args.length; i++) {
                 s.append(args[i]).append(" ");
             }
	        
	          
	            me.hub.Admin.Staff.MandarMSGBungee("§c§lChat-STAFF " + jogador.getDisplayName() + ": §e" + s.toString().replace("&", "§"));
	           
	            
		    }

		   
		
		return false;
	  }
	

	

}

