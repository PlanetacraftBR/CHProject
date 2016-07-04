package me.hub.NMS.BossBar.wither;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class Util {

	private static final ProtocolManager manager = ProtocolLibrary.getProtocolManager();

	public static void sendPacket(Player player, PacketContainer packet) {
		try {
			manager.sendServerPacket(player, packet);
		} catch (Throwable t) {
		}
	}

	public static PacketContainer createPacket(PacketType type) {
		try {
			return manager.createPacket(type);
		} catch (Throwable t) {
		}
		return null;
	}

}
