package me.hub.API.Util;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import com.comphenix.packetwrapper.WrapperPlayClientUseEntity;
import com.comphenix.packetwrapper.WrapperPlayServerEntityDestroy;
import com.comphenix.packetwrapper.WrapperPlayServerNamedEntitySpawn;
import com.comphenix.packetwrapper.WrapperPlayServerPlayerInfo;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

import me.hub.Main;

public class UtilAuraCheck implements Listener {
	
  public Main plugin ;
  public UtilAuraCheck(Main plugin){
  this.plugin = plugin;
  }
    
  private HashMap<Integer, Boolean> entitiesSpawned = new HashMap<Integer, Boolean>();
  private CommandSender invoker;
  private Player checked;
  private static Vector[] vectors = { new Vector(0.0D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, 0.0D), new Vector(1.5D, 0.0D, 0.0D), new Vector(0.0D, 0.0D, -1.5D), new Vector(1.5D, 0.0D, 1.5D), new Vector(-1.5D, 0.0D, -1.5D) };
  private long started;
  private long finished = 9223372036854775807L;
  
  public UtilAuraCheck(Main plugin, Player checked)
  {
    this.plugin = plugin;
    this.checked = checked;
    register();
  }
  
  public static final Random RANDOM = new Random();
  
  public void invoke(CommandSender player, final Callback callback)
  {
    this.invoker = player;
    this.started = System.currentTimeMillis();
    
    int numPlayers = 5;
    for (int i = 1; i <= numPlayers; i++)
    {
      int degrees = 360 / (numPlayers - 1) * i;
      double radians = Math.toRadians(degrees);
      WrapperPlayServerNamedEntitySpawn spawnWrapper;

      if (i == 1) {
        spawnWrapper = getSpawnWrapper(this.checked.getLocation().add(0.0D, 2.0D, 0.0D).toVector(), this.plugin);
      } else {
        spawnWrapper = getSpawnWrapper(this.checked.getLocation().add(2.0D * Math.cos(radians), 0.2D, 2.0D * Math.sin(radians)).toVector(), this.plugin);
      }
      WrapperPlayServerPlayerInfo infoWrapper = getInfoWrapper(spawnWrapper.getPlayerUUID(), EnumWrappers.PlayerInfoAction.ADD_PLAYER);
      infoWrapper.sendPacket(this.checked);
      spawnWrapper.sendPacket(this.checked);
      this.entitiesSpawned.put(Integer.valueOf(spawnWrapper.getEntityID()), Boolean.valueOf(false));
      WrapperPlayServerPlayerInfo RemoveinfoWrapper = getInfoWrapper(spawnWrapper.getPlayerUUID(), EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
      RemoveinfoWrapper.sendPacket(this.checked);
    }
    Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable()
    {
      public void run()
      {
        AbstractMap.SimpleEntry<Integer, Integer> result = end();
        remove(UtilAuraCheck.this.checked.getUniqueId());
        callback.done(UtilAuraCheck.this.started, UtilAuraCheck.this.finished, result, UtilAuraCheck.this.invoker);
      }
    }, 40);
  }
  
  public void markAsKilled(Integer val)
  {
	    if (this.entitiesSpawned.containsKey(val))
	    {
	      this.entitiesSpawned.put(val, Boolean.valueOf(true));
	      kill(val.intValue()).sendPacket(this.checked);
	    }
	    if (!this.entitiesSpawned.containsValue(Boolean.valueOf(false))) {
	      this.finished = System.currentTimeMillis();
	    }
	  }
	  
	  public AbstractMap.SimpleEntry<Integer, Integer> end()
	  {
	    int killed = 0;
	    for (Map.Entry<Integer, Boolean> entry : this.entitiesSpawned.entrySet()) {
	      if (((Boolean)entry.getValue()).booleanValue()) {
	        killed++;
	      } else if (this.checked.isOnline()) {
	        kill(((Integer)entry.getKey()).intValue()).sendPacket(this.checked);
	      }
	    }
	    int amount = this.entitiesSpawned.size();
	    this.entitiesSpawned.clear();
	    return new AbstractMap.SimpleEntry(Integer.valueOf(killed), Integer.valueOf(amount));
	  }
  
  public static WrapperPlayServerNamedEntitySpawn getSpawnWrapper(Vector loc, Main plugin)
  {
    WrapperPlayServerNamedEntitySpawn wrapper = new WrapperPlayServerNamedEntitySpawn();
    wrapper.setEntityID(RANDOM.nextInt(20000));
    wrapper.setPosition(loc);
    wrapper.setPlayerUUID(UUID.randomUUID());
    wrapper.setYaw(0.0F);
    wrapper.setPitch(-45.0F);
    WrappedDataWatcher watcher = new WrappedDataWatcher();
    watcher.setObject(0, Byte.valueOf((byte)(plugin.getConfig().getBoolean("invisibility", false) ? Byte.valueOf((byte)32).byteValue() : 0)));
    watcher.setObject(6, Float.valueOf(0.5F));
    watcher.setObject(11, Byte.valueOf((byte)1));
    wrapper.setMetadata(watcher);
    return wrapper;
  }
  
  public static WrapperPlayServerPlayerInfo getInfoWrapper(UUID playeruuid, EnumWrappers.PlayerInfoAction action)
  {
    WrapperPlayServerPlayerInfo wrapper = new WrapperPlayServerPlayerInfo();
    wrapper.setAction(action);
    WrappedGameProfile profile = new WrappedGameProfile(playeruuid, UtilTexto.TextoAleatorio(15));
    PlayerInfoData data = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(UtilTexto.TextoAleatorio(15)));
    List<PlayerInfoData> listdata = new ArrayList();
    listdata.add(data);
    wrapper.setData(listdata);
    return wrapper;
  }
  
  public static HashMap<UUID, UtilAuraCheck> running = new HashMap<UUID, UtilAuraCheck>();
  public static WrapperPlayServerEntityDestroy kill(int entity)
  {
    WrapperPlayServerEntityDestroy wrapper = new WrapperPlayServerEntityDestroy();
    wrapper.setEntityIds(new int[] { entity });
    return wrapper;
  }
  public void register() {
 Main.getProtocolManager().addPacketListener(new PacketAdapter(Main.plugin, new PacketType[] { WrapperPlayClientUseEntity.TYPE }) {
 
  public void onPacketReceiving(PacketEvent event) {
  if (event.getPacketType() == WrapperPlayClientUseEntity.TYPE) {
  int entID = new WrapperPlayClientUseEntity(event.getPacket()).getTargetID();
  if (running.containsKey(event.getPlayer().getUniqueId())) {
  ((UtilAuraCheck)running.get(event.getPlayer().getUniqueId())).markAsKilled(Integer.valueOf(entID));
  }
  }
  }
  });
  }
  
  public void unregister() {
Main.getProtocolManager().removePacketListeners(Main.plugin);
  }
  

  public UtilAuraCheck remove(UUID id)
  {
    if (this.running.containsKey(id))
    {
      if (this.running.size() == 1) {
        unregister();
      }
      return (UtilAuraCheck)this.running.remove(id);
    }
    return null;
  }
  
  public static abstract interface Callback
  {
    public abstract void done(long paramLong1, long paramLong2, AbstractMap.SimpleEntry<Integer, Integer> paramSimpleEntry, CommandSender paramCommandSender);
  }
}