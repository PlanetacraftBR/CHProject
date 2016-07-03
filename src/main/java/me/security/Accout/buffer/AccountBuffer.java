/**
  *           AccountBuffer.java, Class CH-Group 29 de jun de 2016 - 13:55:11 by αdяiαиcf #Reloads
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

  @Projeto CH --- proprietário CH-Group, projeto criado no dia 29 de jun de 2016 as 13:55:11        
   
  Todo o me.security.Accout.buffer e AccountBuffer.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
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



package me.security.Accout.buffer;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.hub.API.Util.UtilPlayer;
import me.security.Accout.AccountAPI;

/**
 * Código por adriancf, Group CH-Project

 */
public class AccountBuffer {

	
	public static int TotalLoad = 0;
	private static HashMap<Player,AccountAPI> contas = new HashMap<>();
	private static HashMap<String,AccountAPI> contas_name = new HashMap<>();
	
	
	public static AccountAPI Return(Player jogador)
	{
		return contas.get(jogador);
	}
	
	public static AccountAPI Return(String jogador)
	{
		return contas_name.get(jogador);
	}
	
	public static void add(Player jogador)
	{
		if (contas.containsKey(jogador))
			contas.remove(jogador);
		AccountAPI conta = new AccountAPI(jogador.getName());
		contas.put(jogador, conta);
	}
	
	public static void add(String jogador)
	{
		if (contas_name.containsKey(jogador))
			contas_name.remove(jogador);
		AccountAPI conta = new AccountAPI(jogador);
		contas_name.put(jogador, conta);
	}
	
	public static void Remove(Player jogador)
	{
        contas.get(jogador).Pedente();
		contas.remove(jogador);
	}
	
	public static void Remove(String jogador)
	{
		contas_name.remove(jogador);
	}
	
	public static void Reload(Player jogador)
	{
		Remove(jogador);
		add(jogador);
		if (contas.get(jogador).erro)
		{
			UtilPlayer.Kick(jogador, contas.get(jogador).erro_info);
		}
	}
	

	
}
