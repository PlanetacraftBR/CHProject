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

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hub.API.Util.UtilPlayer;
import me.security.Accout.buffer.AccountBuffer;

/**
 * Código por adriancf, Group CH-Project

 */
public class Account {

	///API 2.0 de Account 
	
	
	  @EventHandler
	  public void Login(PlayerLoginEvent event)
	  {
		AccountBuffer.add(event.getPlayer());
		AccountAPI conta = AccountBuffer.Return(event.getPlayer());
		if (conta.erro)
		{
			UtilPlayer.Kick(event.getPlayer(), conta.erro_info);
		}
	  }
	
	  
	  @EventHandler
	  public void Leave(PlayerQuitEvent event)
	  {
		  AccountBuffer.Remove(event.getPlayer());
	  }
	  
	  
	
}
