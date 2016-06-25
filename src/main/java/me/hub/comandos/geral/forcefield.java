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
					
					 UtilAuraCheck check = new UtilAuraCheck(Main.main, alvo);
					    UtilAuraCheck.running.put(alvo.getUniqueId(), check);
					    check.invoke(alvo, new UtilAuraCheck.Callback()
					    {
					      public void done(long started, long finished, AbstractMap.SimpleEntry<Integer, Integer> result, CommandSender invoker)
					      {
					        if (((invoker instanceof Player)) && (!((Player)invoker).isOnline())) {
					          return;
					        }
					        
					        Bukkit.getServer().getConsoleSender().sendMessage("§7Staff §fCONSOLE§7 testou o jogador§c " + alvo.getName() + "§7 hitou  §a" + result.getKey() + "§7/§a" + result.getValue());
					        double time = finished != Long.MAX_VALUE ? (int)((finished - started) / 1000L) : 40 / 20.0D;
					        Bukkit.getServer().getConsoleSender().sendMessage("§7Tempo de : (§a" + time + "§7) Seg");
	                         if (result.getKey() >= 5){
	                        	 Bukkit.getServer().getConsoleSender().sendMessage("§7Possibilidade de hack: §aSim");
	                        	 Click.Host.addSuspicion(alvo, "Esta usando KillAura(ForceField) [Extremo]");
	                        	 UtilPlayer.Kick(alvo, "Você esta muito suspeito de ser algum Cheat");
	                         }
	                         if (result.getKey() == 4){
	                        	 Bukkit.getServer().getConsoleSender().sendMessage("§7Possibilidade de hack: §aSim");
	                        	 Click.Host.addSuspicion(alvo, "Esta usando KillAura(ForceField) [Medio]");
	                        	 UtilPlayer.Kick(alvo, "Você esta muito suspeito de ser algum Cheat");
	                         }
	                         if (result.getKey() == 3){
	                        	 Bukkit.getServer().getConsoleSender().sendMessage("§7Possibilidade de hack: §aTalvez");
	                         Click.Host.addSuspicion(alvo, "Talves está usando KillAura(ForceField) [Baixo]");
	                         }
	                         if (result.getKey() <= 2)
	                        	 Bukkit.getServer().getConsoleSender().sendMessage("§7Possibilidade de hack: §aNão");
	                         
					      }
					    });
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
				
				 UtilAuraCheck check = new UtilAuraCheck(Main.main, alvo);
				    UtilAuraCheck.running.put(alvo.getUniqueId(), check);
				    check.invoke(sender, new UtilAuraCheck.Callback()
				    {
				      public void done(long started, long finished, AbstractMap.SimpleEntry<Integer, Integer> result, CommandSender invoker)
				      {
				        if (((invoker instanceof Player)) && (!((Player)invoker).isOnline())) {
				          return;
				        }
				        
				        UtilServer.AnuncioStaff("§7Staff §f" + jogador.getName() + "§7 testou o jogador§c " + alvo.getName() + "§7 hitou  §a" + result.getKey() + "§7/§a" + result.getValue());
				        double time = finished != Long.MAX_VALUE ? (int)((finished - started) / 1000L) : 40 / 20.0D;
				        UtilServer.AnuncioStaff("§7Tempo de : (§a" + time + "§7) Seg");
                         if (result.getKey() >= 4){
                        	 UtilServer.AnuncioStaff("§7Possibilidade de hack: §aSim");
                        	 Click.Host.addSuspicion(alvo, "Esta usando KillAura(ForceField) [Extremo]");
                         }
                         if (result.getKey() == 3){
                        	 UtilServer.AnuncioStaff("§7Possibilidade de hack: §aTalvez");
                        	 Click.Host.addSuspicion(alvo, "Esta usando KillAura(ForceField) [Medio]");
                         }
                         if (result.getKey() <= 2)
                        	 UtilServer.AnuncioStaff("§7Possibilidade de hack: §aNão");
                         
				      }
				    });
			}
		   }
			
		   }
		
		return false;
	  }
	

	

}

