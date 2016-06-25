/**
 * 
 */
package me.acf.lobby.MagicChest;

import java.util.Random;

import org.bukkit.entity.Player;

import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.config.Config;
import me.acf.lobby.patentes.Patente;
import me.site.account.Account;

/**
 * @author adriancf
 *
 */
public class RoletaMagic {

	  public static String Premio(int i) {
		   String c = "§c§lNADA";
		   
		 if (i == 0)
			 c = "Nada";
		 if ((i >= 1) && (i < 35))
				 {
			 Random r = new Random();  
			       c =  r.nextInt(500)+ "x Planets";
				 }
		 if ((i >= 36) && (i < 95))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(650)+ "x Exp";
		 }
		 
		 if ((i >= 96) && (i < 150))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(100)+ "x §5Cash";
		 }
		 
		 if ((i >= 151) && (i < 200))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(100)+ "x §6§lPaintBall Gun";
		 }
		 if ((i >= 201) && (i < 300))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(120)+ "x §6§lFunGun";
		 }
		 if ((i >= 301) && (i < 350))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(50)+ "x §6§lTnt Gun";
		 }
		 if ((i >= 350) && (i < 420))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(100)+ "x §6§lMelonBomb";
		 }
		 if ((i >= 421) && (i < 490))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(10)+ "x §6§lColorBomb";
		 }
		 if ((i >= 491) && (i < 575))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(90)+ "x §6§lFleshHook";
		 }
		 if ((i >= 576) && (i < 625))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(90)+ "x §6§lEnderTeleport";
		 }
		 if ((i >= 626) && (i < 699))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(20)+ "x §6§lParty FireWork";
		 }
		 if ((i >= 700) && (i < 750))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(20)+ "x §6§lTsunami";
		 }
		 if ((i >= 756) && (i < 790))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(10)+ "x §6§lTrampolim";
		 }
		 if ((i >= 790) && (i < 830))
		 {
	       c =  "§e§lPatente §6§lUP";
		 }
		 if ((i >= 931) && (i < 939))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(5)+ "x §b§lMagic §a§lChest!";
		 }
		 if ((i >= 940) && (i < 950))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(5)+ "x §6§lPlanetsBomb";
		 }
		 if ((i >= 951) && (i < 1350))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetZombie";
		 }
		 if ((i >= 1351) && (i < 1650))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetSheep";
		 }
		 if ((i >= 1351) && (i < 1650))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetSheep";
		 }
		 if ((i >= 1651) && (i < 1850))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetChicken";
		 }
		 if ((i >= 1851) && (i < 2000))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetCow";
		 }
		 if ((i >= 2001) && (i < 2500))
		 {
	 Random r = new Random();  
	       c =  "§f§lPets §aPetCow";
		 }
		 if ((i >= 2501) && (i < 2600))
		 {
	 Random r = new Random();  
	       c =  "§f§lMorph §aPig";
		 }
		 if ((i >= 2601) && (i < 2700))
		 {
	 Random r = new Random();  
	       c =  "§f§lMorph §aChicken";
		 }
		 if ((i >= 2701) && (i < 2900))
		 {
	 Random r = new Random();  
	       c =  "§f§lMorph §aCow";
		 }
		 if ((i >= 2901) && (i < 3100))
		 {
	 Random r = new Random();  
	       c =  "§f§lMorph §aCow";
		 }
		 if ((i >= 3001) && (i < 3200))
		 {
	 Random r = new Random();  
	       c =  "§f§lMorph §aEnderman";
		 }
		 if ((i >= 3201) && (i < 3700))
		 {
	 Random r = new Random();  
     c =  r.nextInt(6000)+ "x §5Cash";
		 }
		 if ((i >= 3701) && (i < 3900))
		 {
	 Random r = new Random();  
     c =  "§aDisco " + r.nextInt(11);
		 }
		 if ((i >= 3901) && (i < 4100))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCapacete de Ouro";
		 }
		 if ((i >= 4101) && (i < 4200))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aPeitoral de Ouro";
		 }
		 if ((i >= 4201) && (i < 4300))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCalça de Ouro";
		 }
		 if ((i >= 4301) && (i < 4500))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aBota de Ouro";
		 }
		 if ((i >= 4501) && (i < 4600))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCapacete de Ferro";
		 }
		 if ((i >= 4601) && (i < 4700))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aPeitoral de Ferro";
		 }
		 if ((i >= 4701) && (i < 4800))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCalça de Ferro";
		 }
		 if ((i >= 4801) && (i < 4900))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aBota de Ferro";
		 }
		 if ((i >= 4901) && (i < 5000))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCapacete de Diamante";
		 }
		 if ((i >= 5001) && (i < 5100))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aPeitoral de Diamante";
		 }
		 if ((i >= 5101) && (i < 5200))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCalça de Diamante";
		 }
		 if ((i >= 5201) && (i < 5300))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aBota de Diamante";
		 }
		 if ((i >= 5401) && (i < 5500))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCapacete de Chainmail";
		 }
		 if ((i >= 5501) && (i < 5600))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aPeitoral de Chainmail";
		 }
		 if ((i >= 5601) && (i < 5700))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aCalça de Chainmail";
		 }
		 if ((i >= 5701) && (i < 5800))
		 {
	 Random r = new Random();  
     c =  "§f§lArmadura §aBota de Chainmail";
		 }
		 if ((i >= 5801) && (i < 6300))
		 {
	       c =  "§e§lPatente §6§lUP";
		 }
		 if ((i >= 6301) && (i < 6900))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(2500)+ "x Exp";
		 }
		 if ((i >= 6901) && (i < 7900))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(9000)+ "x Planets";
		 }
		 if ((i >= 7901) && (i < 8200))
		 {
	 Random r = new Random();  
	       c =  r.nextInt(10)+ "x §b§lMagic §a§lChest!";
		 }
		   return c;
		   
		  
		   }
}
