package me.acf.Magic_Chest;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.acf.Magic_Chest.modulo.Modulos;
import me.hub.Main;
import me.hub.API.Util.Particles;
import me.hub.API.Util.UtilFirework;
import me.hub.API.Util.UtilHolo;
import me.hub.API.Util.UtilParticles;
import me.hub.API.Util.UtilSound;
import me.hub.API.Util.Sound.Sounds;

public class MagicEvent {
	
	public static ArrayList<Entity> magics = new ArrayList<>();
	
	
	public static void Magic(final Player p,final Location loc)
	{
		
		final ArmorStand magic = (ArmorStand)p.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
		   		    
		magic.setHelmet(new ItemStack(Material.ENDER_CHEST));
 	    magic.setGravity(false);
        magic.setVisible(false);
		magics.add(magic);
		    
		new BukkitRunnable()
	    {
	      int step = 0;
	      int step2 = 3;
	      Modulos mod = null;
	    	
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
	        if (this.step == 74)
	        {
	        	magic.setHelmet(null);
	        	Particles.EXPLOSION_NORMAL.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
	        	Particles.CRIT_MAGIC.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
	        	UtilSound.playSound(magic.getEyeLocation(), Sounds.CHICKEN_EGG_POP, 1.5F, 1.2F);
	        }
	        if (this.step == 76)
	        {
	        	UtilFirework.SimplesFirework(loc.clone().add(0.5,-0.5,0.5));
	        } 
	        if (this.step == 78)
            {
	         	mod = new Modulos("§a§l" + p.getName() + "§f§l abriu o §b§lMAGIC CHEST",loc.clone().add(0,0,0));
	        }
	        if (this.step == 82)
	        {
	        	Particles.VILLAGER_HAPPY.display(0.0F, 0.0F, 0.0F, 1.0F, 150, loc.add(0.0D, 1.0D, 0.0D), 20);
	        	UtilSound.playSound(magic.getEyeLocation(), Sounds.LEVEL_UP, 0.5F, 0.2F);
	        	magic.remove();
	        	mod.Remove();
	        	cancel();
	        }
	      }
	        
	      }.runTaskTimer(Main.plugin, 0L, 1L);
	}
	    
	
	
	
	
}
