package me.hub.comandos.geral;




import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;

public class Clan implements CommandExecutor {

public String[] atalhos = new String[] { "mclan","team","time","cla","clã" };
    public String desc = "Ver os coins";
    
    public static HashMap<String, String> TimeAcc = new HashMap<String,String>();
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
// começo
		   try{
			   if (args[0].contains("sair"))
				{
				   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM")){
					   Format.Comando("Time", "Você não tem um time(clã) !", jogador);
				   }else{
					  	try
		    		    {
		            		String buscar = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=REMOVE&tag="+me.acf.clan.Clan.clan.get(jogador)+"&jogador=" + jogador.getName(), "OK");
		            		jogador.sendMessage("§c§lTime §7" + buscar.replace("{timename}", ""+me.acf.clan.Clan.clan.get(jogador)));
		            		Account.UpdateAccount(jogador);
		            		return true;
		    		    }
		                catch (Exception exception)
		    		    {
		                	String buscar = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=REMOVE&tag="+me.acf.clan.Clan.clan.get(jogador)+"&jogador=" + jogador.getName(), "ERRO");
		                	  jogador.sendMessage("§c§lERRO §7" + buscar);
		                	  return true;
		    		    }
				   }
				}
// fim
// começo	
		   if (args[0].contains("recusar"))
			{
			   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM")){
				   String pNAME = TimeAcc.get(jogador.getName());
			          Player player = Bukkit.getPlayer(pNAME.toString());
			          if (player == null){
			        	  Format.Comando("Time", "O Jogador §f" + player.getName() + " §7não esta no servidor!", jogador);
			          }else{
			        	  TimeAcc.remove(jogador.getName());
			        	  Format.Comando("Time", "Você recusou o convite para participar do Time(Clã) §f" + me.acf.clan.Clan.clan.get(player) + " §7!", jogador);
			        	  Format.Comando("Time", "O jogador §f" + jogador.getName() + " §7Recusou o convite para participar do seu Time(Clã)!", player);
			          }
			   }else{Format.Comando("Time", "Você já tem um time(clã) !", jogador);}
			}else{
// fim
// começo	
		   if (args[0].contains("aceitar"))
			{
			   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM")){
				   String pNAME = TimeAcc.get(jogador.getName());
			          Player player = Bukkit.getPlayer(pNAME.toString());
			          if (player == null){
			        	  Format.Comando("Time", "O Jogador §f" + player.getName() + " §7não esta no servidor!", jogador);
			          }else{
			        	  TimeAcc.remove(jogador.getName());
						  	try
			    		    {
			            		String buscar = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=ADD&tag="+me.acf.clan.Clan.clan.get(player)+"&jogador=" + jogador.getName(), "OK");
			            		jogador.sendMessage("§c§lTime §7" + buscar.replace("{timename}", ""+me.acf.clan.Clan.clan.get(player)));
			            		Account.UpdateAccount(jogador);
			            		Format.Comando("Time", "O jogador §f" + jogador.getName() + " §7Agora faz parte do seu Time(Clã)!", player);
			            		return true;
			    		    }
			                catch (Exception exception)
			    		    {
			                	String buscar = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=ADD&tag="+me.acf.clan.Clan.clan.get(player)+"&jogador=" + jogador.getName(), "ERRO");
			                	  jogador.sendMessage("§c§lERRO §7" + buscar);
			                	  return true;
			    		    }
			          }
			   }else{Format.Comando("Time", "Você já tem um time(clã) !", jogador);}
			}else{
// fim
// começo
				   if (args[0].contains("remover"))
					{
					   try{
				          Player player = Bukkit.getPlayer(args[1]);
					   		   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM"))
							   {
					   			Format.Comando("Time", "Você não tem um Time(Clã) !", jogador);
							   }else{
							String BUSrecrutadores = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=INFO&tag="+me.acf.clan.Clan.clan.get(jogador), "recrutadores");
							if (BUSrecrutadores.contains(jogador.getName())){
				   		   if (me.acf.clan.Clan.clan.get(player).equals(me.acf.clan.Clan.clan.get(jogador)))
						   {
				        	  String go = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=REMOVE&tag="+me.acf.clan.Clan.clan.get(jogador)+"&jogador=" + player.getName(), "OK");
				        	  if (go.contains("Você saiu do time(clã) que você estava")){
				        		  Format.Comando("Time", "Você removeu o jogador §f"+player.getName()+" §7Do seu Time(Clã) !", jogador);
				        		  Format.Comando("Time", "O jogador §f"+jogador.getName()+" §7Removeu você do Time(Clã) §f"+me.acf.clan.Clan.clan.get(jogador)+"§7 !", player);
				        	  }
				   			  Format.Comando("Time", "Convite para participar do seu Time(Clã) enviado para o jogador §f"+player.getName(), jogador);
				        	  Format.Comando("Time", "O jogador §f"+jogador.getName()+" §7Está convidando você para fazer parte do Time(Clã) §e"+me.acf.clan.Clan.clan.get(jogador), player);
				        	  Account.UpdateAccount(player);
						   }else{
							   Format.Comando("Time", "O jogador §f"+player.getName()+" §7não é do mesmo Time(Clã) que você !", jogador);
						   }}else{
							   Format.Comando("Time", "Você não é um recrutador do seu time(clã) !", jogador);
						   }
							
				          }
						}catch (Exception exception){
							Format.Comando("Time", "§7Digite §f/time convidar (Nick Jogador Online)", jogador);
						}
						  
					}
// fim
// começo
				   if (args[0].contains("recrutadores"))
					{
					   if (args[1].contains("add"))
						{
						   
						}
					   if (args[1].contains("remover"))
						{
						   
						}
					   if (args[1].contains("ver"))
						{
						   
						}
					   if (args[1].contains("list"))
						{
						   
						}
					   if (args[1].contains("info"))
						{
						   
						}
					   try{
				          Player player = Bukkit.getPlayer(args[1]);
				          if (player == null)
				          {
				        	  Format.Comando("Time", "O Jogador §f" + args[1] + " §7não esta no servidor!", jogador);
				          }else{
					   		   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM"))
							   {
					   			Format.Comando("Time", "Você não tem um Time(Clã) !", jogador);
							   }else{
							String BUSrecrutadores = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=INFO&tag="+me.acf.clan.Clan.clan.get(jogador), "recrutadores");
							if (BUSrecrutadores.contains(jogador.getName())){
				   		   if (me.acf.clan.Clan.clan.get(player).equals("NENHUM"))
						   {
				   		   	  TimeAcc.remove(player.getName());
				        	  Format.Comando("Time", "Convite para participar do seu Time(Clã) enviado para o jogador §f"+player.getName(), jogador);
				        	  Format.Comando("Time", "O jogador §f"+jogador.getName()+" §7Está convidando você para fazer parte do Time(Clã) §e"+me.acf.clan.Clan.clan.get(jogador), player);
				        	  Format.Comando("Time", "Digite §f/time aceitar", player);
				        	  Format.Comando("Time", "Ou", player);
				        	  Format.Comando("Time", "Digite §f/time recusar", player);
				        	  TimeAcc.put(""+player.getName(), ""+jogador.getName());
						   }else{
							   Format.Comando("Time", "O jogador §f"+player.getName()+" §7já tem um Time(Clã) !", jogador);
						   }}else{
							   Format.Comando("Time", "Você não é um recrutador do seu time(clã) !", jogador);
						   }
							}
				          
				          }
						}catch (Exception exception){
							Format.Comando("Time", "§7Digite §f/time convidar (Nick Jogador Online)", jogador);
						}
						  
					}
// fim
// começo
				   if (args[0].contains("convidar"))
					{
					   try{
				          Player player = Bukkit.getPlayer(args[1]);
				          if (player == null)
				          {
				        	  Format.Comando("Time", "O Jogador §f" + args[1] + " §7não esta no servidor!", jogador);
				          }else{
					   		   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM"))
							   {
					   			Format.Comando("Time", "Você não tem um Time(Clã) !", jogador);
							   }else{
							String BUSrecrutadores = AccountWeb.Conectar(Main.site + "/API/clan.php?modo=INFO&tag="+me.acf.clan.Clan.clan.get(jogador), "recrutadores");
							if (BUSrecrutadores.contains(jogador.getName())){
				   		   if (me.acf.clan.Clan.clan.get(player).equals("NENHUM"))
						   {
				   		   	  TimeAcc.remove(player.getName());
				        	  Format.Comando("Time", "Convite para participar do seu Time(Clã) enviado para o jogador §f"+player.getName(), jogador);
				        	  Format.Comando("Time", "O jogador §f"+jogador.getName()+" §7Está convidando você para fazer parte do Time(Clã) §e"+me.acf.clan.Clan.clan.get(jogador), player);
				        	  Format.Comando("Time", "Digite §f/time aceitar", player);
				        	  Format.Comando("Time", "Ou", player);
				        	  Format.Comando("Time", "Digite §f/time recusar", player);
				        	  TimeAcc.put(""+player.getName(), ""+jogador.getName());
						   }else{
							   Format.Comando("Time", "O jogador §f"+player.getName()+" §7já tem um Time(Clã) !", jogador);
						   }}else{
							   Format.Comando("Time", "Você não é um recrutador do seu time(clã) !", jogador);
						   }
							}
				          
				          }
						}catch (Exception exception){
							Format.Comando("Time", "§7Digite §f/time convidar (Nick Jogador Online)", jogador);
						}
						  
					}else{
// fim
// começo
						   jogador.sendMessage(" ");
						   jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lTime §f"+me.acf.clan.Clan.NomeDoClan(jogador));
						   jogador.sendMessage(" ");
						   jogador.sendMessage("§9§lPerfil Do Time §c§l§c§l|§f http://painel.planetacraft.com.br/time/"+me.acf.clan.Clan.clan.get(jogador));
				   }
// fim
			}
			}
			}
		   catch (Exception exception){
			   if (me.acf.clan.Clan.clan.get(jogador).equals("NENHUM"))
			   {
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lTime ");
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§c§l- §f/time convidar (Nick) §c§l|§7 (Convidar um jogador para seu Time(Clã)");
			   jogador.sendMessage("§c§l- §f/time aceitar §c§l|§7 (Aceitar o convite de um Time(Clã))");
			   jogador.sendMessage("§c§l- §f/time recusar §c§l|§7 (Recusar o convite de um Time(Clã))");
			   jogador.sendMessage(" ");
			   }else{
				   jogador.sendMessage(" ");
				   jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lTime §f"+me.acf.clan.Clan.NomeDoClan(jogador));
				   jogador.sendMessage(" ");
				   jogador.sendMessage("§9§lPerfil Do Time §c§l§c§l|§f http://painel.planetacraft.com.br/time/"+me.acf.clan.Clan.clan.get(jogador));
				   jogador.sendMessage(" ");
				   jogador.sendMessage("§c§l- §f/time convidar (Nick) §c§l|§7 (Convidar um jogador para seu Time(Clã)");
				   jogador.sendMessage("§c§l- §f/time aceitar §c§l|§7 (Aceitar o convite de um Time(Clã))");
				   jogador.sendMessage("§c§l- §f/time recusar §c§l|§7 (Recusar o convite de um Time(Clã))");
				   jogador.sendMessage(" ");
			   }
			}

		   
		
		return false;
	  }
	

	

}

