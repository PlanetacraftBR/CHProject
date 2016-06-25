package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.acf.FormatText.Format;
import me.hub.API.ModoDeJogo;
import me.hub.API.Util.UtilHolo;
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

public class Holograma implements CommandExecutor {


	public String[] atalhos = new String[] { "holo" };
    public String desc = "Criar holograma";
    
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
			if (args.length  <= 0)
			{
				Format.Comando("KICK", "/holo <texto>", jogador);
		
				return true;
			}
			if (args.length  == 2)
			{
			if (args[0].contains("remove"))
			{
				 String Motivo = "";

				 StringBuilder s = new StringBuilder();
		            for (int i = 1; i < args.length; i++) {
                        s.append(args[i]).append(" ");
                    }
		            Motivo = s.toString();
		            jogador.sendMessage("§fRemovido todos os hologramas chamados§7 " + Motivo);
			    UtilHolo.RemoveHoloPerm(Motivo);
				return true;
			}
			}
	       String Motivo = "";

				 StringBuilder s = new StringBuilder();
		            for (int i = 0; i < args.length; i++) {
                        s.append(args[i]).append(" ");
                    }
		            Motivo = s.toString();
		            jogador.sendMessage("§fAdicionado holograma com o nome§7 " + Motivo);
				    UtilHolo.HoloPerm(jogador.getLocation(), Motivo);
		  
			
			}
		return false;
	  }


	

}

