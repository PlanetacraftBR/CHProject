package me.site.account;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import me.acf.FormatText.Format;
import me.acf.clan.Clan;
import me.acf.lobby.patentes.Patente;
import me.acf.protocolo.chat.ChatManager;
import me.acf.punish.PunishMananger;
import me.acf.servidor.Servidor;
import me.donate.DonateMananger;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilInv;
import me.hub.API.Util.UtilServer;
import me.hub.Bungee.Bungee;
import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.config.Config;
import me.site.account.rank.Rank;



public class Account extends MiniPlugin
{

	public Account(JavaPlugin plugin) {
		super("Account", plugin);
		AddSistema();
	
	}
	 private static Pattern VALIDO = Pattern.compile("[A-Za-z0-9_]");
	  private static HashMap<Player, String> uuid = new HashMap();
	  private static HashMap<Player, String> nome = new HashMap();
	  private static HashMap<Player, String> kdr = new HashMap();
	  private static HashMap<Player, String> cash = new HashMap();
	  private static HashMap<Player, String> coins = new HashMap();
	  private static HashMap<Player, String> nivel = new HashMap();
	  private static HashMap<Player, String> exp = new HashMap();
	  private static HashMap<Player, String> chave = new HashMap();
	  private static HashMap<Player, Rank> grupo = new HashMap();
	  private static HashMap<Player, Patente> patente = new HashMap();
	  private static HashMap<Player, String> ponto_staff = new HashMap();
	  private static HashMap<Player, String> ip = new HashMap();
	  public static String bot = "Sistema_PlanetaCraft_BR";
	  @EventHandler
	  public void AccountRemove(final PlayerQuitEvent event)
	  {
		  if (Main.plugin.getConfig().getString("Carregar").equals("Registro")) {
			  
		  }else{
			  Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
				   public void run() {
		  removeAccount(event.getPlayer());
		  Servidor.AddLeave();
		              }
	           }
	      , 5L);
		  }
	  }
	  
	  public void AddSistema()
	  {
		  String url_add = Main.site + "/API/addconta.php?nick=";
		  String site = AccountWeb.Conectar(url_add + bot);
		  System.out.print(site);
	  }
	  
	  
	  @EventHandler
	  public void Conta(PlayerLoginEvent event)
	  {
		  String url_add = Main.site + "/API/addconta.php?nick=";
		 
		   String invalidChars = getInvalidChars(event.getPlayer().getName());
		   if (event.getPlayer().getName().length() > 16)
		   {
			   event.getPlayer().kickPlayer("§cSeu Nick esta invalido.");
			   return;
		   }
		   if (!invalidChars.isEmpty())
		    {
			   event.getPlayer().kickPlayer("§cSeu Nick esta invalido.");
		   return;
		    }
		   if (ChatManager.ProtocolNick(event.getPlayer()))
		   {
			   event.getPlayer().kickPlayer("§7Erro ao verificar seu nome, porfavor entre em contato com nossa staff.\nVerifique que seu nome não contem palavras inadequadas");
			   return;
		   }
		   
		    try
		    {
				  String site = AccountWeb.Conectar(url_add + event.getPlayer().getName());
				  System.out.print(site);
				  loadAccount(event.getPlayer());
				  if (site.contains("Servidor da mojang esta offline e nao conseguimos validar o jogador"))
		  		{
					  event.getPlayer().kickPlayer("§7Servidor da mojang esta offline tente novamente mais tarde");
		  		}
		    if ((Servidor.GetMain()) && (!getRank(event.getPlayer()).Has(event.getPlayer(), Rank.STAFFM, false)))
		    {
		    	 event.getPlayer().kickPlayer("§f§oVocê não tem permissão para entrar neste servidor!");		 
		    }
		    if (Bukkit.getOnlinePlayers().size() >= Bukkit.getServer().getMaxPlayers())
		    {
		      if (getRank(event.getPlayer()).Has(event.getPlayer(), Rank.VIPM, false))
		      {
		        for (Player p : UtilServer.Jogadores())
		        {
		          	if (!getRank(p).Has(event.getPlayer(), Rank.VIPM, false))
		          	{
		                event.allow();
				        event.setResult(PlayerLoginEvent.Result.ALLOWED);
		          		p.kickPlayer("§f§lUm Jogador VIP+ robou sua slot!\n§f§lServidor lotado compre §5§lVIP+ §f§lem nossa loja!\nAcesse: §a§l " + Main.site);
		          	   	return;
		          	}
		        }
		        event.getPlayer().kickPlayer("§f§lServidor lotado somente VIP+!");
		      }
		      
		      event.getPlayer().kickPlayer("§f§lServidor lotado compre §5§lVIP+ §f§lem nossa loja!\nAcesse: §a§l " + Main.site);
		    }
		    }
		    catch (Exception exception)
		    {
		    	event.getPlayer().kickPlayer("§7Erro ao recuperar informações da web, por favor, tente novamente em um minuto.");
		      exception.printStackTrace();
		    }
	  }
	  
	 
	  public static void loadAccount(Player p)
	  {
		  JSONObject obj = new JSONObject(AccountWeb.Conectar(Main.site + "/API/conta.php?nick=" + p.getName()));
		  
		  Player jog = Bukkit.getPlayerExact(p.getName());
		  uuid.put(p, obj.getString("uuid"));
		  nome.put(p, obj.getString("nome"));
		  cash.put(p, obj.getString("cash"));
		  kdr.put(p, obj.getString("kdr"));
		  nivel.put(p, obj.getString("nivel"));
		  exp.put(p, obj.getString("exp"));
		  chave.put(p, obj.getString("chaves"));
		  ponto_staff.put(p, obj.getString("pontos_staff"));
		  coins.put(p, obj.getString("planets"));
		  String group = obj.getString("grupo");
		  grupo.put(p, Rank.valueOf(group));
		  String patentes = obj.getString("patente");
		  patente.put(p, Patente.valueOf(patentes));
		  ip.put(p, obj.getString("ip"));
		  PunishMananger.Ban_Motivo.put(p, obj.getString("ban_mt"));
		  PunishMananger.Ban_Staff.put(p, obj.getString("ban_st"));
		  PunishMananger.Dia_Ban.put(p, obj.getString("ban_at"));
		  PunishMananger.Exp_Ban.put(p, obj.getString("ban_es"));
		  DonateMananger.Exp_VIP.put(p, obj.getString("vip_es"));
	       if (!obj.getString("clan").contains("null"))
	       {
	    	   Clan.clan.put(p, obj.getString("clan"));
	       }
	       Config.Criar(p);
		   if ((Account.getRank(p)).Has(p, Rank.STAFF, false))
		   {
			   try {
			   int ponto = Integer.parseInt(Account.getPonto(p));

			   if (ponto <= 0)
			   {
				   
			   }
			   }
		       catch (Exception exception)
   		    {

		    Format.Erro("Não foi possivel capturar seus pontos.", p);
   		    }
		   }
	  }
	  
	  public static void removeAccount(Player p)
	  {
	         for (Player online : Bukkit.getOnlinePlayers()) {
			    	String rank = "" + Account.getRank(online);
			    	if (Account.getRank(online).Has(online, Rank.VIP, false))
			    	{
			    	p.getPlayer().getScoreboard().getTeam(rank.replace("+", "M")).removePlayer(online);
			    	online.getPlayer().getScoreboard().getTeam(rank.replace("+", "M")).removePlayer(online);
		            ScoreboardAPI.scoreboard.getTeam(rank.replace("+", "M")).removePlayer(online);
			    	}
			   
		         }
		  uuid.remove(Bukkit.getOfflinePlayer(p.getName()));
		  nome.remove(Bukkit.getOfflinePlayer(p.getName()));
		  kdr.remove(Bukkit.getOfflinePlayer(p.getName()));
		  cash.remove(Bukkit.getOfflinePlayer(p.getName()));
		  nivel.remove(Bukkit.getOfflinePlayer(p.getName()));
		  exp.remove(Bukkit.getOfflinePlayer(p.getName()));
		  chave.remove(Bukkit.getOfflinePlayer(p.getName()));
		  grupo.remove(Bukkit.getOfflinePlayer(p.getName()));
		  patente.remove(Bukkit.getOfflinePlayer(p.getName()));
		  ponto_staff.remove(Bukkit.getOfflinePlayer(p.getName()));
		  ip.remove(Bukkit.getOfflinePlayer(p.getName()));
		  Clan.clan.remove(Bukkit.getOfflinePlayer(p.getName()));
		  DonateMananger.Exp_VIP.remove(Bukkit.getOfflinePlayer(p.getName()));
		  PunishMananger.Ban_Motivo.remove(Bukkit.getOfflinePlayer(p.getName()));
		  PunishMananger.Ban_Staff.remove(Bukkit.getOfflinePlayer(p.getName()));
		  PunishMananger.Dia_Ban.remove(Bukkit.getOfflinePlayer(p.getName()));
		  PunishMananger.Exp_Ban.remove(Bukkit.getOfflinePlayer(p.getName()));
		  
	  }
	  
	  public static void UpdateAccount(Player p)
	  {
		  removeAccount(p);
		  loadAccount(p);
	  }
	  
	  public static String getCash(Player p)
	  {
		  return (String)cash.get(p);
	  }
	  public static String getKdr(Player p)
	  {
		  return (String)kdr.get(p);
	  }
	  public static String getCoins(Player p)
	  {
		  return (String)coins.get(p).replace(".", "");
	  }
	  public static String getIP(Player p)
	  {
		  return (String)ip.get(p);
	  }
	  public static Rank getRank(Player p)
	  {
		  return (Rank)grupo.get(p);
	  }
	  public static String getChave(Player p)
	  {
		  return (String)chave.get(p);
	  }
	  public static int getChaveAPI(Player p)
	  {
		  return Integer.parseInt((String)chave.get(p));
	  }
	  public static String getUuid(Player p)
	  {
		  return (String)uuid.get(p);
	  }
	  public static Patente getPatente(Player p)
	  {
		  return (Patente)patente.get(p);
	  }
	  public static String getNivel(Player p)
	  {
		  return (String)nivel.get(p);
	  }
	  
	  public static int getNivel_Int(Player p)
	  {
		  return Integer.getInteger(nivel.get(p));
	  }
	  
	  public static String getPonto(Player p)
	  {
		  return (String)ponto_staff.get(p);
	  }
	  
	 public static String getUuidOff(String p)
	 {
		 JSONObject obj = new JSONObject(AccountWeb.Conectar(Main.site + "/API/conta.php?nick=" + p));
		 return obj.getString("uuid");
	 }
     	  
	  
	  public static void AddCoins(Player p, double contidade)
	  {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + p.getName() + "&quantidade=" + contidade, "OK");
		     System.out.print(buscar);
	  }
	  
	  public static void AddCash(Player p, double contidade)
	  {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=ADD&nick=" + p.getName() + "&quantidade=" + contidade, "OK");
		     System.out.print(buscar);
	  }
	  public static void Reset(Player p)
	  {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=RESET&nick=" + p.getName(), "OK");
		     System.out.print(buscar);
			 String buscars = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=RESET&nick=" + p.getName(), "OK");
		     System.out.print(buscars);
	  }
	  public static void AddChave(Player p, int contidade)
	  {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/chave.php?modo=ADD&nick=" + p.getName() + "&quantidade=" + contidade, "OK");
		     System.out.print(buscar);
	  }
	  public static void removeChave(Player p, int contidade)
	  {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/chave.php?modo=VENDA&nick=" + p.getName() + "&quantidade=" + contidade, "OK");
		     System.out.print(buscar);
	  }
	  
	  public static void AddExp(Player p, String contidade)
	  {

		   
			 String buscar = AccountWeb.Conectar(Main.site + "/API/exp.php?modo=UPAR&nick=" + p.getName() + "&quantidade=" + contidade);
		    
		    
			 System.out.print(buscar);
		    
	  }
	  
	  

	  
	
	   
	   public static String getInvalidChars(String s)
		  {
		    return VALIDO.matcher(s).replaceAll("");
		  }
	  }




