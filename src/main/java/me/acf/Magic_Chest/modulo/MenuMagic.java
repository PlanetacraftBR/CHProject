package me.acf.Magic_Chest.modulo;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;

public class MenuMagic {

	public Inventory inv;
	public ArrayList<Magic> baus = new ArrayList<>();
	int Chaves = 50;
	
	public MenuMagic()
	{
		GerarBaus();
	}
	
	
   public void GerarBaus()
   {
	   System.out.println("Gerar sistema de Baus");
	   for (int i=0; i<= Chaves; i++)
	   {
		   if (i >= Magic.MAGIC_MONSTER.Chaves) {
		       AddBau(Magic.MAGIC_MONSTER);
		       i += Magic.MAGIC_MONSTER.Chaves;
		   }
		   if (i >= Magic.MAGIC_EPIC.Chaves) {
		       AddBau(Magic.MAGIC_EPIC);
		       i += Magic.MAGIC_EPIC.Chaves;
		   }
		   if (i >= Magic.MAGIC_RARO.Chaves) {
		       AddBau(Magic.MAGIC_RARO);
		       i += Magic.MAGIC_RARO.Chaves;
		   }
		   if (i >= Magic.MAGIC_NORMAL.Chaves) {
		       AddBau(Magic.MAGIC_NORMAL);
		       i += Magic.MAGIC_NORMAL.Chaves;
		   }
		   
	   }
   }
	
	
   public void AddBau(Magic magic)
   {
	   baus.add(magic);
	   Chaves -= magic.Chaves;
	   System.out.println("[Magic Chest] add "+ magic.nome);
   }
   
	
	public void Menu_Generete()
	{
		
	}
	
	
	public enum Magic
	{
		MAGIC_NORMAL(1,"§a§lNormal"),
		MAGIC_RARO(5,"§4§lRARO"),
		MAGIC_EPIC(8,"§a§lEPIC"),
		MAGIC_MONSTER(10,"§6§lMOSTER");
		
		int Chaves;
		String nome;
		
		Magic(int chaves, String name)
		{
			Chaves = chaves;
			nome = "§b§lMagic "+name;
	     }
	
	
	}
	
	
}
