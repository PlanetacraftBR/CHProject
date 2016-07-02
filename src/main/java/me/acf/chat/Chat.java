package me.acf.chat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.acf.clan.Clan;
import me.acf.lobby.perfil.Perfil;
import me.acf.protocolo.chat.ChatManager;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;
import me.hub.Admin.Staff;
import me.hub.comandos.geral.Fake;
import me.site.account.Account;
import me.site.account.rank.Rank;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Chat extends MiniPlugin{

	public Chat(JavaPlugin plugin) {
		 super("Chat", plugin);
		 ChatManager.Load_Server();
	}
	  private static Map<String, Long> timeout = new HashMap<String, Long>();
	  public static boolean Usar = true;
	  
	  
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
		   if (!(Account.getRank(e.getPlayer())).Has(e.getPlayer(), Rank.VIP, false))
		   {
		  if (!Usar)
		  {
		    	e.getPlayer().sendMessage("                                                                       ");
		    	e.getPlayer().sendMessage("                 §f§lO chat foi §a§ldesativado§f§l!                ");
		    	e.getPlayer().sendMessage("            §c§lSomente §6§lVIPS §c§lpodem falar agora.");
		    	e.getPlayer().sendMessage("                                                                       ");
		    	      e.setCancelled(true);
		    	      return;
		  }
		   
		    if ((timeout.containsKey(e.getPlayer().getName())) && 
		    	      (((Long)timeout.get(e.getPlayer().getName())).longValue() > System.currentTimeMillis())) {
		    	e.getPlayer().sendMessage("                                                                       ");
		    	e.getPlayer().sendMessage("                 §c§lVocê deve falar devagar..                ");
		    	e.getPlayer().sendMessage("            §c§lSomente §6§lVIPS §c§lpodem falar rapido");
		    	e.getPlayer().sendMessage("                                                                       ");
		    	      e.setCancelled(true);
		    	      return;
		    	    }
		   }
		    timeout.put(UtilPlayer.Nome(e.getPlayer()), Long.valueOf(System.currentTimeMillis() + 3000L));
	
		    if (Account.getRank(e.getPlayer()) == Rank.MEMBRO) {
		    	if (chat.contains("&"))
		    	{
			    	e.getPlayer().sendMessage("                                                                       ");
			    	e.getPlayer().sendMessage("                 §c§lVocê deve falar devagar..                ");
			    	e.getPlayer().sendMessage("            §c§lSomente §6§lVIPS §c§lpodem usar COR");
			    	e.getPlayer().sendMessage("                                                                       ");
			    	      e.setCancelled(true);
		    		return;
		    	}
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