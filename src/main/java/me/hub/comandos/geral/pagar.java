package me.hub.comandos.geral;

import java.text.NumberFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilNumber;
import me.hub.API.Util.message.Message;
import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;



public class pagar implements CommandExecutor {

	public String[] atalhos = new String[] { "pay" };
    public String desc = "Dar Coins ou Cash para alguem";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;

			if (args.length  <= 2)
			{
				
				Format.Comando("PAGAR", "/pagar [planets,Cash] (jogador) (Valor)", jogador);
				return true;
			}
		
			if (args.length >= 3) {
	            if (args[0].contains("planets"))
	            {
	            	try {
	            	int contidade = Integer.parseInt(args[2]);
	           	 int taxa = 0;
	            	if (!(Account.getRank(jogador)).Has(jogador, Rank.VIPM, false)) {
	            	    taxa = contidade/250;
	            	}
	           	    int dinheiro = Integer.parseInt(Account.getCoins(jogador));
	           	    if (args[1].equals(jogador.getName()))
	           	    {
	           	    	Format.Erro("Você não pode realizar translações com você mesmo ", jogador);
	           	    	return true;
	           	    }
	            	if (dinheiro >= contidade)
	            	{
	            		contidade = contidade-taxa;
	           		 String buscar1 = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + args[1] + "&quantidade=" + contidade, "OK");
	    		     System.out.print(buscar1);
	    		     
		             String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=REMOVE&nick=" + jogador.getName() + "&quantidade=" + args[2], "OK");
	            	 System.out.println(buscar);
	            	 
	            	 String buscar3 = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + Account.bot + "&quantidade=" + taxa, "OK");
	    		     System.out.print(buscar3);
	    		     
	            	 Account.UpdateAccount(jogador);
	            	 ScoreboardAPI.remover(jogador, "Planets");
	            	 if (!ComandosAPI.JogadorOnline(args[1]).equals("nao"))
	                 {
	            		 Player donete = Bukkit.getPlayerExact(args[1]);
	            		 Format.Comando("PAGAR", "Você recebeu §6" + NumberFormat.getCurrencyInstance().format(contidade) + " do §a" + jogador.getName() + "§7 taxa de §6" + NumberFormat.getCurrencyInstance().format(taxa).replace("$", "").replace(".00", ""), donete);
		            	 Account.UpdateAccount(donete);
		            	 ScoreboardAPI.remover(donete, "Planets");
	                 }
	            	 
	            	 Format.Comando("PAGAR", "Você acaba de mover §a" + NumberFormat.getCurrencyInstance().format(Integer.parseInt(args[2])) + "§7 de sua conta!".replace("$", "").replace(".00", ""), jogador);
                 Format.Comando("PAGAR", "O jogador §6" + args[1] + "§7 recebeu §6" + NumberFormat.getCurrencyInstance().format(contidade) + "§7 taxa de §6" + NumberFormat.getCurrencyInstance().format(taxa).replace("$", "").replace(".00", ""), jogador);
	            	    
	                 
	            	}
	            	else
	            	{
	            		Format.Erro("Você não tem planets para isto!", jogador);
	            	}
	            	}
	                catch (Exception exception)
	    		    {
	                	Format.Erro("Ocorreu um erro ao realizar esta translação", jogador);
	    		    }
	            }
	            else
	                if (args[0].contains("cash"))
		            {
		            	try {
		            	int contidade = Integer.parseInt(args[2]);
		            	 int taxa = 0;
		            	 if (!(Account.getRank(jogador)).Has(jogador, Rank.VIPM, false)) {
		           	    taxa = contidade/90;
		            	 }
		           	    int dinheiro = Integer.parseInt(Account.getCash(jogador));
		           	    if (args[1].equals(jogador.getName()))
		           	    {
		           	    	Format.Erro("Você não pode realizar translações com você mesmo ", jogador);
		           	    	return true;
		           	    }
		            	if (dinheiro >= contidade)
		            	{
		            		contidade = contidade-taxa;
		           		 String buscar1 = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=ADD&nick=" + args[1] + "&quantidade=" + contidade, "OK");
		    		     System.out.print(buscar1);
		    		     
			             String buscar = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=REMOVE&nick=" + jogador.getName() + "&quantidade=" + args[2], "OK");
		            	 System.out.println(buscar);
		            	 
		            	 String buscar3 = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=ADD&nick=" + Account.bot + "&quantidade=" + taxa, "OK");
		    		     System.out.print(buscar3);
		    		     
		            	 Account.UpdateAccount(jogador);
		            	 ScoreboardAPI.remover(jogador, "Cash");
		             	 if (!ComandosAPI.JogadorOnline(args[1]).equals("nao"))
		                 {
		            		 Player donete = Bukkit.getPlayerExact(args[1]);
		            		 Format.Comando("PAGAR", "Você recebeu §6" + NumberFormat.getCurrencyInstance().format(contidade) + " do §a" + jogador.getName() + "§7 taxa de §6" + NumberFormat.getCurrencyInstance().format(taxa).replace("$", "").replace(".00", ""), donete);
			            	 Account.UpdateAccount(donete);
			            	 ScoreboardAPI.remover(donete, "Cash");
		                 }
		            	 Format.Comando("PAGAR", "Você acaba de mover §a" + NumberFormat.getCurrencyInstance().format(Integer.parseInt(args[2])) + "§7 de sua conta!", jogador);
		                 Format.Comando("PAGAR", "O jogador §6" + args[1] + "§7 recebeu §6" + NumberFormat.getCurrencyInstance().format(contidade) + "§7 taxa de §6" + NumberFormat.getCurrencyInstance().format(taxa), jogador);
		            	    
		                 
		            	}
		            	else
		            	{
		            		Format.Erro("Você não tem Cash para isto!", jogador);
		            	}
		            	}
		                catch (Exception exception)
		    		    {
		                	Format.Erro("Ocorreu um erro ao realizar esta translação", jogador);
		    		    }
		            }
	            else
	            {
	            	Format.Erro("Esta economia " + args[0] + " não existe", jogador);
	            }
			
			
			}
		
		return false;
	  }
	

	

}

