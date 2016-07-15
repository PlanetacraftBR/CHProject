/**
 * 
 */
package me.hub.API.Util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author adriancf
 *
 */
public class UtilEnchant {

	
	
	public static ItemStack addEnchant(ItemStack item, Enchantment enchant, int level){
		ItemMeta itemen = item.getItemMeta();
		itemen.addEnchant(enchant, level, true);
		item.setItemMeta(itemen);
        return item;
    }
}
