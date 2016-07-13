package me.acf.Magic_Chest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.hub.Main;
import me.hub.API.Util.Particles;
import me.hub.API.Util.UtilFirework;
import me.hub.API.Util.UtilParticles;
import me.hub.API.Util.UtilSound;
import me.hub.API.Util.Sound.Sounds;

public class MagicEvent {
	
	public static void Magic(final Player p,final Location loc)
	{
		
		   final ArmorStand magic = (ArmorStand)p.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
		   		    
		    magic.setHelmet(new ItemStack(Material.ENDER_CHEST));
		    magic.setGravity(false);
		    magic.setVisible(false);
		    
		    
		new BukkitRunnable()
	    {
	      int step = 0;
	      int step2 = 3;
	      
	      public void run()
	      {
	        if (!p.isOnline())
	        {
	      	magic.remove();
	          cancel();
	        }
	        this.step += 1;
	        this.step2 += 1;
	        if (this.step <= 48)
	        {
	          UtilParticles.display(255, 255, 255, magic.getEyeLocation());
	          UtilParticles.display(Particles.FIREWORKS_SPARK, magic.getEyeLocation(), 20);
	          UtilSound.playSound(magic.getEyeLocation(), Sounds.NOTE_PIANO, 0.5f, 2.0F);
	          magic.teleport(magic.getLocation().add(0, 0.1D, 0));
	        }
	        if (this.step == 50)
	        {
	         Particles.ENCHANTMENT_TABLE.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
	         magic.getWorld().playSound(magic.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 0.5F, 2.0F);
	          
	        }
	        if (this.step == 69)
	        {
	        	magic.setHelmet(null);
	        	Particles.EXPLOSION_NORMAL.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
	        	Particles.CRIT_MAGIC.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
	        	UtilSound.playSound(magic.getEyeLocation(), Sounds.CHICKEN_EGG_POP, 1.5F, 1.2F);
	        }
	        if (this.step == 70)
	        {
	        	UtilFirework.SimplesFirework(magic.getEyeLocation());
	        }
	      }
	        
	      }.runTaskTimer(Main.plugin, 0L, 1L);
	}
	    
	
	
	
	
}
