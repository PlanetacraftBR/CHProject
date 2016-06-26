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


public class ps implements CommandExecutor {

public String[] atalhos = new String[] { "pontostaff","staffp","staffpontos" };
    public String desc = "Ver os coins";
    
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
			   try {
			   int ponto = Integer.parseInt(Account.getPonto(jogador));
		   jogador.sendMessage("§e§lSeus Pontos: §a§l" + Account.getPonto(jogador));
		   jogador.sendMessage("§e§lPossibildade de continuar: §c§l" + ponto/2/2/2 + "%");
		   jogador.sendMessage("§f§lAviso se chegar a 0 você é retirado!");
			   }
		       catch (Exception exception)
   		    {

		    Format.Erro("Não foi possivel capturar seus pontos.", jogador);
		   }
		
		
	  }
		return false;
	

	

	  }
}

