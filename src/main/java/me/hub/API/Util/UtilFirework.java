package me.hub.API.Util;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftFirework;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import me.hub.NMS.CustomEntityFirework_1_8_5_R03;

public class UtilFirework 
{
	public static void playFirework(Location loc, FireworkEffect fe) 
	{
		Firework firework = (Firework) loc.getWorld().spawn(loc, Firework.class);
		
		FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();
		data.clearEffects();
		data.setPower(1);
		data.addEffect(fe);
		firework.setFireworkMeta(data);

		((CraftFirework) firework).getHandle().expectedLifespan = 1;
//		((CraftWorld)loc.getWorld()).getHandle().broadcastEntityEffect(((CraftEntity)firework).getHandle(), (byte)17);
//		firework.remove();
	}

	public static Firework launchFirework(Location loc, FireworkEffect fe, Vector dir, int power) 
	{
		try
		{
			Firework fw = (Firework) loc.getWorld().spawn(loc, Firework.class);

			FireworkMeta data = (FireworkMeta) fw.getFireworkMeta();
			data.clearEffects();
			data.setPower(power);
			data.addEffect(fe);
			fw.setFireworkMeta(data);
			
			if (dir != null)
				fw.setVelocity(dir);
			
			return fw;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void detonateFirework(Firework firework)
	{
		((CraftWorld)firework.getWorld()).getHandle().broadcastEntityEffect(((CraftEntity)firework).getHandle(), (byte)17);

		firework.remove();
	}
	
	public static Firework launchFirework(Location loc, Type type, Color color, boolean flicker, boolean trail, Vector dir, int power) 
	{
		return launchFirework(loc, FireworkEffect.builder().flicker(flicker).withColor(color).with(type).trail(trail).build(), dir, power);
	}

	public static void playFirework(Location loc, Type type, Color color, boolean flicker, boolean trail)
	{
		playFirework(loc, FireworkEffect.builder().flicker(flicker).withColor(color).with(type).trail(trail).build());
	}
	
	public static void SimplesFirework(Location loc)
	{
		Type type = Type.BALL;
        Random r = new Random();  
         int r1i = r.nextInt(16) + 1;
         int r2i = r.nextInt(16) + 1;
         int fw = r.nextInt(23);
         Color c1 = getColor(r1i);
         Color c2 = getColor(r2i);
		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
		CustomEntityFirework_1_8_5_R03.spawn(loc.clone().add(+0.5,0,0.5), effect);
	}
	
	
	  public static Color getColor(int i) {
		  Color c = null;
		  if(i==1){
		  c=Color.AQUA;
		  }
		  if(i==2){
		  c=Color.BLACK;
		  }
		  if(i==3){
		  c=Color.BLUE;
		  }
		  if(i==4){
		  c=Color.FUCHSIA;
		  }
		  if(i==5){
		  c=Color.GRAY;
		  }
		  if(i==6){
		  c=Color.GREEN;
		  }
		  if(i==7){
		  c=Color.LIME;
		  }
		  if(i==8){
		  c=Color.MAROON;
		  }
		  if(i==9){
		  c=Color.NAVY;
		  }
		  if(i==10){
		  c=Color.OLIVE;
		  }
		  if(i==11){
		  c=Color.ORANGE;
		  }
		  if(i==12){
		  c=Color.PURPLE;
		  }
		  if(i==13){
		  c=Color.RED;
		  }
		  if(i==14){
		  c=Color.SILVER;
		  }
		  if(i==15){
		  c=Color.TEAL;
		  }
		  if(i==16){
		  c=Color.WHITE;
		  }
		  if(i==17){
		  c=Color.YELLOW;
		  }
		   
		  return c;
		  }
}
