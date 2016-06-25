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

public class Sudo implements CommandExecutor {


	public String[] atalhos = new String[] { "s" };
    public String desc = "Forçar Pessoas a fazer alguma coisa";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   Player jogador = (Player) sender;
	
		   if ((Account.getRank(jogador)).Has(jogador, Rank.DEV, true))
		   {
			if (args.length  <= 1)
			{
				Format.Comando("SUDO", "/sudo <Jogador> <O que>", jogador);
		
				return true;
			}
			if (args.length >= 2) {
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                {
					 Format.Comando("SUDO", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                return false;
                }
				 StringBuilder s = new StringBuilder();
		            for (int i = 1; i < args.length; i++) {
                        s.append(args[i]).append(" ");
                    }
		           String Motivo = s.toString();
				 Player alvo =  Bukkit.getPlayerExact(args[0]);
				 alvo.chat(Motivo);
				 if (Motivo.charAt(0) == '/') {
				 Format.Comando("SUDO", "Você forço o jogador " + args[0] + " ha executar§a " + Motivo, jogador);
				 }
				 else
				 {
					 Format.Comando("SUDO", "Você forço o jogador " + args[0] + " ha digitar§a " + Motivo, jogador);
				 }
		  
			}
			}
		return false;
	  }


	

}

