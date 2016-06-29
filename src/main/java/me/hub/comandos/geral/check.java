package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.security.GeoIP.API.GeoIPLite;
import me.site.account.Account;
import me.site.account.AccountOFF;
import me.site.account.rank.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.JSONObject;

public class check implements CommandExecutor {

	public String[] atalhos = new String[] { "account", "conta" };
    public String desc = "Verificar a conta do jogador";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, true))
		   {

			if (args.length  <= 0)
			{
				Format.Comando("account", "/conta §anome", jogador);
				return true;
			}
			
				
			  JSONObject obj = new JSONObject(AccountOFF.loadAccount(args[0]));
			  try {
				    String countryCode = GeoIPLite.getCountryCode(obj.getString("ip"));
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§a§lPlanetacraft§f§l_§e§lBR §f§l- §c§lConta do(a) §f"+obj.getString("nome"));
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§9§lPerfil §c§l§c§l|§f http://painel.planetacraft.com.br/perfil/"+obj.getString("nome"));
			   jogador.sendMessage(" ");
			   if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, false))
			   {
			   jogador.sendMessage("§9§lIP §c§l§c§l|§f "+obj.getString("ip") + " (" + countryCode + ")");
			   jogador.sendMessage(" ");
			   }
			   jogador.sendMessage("§9§lUUID §c§l§c§l|§f "+obj.getString("uuid"));
			   jogador.sendMessage("§9§lNome §c§l§c§l|§f "+obj.getString("nome"));
			   jogador.sendMessage("§9§lGrupo §c§l§c§l|§f "+obj.getString("grupo"));
			   jogador.sendMessage("§9§lPatente §c§l§c§l|§f "+obj.getString("patente"));
			   jogador.sendMessage("§9§lNivel §c§l§c§l|§f "+obj.getString("nivel"));
			   jogador.sendMessage("§9§lCash §c§l§c§l|§f "+obj.getString("cash"));
			   jogador.sendMessage("§9§lPlanets §c§l§c§l|§f "+obj.getString("planets"));
			   jogador.sendMessage("§9§lChaves §c§l§c§l|§f "+obj.getString("chaves"));
			   jogador.sendMessage("§9§lKDR Rei §c§l§c§l|§f "+obj.getString("kdr"));
			   jogador.sendMessage(" ");
			   if (obj.getString("email").toString().isEmpty()){
			   jogador.sendMessage("§9§lRegistrado no SITE §c§l§c§l|§f Não");
			   }else{
			   jogador.sendMessage("§9§lRegistrado no SITE §c§l§c§l|§f Sim");
			   }
			   if (obj.getString("ban_mt").toString().isEmpty()){}else{
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§9§lBan Motivo §c§l§c§l|§f "+obj.getString("ban_mt"));
			   jogador.sendMessage("§9§lBan Staff §c§l§c§l|§f "+obj.getString("ban_st"));
			   jogador.sendMessage("§9§lBan Acaba §c§l§c§l|§f "+obj.getString("ban_es"));
			   }
			   if (obj.getString("ban_log").toString().isEmpty()){}else{
			   jogador.sendMessage(" ");
			   jogador.sendMessage("§9§lBan Log §c§l§c§l|§f "+obj.getString("ban_log"));
			   }
			  }
			  catch (Exception exception)
	   		    {
				  Format.Erro("§cJogador não existe em nosso banco de dados", jogador);
	   		    }
		   }
		
		return false;
	  }
	

	

}

