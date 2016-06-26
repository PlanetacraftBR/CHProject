/**
 * 
 */
package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.Bungee.Bungee;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

/**
 * @author adriancf
 *
 */
public class Ajudar implements CommandExecutor{

	public String[] atalhos = new String[] { "help","?","ajuda" };
    public String desc = "Pedir Ajuda para a staff";
    public static ArrayList admin = new ArrayList<String>();
    
    public HashMap<Player, ItemStack[]> invsave = new HashMap<Player, ItemStack[]>();
    
    public HashMap<Player, ItemStack[]> armorsave = new HashMap<Player, ItemStack[]>();
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   
		   Player jogador = (Player) sender;
		    if (!Account.getRank(jogador).Has(jogador, Rank.STAFF, false)) {
		   if (args.length < 1)
		   {
				 Format.Comando("AJUDA", "Use /ajuda §aO que", jogador);
			   return true;
		   }

			
			 StringBuilder s = new StringBuilder();
	            for (int i = 0; i < args.length; i++) {
                s.append(args[i]).append(" ");
            }
	        
	          
	           Format.Comando("AJUDA", "Dúvida foi entrege para nossa staff!", jogador);
	           me.hub.Admin.Staff.MandarMSGBungee(" ");
	            me.hub.Admin.Staff.MandarMSGBungee("§f§lJogador esta com duvida!");
	            me.hub.Admin.Staff.MandarMSGBungee("§e§lDUVIDA " + jogador.getDisplayName() + ": §c" + s.toString().replace("&", "§"));
	            me.hub.Admin.Staff.MandarMSGBungee("§f§lPara responder use §6§l/ajuda " + jogador.getName() + " resposta");
	            me.hub.Admin.Staff.MandarMSGBungee(" ");
	            return true;
		    }
		    else
		    {
				   if (args.length <= 1)
				   {
						 Format.Comando("MSG", "Use /ajuda §ajogador resposta", jogador);
					   return true;
				   }
				   
					 StringBuilder s = new StringBuilder();
			            for (int i = 1; i < args.length; i++) {
		                s.append(args[i]).append(" ");
		            }
			        Format.Comando("MSG", "§7Respondido §a" + s + "§7 para §a" + args[0], jogador);
				    Bungee.MandarMSGBungee(args[0], jogador.getCustomName() + " §f§lRESPONDEU", jogador);
		            Bungee.MandarMSGBungee(args[0], "§e§lRESPOSTA : §f" + s.toString().replace("&", "§"), jogador);
	
			          
			            
		    }
		   
		   
		   return false;
	  }
}
