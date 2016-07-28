package me.security.Punish;

import org.bukkit.entity.Player;

import me.hub.Main;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilTime;
import me.site.account.AccountWeb;

public class PunishAPI {

	public String Ban_Motivo = "§f§oNenhum Motivo localizado";
	public String Ban_Staff  = "Sistema";
	public String Ban_DiaBan = "00/00/00 - 00:00:00";
	public String Ban_ExpBan = "00/00/00 - 00:00:00";
	public Player jogador;
	public boolean banido = false;
	
	public PunishAPI(Player p,String motivo,String staff,String DiaBan,String expBan)
	{
		this.Ban_Motivo = motivo;
		this.Ban_Staff = staff;
		this.Ban_DiaBan = DiaBan;
		this.Ban_ExpBan = expBan;
		
		Check_IP();
		if (banido)
			return;
		Check_Ban();
	}
	
	
	
	private void Check_IP()
	{	
	    	String buscarip = AccountWeb.Conectar(Main.site + "/API/ban_ip.php?nick=" + jogador.getName(), "OK");
		    if (buscarip != null) {
		      UtilPlayer.Kick(jogador, "§fSeu IP esta provavelmente banido por nosso sistema");
		      banido = true;
		    }
				
	}
	
	private void Check_Ban()
	{
		if (Ban_Motivo.equals(""))
			return;
		
		String info_tempo = "§aVitalico"; 
		
		if (!this.Ban_ExpBan.contains("Vitalico")) {
			long tempo = UtilTime.DataTempo(UtilTime.TimeData(), Ban_ExpBan);
			info_tempo = UtilTime.convertString(tempo, 0, UtilTime.TimeUnit.FIT);
		
		if (tempo <= 0)
		{
        	String buscar = AccountWeb.Conectar(Main.site + "/API/ban.php?modo=REMOVE&nick=" + jogador.getName(), "OK");
    	    System.out.print(buscar);
			return;
		}
		}
		
		jogador.kickPlayer("§6" + Main.NomeDoServidor + " - Kickado \n§fVocê foi banido do servidor\n§f§o" 
		+ this.Ban_Motivo + "\n\n§fInformações (" + jogador.getName() + ")\n§fTempo: §a" + info_tempo 
		+ "\n§fStaff que baniu:§6 " + this.Ban_Staff + "\n§fDia do ban:§7 " + this.Ban_DiaBan);
	}
	
	
	
	
		

}
