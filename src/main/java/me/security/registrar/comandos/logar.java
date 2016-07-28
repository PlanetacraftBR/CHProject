package me.security.registrar.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilTitle;
import me.hub.API.Util.message.Message;
import me.hub.Bungee.Bungee;
import me.hub.comandos.ComandosAPI;
import me.security.LoginManager;
import me.security.SecurityManager;

public class logar implements CommandExecutor {

	public String[] atalhos = new String[] { "login", "entrar", "entra", "unixlog" };
    public String desc = "Anunciar no servidor em modo staff";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   if (LoginManager.registrar.contains(jogador))
		   {
			   return true;
		   }
		   if (!Bukkit.getServerName().equals("Logando"))
			   return true;
		   if (args.length < 2)
		   {
				 Format.Comando("Security", "Use /logar §asenha E-Mail", jogador);
			   return true;
		   }

		   String senha = args[0];
		   String email = args[1];
		 
		 if (!SecurityManager.VerSenha(senha,email,jogador))
		 {
			 Format.Erro("Sua senha ou e-mail incorretos .", jogador);
		 }
		 else
		 {
			 Format.Comando("Security", "Você foi logado com exito", jogador);
			 Bungee.SendPlayerToServer(jogador, "lobby");
			 UtilTitle.sendTitle(jogador,20,20,20,"&f&lLogado com exito","&a&lObrigado");
		 }

		   
		
		return false;
	  }
	

	

}

