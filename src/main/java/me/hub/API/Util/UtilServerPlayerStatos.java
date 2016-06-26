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
			    Stats(UtilNPC.entitys.get(nome).getLocation(),"§c§lINDISPONIVEL",UtilNPC.skin.get(nome),nome);
	
				
			}
			else {
				String rename = Statos(nome);
				
				if (name.contains(Beta))
					UtilNPC.entitys.get(nome).setCustomName("§6§o" + rename );
				else
				{
				    Stats(UtilNPC.entitys.get(nome).getLocation(),"§e§o" + rename,UtilNPC.skin.get(nome),nome);
				}
			}
			
		}
	}
	
	public static void Stats(Location loc,String ent,String skin,String nome){
		NPCRegistry re = CitizensAPI.getNPCRegistry();
		NPC entity = re.createNPC(EntityType.PLAYER, ent);
        entity.setName(ent);
		entity.setProtected(true);
        entity.isFlyable();
        entity.data().set(NPC.PLAYER_SKIN_UUID_METADATA, skin);
        entity.despawn(DespawnReason.PENDING_RESPAWN);
		entity.spawn(loc);
		UtilNPC.entitys.get(nome).remove();
		UtilNPC.entitys.put(nome, entity.getEntity());
	}
	
	private static String Statos(String sala)
	{
		
	   String Servidor = AccountWeb.Conectar(Main.site + "/API/sala.php?modo=CONSUTAR_ON&nome=" + sala.replace("§a§o", ""), "online");
	   String nome =  Servidor + " Jogadores Online";
	   
	   
	   
	     return nome;
	}
	
	
	
}
