/**
 * 
 */
package me.hub.Admin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.hub.Main;
import me.hub.API.Util.UtilServer;


/**
 * @author adriancf
 *
 */
public class Staff {

	public static List<String> Staffs = new ArrayList<>();
	public static int Total = 0;
	
	public static void Load()
	{
		LoaderStaff("DONO");
		LoaderStaff("DEV");
		LoaderStaff("STAFFM");
		LoaderStaff("STAFF");
		System.out.print("Staff> Carregando " + Total + " Staffers do servidor!");
	}
	
	public static void MandarMSGBungee(String msg)
	{
		Player p = null;
		  for (Player p2 : UtilServer.Jogadores())
		  {
			  p = p2;
		  }
		for (String player : Staffs) {
			try {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		out.writeUTF("Message");
	    out.writeUTF(player);
		out.writeUTF(msg);
	
		p.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
		  
		
		} catch (Exception e) {
		
		}
		}
	}
	
	private static void LoaderStaff(String grupo)
	{
	     URL host = null;
	      try
	      {
	        host = new URL(Main.site + "/API/contas.php?grupo=" + grupo);
	       
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
	        	String json = inputLine;
	        	if (!json.contains("ERRO"))
	        	{
	        		Staffs.add(json);
	        		Total++;
	        	}
	        }
	        reader.close();
	       
	      }
	      catch (IOException e)
	      {
	       
	      }
	}
}
