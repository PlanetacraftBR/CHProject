package me.security.GeoIP.API.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

import me.hub.Main;

public class Load
{
  private static YamlConfiguration config;
  public static boolean protectionCountry;
  public static List<String> countriesWhitelist = new ArrayList();
  public static List<String> countriesBlacklist = new ArrayList();
  public static boolean protectionProxy;
  public static List<String> addressBlacklist = new ArrayList();
  
  public static void loadConfig()
  {
    config = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder(), "config.yml"));
    
    protectionCountry = config.getBoolean("protection.country.enabled");
    countriesWhitelist = config.getStringList("protection.country.countriesWhitelist");
    countriesBlacklist = config.getStringList("protection.country.countriesBlacklist");
  }
  
}
