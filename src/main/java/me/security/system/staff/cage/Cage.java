package me.security.system.staff.cage;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilBlock;
import me.security.move.AntiMove;

public class Cage {
	
	public Player staff;
	public Player jogador;
	public Location loc;
	public Location Local_Antigo; 
	public Map<Location,Block> lista = new HashMap<>();
	
	public Cage(Player staff, Player jogador)
	{
		this.staff = staff;
		this.jogador = jogador;
		this.loc = jogador.getLocation().add(0,20,0);
	    this.Local_Antigo = jogador.getLocation();
	}
	
	
	public void CriarCage()
	{
	    for (Block block : UtilBlock.getInRadius(loc, 2.5D).keySet()) {
	        if (UtilBlock.solid(block)) {
	        	if (this.loc.getBlockY() >= 200)
	        	{
	        		Format.Erro("Não foi possivel criar o cage", staff);
	        		CageCreate.cages.remove(jogador);
	        		CageCreate.staffs.remove(staff);
	        	return;
	        	}
	        	this.loc = this.loc.add(0,10,0);
	        	CriarCage();
	        	return;
	        }
	    }
		Criar_Block(loc,0, 3, 0);
		Criar_Block(loc,0, -1, 0);
		
    	Criar_Block(loc,-1, 0, 0);
        Criar_Block(loc,0, 0, -1);
        Criar_Block(loc,1, 0, 0);
    	Criar_Block(loc,0, 0, 1);
    	
    	
     	Criar_Block(loc,-1, 2, 0);
        Criar_Block(loc,0, 2, -1);
        Criar_Block(loc,1, 2, 0);
    	Criar_Block(loc,0, 2, 1);
    	AntiMove.loc.put(jogador, loc.add(0,1.0D,0));
    	
    	 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
	     {
	       public void run()
	       {
    	  staff.teleport(jogador.getLocation().add(0,2,-2));
          jogador.teleport(loc.add(0, 1.0D, 0));
          AntiMove.loc.remove(jogador);
	       }
	       }, 20L);
	}
	
	public void Criar_Block(Location loc,double x,double y,double z,Material material)
	{
		Location block = loc.clone().add(x, y, z);
		lista.put(block, block.getBlock());
		block.getBlock().setType(material);
	}		
		
	public void Criar_Block(Location loc,double x,double y,double z)
	{
		Location block = loc.clone().add(x, y, z);
		lista.put(block, block.getBlock());
		block.getBlock().setType(Material.BEDROCK);
		
		loc.getWorld().playEffect(block, Effect.STEP_SOUND, 7);
		
	}
	
	public void Remover_Block()
	{
		if (jogador != null) {
		 if (jogador.isOnline()){
			 jogador.teleport(this.Local_Antigo);
		 }
		}
		for (Location loc : lista.keySet())
		{
			loc.getBlock().setType(Material.AIR);
			
		}
	}


}
