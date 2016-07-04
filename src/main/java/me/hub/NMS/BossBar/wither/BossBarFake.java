package me.hub.NMS.BossBar.wither;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;

import me.hub.Main;
import me.hub.NMS.BossBar.wither.FakeWither;

public class BossBarFake implements Listener {  

	private final static HashMap<Player, FakeWither> players = new HashMap<Player, FakeWither>();
    private static Main plugin;

	public BossBarFake(Main plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				for (Entry<Player, FakeWither> entry : players.entrySet()) {
					Util.sendPacket(entry.getKey(), entry.getValue().getTeleportPacket(getWitherLocation(entry.getKey().getLocation())));
				}
			}
		}, 0L, 5L);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent event) {
		removeBar(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onTeleport(PlayerChangedWorldEvent event) {
		final Player player = event.getPlayer();
		if (!hasBar(player)) {
			return;
		}
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			@Override
			public void run() {
				if (!hasBar(player)) {
					return;
				}
				FakeWither oldWither = getOrCreateWither(player);
				float health = oldWither.getHealth();
				String message = oldWither.getName();
				players.remove(player);
				FakeWither newWither = getOrCreateWither(player);
				newWither.setName(message);
				newWither.setHealth(health);
				spawnWither(newWither, player);
			}
		}, 1L);
	}

	public static boolean hasBar(Player player) {
		return players.containsKey(player);
	}

	public static void removeBar(Player player) {
		if (!hasBar(player)) {
			return;
		}
		Util.sendPacket(player, getOrCreateWither(player).getDestroyPacket());
		players.remove(player);
	}

	public static void setName(Player player, String message, float percent) {
		FakeWither dragon = getOrCreateWither(player);
		dragon.setName(message);
		dragon.setHealth(percent * 3F);
		spawnWither(dragon, player);
	}

	public float getHealth(Player player) {
		if (!hasBar(player)) {
			return -1;
		}
		return getOrCreateWither(player).getHealth();
	}

	public String getMessage(Player player) {
		if (!hasBar(player)) {
			return "";
		}
		return getOrCreateWither(player).getName();
	}

	private static void spawnWither(FakeWither dragon, Player player) {
		Util.sendPacket(player, dragon.getSpawnPacket());
	}

	private static FakeWither getOrCreateWither(Player player) {
		FakeWither dragon = players.get(player);
		if (dragon != null) {
			return dragon;
		} else {
			dragon = new FakeWither();
			players.put(player, dragon);
			return dragon;
		}
	}

	private static Location getWitherLocation(Location loc) {
		return loc.clone().add(loc.getDirection().normalize().multiply(48));
	}

}
