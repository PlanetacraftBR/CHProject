package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.acf.lobby.extend.MobSpawn;
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

public class NoAI implements CommandExecutor {

	public String[] atalhos = new String[] { "mob" };
    public String desc = "Disabalitar e habilitar o NoAI";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFFM, true))
		   {

			if (args.length  <= 0)
			{
				Format.Comando("NoAI", "/NoAI §aON§7|§aOFF", jogador);
				return true;
			}
			if (args.length == 1) {
				if ((args[0].contains("on")) || (args[0].contains("ON")) || (args[0].contains("On")) || (args[0].contains("ativar")))
				{
					if (MobSpawn.NoAI)
					{
						Format.Comando("NoAI", "O NoAI já esta ativado!", jogador);
						return true;
					}
				   MobSpawn.NoAI = true;
				   UtilServer.AnuncioStaff("§f§lO NoAI foi ativado!");
				}
				else
				if ((args[0].contains("off")) || (args[0].contains("OFF")) || (args[0].contains("Off")) || (args[0].contains("desativar")))
				{
					if (!MobSpawn.NoAI)
					{
						Format.Comando("NoAI", "O NoAI já esta desativado!", jogador);
						return true;
					}
				   MobSpawn.NoAI = false;
				   UtilServer.AnuncioStaff("§f§lO NoAI foi desativado!");
				}
				else
				{
					Format.Comando("chat", "/chat §aON§7|§aOFF", jogador);
				}
				
			}
		   
			
		   }
		
		return false;
	  }
	

	

}

