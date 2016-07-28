package me.hub.NMS.BossBar.wither;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject;


public class FakeWither {

	public static void init() {
	}

	private static int idCount = 125226126;

	private final int id;

	private float health = 1;
	private String name;

	public FakeWither() {
		id = idCount++;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("deprecation")
	public PacketContainer getSpawnPacket() {
		PacketContainer spawn = Util.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
		spawn.getIntegers().write(0, id);
		spawn.getIntegers().write(1, (int) EntityType.WITHER.getTypeId());
		spawn.getDataWatcherModifier().write(0, getWatcher());
		return spawn;
	}

	public PacketContainer getDestroyPacket() {
		PacketContainer destroy = Util.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
		destroy.getIntegerArrays().write(0, new int[] { id });
		return destroy;
	}

	public PacketContainer getTeleportPacket(Location loc) {
		PacketContainer teleport = Util.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
		teleport.getIntegers().write(0, id);
		StructureModifier<Double> doubles = teleport.getDoubles();
		doubles.write(0, loc.getX());
		doubles.write(1, loc.getY());
		doubles.write(2, loc.getZ());
		return teleport;
	}

	private static final Serializer byteSerializer = Registry.get(Byte.class, false);
	private static final Serializer stringSerializer = Registry.get(String.class, false);
	private static final Serializer booleanSerializer = Registry.get(Boolean.class, false);
	private static final Serializer intSeriaizer = Registry.get(Integer.class, false);
	private static final Serializer floatSerializer = Registry.get(Float.class, false);

	private static final Integer invConst = Integer.valueOf(881);

	private WrappedDataWatcher getWatcher() {
		WrappedDataWatcher watcher = new WrappedDataWatcher();
		watcher.setObject(new WrappedDataWatcherObject(0, byteSerializer), Byte.valueOf((byte) 0x20));
		watcher.setObject(new WrappedDataWatcherObject(2, stringSerializer), name);
		watcher.setObject(new WrappedDataWatcherObject(3, booleanSerializer), Boolean.TRUE);
		watcher.setObject(new WrappedDataWatcherObject(6, floatSerializer), Float.valueOf(health));
		watcher.setObject(new WrappedDataWatcherObject(14, intSeriaizer), invConst);
		return watcher;
	}

}
