package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.acf.lobby.gadgets.ViewPlayers;
import me.acf.punish.PunishMananger;
import me.hub.Main;
import me.hub.API.Util.UtilInv;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Admin implements CommandExecutor {

	public String[] atalhos = new String[] { "mod" };
    public String desc = "Entrar no modo de administração";
    public static List<Player> admin = new ArrayList<Player>();
    
    public HashMap<Player, ItemStack[]> invsave = new HashMap<Player, ItemStack[]>();
    
    public HashMap<Player, ItemStack[]> armorsave = new HashMap<Player, ItemStack[]>();
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   Player p = (Player) sender;
		   if ((Account.getRank(p)).Has(p, Rank.STAFF, true))
		   {
			      Object infolore;
			      Object cage;
			      ItemMeta cagemeta;
		          ItemMeta infometa;
		          if (!AdminEvents.inadmin.contains(p.getName()))
		          {
		            AdminEvents.inadmin.add(p.getName());
		            
		            admin.add(p);
		            me.hub.Admin.Admin.SumirStaff();
		            
		            AdminEvents.saveinv.put(p.getName(), p.getInventory().getContents());
		            AdminEvents.savearm.put(p.getName(), p.getInventory().getArmorContents());
		            
		            p.getInventory().clear();

				    p.setGameMode(org.bukkit.GameMode.SURVIVAL);
				    p.setAllowFlight(true);
				   

		            p.sendMessage("§aVoce entrou No Modo Admin!");
		            
		            ItemStack info = new ItemStack(Material.BLAZE_ROD);
		            infometa = info.getItemMeta();
		            infometa.setDisplayName("§aInforma§§es.");
		            infolore = new ArrayList();
		            ((List)infolore).add("§aUse Essa Blaze-Rod Para Ter Informa§§es De Um Determinado Jogador.");
		            info.setItemMeta(infometa);
		            
		            cage = new ItemStack(Material.GLASS);
		            cagemeta = ((ItemStack)cage).getItemMeta();
		            cagemeta.setDisplayName("§aCage.");
		            List<String> cagelore = new ArrayList();
		            cagelore.add("§aUse Esse Vidro Para Prender Jogadores.");
		            cagemeta.setLore(cagelore);
		            ((ItemStack)cage).setItemMeta(cagemeta);
		            
		            ItemStack online = new ItemStack(Material.SLIME_BALL);
		            ItemMeta onlinemeta = online.getItemMeta();
		            onlinemeta.setDisplayName("§aOnline.");
		            List<String> onlinelore = new ArrayList();
		            onlinelore.add("§aUse Essa Slime-Ball Para Teleportar-se Para Um Jogador");
		            onlinemeta.setLore(onlinelore);
		            online.setItemMeta(onlinemeta);
		            
		            ItemStack troca = new ItemStack(Material.MAGMA_CREAM);
		            ItemMeta trocameta = troca.getItemMeta();
		            trocameta.setDisplayName("§aTroca§6-§fR§pida!");
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
		            p.getInventory().setItem(4, (ItemStack)cage);
		            p.getInventory().setItem(5, uteis);
		            p.getInventory().setItem(7, troca);
		            p.getInventory().setItem(8, sair);
		          }
		          else
		          {
		            p.getInventory().clear();
		            
		            admin.remove(p);
		            
		            AdminEvents.inadmin.remove(p.getName());
		            p.sendMessage("§aVoce saiu Do Modo Admin!");
		            
		            p.getInventory().setContents((ItemStack[])AdminEvents.saveinv.get(p.getName()));
		            p.getInventory().setArmorContents((ItemStack[])AdminEvents.savearm.get(p.getName()));
		            
					    p.setGameMode(org.bukkit.GameMode.SURVIVAL);
					    p.setAllowFlight(false);
					   
		            
		            me.hub.Admin.Admin.Revelar(p);
		          }
		   }
		
		return false;
	  }
	
  
}

