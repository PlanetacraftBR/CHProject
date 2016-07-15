package me.hub.API.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.hub.API.Chat;

public class UtilTextBottom
{
	public static void display(String text, Player... players)
	{
		for (Player player : players)
		{
           Chat.ActionBar(player, text);
		}
	}
	
	public static void displayProgress(double amount, Player... players)
	{
		displayProgress(null, amount, null, players);
	}
	
	public static void displayProgress(String prefix, double amount, Player... players)
	{
		displayProgress(prefix, amount, null, players);
	}
	
	public static void displayProgress(String prefix, double amount, String suffix, Player... players)
	{
		displayProgress(prefix, amount, suffix, false, players);
	}
	
	public static void displayProgress(String prefix, double amount, String suffix, boolean progressDirectionSwap, Player... players)
	{
		if (progressDirectionSwap)
			amount = 1 - amount;
		
	
		int bars = 24;
		String progressBar = "§a";
		boolean colorChange = false;
		for (int i=0 ; i<bars ; i++)
		{
			if (!colorChange && (float)i/(float)bars >= amount)
			{
				progressBar += "§c";
				colorChange = true;
			}
			
			progressBar += "▌";
		}
		

		for (Player player : players)
		{
				display((prefix == null ? "" : prefix + ChatColor.RESET + " ") + progressBar + (suffix == null ? "" : ChatColor.RESET + " " + suffix), players);
			
		}
	} 
}
