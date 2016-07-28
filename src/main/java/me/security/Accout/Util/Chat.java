package me.security.Accout.Util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FormatText.Format;
import me.acf.clan.Clan;
import me.acf.lobby.perfil.Perfil;
import me.acf.protocolo.chat.ChatManager;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;
import me.hub.comandos.geral.Fake;
import me.security.Accout.buffer.AccountBuffer;
import me.security.Accout.buffer.AccountInfo;
import me.site.account.rank.Rank;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Chat extends MiniPlugin{

	public Chat(JavaPlugin plugin) {
		 super("Chat", plugin);
		 ChatManager.Load_Server();
	}
	  private static Map<String, Long> timeout = new HashMap<String, Long>();
	  public static boolean Usar = true;
	  
	  public static String TransParaFrente(String MSG)
	  {
	  	String texto;
	      texto = new StringBuilder(MSG).reverse().toString();
	      return texto;
	  }
	  
	  @EventHandler
	  public static void Chats(AsyncPlayerChatEvent e)
	  {
		  String chat = e.getMessage();
		  e.setCancelled(true);
		  if (Perfil.MSGlobal.contains(e.getPlayer()))
		  {
			  e.getPlayer().sendMessage("§cVocê esta com o chat global desativado! Você não pode usar o chat.");
			  
			  return;
		  }
		   if (!(AccountInfo.getRank(e.getPlayer())).Has(e.getPlayer(), Rank.VIP, false))
		   {
		  if (!Usar)
		  {
		    	      Format.Comando("CHAT", "O chat foi desativador", e.getPlayer());
		    	      e.setCancelled(true);
		    	      return;
		  }
		   
		    if ((timeout.containsKey(e.getPlayer().getName())) && 
		    	      (((Long)timeout.get(e.getPlayer().getName())).longValue() > System.currentTimeMillis())) {
		    	      Format.Comando("CHAT", "Você não pode escrever rapido.", e.getPlayer());
		    	      e.setCancelled(true);
		    	      return;
		    	    }
		   }
		    timeout.put(UtilPlayer.Nome(e.getPlayer()), Long.valueOf(System.currentTimeMillis() + 3000L));
	
		    if (AccountInfo.getRank(e.getPlayer()) == Rank.MEMBRO) {
		    	if (chat.contains("&"))
		    	{
			    	
			    	Format.Comando("CHAT", "Você não pode escrever colorido", e.getPlayer());
			    	e.setCancelled(true);
		    		return;
		    	}
		    }
		  
		    if (AccountBuffer.Return(e.getPlayer()).Mute == true)
		    {
				Format.Comando("CHAT", "Você não pode usar o chat", e.getPlayer());
		    	e.setCancelled(true);
	    		return;
		    }
		    
          String text = "";
          
          if (Fake.Fake.containsKey(e.getPlayer()))
          {
        	  text = ChatColor.translateAlternateColorCodes('&', Clan.NomeDoClan(e.getPlayer()).replace("NENHUM ", "") + "&e"+ Fake.Fake.get(e.getPlayer()) + ": &f" + chat);
          }
          else
          {
        	  text = ChatColor.translateAlternateColorCodes('&', Clan.NomeDoClan(e.getPlayer()).replace("NENHUM ", "") + "&e"+ e.getPlayer().getDisplayName().replace("MEMBRO", "") + ": &f" + chat);
          }
          
          
			    BaseComponent[] converted = TextComponent.fromLegacyText(text);
			       
			      TextComponent tc = new TextComponent(converted);
			      
		  if (ChatManager.ChatVerificar(e.getPlayer(), chat)) 
			  return;
				    
		          for (Player p : Bukkit.getOnlinePlayers()) {
		          p.spigot().sendMessage(tc);
		          }
			
	  }
}