package me.security.Accout.buffer;

import org.bukkit.entity.Player;

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
	
	
	
}
