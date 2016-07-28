package me.security.Donate;

import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilTime;
import me.security.Accout.buffer.AccountBuffer;
import me.site.account.AccountWeb;

public class DonateAPI {

	public String VIP_DiaVIP = "00/00/00 - 00:00:00";
	public String VIP_ExpVIP = "00/00/00 - 00:00:00";

	public Player jogador;
	
	public DonateAPI(Player jogador, String DiaVIP, String ExpVIP)
	{
		this.jogador = jogador;
		this.VIP_DiaVIP = DiaVIP;
		this.VIP_ExpVIP = ExpVIP;
		
		Check();
	}
	
	
	private void Check()
	{
		String info_tempo = "§aVitalico"; 
		
		if (this.VIP_ExpVIP.equals(""))
		return;
		if (this.VIP_DiaVIP.equals(""))
		{
			UtilPlayer.Kick(jogador, "Ocorreu um erro na data de ativação de seu VIP informe esse erro para um STAFF");
			return;
		}
			long tempo = UtilTime.DataTempo(UtilTime.TimeData(), VIP_ExpVIP);
			info_tempo = UtilTime.convertString(tempo, 0, UtilTime.TimeUnit.FIT);
		
		if (tempo <= 0)
		{
			String buscar = AccountWeb.Conectar(Main.site + "/API/grupo.php?modo=VIP&nick=" + jogador.getName(), "OK");
    	    System.out.print(buscar);
    	    AccountBuffer.Reload(jogador);
			return;
		}
		Format.Comando("§6§lVIP", "§f§lSEU VIP TERMINA EM: " + info_tempo, jogador);
		
	}
}
