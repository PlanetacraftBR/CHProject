/**
 * 
 */
package me.hub.API.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import me.hub.Main;
import net.citizensnpcs.api.jnbt.ByteArrayTag;
import net.citizensnpcs.api.jnbt.CompoundTag;
import net.citizensnpcs.api.jnbt.NBTInputStream;
import net.citizensnpcs.api.jnbt.ShortTag;
import net.citizensnpcs.api.jnbt.StringTag;
import net.citizensnpcs.api.jnbt.Tag;


/**
 * @author adriancf
 *
 */
public class UtilSchematic {
	
	 public static void fillChest(Chest chest, List<ItemStack> collection)
	 {
	  for (ItemStack i : collection) {
	      chest.getInventory().addItem(new ItemStack[] { i });
	   }
	    }
	 
	 public static List<ItemStack> getRandom(List<String> lista)
	 {
	     Random r = new Random();
	   if (lista == null) {
	      return null;
	    }
	   List<ItemStack> items = new ArrayList();
	    for (String str : lista)
	      {
	      String[] args = str.split(" |:");
	        
	    ItemStack item = new ItemStack(Integer.parseInt(args[0]), 
	      new Random().nextInt(Integer.parseInt(args[2]) + Integer.parseInt(args[3])), 
	     Byte.parseByte(args[1]));

	     int chance = r.nextInt(101);
	    if (chance <= Integer.parseInt(args[4])) {
	    items.add(item);
    	if (item.getAmount() <= 0){
    		item.setAmount(1);
    	}
	   }
	   }
	   return items;
	    }
	 
	 
	 public enum Modo {
		 Bau,
		 SetSpawn;
	 }
	 
	 
	  public static void pasteSchematic(World world, Location loc, Schematic schematic, Modo modo)
	    {
		    short[] blocks = schematic.getBlocks();
	        byte[] blockData = schematic.getData();
	 
	        short length = schematic.getLenght();
	        short width = schematic.getWidth();
	        short height = schematic.getHeight();
	 
	        for (int x = 0; x < width; ++x) {
	            for (int y = 0; y < height; ++y) {
	                for (int z = 0; z < length; ++z) {
	                    int index = y * width * length + z * width + x;
	                    Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
	                    block.setTypeIdAndData(blocks[index], blockData[index], true);
	                    
	                    if (modo == Modo.Bau) {
	                    if (block.getLocation().getBlock().getType() == Material.CHEST){
	                    	 Chest chest = (Chest) block.getLocation().getBlock().getState();
	                    	  fillChest(chest,  getRandom(Main.plugin.getConfig().getStringList("Feast-itens")));
	                    }
	                    if (block.getLocation().getBlock().getType() == Material.ENCHANTMENT_TABLE)
	                    {
	                    	world.setSpawnLocation((int)block.getLocation().getX(), (int)block.getLocation().getY()+2, (int)block.getLocation().getZ());
	                    }
	                    }
	                    
	                    if (modo == Modo.SetSpawn) {
	                    if (block.getLocation().getBlock().getType() == Material.REDSTONE_BLOCK)
	                    {
	                    	world.setSpawnLocation((int)block.getLocation().getX(), (int)block.getLocation().getY()+2, (int)block.getLocation().getZ());
	                    }
	                    }
	                    }
	            }
	        }
	    }
	  
		 public static void removePortaSchematic(World world, Location loc, Schematic schematic)
		    {
			    short[] blocks = schematic.getBlocks();
		        byte[] blockData = schematic.getData();
		 
		        short length = schematic.getLenght();
		        short width = schematic.getWidth();
		        short height = schematic.getHeight();
		 
		        for (int x = 0; x < width; ++x) {
		            for (int y = 0; y < height; ++y) {
		                for (int z = 0; z < length; ++z) {
		                    int index = y * width * length + z * width + x;
		                    Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();

		                    if (block.getLocation().getBlock().getType() == Material.PISTON_BASE){
			                    block.setType(Material.AIR);
		                    }
		                    
		                }
		            }
		        }
		    }
	 
	 public static void removeSchematic(World world, Location loc, Schematic schematic)
	    {
		    short[] blocks = schematic.getBlocks();
	        byte[] blockData = schematic.getData();
	 
	        short length = schematic.getLenght();
	        short width = schematic.getWidth();
	        short height = schematic.getHeight();
	 
	        for (int x = 0; x < width; ++x) {
	            for (int y = 0; y < height; ++y) {
	                for (int z = 0; z < length; ++z) {
	                    int index = y * width * length + z * width + x;
	                    Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
	                    block.setType(Material.AIR);
	                }
	            }
	        }
	    }
	 
	 public static Schematic loadSchematic(File file) throws IOException
	    {
	        FileInputStream stream = new FileInputStream(file);
	        NBTInputStream nbtStream = new NBTInputStream(new GZIPInputStream(stream));
	 
	        CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
	        if (!schematicTag.getName().equals("Schematic")) {
	            throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
	        }
	 
	        Map<String, Tag> schematic = schematicTag.getValue();
	        if (!schematic.containsKey("Blocks")) {
	            throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
	        }
	 
	        short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
	        short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
	        short height = getChildTag(schematic, "Height", ShortTag.class).getValue();
	 
	        String materials = getChildTag(schematic, "Materials", StringTag.class).getValue();
	        if (!materials.equals("Alpha")) {
	            throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
	        }
	 
	        byte[] blockId = ((ByteArrayTag)getChildTag(schematic, "Blocks", ByteArrayTag.class)).getValue();
	        byte[] blockData = ((ByteArrayTag)getChildTag(schematic, "Data", ByteArrayTag.class)).getValue();
	        byte[] addId = new byte[0];
	        short[] blocks = new short[blockId.length];
	        if (schematic.containsKey("AddBlocks")) {
	          addId = ((ByteArrayTag)getChildTag(schematic, "AddBlocks", ByteArrayTag.class)).getValue();
	        }
	        for (int index = 0; index < blockId.length; index++) {
	          if (index >> 1 >= addId.length) {
	            blocks[index] = ((short)(blockId[index] & 0xFF));
	          } else if ((index & 0x1) == 0) {
	            blocks[index] = ((short)(((addId[(index >> 1)] & 0xF) << 8) + (blockId[index] & 0xFF)));
	          } else {
	            blocks[index] = ((short)(((addId[(index >> 1)] & 0xF0) << 4) + (blockId[index] & 0xFF)));
	          }
	        }
	        return new Schematic(blocks, blockData, width, length, height);
	    }
	 
	    /**
	    * Get child tag of a NBT structure.
	    *
	    * @param items The parent tag map
	    * @param key The name of the tag to get
	    * @param expected The expected type of the tag
	    * @return child tag casted to the expected type
	    * @throws DataException if the tag does not exist or the tag is not of the
	    * expected type
	    */
	    private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException
	    {
	        if (!items.containsKey(key)) {
	            throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
	        }
	        Tag tag = items.get(key);
	        if (!expected.isInstance(tag)) {
	            throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
	        }
	        return expected.cast(tag);
	    }
	

	
	
}
