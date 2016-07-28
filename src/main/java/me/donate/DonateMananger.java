package me.donate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import me.acf.lobby.patentes.Patente;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Enchant;
import me.hub.API.Util.UtilTime;
import me.hub.Scoreboard.ScoreboardAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

public class DonateMananger extends MiniPlugin {

	  public static HashMap<Player, String> Exp_VIP = new HashMap();
	  public static boolean USAR = false;
	public DonateMananger(JavaPlugin plugin) {
		super("Donate", plugin);

	}
	
	
	@EventHandler
	public void Entrar(PlayerJoinEvent e)
	{
		   if ((Account.getRank(e.getPlayer())).Has(e.getPlayer(), Rank.DONO, false))
		   {
			   e.getPlayer().setOp(true);
			   e.getPlayer().sendMessage("§5§l"+ Bukkit.getServerName() +" §7Você acabou de receber OP !");
		   }
		if (getExpData(e.getPlayer()).equals(""))
    		return;
		 try {
    	Calendar calendar = new GregorianCalendar();
    	SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
    	Date date = new Date();
    	calendar.setTime(date);
        
        String s1 = out.format(calendar.getTime());
        String s2 = getExpData(e.getPlayer());
        
	       Date d1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s1);
	        Date d2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s2);
	        long sec = d2.getTime() - d1.getTime();
	     String   tempo = UtilTime.convertString(sec, 0, UtilTime.TimeUnit.FIT);
		e.getPlayer().sendMessage("§7§lSEU VIP TERMINA EM§a  " + tempo + "§7§l (" + getExpData(e.getPlayer()) + ")");
		 }
		 catch (Exception exception)
		    {
		
			    }
	}
	@EventHandler
	public void Entrar(PlayerLoginEvent e)
	{
	    Calendar now = Calendar.getInstance();
	   String tempo = "";

	    try {
	    	if (getExpData(e.getPlayer()).equals(""))
	    		return;
	    	Calendar calendar = new GregorianCalendar();
	    	SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	    	Date date = new Date();
	    	calendar.setTime(date);
	        
	        String s1 = out.format(calendar.getTime());
	        String s2 = getExpData(e.getPlayer());
	        
	        Date d1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s1);
	        Date d2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").parse(s2);
	        long sec = d2.getTime() - d1.getTime();
	        if (sec < 0)
	        {
	        	String buscar = AccountWeb.Conectar(Main.site + "/API/grupo.php?modo=VIP&nick=" + e.getPlayer().getName(), "OK");
        	    System.out.print(buscar);
	        	Account.UpdateAccount(e.getPlayer());
        	    return;
	        }
	    	
	    } 	    catch (Exception exception)
	    {
		      e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§7Erro ao recuperar informações da web, por favor, tente novamente em um minuto.");
		    }

	
	}
	
	public static String getExpData(Player p)
	{
		  return (String)Exp_VIP.get(p);
	}
	
	   @EventHandler
	   public void VIP(final PlayerInteractEvent event)
	   {
	     Player jogador = event.getPlayer();
	     if (USAR) {
	     if (jogador.getGameMode() == GameMode.CREATIVE)
	       return;
	     if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	       if (event.getClickedBlock().getType() == null) {
	         return;
	       }
	       if (event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
	    	   event.setCancelled(true);
	    	   
			   if ((Account.getRank(jogador)).Has(jogador, Rank.VIPM, true))
			   {
				   VIPOpen(jogador);
			   }
	       }
	       if (event.getClickedBlock().getType() == Material.BEACON) {
	    	   
	       }
	     }
	     }
	   }
	
	    public static void VIPOpen(Player p)
	    {
	      Inventory inv = Bukkit.getServer().createInventory(p, 9, "Patente UPDATE.");
	      
	      
	      AddItem(inv, false, Material.TRIPWIRE_HOOK,1,(byte) 0, "§aPatente UP", "§eProxima patente " + Patente.ProxPatende(p), 2);
	      AddItem(inv, false, Material.REDSTONE,1,(byte) 0, "§c" + Account.getPatente(p).GetTag(false), "§eNenhuma habilidade", 6);
	      
	      
	      p.openInventory(inv);
	    
	    }
	    
	    
	    @EventHandler
		  public void Iventarioitens(InventoryClickEvent e)
		  {
		    Player jogador = (Player)e.getWhoClicked();
		    if ((e.getInventory().getTitle().contains("Patente UPDATE.")) && 
		    	      (e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0))
		    	    {
		    	      if (e.getSlot() == 2)
		    	      {
		    	    	  if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Patente UP"))
		      	        {
		    	    		  jogador.closeInventory();
		    	    		  try {
		    	    			  if (Account.getPatente(jogador).Has(jogador, Patente.unix, false))
		    	    			  {
		    	    				  jogador.sendMessage("§cVocê esta na patente maxima");
		    	    				  return;
		    	    			  }
		    	    	      JSONObject buscar = new JSONObject(AccountWeb.Conectar(Main.site + "/API/exp.php?modo=VENDA&nick=" + jogador.getName() + "&quantidade=5000"));
		    	    	      System.out.print(buscar.getString("OK"));
		    	    		  ScoreboardAPI.remover(jogador);
		    	    		  Patente.UpPatende(jogador);
		    	    		  Account.UpdateAccount(jogador);
		    	    		  jogador.sendMessage("§cVocê deu update em sua patente");
		    	    		
		    	    		  }
		    	    		  catch (Exception exception)
		    	  		    {
		    	    			  jogador.sendMessage("§cOps você não tem 5 Niveis suficiente para isso!");
		    	  		    }
		    	    		  
		      	        }
		    	      }
		    	    }
		  }
	    private static void AddItem(Inventory inv, boolean encantado, Material item,int contidade, byte data,String nome, String meta, int slot)
	    {
	    	      ItemStack bau = new ItemStack(item, contidade , data);
	    	      ItemMeta metae = bau.getItemMeta();
	    	      metae.setDisplayName(nome);
	    	      ArrayList<String> desce = new ArrayList();
	    	      desce.add(meta);
	    	      metae.setLore(desce);
	    	      bau.setItemMeta(metae);
	    	      if (encantado)
	    	      inv.setItem(slot, Enchant.addGlow(bau));
	    	      else
	    	          inv.setItem(slot, bau);
	    }
}
