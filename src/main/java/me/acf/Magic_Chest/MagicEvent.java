package me.acf.Magic_Chest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.hub.Main;
import me.hub.effect.ParticleEffect;

public class MagicEvent {
	
	public static void Magic(final Player p,Location loc)
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
	        if (this.step <= 51)
	        {
	          ParticleEffect.VILLAGER_HAPPY.display(0.0F, 0.0F, 0.0F, 0.0F, 1, magic.getEyeLocation(), 20);
	          ParticleEffect.REDSTONE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, magic.getEyeLocation(), 20);
	          magic.getWorld().playSound(magic.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 0.5F, 2.0F);
	          magic.teleport(magic.getLocation().add(0, 0.1D, 0));
	        }
	        else if (this.step == 53)
	        {
	          ParticleEffect.ENCHANTMENT_TABLE.display(0.0F, 0.0F, 0.0F, 1.0F, 150, magic.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);

	          
	        }
	      }
	        
	      }.runTaskTimer(Main.plugin, 0L, 1L);
	}
	    
	
	
	
	
}
