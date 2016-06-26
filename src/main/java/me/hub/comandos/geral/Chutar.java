package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.acf.FormatText.Format;
import me.hub.API.ModoDeJogo;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

public class Chutar implements CommandExecutor {


	public String[] atalhos = new String[] { "kick" };
    public String desc = "Kickar Pessoas";
    public String Motivo = "Sem Motivo";
    
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
			if (args.length  <= 0)
			{
				Format.Comando("KICK", "/kick <Jogador> <Motivo>", jogador);
		
				return true;
			}
			if (args.length == 1) {
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                 {
					 Format.Comando("KICK", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                 return true;
                 }
				 Player alvo =  Bukkit.getPlayerExact(args[0]);
				 UtilPlayer.Kick(alvo, Motivo, jogador);
				 Format.Comando("KICK", "Você kickou o jogador §a" + UtilPlayer.Nome(alvo) + "§7 por§6 " + Motivo, jogador);
		         Motivo = "Sem motivo";
			}
			if (args.length >= 2) {
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                {
					 Format.Comando("KICK", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                return true;
                }
				 StringBuilder s = new StringBuilder();
		            for (int i = 1; i < args.length; i++) {
                        s.append(args[i]).append(" ");
                    }
		            Motivo = s.toString();
				 Player alvo =  Bukkit.getPlayerExact(args[0]);
				 UtilPlayer.Kick(alvo, Motivo, jogador);
				 Format.Comando("KICK", "Você kickou o jogador §a" + UtilPlayer.Nome(alvo) + "§7 por§6 " + Motivo, jogador);
				    Motivo = "Sem motivo";
			}
			}
		return false;
	  }


	

}

