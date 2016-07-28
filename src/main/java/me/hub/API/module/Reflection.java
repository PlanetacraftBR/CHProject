package me.hub.API.module;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_10_R1.EntityLiving;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.PacketPlayOutSpawnEntityLiving;

import org.inventivetalent.reflection.resolver.ClassResolver;
import org.inventivetalent.reflection.resolver.minecraft.NMSClassResolver;
import org.inventivetalent.reflection.util.AccessUtil;

public class Reflection {
	
	public static ClassResolver    classResolver    = new ClassResolver();
	public static NMSClassResolver nmsClassResolver = new NMSClassResolver();

	public static Class<?> PacketPlayOutPlayerListHeaderFooter = nmsClassResolver.resolveSilent("PacketPlayOutPlayerListHeaderFooter");

	public static Class<?> IChatBaseComponent = nmsClassResolver.resolveSilent("IChatBaseComponent");
	public static Class<?> ChatSerializer     = nmsClassResolver.resolveSilent("ChatSerializer", "IChatBaseComponent$ChatSerializer");

	public static    Class<?> PacketPlayOutPlayerInfo = nmsClassResolver.resolveSilent("PacketPlayOutPlayerInfo");
	public static    Class<?> PlayerInfoData          = nmsClassResolver.resolveSilent("PacketPlayOutPlayerInfo$PlayerInfoData");
	public static Class<?> EnumPlayerInfoAction    = nmsClassResolver.resolveSilent("EnumPlayerInfoAction", "PacketPlayOutPlayerInfo$EnumPlayerInfoAction");

	public static Class<?> EnumGamemode    = nmsClassResolver.resolveSilent("EnumGamemode", "WorldSettings$EnumGamemode");
	public static Class<?> TileEntitySkull = nmsClassResolver.resolveSilent("TileEntitySkull");

	public static Class<?> GameProfile  = classResolver.resolveSilent("net.minecraft.util.com.mojang.authlib.GameProfile", "com.mojang.authlib.GameProfile");
	public static Class<?> LoadingCache = classResolver.resolveSilent("net.minecraft.util.com.google.common.cache.LoadingCache", "com.google.common.cache.LoadingCache");

	public static String getVersion() {
		final String name = Bukkit.getServer().getClass().getPackage().getName();
		final String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}

	public static Class<?> getNMSClass(String className) {
		final String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	
	public static void sendPacket(Player player, EntityLiving entity)
	{
		try {
		   EntityPlayer playerconnect = ((CraftPlayer) player).getHandle();
		   PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(entity);
		   playerconnect.playerConnection.sendPacket(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Object serializeChat(String msg) {
		try {
			return ChatSerializer.getDeclaredMethod("a", String.class).invoke(null, msg);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendPacket(Player p, Object packet)  {
		try {
		if (p == null || packet == null) return;
		Object handle = Reflection.getHandle(p);
		Object connection = AccessUtil.setAccessible(handle.getClass().getDeclaredField("playerConnection")).get(handle);
		Reflection.getMethod(connection.getClass(), "sendPacket", new Class[0]).invoke(connection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public static Class<?> getOBCClass(String className) {
		final String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static Object getHandle(Object obj) {
		try {
			return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getField(Class<?> clazz, String name) {
		try {
			final Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method getMethod(Class<?> clazz, String name, Class<?>... args) {
		for (final Method m : clazz.getMethods()) {
			if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
				m.setAccessible(true);
				return m;
			}
		}
		return null;
	}

	public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length) return false;
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}
