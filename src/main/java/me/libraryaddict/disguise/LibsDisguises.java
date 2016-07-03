package me.libraryaddict.disguise;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import me.hub.Main;
import me.libraryaddict.disguise.utilities.DisguiseUtilities;
import me.libraryaddict.disguise.utilities.PacketsManager;
import me.libraryaddict.disguise.utilities.ReflectionManager;

public class LibsDisguises 
{
    private static Main instance;
    private static DisguiseListener listener;

    
    public static void onEnable(Main java)
    {
        java.getLogger().info("Discovered MC version: " + ReflectionManager.getBukkitVersion());

        PacketsManager.init(java);
        DisguiseUtilities.init(java);

        DisguiseConfig.initConfig(java.getConfig());

        PacketsManager.addPacketListeners();

        listener = new DisguiseListener(java);

        Bukkit.getPluginManager().registerEvents(listener, java);

 

        Main.registerValues();

        instance = java;

    }

    /**
     * Reloads the config with new config options.
     */
    public void reload()
    {
        HandlerList.unregisterAll(listener);
    }

    /**
     * Here we create a nms entity for each disguise. Then grab their default values in their datawatcher. Then their sound volume
     * for mob noises. As well as setting their watcher class and entity size.
     */




    public DisguiseListener getListener()
    {
        return listener;
    }

    /**
     * External APIs shouldn't actually need this instance. DisguiseAPI should be enough to handle most cases.
     *
     * @return The instance of this plugin
     */
    public static Main getInstance()
    {
        return instance;
    }
}
