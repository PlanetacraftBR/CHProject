package me.acf.FormatText;

import org.bukkit.entity.Player;

import me.acf.lobby.perfil.Perfil;

public class Format {
	
	
	
	public static void Comando(String nome, String msg , Player p)
	{
		if (!Perfil.Notification.contains(p))
		p.sendMessage("§c§l" + nome.toUpperCase() + "§7 " + msg);
	}
	
	public static void Main(String nome, String msg , Player p)
	{
		if (!Perfil.Notification.contains(p))
		p.sendMessage("§c§l" + nome.toUpperCase() + "§7 " + msg);
	}
	
	public static void Recebeu(String de, String premium , Player p)
	{
		if (!Perfil.Notification.contains(p))
		p.sendMessage("§9§l" + de + "§f " + premium);
	}

	public static void Erro(String erro , Player p)
	{
		if (!Perfil.Notification.contains(p))
		p.sendMessage("§c§lOPS §7 " + erro);
	}
	
}
