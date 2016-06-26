/**
  *           UtilServerPlayerStatos.java, Class CH-Group 26 de jun de 2016 - 00:39:52 by αdяiαиcf #Reloads
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

  @Projeto CHProject --- proprietário CH-Group, projeto criado no dia 26 de jun de 2016 as 00:39:52        
   
  Todo o me.hub.API.Util e UtilServerPlayerStatos.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
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



package me.hub.API.Util;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import me.hub.Main;
import me.site.account.AccountWeb;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

/**
 * Código por adriancf, Group CH-Project

 */
public class UtilServerPlayerStatos {

	public static String Embreve = "BREVE";
	public static String Beta = "BETA";
	
	public static void UpdateAll()
	{
		for (String nome : UtilNPC.entitys.keySet())
		{
			String name = UtilNPC.entitys.get(nome).getName();
			if (nome.contains(Embreve))
			{
			   Location loc = UtilNPC.entitys.get(nome).getEntity().getLocation();	
			   UtilNPC.entitys.get(nome).setName("§cINDISPONÍVEL");
			   UtilNPC.entitys.get(nome).despawn(DespawnReason.PENDING_RESPAWN);
			   UtilNPC.entitys.get(nome).spawn(loc);
				
			}
			else {
				String rename = Statos(nome);
				
				if (name.contains(Beta))
				{
					  Location loc = UtilNPC.entitys.get(nome).getEntity().getLocation();	
					  UtilNPC.entitys.get(nome).setName("§a§o" + rename);
					  UtilNPC.entitys.get(nome).despawn(DespawnReason.PENDING_RESPAWN);
					  UtilNPC.entitys.get(nome).spawn(loc);	
				}
				else
				{
					  Location loc = UtilNPC.entitys.get(nome).getEntity().getLocation();	
					  UtilNPC.entitys.get(nome).setName("§e§o" + rename);
					  UtilNPC.entitys.get(nome).despawn(DespawnReason.PENDING_RESPAWN);
					  UtilNPC.entitys.get(nome).spawn(loc);
					  
				    
				}
			}
			
		}
	}
	
	
	private static String Statos(String sala)
	{
		
	   String Servidor = AccountWeb.Conectar(Main.site + "/API/sala.php?modo=CONSUTAR_ON&nome=" + sala.replace("§a§o", ""), "online");
	   String nome =  Servidor + " Player(s)";
	   if (Servidor.equals("0"))
               nome = "Ninguem";
	   
	   
	     return nome;
	}
	
	
	
}
