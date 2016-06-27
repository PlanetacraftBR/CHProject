/**
 * 
 */
package me.security;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilTitle;
import me.hub.Admin.Staff;
import me.hub.Bungee.Bungee;
import me.security.move.AntiMove;
import me.security.system.staff.cage.events.CageEvent;
import me.site.account.AccountWeb;

/**
 * @author adriancf
 *
 */
public class SecurityManager extends MiniPlugin {

	public static List<Player> sj = new ArrayList<>(); 
	
	
	public SecurityManager(JavaPlugin plugin)
	{
		super("Sistema de Security Unix",plugin);
		AntiMove anti = new AntiMove(plugin);
	    CageEvent event = new CageEvent(plugin);	
	}
	
	
	public static void Add(Player p)
	{
		sj.add(p);
		System.out.print("Jogador foi adicionado no sistema " + p.getName());
	}
	
	@EventHandler
	public void Ping(ServerListPingEvent event)
	{
		event.setMotd("§6Acesso restrido servidor privado.");
	}
	
	
	  @EventHandler
	  public void Kick(PlayerKickEvent event)
	  {
	    if (event.getReason().equalsIgnoreCase("You logged in from another location")) {
	      event.setCancelled(true);
	      return;
	    }
	    if (event.getReason().equalsIgnoreCase("Invalid move packet received")) {
		      event.setCancelled(true);
		return;    
	    }
	    if (event.getReason().equals("Illegal characters in chat")) {
	        event.setCancelled(true);
	      return;
	      }
	      
	    if (event.getReason().contains("fly")) {
	        event.setCancelled(true);
	        return;
	      }

	    if (event.getReason().equals("Illegal position")) {
	        event.setCancelled(true);
	        return;
	      }
	      
	    if (event.getReason().equals("Kicked for spamming")) {
	        event.setCancelled(true);
	        return;
	      }
	    
	    if (event.getReason().equals("The authentication are currently down for maintenance.")) {
	        event.setCancelled(true);
	        return;
	      }
	      
	    Bungee.KickPlayer(event.getPlayer(), event.getReason());
	    if (!VerificarBungee(event.getPlayer()))
		    event.setCancelled(false);
	    else
	    	event.setCancelled(true);
	  }
	
	  public static boolean VerificarBungee(Player p)
	  {
		    String IP = p.getAddress().getHostString();
		    Bukkit.getLogger().info("IP: " + IP);
			if (!IP.contains("192.99.3.96")) {
				Staff.MandarMSGBungee("§c§lSecurity §7Tentativa invalida de login do jogador §6" + p.getName() + " §7IP: " + IP);	
				return false;
			}
			
		  return true;
	  }
	  
	  
	@EventHandler
	public void Entrar (PlayerJoinEvent event)
	{
		if (!VerificarBungee(event.getPlayer()))
			event.getPlayer().kickPlayer("§6Servidor Privado");
		
	}
	
	
	
	
	
	
	
	public static void AddSenha(String senha,String email, Player p,boolean teleport)
	{
		String pass = sha256(senha);
		 String verificar = AccountWeb.Conectar(Main.site + "/API/registrar.php?nick=" + p.getName() + "&senha=" + pass + "&email=" + email);
		if (verificar.contains("ERRO")) {
			  Format.Erro("E-mail digitado já está registrado no servidor use outro !", p);
			return;
		}
		if (teleport)
		Bungee.SendPlayerToServer(p, "lobby");
        Format.Comando("Security", "Você foi registrado com exito",p);
		   
		UtilTitle log = new UtilTitle("Registrado com exito","Obrigado",4,5,4);
		log.setTitleColor(ChatColor.GREEN);
        log.setSubtitleColor(ChatColor.RED);
	    log.send(p);
        LoginManager.registrar.remove(p);
	}
	public static boolean VerSenha(String senha,String email, Player p)
	{
		String pass = sha256(senha);
        String verificar = AccountWeb.Conectar(Main.site + "/API/logar.php?nick=" + p.getName() +"&senha=" + pass +"&email=" + email);
if (verificar.contains("ERRO"))
	return false;
        return true;
	}
	
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
	
    public static boolean SenhaSegura(Player p, String senha)
    {
    	if (senha.equals(p.getName()))
    		return false;
    	
    	if (senha.contains("123"))
    		return false;
   
    	if (senha.length() < 5)
    		return false;

    	if (senha.contains("qwert"))
    		return false;
    
    	if (senha.contains("passworld"))
    		return false;
    
    	if (senha.contains("asd"))
    		return false;
    	if (senha.contains("321"))
    		return false;
    	if (senha.contains("senha"))
    		return false;
    	if (senha.contains("planeta"))
    		return false;
    	if (senha.contains("jotinha"))
    		return false;
    	if (senha.contains("adriancf"))
    		return false;
    	if (senha.contains("password"))
    		return false;
    	if (senha.contains("minha"))
    		return false;
    	if (senha.contains("meu"))
    		return false;
    	if (senha.contains(p.getName().toLowerCase()))
    		return false;
    	
    	
    	return true;
    }

    public static boolean Email(String email)
    {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
        Matcher m = p.matcher(email); 
        if (m.find())
        	return true;   
        if (email.contains("@trbvn.com"))
        	return false;
     
        return false;
    }
}
