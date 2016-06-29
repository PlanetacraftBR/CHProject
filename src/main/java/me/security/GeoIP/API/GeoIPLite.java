package me.security.GeoIP.API;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.bukkit.Bukkit;

import me.hub.Main;
import me.security.GeoIP.LookupService;

public class GeoIPLite
{
  private static final String LICENSE = "[LICENSE] This product uses data from the GeoLite API created by MaxMind, available at http://www.maxmind.com";
  private static final String GEOIP_URL = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz";

  private static LookupService lookupService;
  
  public static boolean isDataAvailable()
  {
    if (lookupService != null) {
      return true;
    }
    final File data = new File(Main.plugin.getDataFolder(), "GeoIP.dat");
    if (data.exists()) {
      try
      {
        lookupService = new LookupService(data);
        Main.plugin.getLogger().info("[LICENSE] This product uses data from the GeoLite API created by MaxMind, available at http://www.maxmind.com");
        return true;
      }
      catch (IOException e)
      {
        return false;
      }
    }
    Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable()
    {
      public void run()
      {
        try
        {
          URL downloadUrl = new URL("http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz");
          URLConnection conn = downloadUrl.openConnection();
          conn.setConnectTimeout(10000);
          conn.connect();
          InputStream input = conn.getInputStream();
          if (conn.getURL().toString().endsWith(".gz")) {
            input = new GZIPInputStream(input);
          }
          OutputStream output = new FileOutputStream(data);
          byte[] buffer = new byte['?'];
          int length = input.read(buffer);
          while (length >= 0)
          {
            output.write(buffer, 0, length);
            length = input.read(buffer);
          }
          output.close();
          input.close();
        }
        catch (IOException localIOException) {}
      }
    });
    return false;
  }
  
  public static String getCountryCode(String ip)
  {
    if (isDataAvailable()) {
      return lookupService.getCountry(ip).getCode();
    }
    return "--";
  }
  
  public static String getCountryName(String ip)
  {
    if (isDataAvailable()) {
      return lookupService.getCountry(ip).getName();
    }
    return "N/A";
  }
}
