package me.hub.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hub.API.ModoDeJogo;



public class ComandosAPI {

	
	public static String VerConsole(CommandSender sender)
	{
		String Console = "sim";
	    if (sender instanceof Player)
	    {
	    	Console = "Nao";
	    }
	    else
	    {

	    }
	    return Console;
	}
	
	
	
	public static String JogadorOnline(String args)
	{
		String online = "nao";
     	  Player Verificar = Bukkit.getPlayerExact(args);
     	  if (Verificar == null) {
     		 online = "nao";
     		 return online;
     	  }
     	  if (Verificar.isOnline())
     		  online = "sim";
	    
	    return online;
	}
	
	public static void setGameMode(Player jogador, ModoDeJogo mododejogo)
	{
	      if(mododejogo == ModoDeJogo.SOBREVIVENCIA)
	    	  jogador.setGameMode(GameMode.SURVIVAL);
	      if(mododejogo == ModoDeJogo.CRIATIVO)
	    	  jogador.setGameMode(GameMode.CREATIVE);
	      if(mododejogo == ModoDeJogo.AVENTURA)
	    	  jogador.setGameMode(GameMode.ADVENTURE);
	      if(mododejogo == ModoDeJogo.ESPECTADOR)
	    	  jogador.setGameMode(GameMode.SPECTATOR);
	}
	

}
