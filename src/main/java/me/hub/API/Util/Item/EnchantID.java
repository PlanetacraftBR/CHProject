/**
 * 
 */
package me.hub.API.Util.Item;

import org.bukkit.inventory.ItemStack;

import me.hub.API.Enchant;

/**
 * @author adriancf
 *
 */
public class EnchantID {

	
	public static ItemStack GiveID(String id)
	{
	ItemStack item = null;

	if (id.contains(":")) {
        String[] it = id.split(":");
    	item = new ItemStack(Integer.parseInt(it[0]),1, (byte) Integer.parseInt(it[1]));
	}
	if (id.contains("#"))
	{
		String[] it = id.split("#");
		String[] va = id.split(",");
		item.addEnchantment(Enchant.getById(Integer.parseInt(it[1])), Integer.parseInt(va[1]));
	}
	
	return item;
	}
}
