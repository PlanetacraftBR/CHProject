package me.hub.comandos.geral;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class Burn implements CommandExecutor {


	public String[] atalhos = new String[] { "fogo" };
    public String desc = "Colocar Fogo em jogadores";
    
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
			if (args.length  <= 1)
			{
				Format.Comando("FOGO", "/fogo <Jogador> <tempo>", jogador);
		
				return true;
			}
			if (args.length >= 2) {
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                {
					 Format.Comando("FOGO", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                return true;
                }
				 try {
				 Player alvo =  Bukkit.getPlayerExact(args[0]);
				 int tempo = Integer.parseInt(args[1]);
				 alvo.setFireTicks(tempo);
				 }
				   catch (Exception e)
                 {
					   Format.Comando("FOGO", "Tempo invalido §a" + args[1], jogador);
                 }
		  
			}
			}
		return false;
	  }


	

}

