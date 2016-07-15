package me.hub.NMS;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;

public class NMSUtils
{
  private static String version;
  
  public static String getVersion()
  {
    if (version != null) {
      return version;
    }
    String name = Bukkit.getServer().getClass().getPackage().getName();
    String version = name.substring(name.lastIndexOf('.') + 1) + ".";
    return version;
  }
  
  public static Object getHandle(Object o)
  {
    try
    {
      try {
		return getMethod(o.getClass(), "getHandle", new Class[0]).invoke(o, new Object[0]);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    catch (IllegalAccessException e) {}
    return null;
  }
  
  public static Method getMethod(Class<?> clazz, String name, Class<?>... args)
  {
    Method[] arrayOfMethod;
    int j = (arrayOfMethod = clazz.getMethods()).length;
    for (int i = 0; i < j; i++)
    {
      Method m = arrayOfMethod[i];
      if ((m.getName().equals(name)) && ((args.length == 0) || (ClassListEqual(args, m.getParameterTypes()))))
      {
        m.setAccessible(true);
        return m;
      }
    }
    j = (arrayOfMethod = clazz.getDeclaredMethods()).length;
    for (int i = 0; i < j; i++)
    {
      Method m = arrayOfMethod[i];
      if ((m.getName().equals(name)) && ((args.length == 0) || (ClassListEqual(args, m.getParameterTypes()))))
      {
        m.setAccessible(true);
        return m;
      }
    }
    return null;
  }
  
  public static Field getField(Class<?> clazz, String name)
  {
    Field[] arrayOfField;
    int j = (arrayOfField = clazz.getFields()).length;
    for (int i = 0; i < j; i++)
    {
      Field f = arrayOfField[i];
      if (f.getName().equalsIgnoreCase(name))
      {
        f.setAccessible(true);
        return f;
      }
    }
    j = (arrayOfField = clazz.getDeclaredFields()).length;
   
    for (int i = 0; i < j; i++)
    {
      Field f = arrayOfField[i];
      if (f.getName().equalsIgnoreCase(name))
      {
        f.setAccessible(true);
        return f;
      }
    }
    try
    {
      throw new Exception("No such field > " + name + " in class " + clazz.getSimpleName());
    }
    catch (Exception e) {}
    return null;
  }
  
  public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2)
  {
    boolean equal = true;
    if (l1.length != l2.length) {
      return false;
    }
    for (int i = 0; i < l1.length; i++) {
      if (l1[i] != l2[i])
      {
        equal = false;
        break;
      }
    }
    return equal;
  }
  
  public static void setField(Object instance, Field f, Object value)
  {
    try
    {
      f.set(instance, value);
    }
    catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public static Object invokeMethod(Object instance, Method m, Object... args)
  {
    try
    {
      try {
		return m.invoke(instance, args);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    catch (IllegalAccessException e) {}
    return null;
  }
  
  public static Object invokeMethod(Object instance, Method m)
  {
    try
    {
      try {
		return m.invoke(instance, new Object[0]);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    catch (IllegalAccessException e) {}
    return null;
  }
  
  public static Class<?> getNMSClass(String name)
  {
    try
    {
      return getClassWithException(name);
    }
    catch (ClassNotFoundException e)
    {
      try
      {
        return getCraftClassWithException(name);
      }
      catch (ClassNotFoundException e1)
      {
        e1.printStackTrace();
      }
    }
    return null;
  }
  
  private static Class<?> getClassWithException(String name)
    throws ClassNotFoundException
  {
    String classname = "net.minecraft.server." + getVersion() + name;
    return Class.forName(classname);
  }
  
  private static Class<?> getCraftClassWithException(String name)
    throws ClassNotFoundException
  {
    String classname = "org.bukkit.craftbukkit." + getVersion() + name;
    return Class.forName(classname);
  }
  
  public static Class<?> getMojangClass(String name)
  {
    try
    {
      String classname = "com.mojang.authlib." + name;
      return Class.forName(classname);
    }
    catch (ClassNotFoundException e)
    {
      String classname = "net.minecraft.util.com.mojang.authlib." + name;
      try
      {
        return Class.forName(classname);
      }
      catch (ClassNotFoundException e1)
      {
        e1.printStackTrace();
        
        e.printStackTrace();
      }
    }
    return null;
  }
}
