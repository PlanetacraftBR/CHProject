package me.hub.comandos.geral;


import me.acf.FormatText.Format;
import me.antiHack.AntiHack;
import me.hub.Main;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class fly implements CommandExecutor {

	public String[] atalhos = new String[] { "voar" };
    public String desc = "Ver os coins";
    public static ArrayList<Player> fly =  new ArrayList();
    public static ArrayList<Player> Desativar =  new ArrayList();
    
    public static void Desativar(Player p)
    {
    	if (!Desativar.contains(p))
    		Desativar.add(p);
    	fly.remove(p);
    	p.setFlying(false);
		p.setAllowFlight(false);
    }
    
    public static void RemoverDesativar(Player p)
    {
    	Desativar.remove(p);
    }
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
	

		   if ((Account.getRank(jogador)).Has(jogador, Rank.VIP, true))
		   {
			if ((jogador.getGameMode() == GameMode.ADVENTURE) || (jogador.getGameMode() == GameMode.SURVIVAL))
			{
				if (Desativar.contains(jogador))
				{
			     Format.Erro("O modo Fly esta desativado agora!", jogador);
			     return true;
				}
					
				if (!jogador.isFlying())
				{
					fly.add(jogador);
					Format.Comando("FLY", "Você ativou seu modo fly.", jogador);
					jogador.setAllowFlight(true);
				}
				else
				{
					fly.remove(jogador);
					Format.Comando("FLY", "Você desativou seu modo fly.", jogador);
					jogador.setFlying(false);
					jogador.setAllowFlight(false);
					AntiHack.igonorar.add(jogador);
				}
			}
			else
			{
				if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, false))
				   {
					if (!jogador.isFlying())
					{
						fly.add(jogador);
						Format.Comando("FLY", "Você ativou seu modo fly.", jogador);
						jogador.setAllowFlight(true);
					}
					else
					{
						fly.remove(jogador);
						Format.Comando("FLY", "Você desativou seu modo fly.", jogador);
						jogador.setFlying(false);
						jogador.setAllowFlight(false);
						AntiHack.igonorar.add(jogador);
					}
				   }
				else
				Format.Comando("FLY", "Somente jogadores em outro modo de jogo pode usar", jogador);
			}
		   }
		
		return false;
	  }
	

	

}

