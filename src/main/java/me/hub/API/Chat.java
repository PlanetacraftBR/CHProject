package me.hub.API;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.hub.API.Util.UtilHolo;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

public class Chat {

	
public static void ActionBar(Player p, String msg)
{
	if (UtilHolo.Player1_7.contains(p))
		return;
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
