package me.hub.Bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilServer;

public class Bungee extends MiniPlugin
{
	  private static HashSet<String> _connectingPlayers = new HashSet();

	  public Bungee(JavaPlugin plugin)
	  {
	    super("Portal", plugin);

	    Bukkit.getMessenger().registerOutgoingPluginChannel(GetPlugin(), "BungeeCord");
	  }

	  public static void MandarMSGBungee(String player, String msg, Player alvo)
	  {
			try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			out.writeUTF("Message");
		    out.writeUTF(player);
			out.writeUTF(msg);
		
			alvo.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
			} catch (Exception e) {
				
			}
	  }
	  
	  
		public static void MandarMSGBungee(String player,String msg)
		{

			  Player p = null;
			  
			  for (Player playe : UtilServer.Jogadores())
			  {
				  p = playe;
			  }
			  
				try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			out.writeUTF("Message");
		    out.writeUTF(player);
			out.writeUTF(msg);
			
				  p.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
				
			  
			} catch (Exception e) {
			
			}
			
		}
		
  public static void SendAllPlayers(String serverName)
  {
    for (Player player : Bukkit.getOnlinePlayers())
    {
      SendPlayerToServer(player, serverName);
    }
  }
  
  public static void KickPlayer(String jogador, String msg)
  {
	
	  Player p = null;
	  
	  for (Player playe : UtilServer.Jogadores())
	  {
		  p = playe;
	  }
	  
	  ByteArrayOutputStream b = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(b);
	  try {
	  out.writeUTF("KickPlayer");
	  out.writeUTF(jogador);
	  out.writeUTF(msg);
	  p.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
       }
	  catch (IOException e)
	      {
	        e.printStackTrace();
	      }
  }

  public static void SendPlayerToServer(final String player, String serverName)
  {
    if (Bungee._connectingPlayers.contains(player)) {
      return;
    }
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(b);
    try
    {
      out.writeUTF("Connect");
      out.writeUTF(serverName);
      
    }
    catch (IOException localIOException1)
    {
      try
      {
        out.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    Player p = null;
	  for (Player pl : UtilServer.Jogadores())
	  {
		  p = pl;
	  }
	  p.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
    Bungee._connectingPlayers.add(player);

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
    {
      public void run()
      {
        Bungee._connectingPlayers.remove(player);
      }
    }
    , 20L);
    MandarMSGBungee(player,"§7Você foi puchado para §a" + serverName);
  }
  
  public static void SendPlayerToServer(final Player player, String serverName)
  {
    if (Bungee._connectingPlayers.contains(player.getName())) {
      return;
    }
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(b);
    try
    {
      out.writeUTF("Connect");
      out.writeUTF(serverName);
    }
    catch (IOException localIOException1)
    {
      try
      {
        out.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    player.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
    Bungee._connectingPlayers.add(player.getName());

    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
    {
      public void run()
      {
        Bungee._connectingPlayers.remove(player.getName());
        player.sendMessage("§7Conexão com o server para enviar caiu");
      }
    }
    , 20L);
	player.sendMessage("§7Você foi teleportado de§e " + Bukkit.getServerName() +"§7 para §a" + serverName);
  }
}