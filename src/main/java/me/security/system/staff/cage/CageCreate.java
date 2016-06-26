package me.security.system.staff.cage;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.acf.FormatText.Format;

public class CageCreate {
	
    public static HashMap<Player, Cage> cages = new HashMap<>();
    public static HashMap<Player, Player> staffs = new HashMap<>();
    
	public static void CriarCage(Player jogador, Player staff)
	{
		if (staffs.containsKey(staff))
		{
			cages.get(staffs.get(staff)).Remover_Block();
			staffs.remove(staff);
			cages.remove(staffs.get(staff));
			return;
		}
		Cage cage = new Cage(staff, jogador);
	    cage.CriarCage();
	    cages.put(jogador, cage);
	    Format.Comando("Cage","Cage criado para jogador §a" + jogador.getName() , staff);
	    staffs.put(staff, jogador);
	}
	
	public static void RemoverCage(Player jogador)
	{
		if (staffs.containsKey(jogador))
		{
			cages.get(staffs.get(jogador)).Remover_Block();
			staffs.remove(jogador);
			cages.remove(staffs.get(jogador));
			return;
		}
		cages.get(jogador).Remover_Block();
		cages.remove(jogador);
		staffs.remove(cages.get(jogador).staff);
		
	}


}
