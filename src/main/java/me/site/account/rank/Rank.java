package me.site.account.rank;

import org.bukkit.entity.Player;

import me.hub.API.Util.UtilPlayer;


public enum Rank
{
  DONO("Dono", "§b§l"),
  DEV("DEV", "§c§l"),  
  STAFFM("Staff+", "§a§l"), 
  STAFF("Staff", "§a§l"),
  
  //STAFF ^
  
  MIDIA("Midia", "§9§l"), 
  
  VIPM("VIP+", "§5§l"), 
  VIP("VIP", "§6§l"), 
  
  MEMBRO("Membro", "§7");

  
  public String Name = "";
  public String Cor = "";
  
  
  private Rank(String name, String cor)
  {
    this.Cor = cor;
    this.Name = name.toUpperCase();
  }
  
  public boolean Has(Rank rank)
  {
    return Has(null, rank, false);
  }
  
  public boolean Has(Player player, Rank rank, boolean inform)
  {
    if (compareTo(rank) <= 0) {
      return true;
    }
    if (inform)
    {
      UtilPlayer.message(player, "§c§lOPS §7Somente jogador com grupo " + rank.GetTag(false) + "§7 !");
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
}