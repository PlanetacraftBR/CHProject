package me.hub.API;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class UtilDisiguise {

	public static boolean disguiseAsAnimal = true;
	  public static boolean disguiseAsMonster = true;
	  
	  public static void disguise(Entity paramEntity, Player paramPlayer)
	  {
	    if ((((paramEntity instanceof Animals)) && (disguiseAsAnimal)) || (((paramEntity instanceof Monster)) && (disguiseAsMonster)))
	    {
	      if (!DisguiseAPI.isDisguised(paramPlayer))
	      {
	        DisguiseAPI.disguiseToAll(paramPlayer, new MobDisguise(DisguiseType.getType(paramEntity.getType()), true));
	      }
	      else
	      {
	        Disguise localDisguise = DisguiseAPI.getDisguise(paramPlayer);
	        if (localDisguise.getType() == DisguiseType.valueOf(paramEntity.getType().name())) {
	          return;
	        }
	        DisguiseAPI.disguiseToAll(paramPlayer, new MobDisguise(DisguiseType.valueOf(paramEntity.getType().name()), true));
	      }
	    }
	  }
}
