package me.acf.Magic_Chest.modulo;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.acf.Magic_Chest.premios.Premios;
import me.hub.API.Util.UtilInv;

public class MenuMagic {

	public Inventory inv;
	public ArrayList<Magic> baus = new ArrayList<>();
	int Chaves = 50;
	public Player jogador;
	
	public MenuMagic(Player p)
	{
		inv = Bukkit.createInventory(null, 54, "Magic Chest Boxs");
		//Chaves = AccountInfo.getChaves(jogador);
		GerarBaus();
		if (p == null)
			return;
		if (baus.contains(Magic.MAGIC_MONSTER))
			jogador.sendMessage("§b§lMAGIC §7Você tem um §6§lMOSTER §7para abrir");
		if (baus.contains(Magic.MAGIC_RARO))
			jogador.sendMessage("§b§lMAGIC §7Você tem um §4§lMOSTER §7para abrir");
		if (baus.contains(Magic.MAGIC_EPIC))
			jogador.sendMessage("§b§lMAGIC §7Você tem um §a§lMOSTER §7para abrir");
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
	   
	   int slot = 0;
	   for (Magic s : baus)
	   {
	   ArrayList<String> list = new ArrayList<>();
	   list.add("§7Magic Chest Bau, abra e ganhe varios itens");
	   list.add("§9");
	   list.add(s.nome + ", total de chaves para abrir este §aMagic Chest");
	   list.add("§a- " + s.Chaves + " Chaves");
	   list.add("§6");
	   list.add("§7Avaliado em:§e " + s.avaliado + " pontos");
	   list.add("§6Click para abrir");
	   UtilInv.AddItemString(inv, false, Material.ENDER_CHEST, 1, (byte) 0,s.nome,list, slot);
	   slot++;
	   }
   }
	
	
   public void AddBau(Magic magic)
   {
	   Random random = new Random();
	   if (magic == Magic.MAGIC_NORMAL)
	   {
		   int r = random.nextInt(30);
		   if (r < 5) {
			   magic.avaliado = 0.5; 
		       magic.premio = Premios.NADA;
		   }
		   if ((r >= 6 ) && (r < 15)) {
			   magic.avaliado = 3.5; 
		       magic.premio = Premios.PLANETS; 
		   }
		   if ((r >= 15 ) && (r < 21)) {
			   magic.avaliado = 3.9; 
		       magic.premio = Premios.EXP; 
		   }				   
		   if ((r >= 21 ) && (r < 30)) {
			   magic.avaliado = 4.2; 
		       magic.premio = Premios.GADGETS; 
		   }
	   }
	   if (magic == Magic.MAGIC_RARO)
        {
		   int r = random.nextInt(40);
		   if (r < 5) {
			   magic.avaliado = 0.5; 
		       magic.premio = Premios.NADA;
		   }
		   if ((r >= 6 ) && (r < 15)) {
			   magic.avaliado = 3.9; 
		       magic.premio = Premios.PLANETS; 
		   }
		   if ((r >= 15 ) && (r < 21)) {
			   magic.avaliado = 4.3; 
		       magic.premio = Premios.EXP; 
		   }				   
		   if ((r >= 21 ) && (r < 38)) {
			   magic.avaliado = 5.2; 
		       magic.premio = Premios.GADGETS; 
		   }
		   if ((r >= 38 ) && (r < 40)) {
			   magic.avaliado = 5.9; 
		       magic.premio = Premios.PATENTE; 
		   }
	   }
	   if (magic == Magic.MAGIC_EPIC)
	   {
		   int r = random.nextInt(70);
		   if (r < 5) {
			   magic.avaliado = 0.5; 
		       magic.premio = Premios.NADA;
		   }
		   if ((r >= 6 ) && (r < 15)) {
			   magic.avaliado = 6.9; 
		       magic.premio = Premios.PLANETS; 
		   }
		   if ((r >= 15 ) && (r < 21)) {
			   magic.avaliado = 4.3; 
		       magic.premio = Premios.EXP; 
		   }				   
		   if ((r >= 21 ) && (r < 38)) {
			   magic.avaliado = 5.2; 
		       magic.premio = Premios.GADGETS; 
		   }
		   if ((r >= 38 ) && (r < 50)) {
			   magic.avaliado = 5.9; 
		       magic.premio = Premios.PATENTE; 
		   }
		   if ((r >= 50 ) && (r < 65)) {
			   magic.avaliado = 6.9; 
		       magic.premio = Premios.CACHES; 
		   }
		   if ((r >= 65 ) && (r < 70)) {
			   magic.avaliado = 7.9; 
		       magic.premio = Premios.CHAVES; 
		   }
	   }
	   if (magic == Magic.MAGIC_MONSTER)
	   {
		   int r = random.nextInt(80);
		   if (r < 5) {
			   magic.avaliado = 0.5; 
		       magic.premio = Premios.NADA;
		   }
		   if ((r >= 6 ) && (r < 15)) {
			   magic.avaliado = 6.9; 
		       magic.premio = Premios.PLANETS; 
		   }
		   if ((r >= 15 ) && (r < 21)) {
			   magic.avaliado = 4.3; 
		       magic.premio = Premios.EXP; 
		   }				   
		   if ((r >= 21 ) && (r < 38)) {
			   magic.avaliado = 5.2; 
		       magic.premio = Premios.GADGETS; 
		   }
		   if ((r >= 38 ) && (r < 50)) {
			   magic.avaliado = 5.9; 
		       magic.premio = Premios.PATENTE; 
		   }
		   if ((r >= 50 ) && (r < 65)) {
			   magic.avaliado = 6.9; 
		       magic.premio = Premios.CACHES; 
		   }
		   if ((r >= 65 ) && (r < 75)) {
			   magic.avaliado = 7.9; 
		       magic.premio = Premios.CHAVES; 
		   }
		   if ((r >= 75 ) && (r < 80)) {
			   magic.avaliado = 10.0; 
		       magic.premio = Premios.VIP; 
		   }
	   }
	   baus.add(magic);
	   Chaves -= magic.Chaves;
	   System.out.println("[Magic Chest] add "+ magic.nome + " nota: " + magic.avaliado);
   }	
	
	public enum Magic
	{
		MAGIC_NORMAL(1,"§a§lNormal",1.0,Premios.NADA),
		MAGIC_RARO(5,"§4§lRARO",1.6,Premios.NADA),
		MAGIC_EPIC(8,"§a§lEPIC",2.0,Premios.NADA),
		MAGIC_MONSTER(10,"§6§lMOSTER",3.2,Premios.NADA);
		
		int Chaves;
		String nome;
		double avaliado = 1.3;
		Premios premio;
		
		Magic(int chaves, String name,double aval,Premios pre)
		{
			Chaves = chaves;
			nome = "§b§lMagic "+name;
			avaliado = aval;
	     }
	
	
	}
	
	
}
