package me.hub.API.Util;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import me.hub.Main;



public class UtilPlayer
{
	
  public static String Nome(Player jogador)
  {
	  return jogador.getName();
  }
  
  public static int Ping(Player jogador)
  {
	  if (jogador.getName().equals("BrinePlay"))
	  {
		  int lag = ((CraftPlayer)jogador).getHandle().ping+292;
		  return ((CraftPlayer)jogador).getHandle().ping;
	  }
		  return ((CraftPlayer)jogador).getHandle().ping;
  }
  
  public static void message(Player jogador, String msg)
  {
	  jogador.sendMessage(msg);
  }
  
  
  public static void Teleportar(Player jogador, Player para)
  {
	  jogador.teleport(para.getLocation());
  }
  
  public static void Teleportar(Player jogador, Location para)
  {
	  jogador.teleport(para);
  }
  
  public static void Kick(Player jogador, String Motivo, Player staff)
  {
	  String Msg = "§6" + Main.NomeDoServidor + " - Kickado \n§fVocê foi kickado por " + Motivo + ". ( §c" + staff.getName() +"§f )\nCuidado com os kicks para não ser §cbanido permanente§f.\nVocê pode retornar ao servidor normalmente agora.";
	  jogador.kickPlayer(Msg);
  }
  
  public static void Kick(Player jogador, String Motivo)
  {
	  String Msg = "§6" + Main.NomeDoServidor + " - Kickado \n§fVocê foi kickado por " + Motivo + ". ( §cSistema§f )\nCuidado com os kicks para não ser §cbanido permanente§f.\nVocê pode retornar ao servidor normalmente agora.";
	  jogador.kickPlayer(Msg);
  }
  

  public static void Teleportar(Player jogador, Entity para)
  {
	  jogador.teleport(para.getLocation());
  }
  
  public static Projectile Disparo(Player jogador, Class item)
  {
	  return jogador.launchProjectile(item);
  }
  
  public static String getCardinalDirection(Player player) {
      double rot = (player.getLocation().getYaw() - 90) % 360;
      if (rot < 0) {
          rot += 360.0;
      }
      return getDirection(rot);
  }

  
  private static String getDirection(double rot) {
      if (0 <= rot && rot < 22.5) {
          return "West";
      } else if (22.5 <= rot && rot < 67.5) {
          return "WN";
      } else if (67.5 <= rot && rot < 112.5) {
          return "North"; //East
      } else if (112.5 <= rot && rot < 157.5) {
          return "NE";
      } else if (157.5 <= rot && rot < 202.5) {
          return "East";
      } else if (202.5 <= rot && rot < 247.5) {
          return "ES";
      } else if (247.5 <= rot && rot < 292.5) {
          return "South"; //West;
      } else if (292.5 <= rot && rot < 337.5) {
          return "SW";
      } else if (337.5 <= rot && rot < 360.0) {
          return "West";
      } else {
          return null;
      }
  }

}