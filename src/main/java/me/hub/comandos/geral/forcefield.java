package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.antiHack.autoclick.Click;
import me.hub.Main;
import me.hub.API.Util.UtilAuraCheck;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.UtilTime;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import java.util.AbstractMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class forcefield implements CommandExecutor {

	public String[] atalhos = new String[] { "f" };
    public String desc = "Testar jogador é hacker KillAura";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		  
		  
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {

				if (args.length  <= 0)
				{
					Bukkit.getServer().getConsoleSender().sendMessage("§c§lKILLAURA /f §ajogador");
					return true;
				}
				if (args.length == 1) {
					 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
	                 {
						 Bukkit.getServer().getConsoleSender().sendMessage("§c§lKILLAURA Jogador §a" + args[0] + "§7 não esta online.");
	                 return true;
	                 }
					final Player alvo =  Bukkit.getPlayerExact(args[0]);
					
					 sender.sendMessage("Temporariamente desativado para manutenções!");
					 UtilAuraCheck check = new UtilAuraCheck(Main.plugin, alvo);
					 
				}
			    
		   }
		   else{
				  final Player jogador = (Player) sender;
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, true))
		   {

			if (args.length  <= 0)
			{
				Format.Comando("KillAura", "/f §ajogador", jogador);
				return true;
			}
			if (args.length == 1) {
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                 {
					 Format.Comando("KillAura", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                 return true;
                 }
				final Player alvo =  Bukkit.getPlayerExact(args[0]);
				
				sender.sendMessage("Temporariamente desativado para manutenções!");
				 UtilAuraCheck check = new UtilAuraCheck(Main.plugin, alvo);
				    
			}
		   }
			
		   }
		
		return false;
	  }
	

	

}

