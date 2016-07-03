package me.hub.recharge;

import java.io.PrintStream;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.hub.API.Util.UtilTime;




public class RechargeData
{
  public Recharge Host;
  public long Time;
  public long Recharge;
  public Player Player;
  public String Name;
  public ItemStack Item;
  
  public RechargeData(long time)
  {
    this.Time = time;
  }
  
  public RechargeData(Recharge host, Player player, String name, ItemStack stack, long rechargeTime)
  {
    this.Host = host;
    
    this.Player = player;
    this.Name = name;
    this.Item = player.getItemInHand();
    this.Time = System.currentTimeMillis();
    this.Recharge = rechargeTime;
  }
  
  public boolean Update()
  {
    if ((this.Item != null) && (this.Name != null) && (this.Player != null))
    {
      try
      {
        if (this.Player.getItemInHand().getType() == this.Item.getType())
        {
          if (!UtilTime.elapsed(this.Time, this.Recharge))
          {


            double percent = (System.currentTimeMillis() - this.Time) / this.Recharge;
            
          
          }
          else
          {


            if (this.Recharge > 4000L) {
              this.Player.playSound(this.Player.getLocation(), Sound.BLOCK_NOTE_PLING, 0.4F, 3.0F);
            }
          }
        }
      }
      catch (Exception e) {
        System.out.println("Recharge Erro!");
      }
    }
    



















    return UtilTime.elapsed(this.Time, this.Recharge);
  }
  
  public long GetRemaining()
  {
    return this.Recharge - (System.currentTimeMillis() - this.Time);
  }
}
