package me.hub.recharge;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.API.Util.UtilGear;
import me.hub.API.Util.UtilTextBottom;
import me.hub.API.Util.UtilTime;
import me.hub.API.module.C;

import org.bukkit.entity.Player;

public class RechargeData
{
	public Recharge Host;

	public long Time;
	public long Recharge;

	public Player Player;
	public String Name;

	public ItemStack Item;
	
	public boolean DisplayForce = false;
	public boolean Countdown = false; 		
	public boolean AttachItem;
	public boolean AttachDurability;

	public RechargeData(Recharge host, Player player, String name, ItemStack stack, long rechargeTime, boolean attachitem, boolean attachDurability,boolean display_bar)
	{
		Host = host;

		Player = player;
		Name = name;
		Item = player.getItemInHand();
		Time = System.currentTimeMillis();
		Recharge = rechargeTime;
		
		AttachItem = attachitem;
		DisplayForce = display_bar;
		AttachDurability = attachDurability;
	}

	public boolean Update()
	{
		if ((DisplayForce || Item != null) && Name != null && Player != null)
		{
		
			double percent = (double)(System.currentTimeMillis() - Time)/(double)Recharge;
			
			if (DisplayForce || AttachItem)
			{
				try
				{
					if (DisplayForce || (Item != null && UtilGear.isMat(Player.getItemInHand(), Item.getType())))
					{
						if (!UtilTime.elapsed(Time, Recharge))
						{
							UtilTextBottom.displayProgress("§l" + Name, percent, UtilTime.MakeStr(Recharge - (System.currentTimeMillis() - Time)), Countdown, Player);
						}
						else
						{
							
							if (!Countdown)
								UtilTextBottom.display(C.cGreen + C.Bold + Name + " Recarregada", Player);
							else
								UtilTextBottom.display(C.cRed + C.Bold + Name + " Terminou", Player);
							
							//PLING!
							if (Recharge > 4000)
								Player.playSound(Player.getLocation(), Sound.BLOCK_NOTE_PLING, 0.4f, 3f);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if (AttachDurability && Item != null)
			{
				Item.setDurability((short) (Item.getType().getMaxDurability() - (Item.getType().getMaxDurability() * percent)));
			}
		}


		return UtilTime.elapsed(Time, Recharge);
	}

	public long GetRemaining()
	{
		return Recharge - (System.currentTimeMillis() - Time);
	}

	public void debug(Player player)
	{
		player.sendMessage("Recarregar: " + Recharge);
		player.sendMessage("Tempo: " + Time);
		player.sendMessage("Decorrido: " + (System.currentTimeMillis() - Time));
		player.sendMessage("Remanescente: " + GetRemaining());
	}
}
