package me.hub.EnchantTable;

import java.util.ArrayList;

import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.plugin.java.JavaPlugin;

import me.hub.MiniPlugin;

public class Enchant extends MiniPlugin {

	
	public Enchant(JavaPlugin plugin) {
		super("EnchantTable", plugin);
		
	}
	ArrayList<ItemStack> list = new ArrayList<ItemStack>();
	

	@EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory() instanceof EnchantingInventory) {
            EnchantingInventory inv = (EnchantingInventory) e.getInventory();
            Dye d = new Dye();
            d.setColor(DyeColor.BLUE);
            ItemStack i = d.toItemStack();
            i.getItemMeta().setDisplayName("§a§lEnchantTable - Item");
            i.setAmount(64);
            list.add(i);
            inv.setItem(1, i);

        }
    }

	@EventHandler
	public void PlayerIntaract(InventoryClickEvent e)
	{
		if (list.contains(e.getCurrentItem()))
		{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void hopper(InventoryMoveItemEvent e)
	{
		if (list.contains(e.getItem()))
		{
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void Player(ItemSpawnEvent e)
	{
		if (list.contains(e.getEntity().getItemStack()))
		{
			e.setCancelled(true);
			e.getEntity().remove();
		}
	}
	
	@EventHandler
	public void Drop(PlayerPickupItemEvent e)
	{
		if (list.contains(e.getItem().getItemStack()))
		{
			e.setCancelled(true);
			e.getItem().remove();
		}
	}

	
}
