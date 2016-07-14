package me.acf.Magic_Chest.modulo;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import me.acf.Magic_Chest.MagicEvent;

public class Modulos {
	
	public Location loc;
	public HashMap<Integer,Entity> spawns = new HashMap<>(); 
	private int total = 0;
	
	public Modulos(String name, Location l)
	{
		loc = l.clone().add(0,0.3,0);
		Spawn();
		Rename(0,name);
		Premio();
	}
	
	public void Remove()
	{
		for (int i=0; i < total; i++)
		spawns.get(i).remove();
	}
    
	
	public void Rename(int armor,String name)
	{
		spawns.get(armor).setCustomName(name);
		spawns.get(armor).setCustomNameVisible(true);
	}
	
	
	public void Premio()
	{
		loc = loc.clone().add(0,-0.5,0);
		Spawn();
		Rename(1,"§b§lSIMPLES " + "§f§lNADA");
	}
	
	
	
	
	private void Spawn()
	{
		final ArmorStand magic = (ArmorStand)loc.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
		    
 	    magic.setGravity(false);
        magic.setVisible(false);
        MagicEvent.magics.add(magic);
        spawns.put(total, magic);
	    total++;
	}
	
}
