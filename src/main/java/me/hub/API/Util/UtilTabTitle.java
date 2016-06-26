package me.hub.API.Util;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class UtilTabTitle
{
	public static void sendTabTitle(Player player, String header, String footer)
	  {
	    if (header == null) {
	      header = "";
	    }
	    header = ChatColor.translateAlternateColorCodes('&', header);
	    if (footer == null) {
	      footer = "";
	    }
	    footer = ChatColor.translateAlternateColorCodes('&', footer);
	    
	    header = header.replaceAll("%player%", player.getDisplayName());
	    footer = footer.replaceAll("%player%", player.getDisplayName());
	    
	    PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
	    IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
	    IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
	    PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
	    try
	    {
	      Field field = headerPacket.getClass().getDeclaredField("b");
	      field.setAccessible(true);
	      field.set(headerPacket, tabFoot);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	      connection.sendPacket(headerPacket);
	    }
	  }
}