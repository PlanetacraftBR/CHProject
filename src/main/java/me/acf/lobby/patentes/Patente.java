package me.acf.lobby.patentes;

import org.bukkit.entity.Player;

import me.hub.Main;
import me.hub.API.Util.UtilPlayer;
import me.site.account.Account;
import me.site.account.AccountWeb;


public enum Patente
{
  unix("Unix", "§c§l"),
  Lider("Lider", "§5§l"),

  Supreme("Supreme", "§4"),
  Platina("Platina", "§7"),
  Titan("Titan", "§8"),
  ExpMaster("ExpMaster", "§9"),
  DeadKill("DeadKill", "§4"),  
  Jotinha("Jotinha", "§c"),
  Legendario("Legendario", "§a"),   
  Ultra("Ultra", "§b"),
  Chef("Chef", "§6"), 
  Pro("Pro", "§5"), 
  Amador("Amador", "§6§l"), 
  Iniciante("Iniciante", "§e");

  
  public String Name = "";
  public String Cor = "";
  
  
  private Patente(String name, String cor)
  {
    this.Cor = cor;
    this.Name = name.toUpperCase();
  }
  
  public static String ProxPatende(Player jogador)
  {
	  String Patende = Account.getPatente(jogador).GetName(true);
	  if (Patende.equals("iniciante"))
	  {
      return "Amador";
	  }
	  if (Patende.equals("amador"))
	  {
		  return "Pro";
	  }
	  if (Patende.equals("pro"))
	  {
		  return "Chef";
	  }
	  if (Patende.equals("chef"))
	  {
		  return "Ultra";
	  }
	  if (Patende.equals("ultra"))
	  {
		  return "Legendario";
	  }
	  if (Patende.equals("legendario"))
	  {
		  return "Jotinha";
	  }
	  if (Patende.equals("jotinha"))
	  {
		  return "DeadKill";
	  }
	  if (Patende.equals("deadkill"))
	  {
		  return "ExpMaster";
	  }
	  if (Patende.equals("expmaster"))
	  {
		  return "Titan";
	  }
	  if (Patende.equals("titan"))
	  {
		  return "Platina";
	  }
	  if (Patende.equals("platina"))
	  {
		  return "Supreme";
	  }
	  if (Patende.equals("supreme"))
	  {
		  return "Lider";
	  }
	  if (Patende.equals("Lider"))
	  {
		  return "Unix";
	  }
	  if (Patende.equals("Unix"))
	  {
		  return "Max Patente";
	  }
	return Patende;
  }
  
  public static void UpPatende(Player jogador)
  {
	  String Patende = Account.getPatente(jogador).GetName(true);
	  if (Patende.equals("iniciante"))
	  {
	    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Amador");
		System.out.print(buscar);
	  }
	  if (Patende.equals("amador"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Pro");
			System.out.print(buscar);	
			return;
	  }
	  if (Patende.equals("pro"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Chef");
			System.out.print(buscar);
			return;
	  }
	  if (Patende.equals("chef"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Ultra");
			System.out.print(buscar);
			return;
	  }
	  if (Patende.equals("ultra"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Legendario");
			System.out.print(buscar);
			return;
	  }
	  if (Patende.equals("legendario"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Jotinha");
			System.out.print(buscar);
			return;
	  }
	  if (Patende.equals("jotinha"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=DeadKill");
			System.out.print(buscar);
			return;
	  }
	  if (Patende.equals("deadkill"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=ExpMaster");
			System.out.print(buscar);
	  }
	  if (Patende.equals("expmaster"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Titan");
			System.out.print(buscar);
	  }
	  if (Patende.equals("titan"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Platina");
			System.out.print(buscar);
	  }
	  if (Patende.equals("platina"))
	  {
		    String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Supreme");
			System.out.print(buscar);
	  }
	  if (Patende.equals("supreme"))
	  {
		  String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Lider");
			System.out.print(buscar);
	  }
	  if (Patende.equals("supreme"))
	  {
		  String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=Lider");
			System.out.print(buscar);
	  }
	  if (Patende.equals("lider"))
	  {
		  String buscar = AccountWeb.Conectar(Main.site + "/API/patente.php?nick=" + jogador.getName() + "&patente=unix");
			System.out.print(buscar);
	  }
	  if (Patende.equals("unix"))
	  {
	
	  }
  }
  
  public boolean Has(Patente rank)
  {
    return Has(null, rank, false);
  }
  
  public boolean Has(Player player, Patente rank, boolean inform)
  {
    if (compareTo(rank) <= 0) {
      return true;
    }
    if (inform)
    {
      UtilPlayer.message(player, "§c§lOPS §7Somente jogador com patente " + rank.GetTag(false) + "§7 !");
    }
    
    return false;
  }
  
  public String GetTag(boolean uppercase)
  {
    String name = this.Name;
    if (uppercase) {
      name = this.Name.toUpperCase();
    }
    return this.Cor + name;
  }
  
  public String GetCor()
  {
  
    return this.Cor;
  }
  
  
  public String GetName(boolean uppercase)
  {
    String name = this.Name;
    if (uppercase) {
      name = this.Name.toLowerCase();
    }
    return name;
  }
}