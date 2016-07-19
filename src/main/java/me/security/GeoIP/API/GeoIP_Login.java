package me.security.GeoIP.API;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.hub.API.Util.UtilPlayer;
import me.security.GeoIP.API.config.Load;
import me.site.account.Account;

public class GeoIP_Login
  implements Listener
{
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPreLogin(PlayerJoinEvent event)
  {
    if (Load.protectionCountry)
    {
      String countryCode = GeoIPLite.getCountryCode(Account.getIP(event.getPlayer()));
      if ((!Load.countriesBlacklist.isEmpty()) && (Load.countriesBlacklist.contains(countryCode)))
      {
       UtilPlayer.Kick(event.getPlayer(), "O seu país está banido deste servidor");
        return;
      }
      if ((!Load.countriesWhitelist.isEmpty()) && (!Load.countriesWhitelist.contains(countryCode)))
      {
    	  UtilPlayer.Kick(event.getPlayer(), "O seu país está banido deste servidor");
        return;
      }
    }
  }
}
