package me.hub.API.Util;

import org.bukkit.entity.Player;

import me.hub.API.module.Reflection;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

public class UtilActionBar {

	
	public static void ActionBar(Player p, String msg)
	{
		if (UtilHolo.Player1_7.contains(p))
			return;
	    IChatBaseComponent barmsg = ChatSerializer.a("{\"text\": \""+ msg +"\"}");
	    PacketPlayOutChat bar = new PacketPlayOutChat(barmsg, (byte) 2);
	    Reflection.sendPacket(p, bar);
	}
	
	public static void ActionBar(String msg)
	{
		for (Player jogador : UtilServer.Jogadores())
			ActionBar(jogador,msg);
	}
}
