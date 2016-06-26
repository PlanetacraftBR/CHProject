/**
  *           UtilNPC.java, Class CH-Group 25 de jun de 2016 - 22:23:18 by αdяiαиcf #Reloads
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

  @Projeto CHProject --- proprietário CH-Group, projeto criado no dia 25 de jun de 2016 as 22:23:18        
   
  Todo o me.hub.API.Util e UtilNPC.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
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

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

/**
 * Código por adriancf, Group CH-Project

 */
public class UtilNPC {

	public static HashMap<String,Entity> entitys = new HashMap<String, Entity>();
	
	public static void SpawnEntity(String nome_entity,String em_baixo,String em_cima,String skin, Location loc)
	{
		
		NPCRegistry re = CitizensAPI.getNPCRegistry();
		NPC entity = re.createNPC(EntityType.PLAYER, nome_entity);
        entity.setName(em_baixo);
		entity.setProtected(true);
        entity.isFlyable();
        entity.data().set(NPC.PLAYER_SKIN_UUID_METADATA, skin);
        entity.despawn(DespawnReason.PENDING_RESPAWN);
		entity.spawn(loc);
        entitys.put(nome_entity,entity.getEntity());
        UtilHolo.Holo(loc.clone().add(0,0.5,0), em_cima);
	}
	
	public static void RenameEntity(String entity, String novo_Nome)
	{
		entitys.get(entity).setCustomName(novo_Nome);
	}
	
	
}
