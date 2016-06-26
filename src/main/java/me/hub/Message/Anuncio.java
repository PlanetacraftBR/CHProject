package me.hub.Message;

import java.lang.reflect.Field;
import java.sql.SQLException;

import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import me.acf.lobby.MagicChest.ChestMagic;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Chat;
import me.hub.API.Util.UtilServer;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;

public class Anuncio extends MiniPlugin{

	public Anuncio(JavaPlugin plugin) {
		 super("Anuncio", plugin);
	}


   

    public static void broadCast(String tag, String msg)
    {
    	Bukkit.broadcastMessage("§6§l" + tag + " " + msg);
        for (Player cur : UtilServer.Jogadores())
        {
          cur.playSound(cur.getLocation(), Sound.NOTE_BASS, 2.0F, 0.0F);
        }
        
    }
    


    
}
