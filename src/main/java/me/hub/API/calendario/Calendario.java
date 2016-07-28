package me.hub.API.calendario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.hub.Main;
import me.hub.API.Util.UtilTime;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.UnixRuntime.HostFS;

	
public class Calendario {

	  public static String evento = "Nenhum";
	  
	  public Calendario ()
	  {
		  System.out.print("Calendario Ativo.");
		  Verificar();
	  }
	  
	  public static void Verificar()
	  {
        try {
			AnoNovo();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Bukkit.broadcastMessage(evento);
	  }
	  
	
     
       private static int RetornarAno()
       {
    	   DateFormat dateFormat = new SimpleDateFormat("yyyy");
    		Date date = new Date();
    		return Integer.parseInt(dateFormat.format(date));

       }
	  
	  public static void AnoNovo() throws ParseException
	  {
		  Calendar calendar = new GregorianCalendar();
	    	SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	    	Date date = new Date();
	    	calendar.setTime(date);
	    	
	        String s1 = out.format(calendar.getTime());
	        String s2 = "01/01/" + RetornarAno() + " - 23:59:59";
	        
	        Date d1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s1);
	        Date d2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s2);

	        long sec = d2.getTime() - d1.getTime();
	        sec = (sec / (1000));
	        if ((sec < 86400) && (sec > 0))
	        {
	        	System.out.print("Hoje é ano novo!");
	        	evento = "Ano Novo";
	        }
	  }
	
	
	
	
	  
}
