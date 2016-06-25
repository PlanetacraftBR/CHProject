package me.acf.lobby.MagicChest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import me.acf.lobby.gadgets.Gadgets;
import me.acf.lobby.gadgets.Menu;
import me.acf.lobby.patentes.Patente;
import me.acf.lobby.perfil.Perfil;
import me.hub.Main;
import me.hub.API.Enchant;
import me.hub.API.Util.UtilInv;
import me.hub.API.Util.UtilServer;
import me.hub.Scoreboard.ScoreboardAPI;
import me.hub.effect.ParticleEffect;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class ChestMagic implements Listener
{

	public static HashMap<Player,String> armor = new HashMap<>();
	public static HashMap<Location, Player> open = new HashMap<>();
	public static HashMap<Player, Location> Local = new HashMap<>();
	public static ArrayList<Entity> remove = new ArrayList<>();
	 @EventHandler
	   public void EnderChest(final PlayerInteractEvent event)
	   {
	     Player jogador = event.getPlayer();
	     if (jogador.getGameMode() == GameMode.CREATIVE)
	       return;
	     if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	       if (event.getClickedBlock().getType() == null) {
	         return;
	       }
	       if (event.getClickedBlock().getType() == Material.CHEST) {
	    	   event.setCancelled(true);
	       }
	       if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
	    	   if (Account.getChave(event.getPlayer()).equals("0"))
	    	   {
	   		  jogador.sendMessage("                                                                       ");
 	          jogador.sendMessage("                    §e§lVocê não tem chave!"            );
 	          jogador.sendMessage("                                                     ");
 		   event.setCancelled(true);
 	          return; 
	    	   }
	    event.setCancelled(true);
	try {
	    if (open.get(event.getClickedBlock().getLocation()).equals(event.getPlayer()))
	    {
			if (!Perfil.Notification.contains(jogador))
	    	event.getPlayer().sendMessage("§cVocê já esta abrindo o MagicChest!");
        	return;
	    }
        if (open.containsKey(event.getClickedBlock().getLocation()))
        {
			if (!Perfil.Notification.contains(jogador))
        	event.getPlayer().sendMessage("§cMagic Chest já esta sendo aberto espere!");
        	return;
        }
        
	}
	catch (Exception exception)
    {
		
    }
	Local.remove(event.getPlayer());
	   CriarBau(event.getPlayer(),event.getClickedBlock().getLocation());
             
	       }
	       }
	     }
	 
	  @EventHandler
	  public void Iventarioitens(InventoryClickEvent e)
	  {
	    Player jogador = (Player)e.getWhoClicked();
	    try {
	    if ((e.getInventory().getTitle().contains("Magic Chest (" + jogador.getName() + ")")) && 
	    	      (e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0))
	    	    {
	               if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lNormal §b§lMagic Chest"))
	               {
	            	   if (Account.getChaveAPI(jogador) < 2)
	            	   {
	                    	jogador.sendMessage("§cOps você não tem chaves para isto!"); 
	            		   return;
	            	   }
	            	   if (open.containsKey(Local.get(jogador)))
	                   {
	           			if (!Perfil.Notification.contains(jogador))
	                   	jogador.sendMessage("§cMagic Chest já esta sendo aberto espere!");
	                   	return;
	                   }
	            	   Account.removeChave(jogador, 2);
	            	   Account.UpdateAccount(jogador);
	            	   ScoreboardAPI.remover(jogador);
	                   open(jogador, Local.get(jogador), "Normal");
	           	    open.put(Local.get(jogador), jogador);  
	           	    UtilServer.AnuncioServidor("§5§lMagic §6§l" + jogador.getCustomName() + "§7 esta abrindo o " + e.getCurrentItem().getItemMeta().getDisplayName());
	               Local.remove(jogador);
	               }
	               if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lEpic §b§lMagic Chest"))
	               {
	            	   if (Account.getChaveAPI(jogador) < 10)
	            	   {
	                    	jogador.sendMessage("§cOps você não tem chaves para isto!"); 
	            		   return;
	            	   }
	            	   if (Account.getPatente(jogador).Has(jogador, Patente.Ultra, true))
	        		   {
	        		
	            		   
	            	   if (open.containsKey(Local.get(jogador)))
	                   {
	           			if (!Perfil.Notification.contains(jogador))
	                   	jogador.sendMessage("§cMagic Chest já esta sendo aberto espere!");
	                   	return;
	                   }
	            	   Account.removeChave(jogador, 10);
	            	   Account.UpdateAccount(jogador);
	            	   ScoreboardAPI.remover(jogador);
	                   open(jogador, Local.get(jogador), "Epic");
	           	    open.put(Local.get(jogador), jogador);  
	           	    UtilServer.AnuncioServidor("§5§lMagic §6§l" + jogador.getCustomName() + "§7 esta abrindo o " + e.getCurrentItem().getItemMeta().getDisplayName());
	               Local.remove(jogador);
	        		   }
	               }
	    	    }
	    
	  } catch (Exception e1) {
	  }
	  }
	   
	 private static void CriarBau(Player p,Location loc)
	 {
		 Inventory inv = Bukkit.getServer().createInventory(p, 9, "Magic Chest (" + p.getName() + ")");
		 AddItem(inv,Material.CHEST,1,"§a§lNormal §b§lMagic Chest", new String[] {"§7Normal Chest possibilidades de ganhar Gadgets!","","§7- §690%     §a§lNormal","§7- §605%      §5§lRARO","§7- §605%   §c§lLENDARIO","","§7Pessoas que podem usar: §e§lNormal","§7Custo para usar: §d§l2 Chaves"}, 0);
		 AddItem(inv,Material.CHEST,1,"§a§lEpic §b§lMagic Chest", new String[] {"§7Normal Chest possibilidades de ganhar Gadgets e !","","§7- §685%     §a§lNormal","§7- §610%      §5§lRARO","§7- §605%   §c§lLENDARIO","","§7Pessoas que podem usar: §e§lNormal","§7Patente que podem usar: §a§lULTRA","§7Custo para usar: §d§l10 Chaves"}, 4);
	     Local.put(p, loc); 
	     p.openInventory(inv);
	 }
	 
	    private static void AddItem(Inventory inv, Material item,int contidade,String nome, String[] strings, int slot)
	    {
	    	      ItemStack bau = new ItemStack(item, contidade);
	    	      ItemMeta metae = bau.getItemMeta();
	    	      metae.setDisplayName(nome);
	    	      metae.setLore(Arrays.asList(strings));
	    	      bau.setItemMeta(metae);
	    	      inv.setItem(slot, bau);
	    }
	 
	 @EventHandler
	 public void CasoSair(PlayerQuitEvent event)
	 {
		 event.getPlayer().getInventory().clear();
		 armor.remove(event.getPlayer());
	 }
	 
	 @EventHandler
	    public void manipulate(final PlayerArmorStandManipulateEvent e)
	    {

	    	   if (e.getRightClicked().getCustomName().contains("§b§lBau"))
	    	   {
	    	    	 if (!e.getRightClicked().hasMetadata(e.getPlayer().getCustomName())) {
	    	    		e.getPlayer().sendMessage("§cVocê não é o dono dos baus!");
	    	    		e.setCancelled(true);
	    	    		 return;
	    	    	 }
	    			 ChestMagic.armor.remove(e.getPlayer());
	    			 ChestMagic.armor.put(e.getPlayer(), e.getRightClicked().getCustomName());
	                 
					 e.getRightClicked().setCustomNameVisible(true);
					 e.setCancelled(true);
				
				        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable() {
				            @Override
				            public void run() {
				            e.getRightClicked().remove();  
				            }
				        }, 50);
	    		   return;
	    	   }
	            if(!e.getRightClicked().isVisible())
	            {
	                e.setCancelled(true);
	            }
	    }
	    
	 
  public static void open(final Player paramPlayer, final Location loc, String bau)
  {
	  Inventory inv;
	  Random r = new Random();
	  int premio = 0;
	  if (bau.equals("Normal"))
		  premio = 935;
	  if (bau.equals("Epic"))
		  premio = 8200;
	  final String c1 = RoletaMagic.Premio(r.nextInt(premio));
	  final String c2 = RoletaMagic.Premio(r.nextInt(premio));
	  final String c3 = RoletaMagic.Premio(r.nextInt(premio));
	  armor.put(paramPlayer, "Nenhum");
	  UtilInv.save(paramPlayer);
	  paramPlayer.getInventory().clear();
	  for (int i = 0; i < 36; i++) {
		    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		    String name = "§9>> §e§lCLICK EM UM BAU §9<<";
		    skullMeta.setOwner("MHF_Chest");
		    skullMeta.setDisplayName("§b" + name);
		    skull.setItemMeta(skullMeta);
	        paramPlayer.getInventory().setItem(i, skull);
	  }
    final ArmorStand localArmorStand = (ArmorStand)paramPlayer.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
    
    
    localArmorStand.setHelmet(new ItemStack(Material.ENDER_CHEST));
    localArmorStand.setGravity(false);
    localArmorStand.setVisible(false);
    localArmorStand.setMetadata(paramPlayer.getCustomName(), new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
   final ArmorStand localArmorStand2 = (ArmorStand)paramPlayer.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
    
    
    localArmorStand2.setGravity(false);
    localArmorStand2.setVisible(false);
    localArmorStand2.setMetadata(paramPlayer.getCustomName(), new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
   final ArmorStand localArmorStand3 = (ArmorStand)paramPlayer.getWorld().spawn(loc.clone().add(0.5,0,0.5), ArmorStand.class);
    
    localArmorStand3.setGravity(false);
    localArmorStand3.setVisible(false);
    localArmorStand3.setMetadata(paramPlayer.getCustomName(), new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
    remove.add(localArmorStand);
    remove.add(localArmorStand2);
    remove.add(localArmorStand3);
    new BukkitRunnable()
    {
      int step = 0;
      int step2 = 3;
      
      public void run()
      {
        if (!paramPlayer.isOnline())
        {
      	localArmorStand.remove();
    	localArmorStand2.remove();
    	localArmorStand3.remove();
          cancel();
        }
        this.step += 1;
        this.step2 += 1;
        if (this.step <= 51)
        {
          ParticleEffect.VILLAGER_HAPPY.display(0.0F, 0.0F, 0.0F, 0.0F, 1, localArmorStand.getEyeLocation(), 20);
          ParticleEffect.REDSTONE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand.getEyeLocation(), 20);
          localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.NOTE_PIANO, 0.5F, 2.0F);
          localArmorStand.teleport(localArmorStand.getLocation().add(0, 0.1D, 0));
          localArmorStand2.teleport(localArmorStand.getLocation().add(0, 0.1D, 0));
          localArmorStand3.teleport(localArmorStand.getLocation().add(0, 0.1D, 0));
        }
        else if (this.step == 53)
        {
          ParticleEffect.ENCHANTMENT_TABLE.display(0.0F, 0.0F, 0.0F, 1.0F, 150, localArmorStand.getEyeLocation().add(0.0D, 1.0D, 0.0D), 20);
          new BukkitRunnable()
          {
            int step3 = 0;
            
            public void run()
            {
              this.step3 += 1;
              if (this.step3 <= 5) {
            	  localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.FIREWORK_TWINKLE2, 2.0F, 1.0F);
              } else {
                cancel();
              }
            }
          }.runTaskTimer(Main.plugin, 0L, 3L);
        }
        else if ((this.step < 104) && (this.step > 100))
        {
             localArmorStand2.teleport(localArmorStand2.getLocation().add(-0.7D, 0.0D, 0));
             localArmorStand3.teleport(localArmorStand3.getLocation().add(0.7D, 0.0D, 0));
             localArmorStand2.setHelmet(new ItemStack(Material.ENDER_CHEST));
             localArmorStand3.setHelmet(new ItemStack(Material.ENDER_CHEST));
             ParticleEffect.REDSTONE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand.getEyeLocation(), 20);
             ParticleEffect.SPELL_WITCH.display(0.0F, 0.0F, 0.0F, 0.0F, 1, localArmorStand2.getEyeLocation(), 20);
             ParticleEffect.SPELL_WITCH.display(0.0F, 0.0F, 0.0F, 0.0F, 1, localArmorStand3.getEyeLocation(), 20);
             localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.NOTE_BASS_GUITAR, 2.0F, 0.7F);
             localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.FIREWORK_TWINKLE2, 2.0F, 1.0F);
        
        }
        else if ((this.step < 136) && (this.step > 110))
        {
        	ParticleEffect.REDSTONE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand.getEyeLocation(), 20);
            localArmorStand.teleport(localArmorStand.getLocation().add(0.0D, -0.2D, 0));
        	 localArmorStand2.teleport(localArmorStand2.getLocation().add(0.0D, -0.2D, 0));
             localArmorStand3.teleport(localArmorStand3.getLocation().add(0.0D, -0.2D, 0));
             localArmorStand.setCustomName("§a§l???");
             localArmorStand2.setCustomName("§a§l???");
             localArmorStand3.setCustomName("§a§l???");
             localArmorStand.setCustomNameVisible(true);
             localArmorStand2.setCustomNameVisible(true);
             localArmorStand3.setCustomNameVisible(true);
             localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.NOTE_BASS_GUITAR, 2.0F, 0.2F);
             localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.NOTE_STICKS, 2.0F, 1.0F);
        }
        else if (this.step == 139)
        {
        	  localArmorStand2.setCustomName("§a§lBoa");
              localArmorStand.setCustomName("§b§lSorte");
              localArmorStand3.setCustomName("§a§lPara você");
        }
        else if (this.step == 156)
        {
        	localArmorStand.setCustomName("§b§lBau §a§l#2");
            localArmorStand2.setCustomName("§b§lBau §a§l#1");
            localArmorStand3.setCustomName("§b§lBau §a§l#3");
            

            localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.ANVIL_LAND, 10.0F, 1.0F);
            ParticleEffect.LAVA.display(0.0F, 0.0F, 0.0F, 1.0F, 50, localArmorStand.getLocation(), 20);
            ParticleEffect.LAVA.display(0.0F, 0.0F, 0.0F, 1.0F, 50, localArmorStand2.getLocation(), 20);
            ParticleEffect.LAVA.display(0.0F, 0.0F, 0.0F, 1.0F, 50, localArmorStand3.getLocation(), 20);
            ParticleEffect.SMOKE_LARGE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand.getLocation(), 20);
            ParticleEffect.SMOKE_LARGE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand2.getLocation(), 20);
            ParticleEffect.SMOKE_LARGE.display(0.0F, 0.0F, 0.0F, 5.0F, 1, localArmorStand3.getLocation(), 20);
        }
        else if (this.step == 490)
        {
        	localArmorStand.remove();
        	localArmorStand2.remove();
        	localArmorStand3.remove();
        	paramPlayer.getInventory().clear();
      	    UtilInv.restore(paramPlayer);
      	    UtilInv.save(paramPlayer);
      	  paramPlayer.sendMessage("                                                                       ");
          paramPlayer.sendMessage("                    §b§lMagic §a§lChest!"            );
          paramPlayer.sendMessage("                                              ");
          paramPlayer.sendMessage(" §7Você perdeu os prêmios    ");
              paramPlayer.sendMessage("  §7X§a§l " + c1);
	          paramPlayer.sendMessage("  §7X§a§l " + c2);
	          paramPlayer.sendMessage("  §7X§a§l " + c3);
      	    paramPlayer.sendMessage("§cDesculpe mais você demorou para escolher um bau!");
        	cancel();
            open.remove(loc);
            remove.remove(localArmorStand);
            remove.remove(localArmorStand2);
            remove.remove(localArmorStand3);
        }
       paramPlayer.closeInventory();
        if (!armor.get(paramPlayer).equals("Nenhum")){
        	
 		     localArmorStand.setCustomName("§a§l" + c2);
             localArmorStand2.setCustomName("§a§l" + c1);
    		 localArmorStand3.setCustomName("§a§l" + c3);
        	 localArmorStand.getWorld().playSound(localArmorStand.getLocation(), Sound.CHEST_CLOSE, 10.0F, 1.0F);
        	 if (!armor.get(paramPlayer).contains("#2"))
        		localArmorStand.remove();
        	if (!armor.get(paramPlayer).contains("#1"))
     		   localArmorStand2.remove();
        	if (!armor.get(paramPlayer).contains("#3"))
     		   localArmorStand3.remove();
 
  		      paramPlayer.sendMessage("                                                                       ");
	          paramPlayer.sendMessage("                    §b§lMagic §a§lChest!"            );
	          paramPlayer.sendMessage("                                              ");
	          paramPlayer.sendMessage(" §7Você perdeu os prêmios    ");
	          if (!armor.get(paramPlayer).contains("#1"))
	              paramPlayer.sendMessage("  #1 §7X§a§l " + c1);
	          if (!armor.get(paramPlayer).contains("#2"))
		          paramPlayer.sendMessage("  #2 §7X§a§l " + c2);
	          if (!armor.get(paramPlayer).contains("#3"))
		          paramPlayer.sendMessage("  #3 §7X§a§l " + c3);
        	UtilInv.restore(paramPlayer);
        	 if (armor.get(paramPlayer).contains("#1"))
       	 Premio.Verificar(localArmorStand2.getCustomName(), paramPlayer);
        	 if (armor.get(paramPlayer).contains("#2"))
               	 Premio.Verificar(localArmorStand.getCustomName(), paramPlayer);
        	 if (armor.get(paramPlayer).contains("#3"))
               	 Premio.Verificar(localArmorStand3.getCustomName(), paramPlayer);
            cancel();
            open.remove(loc);
            remove.remove(localArmorStand);
            remove.remove(localArmorStand2);
            remove.remove(localArmorStand3);
            
        }
  
      }
    }.runTaskTimer(Main.plugin, 0L, 1L);
  }
  
  public static void Remove_Stop()
  {
	  for (Entity ent : ChestMagic.remove)
	  {
		  ent.remove();
	  }
  }
  
  private static Color getColor(int i) {
	   Color c = null;
	
	   c=Color.PURPLE;
	   
	
	    
	   return c;
	   }
  public static void backup() {}
}
