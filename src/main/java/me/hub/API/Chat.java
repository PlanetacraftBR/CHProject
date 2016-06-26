package me.hub.API;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Chat {

	
public static void ActionBar(Player p, String msg)
{
    IChatBaseComponent barmsg = ChatSerializer.a("{\"text\": \""+ msg +"\"}");
			    PacketPlayOutChat bar = new PacketPlayOutChat(barmsg, (byte) 2);
			    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
}


public static String TransParaFrente(String MSG)
{
	String texto;
    texto = new StringBuilder(MSG).reverse().toString();
    return texto;
}


}
