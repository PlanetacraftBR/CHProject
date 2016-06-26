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

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

/**
 * Código por adriancf, Group CH-Project

 */
public class UtilNPC {

	public static HashMap<String,NPC> entitys = new HashMap<String, NPC>();
	public static HashMap<String,String> nomes = new HashMap<String, String>();
	public static HashMap<String,String> skin = new HashMap<String, String>();
	public static HashMap<Location,String> location = new HashMap<Location, String>();
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
		if (entitys.containsKey(entity))
		{
			nome_entity = nome_entity+ "" + UtilTexto.TextoAleatorio(5);
		}
        entitys.put(nome_entity,entity);
        nomes.put(nome_entity,em_cima);
        UtilNPC.skin.put(nome_entity,skin);
        location.put(entity.getEntity().getLocation(), nome_entity);
	}
	
	/*
	  UtilNPC.location.get(entity) -- Pegar o nome do NPC no local
	 
	
	*/
	
	public static void AparecerHolo(Player p)
	{
		for (String s : nomes.keySet()) {
		UtilHolo.showHolo(p,nomes.get(s),entitys.get(s).getEntity().getLocation().clone().add(0,0.1,0));
		}
		}
	
	public static void DarUpdate_All()
	{
		UtilServerPlayerStatos.UpdateAll();
	}
	
	public static void RenameEntity(String entity, String novo_Nome)
	{
		entitys.get(entity).setName(novo_Nome);
	}
	
	
}
