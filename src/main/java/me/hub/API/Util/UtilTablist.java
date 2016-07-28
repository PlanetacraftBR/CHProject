package me.hub.API.Util;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.hub.Main;
import me.hub.API.module.tablist.TabAPI;
import me.hub.API.module.tablist.TabItem;

public class UtilTablist {

	
	public static void AddItem(final Player jogador,final String item_text)
	{
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {       	
            	 TabAPI.setItems(jogador, Arrays.asList(new TabItem(item_text)));
            }
	    }, 10);
	}
	
	public static void RemoveItem(final Player jogador,final String item_text)
	{
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
            	 TabAPI.removeItem(jogador, new TabItem(item_text));
            }
	    }, 10);
	}
	
	public static void ClearTab(final Player jogador)
	{
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
		TabAPI.clearAllItems(jogador);
            }
	    }, 10);
	}
	
	
	public static void setHeader(final Player jogador, final String... msg)
	{
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
		TabAPI.setHeader(jogador, msg);
            }
    }, 10);
	}

	public static void setFooter(final Player jogador, final String... msg)
	{
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
		TabAPI.setFooter(jogador, msg);
            }
    }, 10);
	}
	
	
	public static void setHeader(String... msg)
	{
		for (Player p : UtilServer.Jogadores())
			setHeader(p,msg);
	}
	
	public static void setFooter(String... msg)
	{
		for (Player p : UtilServer.Jogadores())
			setHeader(p,msg);
	}
}
