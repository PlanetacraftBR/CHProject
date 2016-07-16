/**
 * 
 */
package me.hub.API.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.hub.API.Enchant;
import me.site.account.Account;

/**
 * @author adriancf
 *
 */
public class UtilInv {

    public static void save(Player p) {
        
    	  YamlConfiguration cfg = null;
		  File file = null;
		  file = new File("plugins/CHub/UserData/" + Account.getUuidOff(p.getName()).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  
          cfg.set("inventory.armor", p.getInventory().getArmorContents());
          cfg.set("inventory.content", p.getInventory().getContents());
          
          cfg.set("enderchest.content", p.getEnderChest().getContents());
          try {
			cfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void restore_armor(Player p)
    {
    	 YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/CHub/UserData/" + Account.getUuid(p).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml"));
         ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
         p.getInventory().setArmorContents(content);
    }
    
    @SuppressWarnings("unchecked")
    public static void restore(Player p)  {
    	try {
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/CHub/UserData/" + Account.getUuid(p).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml"));
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
        content = ((List<ItemStack>) c.get("enderchest.content")).toArray(new ItemStack[0]);
        p.getEnderChest().setContents(content);
    	}
    	
    	catch (Exception e) {
    		System.out.print("Nada encontrado no inventario do " + p.getName());
    	}
    }
    
    
    public static void InvErro(Player p, String erro,String modulo)
    {
    	 Inventory inv = Bukkit.getServer().createInventory(p, 54, "§4§l>> " + modulo + "ERROR LOCATION <<.");
    	 for (int slot_Inicial = 0; slot_Inicial < 54; slot_Inicial++)
	        {
    		 AddItem(inv, false, Material.INK_SACK,1,(byte) 8, erro, "Para mais informações consulte o log!", slot_Inicial);
	        }
    	 p.openInventory(inv);
    }
    
    //Daqui para baixo é a api de item
    
    public static void AddItem(Inventory inv, boolean encantado, Material item,int contidade, byte data,String nome, String list, int slot)
    {
    	      ItemStack bau = new ItemStack(item, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = new ArrayList();
    	      desce.add(list);
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    public static void AddItemString(Inventory inv, boolean encantado, Material enderChest,int contidade, byte data,String nome, ArrayList<String> meta, int slot)
    {
    	      ItemStack bau = new ItemStack(enderChest, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = meta;
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    public static void AddItem(Inventory inv, boolean encantado, int item,int contidade, byte data,String nome, String meta, int slot)
    {
    	      ItemStack bau = new ItemStack(item, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = new ArrayList();
    	      desce.add(meta);
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    public static ItemStack AddItemReturn(int mushroomSoup,int contidade, byte i,String nome, String meta)
    {
    	      ItemStack bau = new ItemStack(mushroomSoup, contidade , i);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = new ArrayList();
    	      desce.add(meta);
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      return bau;
    }
    public static ItemStack AddItemReturn(Material mushroomSoup,int contidade, int i,String nome, String meta)
    {
    	      ItemStack bau = new ItemStack(mushroomSoup, contidade , (byte)i);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = new ArrayList();
    	      desce.add(meta);
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      return bau;
    }
    
    public static ItemStack AddItemReturn(Material mushroomSoup,int contidade, byte i,String nome, String meta)
    {
    	      ItemStack bau = new ItemStack(mushroomSoup, contidade , i);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      ArrayList<String> desce = new ArrayList();
    	      desce.add(meta);
    	      metae.setLore(desce);
    	      bau.setItemMeta(metae);
    	      return bau;
    }

    public static void AddItem(Inventory inv, boolean encantado, Material item,int contidade, byte data,String nome, int slot)
    {
    	      ItemStack bau = new ItemStack(item, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
  
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    public static void AddItem(Inventory inv, boolean encantado, int item,int contidade, byte data,String nome, int slot)
    {
    	      ItemStack bau = new ItemStack(item, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    
    public static void repairAll(Player player) {
        for (ItemStack is : player.getInventory().getContents()) {
            try
            {
            	if (Material_Quebravel(is))
              is.setDurability((short)0);
            }
            catch (NullPointerException localNullPointerException) {}
          }
          for (ItemStack is : player.getEquipment().getArmorContents()) {
            try
            {
              is.setDurability((short)0);
            }
            catch (NullPointerException localNullPointerException1) {}
          }
    	}
    	
    
    public static byte GetData(ItemStack stack)
	{
		if (stack == null)
			return (byte)0;
		
		if (stack.getData() == null)
			return (byte)0;
		
		return stack.getData().getData();
}
    
    public static boolean IsItem(ItemStack item, Material type, byte data)
	{
		return IsItem(item, null, type.getId(), data);
	}
	
	public static boolean IsItem(ItemStack item, String name, Material type, byte data)
	{
		return IsItem(item, name, type.getId(), data);
	}
	
	public static boolean IsItem(ItemStack item, String name, int id, byte data)
	{
		if (item == null)
			return false;
		
		if (item.getTypeId() != id)
			return false;
		
		if (data != -1 && GetData(item) != data)
			return false;
		
		if (name != null && (item.getItemMeta().getDisplayName() == null || !item.getItemMeta().getDisplayName().contains(name)))
			return false;
		
		return true;
}
	
    public static boolean Material_Quebravel(ItemStack Item)
    {
       if ((Item.getType() == Material.WOOD_SWORD) ||
    		   (Item.getType() == Material.STONE_SWORD) ||
    		   (Item.getType() == Material.GOLD_SWORD) ||
    		   (Item.getType() == Material.IRON_SWORD) ||
    		   (Item.getType() == Material.STONE_SWORD) ||
    		   (Item.getType() == Material.DIAMOND_SWORD) ||
    		   (Item.getType() == Material.WOOD_AXE) ||
    		   (Item.getType() == Material.STONE_AXE) ||
    		   (Item.getType() == Material.GOLD_AXE) ||
    		   (Item.getType() == Material.IRON_AXE) ||
    		   (Item.getType() == Material.DIAMOND_AXE) ||
    		   (Item.getType() == Material.WOOD_HOE) ||
    		   (Item.getType() == Material.STONE_HOE) ||
    		   (Item.getType() == Material.GOLD_HOE) ||
    		   (Item.getType() == Material.IRON_HOE) ||
    		   (Item.getType() == Material.WOOD_PICKAXE) ||
    		   (Item.getType() == Material.STONE_PICKAXE) ||
    		   (Item.getType() == Material.GOLD_PICKAXE) ||
    		   (Item.getType() == Material.IRON_PICKAXE) ||
    		   (Item.getType() == Material.DIAMOND_PICKAXE) ||
    		   (Item.getType() == Material.BOW) ||
    		   (Item.getType() == Material.FISHING_ROD) ||
    		   (Item.getType() == Material.FLINT_AND_STEEL) ||
    		   (Item.getType() == Material.CHAINMAIL_BOOTS) ||
    		   (Item.getType() == Material.CHAINMAIL_CHESTPLATE) ||
    		   (Item.getType() == Material.CHAINMAIL_HELMET) ||
    		   (Item.getType() == Material.CHAINMAIL_LEGGINGS) ||
    		   (Item.getType() == Material.LEATHER_BOOTS) ||
    		   (Item.getType() == Material.LEATHER_CHESTPLATE) ||
    		   (Item.getType() == Material.LEATHER_HELMET) ||
    		   (Item.getType() == Material.LEATHER_LEGGINGS) ||
    		   (Item.getType() == Material.GOLD_BOOTS) ||
    		   (Item.getType() == Material.GOLD_CHESTPLATE) ||
    		   (Item.getType() == Material.GOLD_HELMET) ||
    		   (Item.getType() == Material.GOLD_LEGGINGS) ||
    		   (Item.getType() == Material.IRON_BOOTS) ||
    		   (Item.getType() == Material.IRON_CHESTPLATE) ||
    		   (Item.getType() == Material.IRON_HELMET) ||
    		   (Item.getType() == Material.IRON_LEGGINGS) ||
    		   (Item.getType() == Material.DIAMOND_BOOTS) ||
    		   (Item.getType() == Material.DIAMOND_CHESTPLATE) ||
    		   (Item.getType() == Material.DIAMOND_HELMET) ||
    		   (Item.getType() == Material.DIAMOND_LEGGINGS))
    	   return true;
    	
    	return false;
    }
}
