package me.antiHack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FormatText.Format;
import me.antiHack.FastHeal.FastHeal;
import me.antiHack.Move.Fly;
import me.antiHack.Move.NoFall;
import me.antiHack.Move.NoSlowDown;
import me.antiHack.Move.Speed;
import me.antiHack.Move.ToggleSneak;
import me.antiHack.autoclick.Click;
import me.antiHack.pvp.AntiKnockback;
import me.antiHack.pvp.AutoSoup;
import me.antiHack.pvp.FastBow;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilAction;
import me.hub.API.Util.UtilEnt;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.UtilTime;
import me.hub.Admin.Staff;
import me.hub.Bungee.Bungee;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;
import me.hub.effect.MovementDetection;

public class AntiHack
  extends MiniPlugin
{
  private static Object _antiHackLock = new Object();
  public static AntiHack Instance;

  public Bungee Portal;
  private HashMap<Player, HashMap<String, ArrayList<Long>>> _suspicion = new HashMap();
  private HashMap<Player, HashMap<String, ArrayList<Long>>> _offense = new HashMap();
  private HashMap<Player, Long> _ignore = new HashMap();
  private HashSet<Player> _velocityEvent = new HashSet();
  private HashMap<Player, Long> _lastMoveEvent = new HashMap();
  public static ArrayList igonorar = new ArrayList<Player>();
  public int FloatHackTicks = 6;
  public int HoverHackTicks = 3;
  public int RiseHackTicks = 6;
  public int SpeedHackTicks = 6;
  public int IdleTime = 20000;
  public int FlightTriggerCancel = 2000;
  public ArrayList<Detector> _detectors;

  
  public AntiHack(JavaPlugin plugin, Bungee portal)
  {
    super("AntiHack", plugin);
    

    this.Portal = portal;
   
    
    this._detectors = new ArrayList();
    
    this._detectors.add(new Fly(this));
    this._detectors.add(new Speed(this));
    this._detectors.add(new AutoSoup(this));
    this._detectors.add(new FastHeal(this));
    this._detectors.add(new FastBow(this));
    this._detectors.add(new NoFall(this));
    this._detectors.add(new NoSlowDown(this));
    this._detectors.add(new AntiKnockback(this));
    this._detectors.add(new ToggleSneak(this));
    
    MovementDetection MovementDetection = new MovementDetection(plugin);
    Click Click = new Click(plugin);
  } 
  

	ArrayList<BlockCode> blockCodes = new ArrayList<BlockCode>();
	
	public class BlockCode {
		String code;
		String permGlobal;
		String permLocal;
		boolean sendToBlock;
		
		BlockCode (String code, String permGlobal, String permLocal, boolean sendToBlock){
			this.code = code;
			this.permGlobal = permGlobal;
			this.permLocal = permLocal;
			this.sendToBlock = sendToBlock;
		}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onPlayerJoin (PlayerJoinEvent event){
	      String nofly = "&f &f &1 &0 &2 &4 ";
	      nofly = nofly.replaceAll("(&([a-f0-9]))", "Â§$2");
	      event.getPlayer().sendMessage(nofly);
	      
	      String nocheat = "&f &f &2 &0 &4 &8";
	      nocheat = nocheat.replaceAll("(&([a-f0-9]))", "Â§$2");
	      event.getPlayer().sendMessage(nocheat);
	      
		for (int i = 0; i<blockCodes.size(); i++) 
		{
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', blockCodes.get(i).code));
		}
		
		} 
	
	public void initCfg(){
		// Schematica
		blockCodes.add(new BlockCode ("&0&2&0&0&e&f","schematica","printer", true));
		blockCodes.add(new BlockCode ("&0&2&1&0&e&f","schematica","save", true));
		blockCodes.add(new BlockCode ("&0&2&1&1&e&f","schematica","load", true));
		// Zombe 
		blockCodes.add(new BlockCode ("&f &f &2 &0 &4 &8 ","cheats","zmbzcheat", true));
		blockCodes.add(new BlockCode ("&f &f &4 &0 &9 &6 ","cheats","zmbnoclip", true));
		blockCodes.add(new BlockCode ("&f &f &1 &0 &2 &4 ","fly","zmbfly", true));
		// CJB
		blockCodes.add(new BlockCode ("&3 &9 &2 &0 &0 &2 ","cheats","cjbxray", true));
		blockCodes.add(new BlockCode ("&3 &9 &2 &0 &0 &1 ","fly","cjbfly", true));
		blockCodes.add(new BlockCode ("&3 &9 &2 &0 &0 &3 ","radar","cjbradar", true));		
		// Rei's Minimap
		blockCodes.add(new BlockCode ("&0&0&1&e&f", "cheats","reicave", false));
		blockCodes.add(new BlockCode ("&0&0&2&3&4&5&6&7&e&f","radar","reiradar", false));
		// Automap
		blockCodes.add(new BlockCode ("&0&0&1&f&e","cheats","automap-ore", true));
		blockCodes.add(new BlockCode ("&0&0&2&f&e","cheats","automap-cave", true));
		blockCodes.add(new BlockCode ("&0&0&3&4&5&6&7&8&f&e","radar","automap-radar", true));
		// Smart-moving
		blockCodes.add(new BlockCode ("&0&1&0&1&2&f&f","cheats","smart-climb", true));
		blockCodes.add(new BlockCode ("&0&1&3&4&f&f","cheats","smart-swim", true));
		blockCodes.add(new BlockCode ("&0&1&5&f&f","cheats","smart-crawl", true));
		blockCodes.add(new BlockCode ("&0&1&6&f&f","cheats","smart-slide", true));
		blockCodes.add(new BlockCode ("&0&1&7&f&f","fly","smart-fly", true));
		blockCodes.add(new BlockCode ("&0&1&8&9&a&b&f&f","cheats","smart-jump", true));
	}

  public static void Initialize(JavaPlugin plugin, Bungee portal)
  {
    Instance = new AntiHack(plugin, portal);
  }
  
  @EventHandler
  public void playerMove(PlayerMoveEvent event)
  {

	    if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
	    	AntiHack.igonorar.remove(event.getPlayer());
	    }
    synchronized (_antiHackLock)
    {
      this._lastMoveEvent.put(event.getPlayer(), Long.valueOf(System.currentTimeMillis()));
    }
  }
  
  @EventHandler
  public void playerTeleport(PlayerTeleportEvent event)
  {
    synchronized (_antiHackLock)
    {
      this._ignore.put(event.getPlayer(), Long.valueOf(System.currentTimeMillis() + 2000L));
    }
  }
  
  @EventHandler
  public void playerVelocity(PlayerVelocityEvent event)
  {
    synchronized (_antiHackLock)
    {
      this._velocityEvent.add(event.getPlayer());
    }
  }
  
  @EventHandler
  public void playerToggleFly(PlayerToggleFlightEvent event)
  {
	  try {
    Player player = event.getPlayer();
    synchronized (_antiHackLock)
    {
      if (!this._suspicion.containsKey(player)) {
        return;
      }
      Iterator<Long> offenseIterator;
      for (Iterator localIterator = ((HashMap)this._suspicion.get(player)).values().iterator(); localIterator.hasNext(); offenseIterator.hasNext())
      {
        ArrayList<Long> offenseList = (ArrayList)localIterator.next();
        
        offenseIterator = offenseList.iterator();
        
        long time = ((Long)offenseIterator.next()).longValue();
        if (!UtilTime.elapsed(time, this.FlightTriggerCancel)) {
          offenseIterator.remove();
        }
      }
    }
	  } catch (Exception e1) {
	  }
	  
  }
  
  @EventHandler
  public void playerQuit(PlayerQuitEvent event)
  {
    ResetAll(event.getPlayer());
  }
  
  @EventHandler
  public void startIgnore(PlayerMoveEvent event)
  {
    Player player = event.getPlayer();
    synchronized (_antiHackLock)
    {
      if (this._velocityEvent.remove(player)) {
        setIgnore(player, 1500L);
      }
      if (this._lastMoveEvent.containsKey(player))
      {
        long timeBetweenPackets = System.currentTimeMillis() - ((Long)this._lastMoveEvent.get(player)).longValue();
        if (timeBetweenPackets > 1000L) {
          setIgnore(player, 1500L);
        }
      }
    }
  }
  
  public void setIgnore(Player player, long time)
  {
    for (Detector detector : this._detectors) {
      detector.Reset(player);
    }
    synchronized (_antiHackLock)
    {
      if ((this._ignore.containsKey(player)) && (((Long)this._ignore.get(player)).longValue() > System.currentTimeMillis() + time)) {
        return;
      }
      this._ignore.put(player, Long.valueOf(System.currentTimeMillis() + time));
    }
  }
  
  public boolean isValid(Player player, boolean groundValid)
  {
    if ((player.isFlying()) || (player.isInsideVehicle()) || (player.getGameMode() == GameMode.CREATIVE)) {
      return true;
    }
  
    if (groundValid) {
      if ((UtilEnt.onBlock(player)) || (player.getLocation().getBlock().getType() != Material.AIR)) {
        return true;
      }
    }
    if (AntiHack.igonorar.contains(player))
    	return true;
    return (this._ignore.containsKey(player)) && (System.currentTimeMillis() < ((Long)this._ignore.get(player)).longValue());
  }
  
  public void MSGaddSuspicion(Player player, String type)
  {
    Staff.MandarMSGBungee("§c§lAVISO [" + Bukkit.getServerName() + "] §7" + player.getName() + " suspeito de §9 " + type + ".");

    System.out.println(player.getName() + " suspeito de " + type + ".");
  }
  
  public void addSuspicion(Player player, String type)
  {
    synchronized (_antiHackLock)
    {
      if (!this._suspicion.containsKey(player)) {
        this._suspicion.put(player, new HashMap());
      }
      if (!((HashMap)this._suspicion.get(player)).containsKey(type)) {
        ((HashMap)this._suspicion.get(player)).put(type, new ArrayList());
      }
      ((ArrayList)((HashMap)this._suspicion.get(player)).get(type)).add(Long.valueOf(System.currentTimeMillis()));
    }
    Staff.MandarMSGBungee("§c§lAVISO [" + Bukkit.getServerName() + "] §7" + player.getName() + " suspeito de §9 " + type + ".");
      
    
    System.out.println(player.getName() + " suspeito de " + type + ".");
  }
  @EventHandler
  public void Utils(Atualizar event)  {
    if (event.getType() != ModosUpdate.SEC) {
      return;
    }
	  for (Player p : UtilServer.Jogadores())
	  {
		  Click.Click.remove(p);
			if (ToggleSneak.inv.contains(p))
			  addSuspicion(p, "Suspeito de ToggleSneak");
		  
		  
	  }
  }
  
 
  
  @EventHandler
  public void processOffenses(Atualizar event)
  {
    if (event.getType() != ModosUpdate.SEC) {
      return;
    }
    synchronized (_antiHackLock)
    {
      Iterator localIterator2;
      for (Iterator localIterator1 = this._suspicion.keySet().iterator(); localIterator1.hasNext(); localIterator2.hasNext())
      {
        Player player = (Player)localIterator1.next();
        if (!this._offense.containsKey(player)) {
          this._offense.put(player, new HashMap());
        }
        localIterator2 = ((HashMap)this._suspicion.get(player)).keySet().iterator();
        String type = (String)localIterator2.next();
        if (!((HashMap)this._offense.get(player)).containsKey(type)) {
          ((HashMap)this._offense.get(player)).put(type, new ArrayList());
        }
        Iterator<Long> offenseIterator = ((ArrayList)((HashMap)this._suspicion.get(player)).get(type)).iterator();
        while (offenseIterator.hasNext())
        {
          long time = ((Long)offenseIterator.next()).longValue();
          if (UtilTime.elapsed(time, this.FlightTriggerCancel))
          {
            offenseIterator.remove();
            ((ArrayList)((HashMap)this._offense.get(player)).get(type)).add(Long.valueOf(time));
          }
        }
      }
    }
  }
  
  @EventHandler
  public void generateReports(Atualizar event)
  {
    if (event.getType() != ModosUpdate.SLOW) {
      return;
    }
    synchronized (_antiHackLock)
    {
    	try {
      for (Player player : this._offense.keySet())
      {
        String out = "";
        int total = 0;
        for (String type : this._offense.get(player).keySet())
        {
          Iterator<Long> offenseIterator = ((ArrayList)((HashMap)this._suspicion.get(player)).get(type)).iterator();
          while (offenseIterator.hasNext())
          {
            long time = ((Long)offenseIterator.next()).longValue();
            if (UtilTime.elapsed(time, 300000L)) {
              offenseIterator.remove();
            }
          }
          int count = ((ArrayList)((HashMap)this._offense.get(player)).get(type)).size();
          total += count;
          
          out = out + count + " " + type + ", ";
        }
        if (out.length() > 0) {
          out = out.substring(0, out.length() - 2);
        }
        String severity = "";
        if (total > 6) {
          severity = "Extreme";
        } else if (total > 4) {
          severity = "High";
        } else if (total > 2) {
          severity = "Medium";
        } else {
          severity = "Low";
        }
        sendReport(player, out, severity);
      }
    
	  } catch (Exception e) {

	  }
    }
  }
  
  public void sendReport(Player player, String report, String severity)
  {
    if (severity.equals("Extreme"))
    {
      UtilPlayer.Kick(player, "Você esta muito suspeito de ser algum Cheat");
      

      
      ServerListPingEvent event = new ServerListPingEvent(null, Bukkit.getServer().getMotd(), Bukkit.getServer().getOnlinePlayers().size(), Bukkit.getServer().getMaxPlayers());
      GetPluginManager().callEvent(event);
      
      String motd = event.getMotd();
      String game = "N/A";
      String map = "N/A";
      
      String[] args = motd.split("\\|");
      if (args.length > 0) {
        motd = args[0];
      }
      if (args.length > 2) {
        game = args[2];
      }
      if (args.length > 3) {
        map = args[3];
      }

    }
  }
  
  private void ResetAll(Player player)
  {
    synchronized (_antiHackLock)
    {
      this._ignore.remove(player);
      this._velocityEvent.remove(player);
      this._lastMoveEvent.remove(player);
      
      this._offense.remove(player);
      this._suspicion.remove(player);
      for (Detector detector : this._detectors) {
        detector.Reset(player);
      }
    }
  }
  
  @EventHandler
  public void cleanupPlayers(Atualizar event)
  {
    if (event.getType() != ModosUpdate.SLOW) {
      return;
    }
    Iterator localIterator;
    label156:
    for (Iterator<Map.Entry<Player, Long>> playerIterator = this._ignore.entrySet().iterator(); playerIterator.hasNext(); localIterator.hasNext())
    {
      Player player = (Player)((Map.Entry)playerIterator.next()).getKey();
      if ((player.isOnline()) && (!player.isDead()) && (player.isValid())) {
        break label156;
      }
      playerIterator.remove();
      
      this._velocityEvent.remove(player);
      this._lastMoveEvent.remove(player);
      
      this._offense.remove(player);
      this._suspicion.remove(player);
      
      localIterator = this._detectors.iterator(); 
      Detector detector = (Detector)localIterator.next();
      detector.Reset(player);
    }
  }
  
  public HashMap<Player, HashMap<String, ArrayList<Long>>> getOffenses()
  {
    return this._offense;
  }
  
  private static Map<UUID, Long> lastAttacked = new HashMap();
  
  public static long getLastAttackTime(UUID uuid)
  {
    if (!lastAttacked.containsKey(uuid)) {
      lastAttacked.put(uuid, Long.valueOf(System.currentTimeMillis()));
    }
    return ((Long)lastAttacked.get(uuid)).longValue();
  }
  
  public static void setLastAttackTime(UUID uuid)
  {
    lastAttacked.put(uuid, Long.valueOf(System.currentTimeMillis()));
  }
}
