package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.acf.lobby.perfil.Perfil;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Private implements CommandExecutor {

	public String[] atalhos = new String[] { "msg", "tell", "call", "message" };
    public String desc = "Mandar MSG privadas";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if (args.length < 1)
		   {
				 Format.Comando("MSG", "Use /tell §ajogador texto", jogador);
			   return true;
		   }
			 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
             {
					 Format.Comando("MSG", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
             return true;
             }
			 if (Fake.Fake.containsKey(args[0]))
			 {
				 Format.Comando("MSG", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
	             return true;
			 }
			 Player player = Bukkit.getPlayerExact(args[0]);
			 if (Perfil.MsgPrivate.contains(player))
			 {
				 jogador.sendMessage("§cEste jogador não quer receber mensagens privadas");
				 return true;
			 }
			 
			 StringBuilder s = new StringBuilder();
	            for (int i = 1; i < args.length; i++) {
                 s.append(args[i]).append(" ");
             }
			    if (Account.getRank(jogador) == Rank.MEMBRO) {
			    	player.sendMessage("§c§lMSG §e" + UtilPlayer.Nome(jogador) + ": §f" + s.toString());
			    	jogador.sendMessage("§c§lMSG §e" + UtilPlayer.Nome(jogador) + ": §f" + s.toString());
			    }
			    else
			    {
			    	player.sendMessage("§c§lMSG §e" + UtilPlayer.Nome(jogador) + ": §f" + s.toString().replace("&", "§"));
			    	jogador.sendMessage("§c§lMSG §e" + UtilPlayer.Nome(jogador) + ": §f" + s.toString().replace("&", "§"));
		  }
		   
		
		return false;
	  }
	

	

}

