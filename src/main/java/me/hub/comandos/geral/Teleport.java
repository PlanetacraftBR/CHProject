package me.hub.comandos.geral;
 
import me.acf.FormatText.Format;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
 
 
public class Teleport implements CommandExecutor {
 
 
        public String[] atalhos = new String[] { "tp" };
    public String desc = "Teleportar";
   
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
        			if (args.length  <= 0)
        			{
        				Format.Comando("TP", "/tp §a<jogador>", jogador);
        		
        				return true;
        			}
        			if (args.length == 1) {
        				
        				if (args[0].equals("@a"))
        				{
    						Format.Comando("TP", "Teleportando o servidor todo para você", jogador);
        					for (Player alvo : UtilServer.Jogadores())
        					{
        						if (!UtilPlayer.Nome(jogador).equals(UtilPlayer.Nome(alvo))) {
        						Format.Comando("TP", "Teleportado para o jogador §a" + UtilPlayer.Nome(jogador), alvo);
        						UtilPlayer.Teleportar(alvo, jogador);
        						}
        					}
        					return false;
        				}
        				
        				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                         {
        					 Format.Comando("TP", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                         return true;
                         }
        		        
        				 Player alvo = Bukkit.getPlayer(args[0]);
        				 UtilPlayer.Teleportar(jogador, alvo);
        				 Format.Comando("TP", "Teleportado para o jogador §a" + UtilPlayer.Nome(alvo), jogador);
        				 
        				 
        			}
        			if (args.length == 2) {
        				
        				
        				if (args[0].equals("@a"))
        				{
              				 if (ComandosAPI.JogadorOnline(args[1]).equals("nao"))
                             {
            					 Format.Comando("TP", "Jogador §a" + args[1] + "§7 não esta online.", jogador);
                             return false;
                             }
        					Player para = Bukkit.getPlayer(args[1]);
        		        
    						Format.Comando("TP", "Teleportando o servidor todo para " + UtilPlayer.Nome(para), jogador);
        					for (Player alvo : UtilServer.Jogadores())
        					{ 
        						if (!alvo.getName().contains(jogador.getName())) {
        						Format.Comando("TP", "Teleportado para o jogador §a" + UtilPlayer.Nome(jogador), alvo);
        						UtilPlayer.Teleportar(alvo, para);
        						}
        					}
        					return false;
        				}
        				
        				 if (ComandosAPI.JogadorOnline(args[0]).equals("nao"))
                         {
        					 Format.Comando("TP", "Jogador §a" + args[0] + "§7 não esta online.", jogador);
                         return false;
                         }
        				 if (ComandosAPI.JogadorOnline(args[1]).equals("nao"))
                         {
        					 Format.Comando("TP", "Jogador §a" + args[1] + "§7 não esta online.", jogador);
                         return false;
                         }
        				 Player alvo = Bukkit.getPlayer(args[0]);
        				 Player alvo2 = Bukkit.getPlayer(args[1]);
        				 UtilPlayer.Teleportar(alvo, alvo2);
        				 Format.Comando("TP", "Teleportado §a" + UtilPlayer.Nome(alvo) + "§7 para §a" + UtilPlayer.Nome(alvo2), jogador);
        				 
        				 
        			}
        			if (args.length >= 3) {
                        Location playerLocation = jogador.getLocation();
                        try
                          {
                  double x = Integer.parseInt(args[0]);
                  double y = Integer.parseInt(args[1]);
                  double z = Integer.parseInt(args[2]);

                  playerLocation.setX(x);
                  playerLocation.setY(y);
                  playerLocation.setZ(z);
                 
                  jogador.teleport(playerLocation);
                  Format.Comando("TP", "Teleportado para X: " + x + ", Y: " + y + ", Z: " + z , jogador);
                          }
                        catch (Exception e)
                          {
                            Format.Comando("TP", "Cordenadas Invalidas", jogador);
                                return true;
                          }
        			}
        		   }
          
                return false;
          }
        
 
 
}