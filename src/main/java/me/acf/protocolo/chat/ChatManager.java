/**
 * 
 */
package me.acf.protocolo.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.acf.punish.PunishMananger;
import me.hub.Main;
import me.hub.Admin.Staff;

/**
 * @author adriancf
 *
 */
public class ChatManager {
	
	 private static Pattern VALIDO = Pattern.compile("[A-Za-z]");
	 private static ArrayList<String> palavras = new ArrayList<>();
	 private static ArrayList<String> palavras_chat = new ArrayList<>();
	 private static ArrayList<String> Geral_Ofensivas = new ArrayList<>();
	 private static HashMap<Player,Integer> avisos = new HashMap<>();
	 
	 public ChatManager()
	 {
		 palavras.add("fdp");
		 palavras.add("gay");
		 palavras.add("viado");
		 palavras.add("lixo");
		 palavras.add("viada");
		 palavras.add("filhodaputa");
		 palavras.add("puta");
		 palavras.add("sexo");
		 palavras.add("vargina");
		 palavras.add("sexo");
		 palavras.add("abuser");
		 palavras.add("redtube");
		 palavras.add("porno");
	 }
	 
	 public static void Load_Server()
	 {
		for (String text : Main.plugin.getConfig().getStringList("Palavras-proibidas"))
		{
		 palavras_chat.add(text);
		}
		 
	 }
	
	 
	 
	private static boolean Verificar(String msg)
	{
		String chat = msg.toLowerCase();
		chat = chat.replace(" ", "");
		chat = chat.replace("@", "a");
		chat = Deixarletras(chat);
		System.out.print("Verificando " + chat);
		if (palavras.contains(chat))
		{
			return true;
		}
		return false;
	}
	private static boolean Verificar_Chat(String msg)
	{
		String chat = msg.toLowerCase();
		chat = chat.replace(" ", "");
		chat = chat.replace("@", "a");
		chat = Deixarletras(chat);
		System.out.print("Verificando " + chat);
		if (palavras_chat.contains(chat))
		{
			return true;
		}
		return false;
	}
	public static boolean ChatVerificar(Player p, String chat)
	{
		
		if ((Verificar(chat)) || (Verificar_Chat(chat)))
		{
			p.sendMessage("");
			p.sendMessage("§4§lAVISO §f§lVocê foi alertado pelo.");
			p.sendMessage("§a§lStaff: §6§l_zUnix");
			p.sendMessage("§a§lMotivo: §f§lChat inadequado.");
			p.sendMessage("§a§lAvisos: " + avisos.get(p) + "/6");
			p.sendMessage("");
		    if (avisos.containsKey(p))
		    {
		    	int warn = avisos.get(p)+1;
		    	avisos.remove(p);
		    	avisos.put(p, warn);
		    }
		    else
		     avisos.put(p, 1);
			if (avisos.get(p) >= 6)
			{
				PunishMananger.AddPunish(p.getName(), "inadequadas", "_zUnix");
				return true;
			}
			return true;
		}
		return false;
	}
	public static boolean ProtocolNick(Player p)
	{
		if (Verificar(p.getName()))
		{
			return true;
		}
		return false;
	}
	
	private static String Deixarletras(String msg)
	{
		int o = msg.length()-1;
        String resultado = msg;
		for (int i = 0; i <= o; i++)
		{
			   String invalidChars = getInvalidChars(""+msg.charAt(i));
			   if (!invalidChars.isEmpty())
			    {
				 resultado = resultado.replace("" + msg.charAt(i), "");
			    }
		}
		
		return resultado;
	}
	
	   public static String getInvalidChars(String s)
		  {
		    return VALIDO.matcher(s).replaceAll("");
		  }
	   
	   
	   

}
