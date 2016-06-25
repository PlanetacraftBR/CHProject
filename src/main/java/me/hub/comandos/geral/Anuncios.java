package me.hub.comandos.geral;

import java.util.ArrayList;

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

public class Anuncios implements CommandExecutor {

	public String[] atalhos = new String[] { "veranuncios" };
    public String desc = "Ver os anuncios deste servidor";
    
    
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
			   if (args.length  == 1)
				{
				   if (args[0].equals("reload"))
				   {
					   me.hub.API.Anuncio.Anuncios.LoaderMSG("Todos"); 
					   me.hub.API.Anuncio.Anuncios.LoaderMSG(Bukkit.getServerName()); 
					   Format.Comando("Anuncio", "Você deu reload nas msg", jogador);
					   return true;
				   }
				}
			   jogador.sendMessage("§f§lAnuncios deste servidor! Numero: §6§l" + me.hub.API.Anuncio.Anuncios.anuncios);
			   for (String s : me.hub.API.Anuncio.Anuncios.lista)
			   {
				   jogador.sendMessage(s.replace("&", "§").replace("{Player}", jogador.getName()).replace("{Linha}", "\n").replace("{ServerName}", Bukkit.getServerName()));
			   }
		   }
		   return false;
	  }
		   }