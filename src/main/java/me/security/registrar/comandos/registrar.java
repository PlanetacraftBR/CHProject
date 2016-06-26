package me.security.registrar.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilTitle;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.security.LoginManager;
import me.security.SecurityManager;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

public class registrar implements CommandExecutor {

	public String[] atalhos = new String[] { "registra", "register", "cadastrar", "unixreg" };
    public String desc = "Anunciar no servidor em modo staff";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if (!Bukkit.getServerName().equals("Logando"))
		   {
			   if (args.length < 2)
			   {
					 Format.Comando("Security", "Use /registrar §asenha E-Mail", jogador);
				   return true;
			   }

			   String senha = args[0];
			   String email = args[1];
			  if (!SecurityManager.SenhaSegura(jogador, senha))
			  {
				  Format.Erro("A senha digitada não pode conter o seu nick nem sequencia de numeros como 123 e não pode ter menos do que 5 caracteres !", jogador);
			      return true;
			  }
			  if (!SecurityManager.Email(email))
			  {
				  Format.Erro("Email digitado não é valido digite outro !", jogador);
			      return true;
			  }
				 String verificar = AccountWeb.Conectar(Main.site + "/API/registrado.php?nick=" + jogador.getName(), "registrado");
				 if (!verificar.contains("sim"))
				 {
					  SecurityManager.AddSenha(senha,email,jogador, false);
				 }
	          
			   return true;
		   }
		   if (!LoginManager.registrar.contains(jogador))
		   {
			   return true;
		   }
		   if (args.length < 2)
		   {
				 Format.Comando("Security", "Use /registrar §asenha E-Mail", jogador);
			   return true;
		   }

		   String senha = args[0];
		   String email = args[1];
		  if (!SecurityManager.SenhaSegura(jogador, senha))
		  {
			  Format.Erro("A senha digitada não pode conter o seu nick nem sequencia de numeros como 123 e não pode ter menos do que 5 caracteres !", jogador);
		      return true;
		  }
		  if (!SecurityManager.Email(email))
		  {
			  Format.Erro("Email digitado não é valido digite outro !", jogador);
		      return true;
		  }
			
		  SecurityManager.AddSenha(senha,email,jogador,true);
			return false;
	  }
	

	

}

