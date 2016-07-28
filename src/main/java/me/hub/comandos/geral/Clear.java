package me.hub.comandos.geral;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class Clear implements CommandExecutor {


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
	
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, true))
		   {
			if (args.length  < 1)
			{
				jogador.getInventory().clear();
				jogador.getInventory().setBoots(null);
				jogador.getInventory().setHelmet(null);
				jogador.getInventory().setChestplate(null);
				jogador.getInventory().setLeggings(null);
		        Format.Comando("CLEAR", "Você limpou seu inventário.", jogador);
				return true;
			}
				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                {
					 Format.Comando("CLEAR", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                return true;
                }
				 Player alvo = Bukkit.getPlayerExact(args[0]);
				    alvo.getInventory().clear();
					alvo.getInventory().setBoots(null);
					alvo.getInventory().setHelmet(null);
					alvo.getInventory().setChestplate(null);
					alvo.getInventory().setLeggings(null);
					
				 UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o limpou o inventario do §a§o" + args[0]);
		  
				
			}
		   
	  
			
			
		return false;
	  }


	
	  
}

