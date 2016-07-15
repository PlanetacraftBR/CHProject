package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.acf.servidor.Servidor;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhiteList implements CommandExecutor {

	public String[] atalhos = new String[] { "manutencao","wl","whitel","manu" };
    public String desc = "Ativar e desativar chat";
    
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

			if (args.length  <= 0)
			{
				Format.Comando("WL", "/whitelist §aON§7|§aOFF", jogador);
				return true;
			}
			if (args.length == 1) {
				if ((args[0].contains("on")) || (args[0].contains("ON")) || (args[0].contains("On")) || (args[0].contains("ativar")))
				{
					if (Servidor.GetMain())
					{
						Format.Erro("O servidor já esta em manutenção", jogador);
						return true;
					}
					Servidor.ModoGame("MANUTENCAO");
				    UtilServer.AnuncioStaff("§7Modo manutenção foi ativada!");
				}
				else
				if ((args[0].contains("off")) || (args[0].contains("OFF")) || (args[0].contains("Off")) || (args[0].contains("desativar")))
				{
					
					if (!Servidor.GetMain())
					{
						Format.Erro("O servidor não esta em manutenção", jogador);
						return true;
					}
                 Servidor.ModoGame("ON");
                 UtilServer.AnuncioStaff("§7Modo manutenção foi desativada");
				}
				else
				{
					Format.Comando("WL", "/whitelist §aON§7|§aOFF", jogador);
				}
			}
		   
			
		   }
		
		return false;
	  }
	

	

}

