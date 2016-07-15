package me.site.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;


public class AccountWeb {

	
	public AccountWeb(String link)
	{
		System.out.print(Conectar(link));
	}
	
	public static String Conectar(String link)
    {
	     URL host = null;
	     String texto = "{'ERRO':'NADA'}";
	      try
	      {
	        host = new URL(link);
	       
	      }
	      catch (MalformedURLException e1)
	      {
	    	   e1.printStackTrace();
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
	        	String txtUTF8 = new String(inputLine.getBytes("UTF-8"));
	            texto = txtUTF8;
	        }
	        reader.close();
	      }
	      catch (IOException e)
	      {
              e.printStackTrace();
	      }
	      System.out.print(texto);
      return texto;
    }
	
	public static String Conectar (String link, String buscar)
    {
    	String respond = null;
      URL host = null;
      System.out.print("Conenctando no " + link);
      try
      {
        host = new URL(link);
       
      }
      catch (MalformedURLException e1)
      {
       e1.printStackTrace();
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
        	System.out.print(inputLine);
        	JSONObject obj = new JSONObject(json);
            respond = obj.getString(buscar);
        }
        reader.close();
      
      }
      catch (Exception e)
      {
    	 e.printStackTrace();
      }
      return respond;
    }
	
}
