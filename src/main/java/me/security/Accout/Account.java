/**
  *           Account.java, Class CH-Group 29 de jun de 2016 - 13:36:18 by αdяiαиcf #Reloads
  *
  *###################################################################################
  *###################################################################################
  *
  *101101101101   101101101101   101101101101              10110110101    101101101101
  *101      101   101            101                       101            101
  *101      101   101            101                       101            101
  *101101101101   101            101101101101    101101    101            101
  *101      101   101            101                       101   101101   101    101101
  *101      101   101            101                       101      101   101       101
  *101      101   101101101101   101                       101101101101   101 101101101
  *
  *################################################################################### 
  *###################################################################################

                                   adriancf

  @Projeto CH --- proprietário CH-Group, projeto criado no dia 29 de jun de 2016 as 13:36:18        
   
  Todo o me.security.Accout e Account.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
  Portantando você, não pode retirar os direitos dos criadores desta class.


                                    @Info
                                   αdяiαиcf
     
    *  Dependências utilizadas por CH-Projets  
    *  Uso de projetos JSon
    *  Uso de Maven
    *  Uso de CGlib
    *  Entre outras...


Ass: αdяiαиcf - Códigos livres


*/



package me.security.Accout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.servidor.Servidor;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;
import me.security.Accout.Util.Chat;
import me.security.Accout.buffer.AccountBuffer;
import me.security.Accout.buffer.AccountInfo;
import me.security.Punish.PunishAPI;
import me.site.account.rank.Rank;

/**
 * Código por adriancf, Group CH-Project
 */

public class Account extends MiniPlugin {

	
	boolean Carregar_Chat = true;
	
	private JavaPlugin plugin;
	public Account(JavaPlugin plugin) {
		super("Account v2", plugin);
		this.plugin = plugin;
	
	}

	private void Loader()
	{
		Chat chat;
		if (Carregar_Chat)
			 chat = new Chat(plugin);
	}

	/** Nota AdrianCF:
	 *  Account 2.0 
	 *  Essa Plataforma de Account tem melhorias e novidades 
	 *  Favor não alterar nada nesta class
	 */
	
	
	  @EventHandler
	  public void Login(PlayerLoginEvent event)
	  {
		AccountBuffer.add(event.getPlayer());
		AccountAPI conta = AccountBuffer.Return(event.getPlayer());
		AccountBuffer.CheckBan(event.getPlayer());
		if (conta.erro)
		{
			UtilPlayer.Kick(event.getPlayer(), conta.erro_info);
			return;
		}
		
		  if (Servidor.GetMain())
		  { 
			  if (AccountInfo.getRank(event.getPlayer()).Has(event.getPlayer(), Rank.STAFF, false))
		      {
				  event.allow();
			      event.setResult(PlayerLoginEvent.Result.ALLOWED);
		      }
			  else
			UtilPlayer.Kick(event.getPlayer(),"§f§oVocê não tem permissão para entrar neste servidor!"); 
			return;
			}		 
		    if (Bukkit.getOnlinePlayers().size() >= Bukkit.getServer().getMaxPlayers())
		    {
		      if (AccountInfo.getRank(event.getPlayer()).Has(event.getPlayer(), Rank.VIPM, false))
		      {
		        for (Player p : UtilServer.Jogadores())
		        {
		          	if (!AccountInfo.getRank(p).Has(event.getPlayer(), Rank.VIPM, false))
		          	{
		                event.allow();
				        event.setResult(PlayerLoginEvent.Result.ALLOWED);
				        UtilPlayer.Kick(event.getPlayer(),"§f§lUm Jogador VIP+ robou sua slot!\n§f§lServidor lotado compre §5§lVIP+ §f§lem nossa loja!\nAcesse: §a§l " + Main.site);
		          	   	return;
		          	}
		        }
		        UtilPlayer.Kick(event.getPlayer(),"§f§lServidor lotado somente VIP+!");
		      }
		      
		      UtilPlayer.Kick(event.getPlayer(),"§f§lServidor lotado compre §5§lVIP+ §f§lem nossa loja!\nAcesse: §a§l " + Main.site);
		    }
		   
		    PunishAPI ban = new PunishAPI(event.getPlayer(), conta.json.getString("ban_mt"), conta.json.getString("ban_st"),conta.json.getString("ban_at"), conta.json.getString("ban_s"));
            conta.ban = ban;		  
	  }
	
	  
	  @EventHandler
	  public void Leave(PlayerQuitEvent event)
	  {
		  System.out.print(event.getPlayer().getName()+" tempo online: " + AccountBuffer.Return(event.getPlayer()).Tempo_online());
		  AccountBuffer.Remove(event.getPlayer());
	  }
	  

	  
	  
	
}
