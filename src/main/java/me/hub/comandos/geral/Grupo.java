package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class Grupo implements CommandExecutor {

	public String[] atalhos = new String[] { "rank" };
    public String desc = "Setar rank do jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
	

		   if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, true))
		   {
			   if (args.length  == 2)
				{
				   try {
		      		String buscar = AccountWeb.Conectar(Main.site + "/API/grupo.php?modo=ADD&nick=" + args[0] +"&grupo=" + args[1], "OK");
            		jogador.sendMessage("§6§lRANK §7" + buscar.replace("&", "§").replace("pular", "\n"));
				   }
				      catch (Exception exception)
		    		    {

				      		String buscar = AccountWeb.Conectar(Main.site + "/API/grupo.php?modo=ADD&nick=" + args[0] +"&grupo=" + args[1], "ERRO");
		                	  jogador.sendMessage("§c§lERRO §7" + buscar.replace("&", "§").replace("pular", "\n"));
		    		    }
				   }
			   else
			   {
				  Format.Comando("RANK", "/rank §ajogador rank", jogador);
			   }
		}
		
		return false;
	  }
	

	

}

