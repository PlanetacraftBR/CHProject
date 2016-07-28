package me.hub.comandos.geral;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class Monitor implements CommandExecutor {

	public String[] atalhos = new String[] { "monitor", "desempenho", "monitorar", "lag", "painel" };
    public String desc = "Ver o desempenho do servidor";
    
    private double _ticksPerSecond;
    private double _ticksPerSecondAverage;
    
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
				  double Free = Runtime.getRuntime().freeMemory() / 1048576L;
				  double Alocada = Runtime.getRuntime().totalMemory() / 1048576L;
				  double Uso = Alocada - Free;
				  
				  Bukkit.getServer().getConsoleSender().sendMessage(" ");
				  Bukkit.getServer().getConsoleSender().sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lInfo Server");
				  Bukkit.getServer().getConsoleSender().sendMessage(" ");
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lLive (Delay) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecond) }));
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lAvg (Tempo de resposta) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecondAverage * 20.0D) }));
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lMemoria Alocada §c§l§c§l|§f§l " + Alocada + "MB");
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lMemoria Livre §c§l§c§l|§f§l " + Runtime.getRuntime().freeMemory() / 1048576L + "MB");
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lMemoria em Uso §c§l§c§l|§f§l " + Uso + "MB");
				  Bukkit.getServer().getConsoleSender().sendMessage("§9§lMemoria em Maximo §c§l§c§l|§f§l " + Runtime.getRuntime().maxMemory() / 1048576L + "MB");
				  Bukkit.getServer().getConsoleSender().sendMessage(" ");
		   }else{
		   Player jogador = (Player) sender;
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, false))
		   {
				  
			      
				  double Free = Runtime.getRuntime().freeMemory() / 1048576L;
				  double Alocada = Runtime.getRuntime().totalMemory() / 1048576L;
				  double Uso = Alocada - Free;
				  
				  jogador.sendMessage(" ");
				  jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lInfo Server");
				  jogador.sendMessage(" ");
				  jogador.sendMessage("§9§lLive (Delay) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecond) }));
				  jogador.sendMessage("§9§lAvg (Tempo de resposta) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecondAverage * 20.0D) }));
				  jogador.sendMessage("§9§lMemoria Alocada §c§l§c§l|§f§l " + Alocada + "MB");
				  jogador.sendMessage("§9§lMemoria Livre §c§l§c§l|§f§l " + Runtime.getRuntime().freeMemory() / 1048576L + "MB");
				  jogador.sendMessage("§9§lMemoria em Uso §c§l§c§l|§f§l " + Uso + "MB");
				  jogador.sendMessage("§9§lMemoria em Maximo §c§l§c§l|§f§l " + Runtime.getRuntime().maxMemory() / 1048576L + "MB");
				  jogador.sendMessage(" ");
		   }else{
				  
			      
				  double Free = Runtime.getRuntime().freeMemory() / 1048576L;
				  double Alocada = Runtime.getRuntime().totalMemory() / 1048576L;
				  double Uso = Alocada - Free;
				  
				  jogador.sendMessage(" ");
				  jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lInfo Server");
				  jogador.sendMessage(" ");
				  jogador.sendMessage("§9§lLive (Delay) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecond) }));
				  jogador.sendMessage("§9§lAvg (Tempo de resposta) §c§l§c§l|§f§l " + String.format("%.00f", new Object[] { Double.valueOf(this._ticksPerSecondAverage * 20.0D) }));
				  jogador.sendMessage(" ");
		   }
		   
		   }
		   return false;
	  }
		   
		   }