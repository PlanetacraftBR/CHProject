package me.hub.API.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.hub.API.Util.UtilServer;
import me.hub.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class UtilAuraCheck implements Listener {
	
  public Main plugin ;
    
  private Player checked;
  private static Vector[] vectors = { new Vector(0.0D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, 0.0D), new Vector(1.5D, 0.0D, 0.0D), new Vector(0.0D, 0.0D, -1.5D), new Vector(1.5D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, -1.5D) };
  public  ArrayList<NPC> npc = new ArrayList<>();
  public  HashMap<Entity,NPC> npc_entity = new HashMap<>();
  public int Hits = 0;
 
  public UtilAuraCheck(Main plugin, Player checked)
  {
    this.plugin = plugin;
    this.checked = checked;
    Spawn();
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  public static final Random RANDOM = new Random();
  
  
	@EventHandler
	public void Hi(EntityDamageByEntityEvent e)
	{

	    if (!(e.getDamager() instanceof Player)) {
		      return;
		    }
	    
		Player p = (Player) e.getDamager();
	  if (p == this.checked)
	  {
		  Hits++;
		  npc_entity.get(e.getEntity()).destroy();
	  }
  }
  
  
  public void Spawn()
  {
    int numPlayers = 5;
    for (int i = 1; i <= numPlayers; i++)
    {
      int degrees = 360 / (numPlayers - 1) * i;
      double radians = Math.toRadians(degrees);
      final NPC spawnWrapper;

      if (i == 1) {
        spawnWrapper = getSpawnWrapper(this.checked.getWorld(),this.checked.getLocation().add(0.0D, 2.0D, 0.0D).toVector(), this.plugin);
      } else {
        spawnWrapper = getSpawnWrapper(this.checked.getWorld(),this.checked.getLocation().add(2.0D * Math.cos(radians), 0.2D, 2.0D * Math.sin(radians)).toVector(), this.plugin);
      }
     
      npc.add(spawnWrapper);
      npc_entity.put(spawnWrapper.getEntity(), spawnWrapper);
      for (Player online : UtilServer.Jogadores())
      {
    	  if (online != this.checked)
    	  {
    		  online.hidePlayer((Player) spawnWrapper.getEntity());
    	  }
      }
      
    }
    
    Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable()
    {
      public void run()
      {
    	  for (NPC npcs : npc) {
    	  npcs.destroy();
    	  npc_entity.remove(npcs.getEntity());
    	  }
      }
    }, 40); 
  }
  
  
  private NPC getSpawnWrapper(World world,Vector location, Main main)
  {
      String nome = UtilTexto.TextoAleatorio(10);
	  NPCRegistry re = CitizensAPI.getNPCRegistry();
	  NPC entity = re.createNPC(EntityType.PLAYER, nome);
      entity.setName(nome);
	  entity.setProtected(true);
      entity.isFlyable();
      entity.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "PlanetaCraft_BR");
      entity.despawn(DespawnReason.PENDING_RESPAWN);
      
	  entity.spawn(new Location(world, location.getX(),location.getY(),location.getZ()));
	  return entity;
  }
  
 


}