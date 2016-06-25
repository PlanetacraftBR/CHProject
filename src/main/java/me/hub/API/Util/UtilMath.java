package me.hub.API.Util;

import java.text.DecimalFormat;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.hub.Main;

public class UtilMath
{
  public static double trim(int degree, double d)
  {
    String format = "#.#";
    for (int i = 1; i < degree; i++) {
      format = format + "#";
    }
    DecimalFormat twoDForm = new DecimalFormat(format);
    return Double.valueOf(twoDForm.format(d)).doubleValue();
  }
  
  public static double format_Double(double d)
  {
	  double format = d;
	  format = Double.parseDouble(Double.toString(format).substring(0, Double.toString(format).indexOf('.') + 2));
	  return format;
	  
  }
  
  private static final int BIG_ENOUGH_INT = 16384;
  private static final double BIG_ENOUGH_FLOOR = 16384.0D;
  private static final double CEIL = 0.9999999D;
  private static final double BIG_ENOUGH_CEIL = 16384.999999999996D;
  private static final double BIG_ENOUGH_ROUND = 16384.5D;
  
  public static final int random(int range)
  {
    return random.nextInt(range + 1);
  }
  
  public static final int random(int start, int end)
  {
    return start + random.nextInt(end - start + 1);
  }
  
  public static final boolean randomBoolean()
  {
    return random.nextBoolean();
  }
  
  public static final boolean randomBoolean(float chance)
  {
    return random() < chance;
  }
  
  public static final float random()
  {
    return random.nextFloat();
  }
  
  public static final float random(float range)
  {
    return random.nextFloat() * range;
  }
  
  public static final float random(float start, float end)
  {
    return start + random.nextFloat() * (end - start);
  }
  
  public static Random random = new Random();
  
  public static int r(int i)
  {
    return random.nextInt(i);
  }
  
  public static double offset2d(Entity a, Entity b)
  {
    return offset2d(a.getLocation().toVector(), b.getLocation().toVector());
  }
  
  public static double offset2d(Location a, Location b)
  {
    return offset2d(a.toVector(), b.toVector());
  }
  
  public static double offset2d(Vector a, Vector b)
  {
    a.setY(0);
    b.setY(0);
    return a.subtract(b).length();
  }
  
  public static double offset(Entity a, Entity b)
  {
    return offset(a.getLocation().toVector(), b.getLocation().toVector());
  }
  
  public static double offset(Location a, Location b)
  {
    return offset(a.toVector(), b.toVector());
  }
  
  public static double offset(Vector a, Vector b)
  {
    return a.subtract(b).length();
  }
  public static double randomDouble(double min, double max) {
      return Math.random() < 0.5 ? ((1 - Math.random()) * (max - min) + min) : (Math.random() * (max - min) + min);
  }


}
