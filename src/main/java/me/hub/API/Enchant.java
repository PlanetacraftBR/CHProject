package me.hub.API;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
public class Enchant extends EnchantmentWrapper
{
 
    private static Enchantment glow;
 
    public Enchant( int id )
    {
        super(id);
    }
 
    @Override
    public boolean canEnchantItem(ItemStack item)
    {
        return true;
    }
 
    @Override
    public boolean conflictsWith(Enchantment other)
    {
        return false;
    }
 
    @Override
    public EnchantmentTarget getItemTarget()
    {
        return null;
    }
 
    @Override
    public int getMaxLevel()
    {
        return 10;
    }
 
    @Override
    public String getName()
    {
        return "Glow";
    }
 
    @Override
    public int getStartLevel()
    {
        return 1;
    }
    
    
 
    public static Enchantment getGlow()
    {
        if ( glow != null )
            return glow;
     
        try
        {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null , true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
     
        glow = new Enchant(255);
        Enchantment.registerEnchantment(glow);
        return glow;
    }
 
    public static ItemStack addGlow(ItemStack item){ 
    	  net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
    	  NBTTagCompound tag = null;
    	  if (!nmsStack.hasTag()) {
    	      tag = new NBTTagCompound();
    	      nmsStack.setTag(tag);
    	  }
    	  if (tag == null) tag = nmsStack.getTag();
    	  NBTTagList ench = new NBTTagList();
    	  tag.set("ench", ench);
    	  nmsStack.setTag(tag);
    	  return CraftItemStack.asCraftMirror(nmsStack);
    	}
}