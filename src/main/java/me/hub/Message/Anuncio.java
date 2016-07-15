package me.hub.Message;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;

public class Anuncio extends MiniPlugin{

	public Anuncio(JavaPlugin plugin) {
		 super("Anuncio", plugin);
	}


   

    public static void broadCast(String tag, String msg)
    {
    	Bukkit.broadcastMessage("§6§l" + tag + " " + msg);
        
    }
    


    
}
