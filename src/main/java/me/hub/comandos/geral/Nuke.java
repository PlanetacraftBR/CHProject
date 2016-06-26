package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class Nuke implements CommandExecutor {

	public String[] atalhos = new String[] { "tnt" };
    public String desc = "Criar uma chuva de tnt";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, true))
		   {
				if (args.length  <= 0)
				{
					   final Location loc = jogador.getLocation();
						final World world = loc.getWorld();
						for (int x = -10; x <= 10; x += 5)
						{
							for (int z = -10; z <= 10; z += 5)
							{
								final Location tntloc = new Location(world, loc.getBlockX() + x, world.getHighestBlockYAt(loc) + 64, loc.getBlockZ() + z);
								final TNTPrimed tnt = world.spawn(tntloc, TNTPrimed.class);
							}
						}
						 UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o criou uma chuva perdo dele.");
					return true;
				}
		          Player player = Bukkit.getPlayer(args[0]);
		          if (player == null)
		          {
		        	  Format.Comando("NUKER", "jogador " + args[0] + " não esta no servidor!", jogador);
		            return true;
		          }
		         
		            final Location loc = player.getLocation();
					final World world = loc.getWorld();
					for (int x = -10; x <= 10; x += 5)
					{
						for (int z = -10; z <= 10; z += 5)
						{
							final Location tntloc = new Location(world, loc.getBlockX() + x, world.getHighestBlockYAt(loc) + 64, loc.getBlockZ() + z);
							final TNTPrimed tnt = world.spawn(tntloc, TNTPrimed.class);
						}
					}
				     UtilServer.AnuncioStaff("§a§o" + jogador.getName() + "§f§o criou uma chuva de TNT para §a§o" + args[0]);
		   }
		
		return false;
	  }
	

	

}

