package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilServer;
import me.security.system.staff.cage.CageCreate;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class AdminEvents extends MiniPlugin {
	
	/* Usuario que criou este lixo
	 * Deixo um recado
	 * Vai jogar Agar.io pf 
       Ou vai jogar no craftlandia que vc ganha mais serio ;-;
       By @AdrianCF #Reload
       Obrigado Thanks
	 */
	
	  public static ArrayList<String> inadmin = new ArrayList();
	  public static ArrayList<String> frozen = new ArrayList();
	  public static ArrayList<String> reportado = new ArrayList();
	  public static ArrayList<String> reporter = new ArrayList();
	  public static ArrayList<String> motivo = new ArrayList();
	  public static HashMap<String, ItemStack[]> saveinv = new HashMap();
	  public static HashMap<String, ItemStack[]> savearm = new HashMap();
	  public static HashMap<String, Integer> testes = new HashMap();
	  public static HashMap<String, String> ver = new HashMap();
	  public static String forcefield;
	  public static String killaura;
	  
	public AdminEvents(JavaPlugin plugin) {
		super("AdminEvents", plugin);
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void ESair(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		inadmin.remove(p.getName());
		frozen.remove(p.getName());
		reportado.remove(p.getName());
		reporter.remove(p.getName());
		motivo.remove(p.getName());
		saveinv.remove(p.getName());
		savearm.remove(p.getName());
		testes.remove(p.getName());
		ver.remove(p.getName());
	}
	
  @EventHandler
  void onInteract(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (inadmin.contains(p.getName()))
    {
      Player[] arrayOfPlayer1;
      int j;
      int i;
      if (p.getItemInHand().getType().equals(Material.MAGMA_CREAM))
      {
        p.getInventory().clear();
        
        p.sendMessage("§aVoce saiu Do Modo Admin!");
        
        p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
        p.getInventory().setArmorContents((ItemStack[])savearm.get(p.getName()));
        
        p.updateInventory();
        
        inadmin.remove(p.getName());
        
        new BukkitRunnable()
        {
          public void run()
          {
            inadmin.add(p.getName());
            
            saveinv.put(p.getName(), p.getInventory().getContents());
            savearm.put(p.getName(), p.getInventory().getArmorContents());
            
            p.getInventory().clear();
            
            p.sendMessage("§aVoce entrou No Modo Admin!");
            
            ItemStack info = new ItemStack(Material.BLAZE_ROD);
            ItemMeta infometa = info.getItemMeta();
            infometa.setDisplayName("§aInformaçoes.");
            Object infolore = new ArrayList();
            ((List)infolore).add("§aUse Essa Blaze-Rod Para Ter Informaçoes De Um Determinado Jogador.");
            info.setItemMeta(infometa);
            
            ItemStack cage = new ItemStack(Material.GLASS);
            ItemMeta cagemeta = cage.getItemMeta();
            cagemeta.setDisplayName("§aCage.");
            List<String> cagelore = new ArrayList();
            cagelore.add("§aUse Esse Vidro Para Prender Jogadores.");
            cagemeta.setLore(cagelore);
            cage.setItemMeta(cagemeta);
            
            ItemStack online = new ItemStack(Material.SLIME_BALL);
            ItemMeta onlinemeta = online.getItemMeta();
            onlinemeta.setDisplayName("§aOnline.");
            List<String> onlinelore = new ArrayList();
            onlinelore.add("§aUse Essa Slime-Ball Para Teleportar-se Para Um Jogador");
            onlinemeta.setLore(onlinelore);
            online.setItemMeta(onlinemeta);
            
            ItemStack troca = new ItemStack(Material.MAGMA_CREAM);
            ItemMeta trocameta = troca.getItemMeta();
            trocameta.setDisplayName("§aTroca§6-§fRapida!");
            List<String> trocalore = new ArrayList();
            trocalore.add("§aUse Essa Magma-Cream Para Sair e Voltar Para o Modo Admin.");
            trocameta.setLore(trocalore);
            troca.setItemMeta(trocameta);
            
            ItemStack sair = new ItemStack(Material.EYE_OF_ENDER);
            ItemMeta sairmeta = sair.getItemMeta();
            sairmeta.setDisplayName("§aSair.");
            List<String> sairlore = new ArrayList();
            sairlore.add("§aUse Esse Olho Do Fim Para Sair Do Modo Admin.");
            sairmeta.setLore(sairlore);
            sair.setItemMeta(sairmeta);
            
            ItemStack uteis = new ItemStack(Material.GREEN_RECORD);
            ItemMeta uteismeta = uteis.getItemMeta();
            uteismeta.setDisplayName("§aTestes");
            List<String> uteislore = new ArrayList();
            uteislore.add("§aUse Este Disco Para testar Um Jogador.");
            uteismeta.setLore(uteislore);
            uteis.setItemMeta(uteismeta);
            
            p.getInventory().setItem(0, info);
            p.getInventory().setItem(2, online);
            p.getInventory().setItem(4, cage);
            p.getInventory().setItem(5, uteis);
            p.getInventory().setItem(7, troca);
            p.getInventory().setItem(8, sair);
            
            p.updateInventory();
          }
        }.runTaskLater(Main.plugin, 5L);
      }
      else if (p.getItemInHand().getType().equals(Material.EYE_OF_ENDER))
      {
        e.setCancelled(true);
        
        p.getInventory().clear();
        
        p.sendMessage("§aVoce saiu Do Modo Admin!");
        
        p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
        p.getInventory().setArmorContents((ItemStack[])savearm.get(p.getName()));
        
        p.updateInventory();
        
        Admin.admin.remove(p);
        
	    p.setGameMode(org.bukkit.GameMode.SURVIVAL);
        p.setAllowFlight(false);
		   
     
        me.hub.Admin.Admin.Revelar(p);
        
        inadmin.remove(p.getName());
      }
      else if (p.getItemInHand().getType().equals(Material.SLIME_BALL))
      {
        Inventory inv = Bukkit.createInventory(p, 54, "§bOnline");
			for (Player online : UtilServer.Jogadores())
			{
          if (!inadmin.contains(online.getName()))
          {
            ItemStack head = new ItemStack(Material.SKULL_ITEM);
            head.setDurability((short)3);
            SkullMeta headmeta = (SkullMeta)head.getItemMeta();
            headmeta.setOwner(online.getName());
            headmeta.setDisplayName(online.getName());
            List<String> headlore = new ArrayList();
            headlore.add("§f=====§6()§f=====");
            headlore.add("§bKILLS §f: §6" + online.getStatistic(Statistic.PLAYER_KILLS));
            headlore.add("§bDEATHS §f: §6" + online.getStatistic(Statistic.DEATHS));
            headlore.add("§f=====§6()§f=====");
            headmeta.setLore(headlore);
            head.setItemMeta(headmeta);
            inv.addItem(new ItemStack[] { head });
          }
        }
        p.openInventory(inv);
      }
    }
  }
  
  @EventHandler
  public void onBlockDamage(BlockDamageEvent e) {
	    Player p = e.getPlayer();
	    Block b = e.getBlock();
        if (p.getItemInHand().getType().equals(Material.GLASS)){
        	if (b.getType().equals(Material.GLASS)){
        	   if (Admin.admin.contains(p)){
        		if ((Account.getRank(p)).Has(p, Rank.STAFF, false))
				   {
        		b.setType(Material.AIR);
        		e.setCancelled(true);
				 }
        	   }
        	}
        }
  }
  
  @EventHandler
  void onInteractEntity(PlayerInteractEntityEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getRightClicked() instanceof Player))
    {
      Player r = (Player)e.getRightClicked();
      if (inadmin.contains(p.getName())) {
        if (p.getItemInHand().getType().equals(Material.GLASS))
        {
          CageCreate.CriarCage(r, p);
        }
        else if (p.getItemInHand().getType().equals(Material.BLAZE_ROD))
        {
          p.sendMessage("§aInformações Do Jogador §f : &6 "+r.getName());
          p.sendMessage("§a ");
          p.chat("/conta "+r.getName());
        }
        else if (p.getItemInHand().getType().equals(Material.AIR))
        {
          PlayerInventory inv = r.getInventory();
          
          p.openInventory(inv);
        }
        else if (p.getItemInHand().getType().equals(Material.GREEN_RECORD))
        {
          Inventory inv = Bukkit.createInventory(p, 27, "§aTestes");
          
          ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
          vidro.setDurability((short)1);
          ItemMeta vidrometa = vidro.getItemMeta();
          vidrometa.setDisplayName("§bkAdmin!");
          vidro.setItemMeta(vidrometa);
          
          ItemStack autosoup = new ItemStack(Material.MUSHROOM_SOUP);
          ItemMeta autosoupmeta = autosoup.getItemMeta();
          autosoupmeta.setDisplayName("§6Auto§7-§fSoup.");
          autosoup.setItemMeta(autosoupmeta);
          
          ItemStack killaura = new ItemStack(Material.IRON_FENCE);
          ItemMeta killaurameta = killaura.getItemMeta();
          killaurameta.setDisplayName("§6Kill§7-§fAura.");
          killaura.setItemMeta(killaurameta);
          
          ItemStack forcefield = new ItemStack(Material.MONSTER_EGG);
          forcefield.setDurability((short)65);
          ItemMeta forcefieldmeta = forcefield.getItemMeta();
          forcefieldmeta.setDisplayName("§6Force§7-§fField.");
          forcefield.setItemMeta(forcefieldmeta);
          
          ItemStack nofall = new ItemStack(Material.FEATHER);
          ItemMeta nofallmeta = nofall.getItemMeta();
          nofallmeta.setDisplayName("§6Nofall");
          nofall.setItemMeta(nofallmeta);
          
          inv.setItem(0, autosoup);
          
          inv.setItem(4, killaura);
          
          inv.setItem(8, nofall);
          
          inv.setItem(22, forcefield);

          p.openInventory(inv);
          testes.put(r.getName(), Integer.valueOf(0));
        }
      }
    }
  }
  
  @EventHandler
  void onInvClick(InventoryClickEvent e)
  {
    Inventory inv = e.getInventory();
    
    ItemStack i = e.getCurrentItem();
    
    final Player p = (Player)e.getWhoClicked();
    if (inv.getTitle().equals("§bOnline"))
    {
      e.setCancelled(true);
      if ((i == null) || (i.getType().equals(Material.AIR)) || (!i.hasItemMeta()))
      {
        p.closeInventory();
        e.setCancelled(true);
        return;
      }
      if (i.getType().equals(Material.SKULL_ITEM))
      {
        Player r = Bukkit.getPlayer(ChatColor.stripColor(i.getItemMeta().getDisplayName()));
        
        p.teleport(r);
      }
    }
    else if (inv.getTitle().equals("§aTestes"))
    {
      e.setCancelled(true);
      if ((i == null) || (i.getType().equals(Material.AIR)) || (!i.hasItemMeta()))
      {
        p.closeInventory();
        e.setCancelled(true);
        return;
      }
      if (i.getType().equals(Material.MUSHROOM_SOUP))
      {
        e.setCancelled(true);
        Set<String> nhs = testes.keySet();
        for (String r : nhs)
        {
          final Player rr = Bukkit.getPlayer(r);
          
          testes.get(rr.getName());
          if (rr != null)
          {
            saveinv.put(rr.getName(), rr.getInventory().getContents());
            savearm.put(rr.getName(), rr.getInventory().getArmorContents());
            rr.getInventory().clear();
            PlayerInventory rrinv = rr.getInventory();
            
            p.openInventory(rrinv);
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(11, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 1L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(12, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 10L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(13, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 20L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(14, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 30L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(15, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 40L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                rr.damage(7.0D);
                rr.getInventory().setItem(16, new ItemStack(Material.MUSHROOM_SOUP));
              }
            }.runTaskLater(Main.plugin, 50L);
            
            new BukkitRunnable()
            {
              public void run()
              {
                p.getOpenInventory().close();
                
                rr.getInventory().clear();
                
                rr.getInventory().setContents((ItemStack[])saveinv.get(rr.getName()));
                rr.getInventory().setArmorContents((ItemStack[])savearm.get(rr.getName()));
                
                rr.setHealth(20.0D);
              }
            }.runTaskLater(Main.plugin, 75L);
          }
          testes.remove(rr.getName());
        }
      }
      else if (i.getType().equals(Material.MONSTER_EGG))
      {
        e.setCancelled(true);
        
        p.closeInventory();
        
        Set<String> nhs = testes.keySet();
        for (String r : nhs)
        {
          final Player rr = Bukkit.getPlayer(r);
          
          testes.get(rr.getName());
          if (rr != null)
          {
            int x = rr.getLocation().getBlockX();
            int y = rr.getLocation().getBlockY();
            int z = rr.getLocation().getBlockZ();
            
            Location loc = new Location(rr.getWorld(), x, y, z);
            
            final Bat morcego = (Bat)p.getWorld().spawnEntity(loc, EntityType.BAT);
            
            morcego.setMetadata("morcegaum", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
            morcego.setCustomName(rr.getName());
            morcego.setCustomNameVisible(true);
            morcego.setHealth(2.5D);
            
            new BukkitRunnable()
            {
              public void run()
              {
                if (morcego.isDead()) {
                	p.sendMessage("§cO Jogador "+rr.getName()+" &cEstá Usando Force-Field! Chance &f: "+forcefield);
                } else {
                  morcego.remove();
                }
              }
            }.runTaskLater(Main.plugin, 20L);
          }
          testes.remove(rr.getName());
        }
      }
      else if (i.getType().equals(Material.IRON_FENCE))
      {
        p.closeInventory();
        
        Set<String> nhs = testes.keySet();
        for (String r : nhs)
        {
          final Player rr = Bukkit.getPlayer(r);
          
          testes.get(rr.getName());
          if (rr != null)
          {
            int x = rr.getLocation().getBlockX() + 2;
            int y = rr.getLocation().getBlockY();
            int z = rr.getLocation().getBlockZ() + 2;
            
            Location loc = new Location(rr.getWorld(), x, y, z);
            
            final Bat morcego = (Bat)p.getWorld().spawnEntity(loc, EntityType.BAT);
            
            final Bat morcego1 = (Bat)p.getWorld().spawnEntity(loc, EntityType.BAT);
            
            final Bat morcego2 = (Bat)p.getWorld().spawnEntity(loc, EntityType.BAT);
            
            final Bat morcego3 = (Bat)p.getWorld().spawnEntity(loc, EntityType.BAT);
            
            morcego.setMetadata("batiman", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
            morcego.setCustomName(rr.getName());
            morcego.setCustomNameVisible(true);
            morcego.setHealth(2.5D);
            
            morcego1.setMetadata("batiman", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
            morcego1.setCustomName(rr.getName());
            morcego1.setCustomNameVisible(true);
            morcego1.setHealth(2.5D);
            
            morcego2.setMetadata("batiman", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
            morcego2.setCustomName(rr.getName());
            morcego2.setCustomNameVisible(true);
            morcego2.setHealth(2.5D);
            
            morcego3.setMetadata("batiman", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
            morcego3.setCustomName(rr.getName());
            morcego3.setCustomNameVisible(true);
            morcego3.setHealth(2.5D);
            
            new BukkitRunnable()
            {
              public void run()
              {
                if (morcego.isDead()) {
                	p.sendMessage("§cO Jogador "+rr.getName()+" &cEstá Usando Kill-Aura! Chance &f: "+forcefield);
                } else {
                  morcego.remove();
                }
                morcego1.remove();
                
                morcego2.remove();
                
                morcego3.remove();
              }
            }.runTaskLater(Main.plugin, 20L);
          }
          testes.remove(rr.getName());
        }
      }
      else if (i.getType().equals(Material.FEATHER))
      {
        p.closeInventory();
        
        Set<String> nhs = testes.keySet();
        for (String r : nhs)
        {
          Player rr = Bukkit.getPlayer(r);
          
          testes.get(rr.getName());
          if (rr != null) {
                  Vector vector = new Vector(0.0D, 2.5D, 0.0D);
                  
                  rr.setVelocity(vector);
                  
                  rr.setMetadata("nofall", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
                
          }
          testes.remove(rr.getName());
        }
      }
    }
    else
    {
      if (inv.getTitle().equals("§bHacks"))
      {
        if ((i == null) || (i.getType().equals(Material.AIR)) || (!i.hasItemMeta()))
        {
          p.closeInventory();
          
          return;
        }
      }
    }
  }
  
  @EventHandler
  void onKillauraHit(EntityDamageByEntityEvent e)
  {
    if ((e.getEntity() instanceof Bat))
    {
      Bat morcego = (Bat)e.getEntity();
      if (morcego.hasMetadata("morcegaum")) {
        forcefield = "100%";
      } else if (morcego.hasMetadata("batiman")) {
        killaura = "100%";
      }
    }
  }
  
  @EventHandler
  void onMove(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if (frozen.contains(p.getName())) {
      p.teleport(p.getLocation());
    }
  }
  
  @EventHandler
  void onQuit(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    
    inadmin.remove(p.getName());
    frozen.remove(p.getName());
  }
  
  @EventHandler
  void onInteractSopa(PlayerInteractEvent e)
  {
    int vida = 7;
    int fome = 7;
    if (e.getItem() == null) {
      return;
    }
    if (e.getItem().getType().equals(Material.MUSHROOM_SOUP))
    {
      e.setCancelled(true);
      Player p = e.getPlayer();
      Damageable d = p;
      if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
      {
        if (d.getHealth() < d.getMaxHealth())
        {
          p.setHealth(d.getHealth() + vida > d.getMaxHealth() ? d.getMaxHealth() : d.getHealth() + vida);
          e.getItem().setType(Material.BOWL);
        }
        if (p.getFoodLevel() < 20)
        {
          p.setFoodLevel(fome + p.getFoodLevel() > 20 ? 20 : fome + p.getFoodLevel());
          if (p.getFoodLevel() == 20)
          {
            p.setExhaustion(0.0F);
            e.getItem().setType(Material.BOWL);
          }
        }
      }
    }
  }
}
