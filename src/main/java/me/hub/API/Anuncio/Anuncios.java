/**
 * 
 */
package me.hub.API.Anuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilServer;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;

/**
 * @author adriancf
 *
 */
public class Anuncios extends MiniPlugin {

	/**
	 * @param moduleName
	 * @param plugin
	 */
	public static int anuncios;
    static HashMap<Integer, String> anuncio = new HashMap<>();
    public static List<String> lista = new ArrayList<>();
    public static List<String> executar = new ArrayList<>();
	public Anuncios(JavaPlugin plugin) {
		super("Anuncios", plugin);
		LoaderMSG("Todos");
		LoaderMSG(Bukkit.getServerName());
		System.out.print("Anuncio> Carregando " + anuncios + " anuncios");
	}
	
	   @EventHandler
	    public void Utils(Atualizar event)  {
	      if (event.getType() != ModosUpdate.SLOWER) {
	        return;
	      }
	      for (Player cur : UtilServer.Jogadores())
	        {
	          cur.sendMessage(Anuncio().replace("&", "§").replace("{Player}", cur.getName()).replace("{Linha}", "\n").replace("{ServerName}", Bukkit.getServerName()));
	        }
	   }
	   
	private String Anuncio()
	{
	if (executar.size() < 1)
	{
		for (String list : lista)
		{
			executar.add(list);
		}
		return "§f§lSeja-Bem Vindo ao §6§lPlanetaCraft_BR";
	}
	else
	{
		for (String ord : executar)
		{
			executar.remove(ord);
			return ord;
		}
	}
		return null;
	}
	
	
	public static void LoaderMSG(String servidor)
	{
   		anuncio.clear();
   		anuncios = 0;
	     URL host = null;
	      try
	      {
	        host = new URL(Main.site + "/API/anuncio.php?servidor=" + servidor);
	       
	      }
	      catch (MalformedURLException e1)
	      {
	       
	      }
	      URLConnection connection = null;
	      try
	      {
	        connection = host.openConnection();
	   
	        BufferedReader reader = null;
	     
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	         
	        String inputLine;
	        while ((inputLine = reader.readLine()) != null)
	        {
	        	if (!inputLine.contains("ERRO"))
	        	{
	            	String txtUTF8 = new String(inputLine.getBytes("UTF-8"));
	            	JSONObject obj = new JSONObject(txtUTF8);
	        		anuncios++;
	        		anuncio.put(anuncios, "§b§l" + obj.getString("Tag") + " §e" + obj.getString("Texto"));
	        		lista.add("§b§l" + obj.getString("Tag") + " §e" + obj.getString("Texto"));
	        	}
	        }
	        reader.close();
	       
	      }
	      catch (IOException e)
	      {
	       
	      }
	}

}
