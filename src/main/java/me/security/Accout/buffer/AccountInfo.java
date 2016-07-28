package me.security.Accout.buffer;

import org.bukkit.entity.Player;

import me.acf.lobby.patentes.Patente;
import me.security.Accout.AccountAPI;
import me.site.account.rank.Rank;

public class AccountInfo {

	
	public static Rank getRank(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.rank;
	}
	
	public static Rank getRank(String p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.rank;
	}
	
	public static Patente getPatente(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.patente;
	}
	
	public static Patente getPatente(String p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.patente;
	}
	
	public static String getIP(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.ip;
	}
	
	
	public static int getPlanets(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.Planets();
	}
	
	public static int getPlanets(String p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.Planets();
	}
	
	public static int getCash(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.Cash();
	}
	
	public static int getChaves(Player p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.Chave();
	}
	
	public static int getCash(String p)
	{
		AccountAPI conta = AccountBuffer.Return(p);
		return conta.Cash();
	}
	
	
	
	
}
