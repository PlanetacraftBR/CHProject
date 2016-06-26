package me.hub.API.Util.Compass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;
import me.hub.API.Util.UtilGear;
import me.hub.API.Util.UtilInv;
import me.hub.API.Util.UtilMath;
import me.hub.API.Util.UtilServer;
import me.hub.API.module.C;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;


public class Bussola extends MiniPlugin
{


  public Bussola(JavaPlugin plugin)
  {
    super("Compass Addon", plugin);

  }

  public static ArrayList<Player> ignorar = new ArrayList<Player>();
  
  
  @EventHandler
  public void Update(Atualizar event) {
    if (event.getType() != ModosUpdate.SEC) {
      return;
    }

    for (Player player : UtilServer.Jogadores())
    {


      Player target = null;
      double bestDist = 0.0D;

      for (Player other : Bukkit.getOnlinePlayers())
      {
        if (other.equals(player)) {
          continue;
        }
        if (!ignorar.contains(other))
      	  return;
        if (!other.getLocation().getWorld().getName().contains(player.getWorld().getName()))
     	   return;
        
        double dist = player.getLocation().distance(other.getLocation());

        target = other;
        bestDist = dist;
      }

      if (target == null)
        continue;
     
      if (!player.getInventory().contains(Material.COMPASS)) {
        return;
      }
      player.setCompassTarget(target.getLocation());

        ItemStack stack = new ItemStack(Material.COMPASS);

        double heightDiff = target.getLocation().getY() - player.getLocation().getY();

        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(
          "    " + C.cWhite + C.Bold + "Jogador:§e "+ target.getName() + 
          "    " + C.cWhite + C.Bold + "Distancia:§e " + UtilMath.format_Double(bestDist) + 
          "    " + C.cWhite + C.Bold + "Altura:§e "  + UtilMath.format_Double(heightDiff));
        stack.setItemMeta(itemMeta);
        player.getInventory().remove(Material.COMPASS);
        player.getInventory().addItem(stack);
      }
    }
  

  @EventHandler
  public void DropItem(PlayerDropItemEvent event)
  {
    if (UtilInv.IsItem(event.getItemDrop().getItemStack(), Material.COMPASS, (byte) 0)) {
        event.setCancelled(true);
    }
  }

  @EventHandler
  public void DeathRemove(PlayerDeathEvent event) {
  
    HashSet remove = new HashSet();

    for (ItemStack item : event.getDrops()) {
      if (UtilInv.IsItem(item, Material.COMPASS, (byte) 0))
        remove.add(item);
    }

      event.getDrops().remove(Material.COMPASS);
    
  }

  @EventHandler
  public void InventoryClick(InventoryClickEvent event)
  {
  }

  @EventHandler
  public void PlayerInteract(PlayerInteractEvent event) {
 
    Player player = event.getPlayer();

    if (!UtilGear.isMat(player.getItemInHand(), Material.COMPASS)) {
      return;
    }


    Player target = null;
    double bestDist = 0.0D;



    if (target != null)
    {
      player.teleport(target.getLocation().add(0.0D, 1.0D, 0.0D));
    }
  }
}