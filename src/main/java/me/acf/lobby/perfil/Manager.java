/**
 * 
 */
package me.acf.lobby.perfil;

import org.bukkit.entity.Player;

import me.acf.lobby.gadgets.ViewPlayers;
import me.hub.config.Config;

/**
 * @author adriancf
 *
 */
public class Manager {

	
	private Player jogador;

	
	public Manager(Player p)
	{
		jogador = p;
		Carregar();
		
	}
	
	private void Carregar()
	{
		Config.addn(jogador, "Perfil.VerJogadores", "sim");
		Config.addn(jogador, "Perfil.MsgPrivate", "sim");
		Config.addn(jogador, "Perfil.ChatGlobal", "sim");
		Config.addn(jogador, "Perfil.SvNot", "sim");
		Config.addn(jogador, "Perfil.Dj", "nao");
	}
	
	public void Load()
	{
		Verificar_Jogadores();
		Verificar_MSGP();
		Verificar_CG();
	    Verificar_Not();
	    Verificar_DJ();
	}
	public void Update(String up)
	{
		if (verficar(up))
			Config.Set(jogador, "Perfil." + up, "nao");	
		else
			Config.Set(jogador, "Perfil." + up, "sim");
	Load();	
	}
	
	private void Verificar_Jogadores()
	{
		Perfil.VerJogadores.remove(jogador);
		if (!verficar("VerJogadores")) {
			Perfil.VerJogadores.add(jogador);
		  ViewPlayers.Esconder(jogador);
		}
		else {
			Perfil.VerJogadores.remove(jogador);
		    ViewPlayers.Revelar(jogador);
		}
		}
	
	private void Verificar_MSGP()
	{
		Perfil.MsgPrivate.remove(jogador);
if (!verficar("MsgPrivate"))
	Perfil.MSGPrivate.add(jogador);
else
	Perfil.MSGPrivate.remove(jogador);
	}
	
	private void Verificar_DJ()
	{
		Perfil.DoubleJump.remove(jogador);
		if (!verficar("Dj"))
			Perfil.DoubleJump.add(jogador);
		else
			Perfil.DoubleJump.remove(jogador);
	}
	
	private void Verificar_CG()
	{
		Perfil.MSGlobal.remove(jogador);
		if (!verficar("ChatGlobal"))
			Perfil.MSGlobal.add(jogador);
		else
			Perfil.MSGlobal.remove(jogador);
	}
	
	private void Verificar_Not()
	{
		Perfil.Notification.remove(jogador);
		if (!verficar("SvNot"))
			Perfil.Notification.add(jogador);
		else
			Perfil.Notification.remove(jogador);
	}
	
	public boolean verficar(String ver)
	{
		String verificar = Config.retornar(jogador, "Perfil." + ver);
		if (verificar.equals("sim"))
		return true;
		if (verificar.equals("nao"))
			return false;
		
		return true;
	}
	
	public String Ativo(String ver)
	{
		String texto = "Não existe";
		if (verficar(ver))
			texto = "§aAtivado";
		else
			texto = "§cDesativado";
		
		return texto;
	}
}
