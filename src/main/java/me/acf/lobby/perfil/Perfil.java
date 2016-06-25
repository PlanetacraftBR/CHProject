package me.acf.lobby.perfil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.lobby.gadgets.Gadgets;
import me.acf.lobby.gadgets.Menu;
import me.hub.MiniPlugin;
import me.hub.API.Enchant;

public class Perfil extends MiniPlugin{
	
    public Perfil(JavaPlugin plugin) {
		super("Perfil", plugin);
		// TODO Auto-generated constructor stub
	}

    public static ArrayList<Player> VerJogadores = new ArrayList<Player>();
    public static ArrayList<Player> MsgPrivate = new ArrayList<Player>();
    public static ArrayList<Player> MSGPrivate = new ArrayList<Player>();
    public static ArrayList<Player> MSGlobal = new ArrayList<Player>();
    public static ArrayList<Player> Notification = new ArrayList<Player>();
    public static ArrayList<Player> DoubleJump = new ArrayList<Player>();
    
    private static HashMap<Integer, String> itens = new HashMap<Integer, String>();
    
	public static void PerfilOPEN(Player p)
    {
      Inventory inv = Bukkit.getServer().createInventory(p, 54, "Preferencias");
      Manager man = new Manager(p); 
      
      AddItem(inv, false, Material.EYE_OF_ENDER,1,(byte) 0, "§aVer jogadores", new String[] {  man.Ativo("VerJogadores"),"§3","§7>> Click em baixo para alterar §7<<" } ,0);
      AddItemActive(p,inv,"VerJogadores", new String[] {  "§6Desativar os jogadores do servidor","§3","§7>> Click para alterar §7<<" } ,0);
      AddItem(inv, false, Material.MAP,1,(byte) 0, "§aMensagens Privadas", new String[] {  man.Ativo("MsgPrivate"),"§3","§7>> Click em baixo para alterar §7<<" } ,2);
      AddItemActive(p,inv,"MsgPrivate", new String[] {  "§6Não receber mensagens particulares de outros jogadores.","§3","§7>> Click para alterar §7<<" } ,2);
      AddItem(inv, false, Material.PAPER,1,(byte) 0, "§aChat Global", new String[] {  man.Ativo("ChatGlobal"),"§3","§7>> Click em baixo para alterar §7<<" } ,4);
      AddItemActive(p,inv,"ChatGlobal", new String[] {  "§6Não receber mensagens no chat global deste servidor.","§3","§7>> Click para alterar §7<<" } ,4);
      AddItem(inv, false, Material.REDSTONE,1,(byte) 0, "§aServer Notificações", new String[] {  man.Ativo("SvNot"),"§3","§7>> Click em baixo para alterar §7<<" } ,6);
      AddItemActive(p,inv,"SvNot", new String[] {  "§6Não receber mensagens do servidor (Comandos,alertas,etc..).","§3","§7>> Click para alterar §7<<" } ,6);
      AddItem(inv, false, Material.FIREBALL,1,(byte) 0, "§aDoubleJump", new String[] {  man.Ativo("Dj"),"§3","§7>> Click em baixo para alterar §7<<" } ,8);
      AddItemActive(p,inv,"Dj", new String[] {  "§6Dar um Double Jump (Pular mais alto).","§3","§7>> Click para alterar §7<<" } ,8);
      

      
      
      p.openInventory(inv);
    
    }
	
	private void Update(Inventory inv, Player p)
	{
	      Manager man = new Manager(p); 
	      
	      AddItem(inv, false, Material.EYE_OF_ENDER,1,(byte) 0, "§aVer jogadores", new String[] {  man.Ativo("VerJogadores"),"§3","§7>> Click em baixo para alterar §7<<" } ,0);
	      AddItemActive(p,inv,"VerJogadores", new String[] {  "§6Desativar os jogadores do servidor","§3","§7>> Click para alterar §7<<" } ,0);
	      AddItem(inv, false, Material.MAP,1,(byte) 0, "§aMensagens Privadas", new String[] {  man.Ativo("MsgPrivate"),"§3","§7>> Click em baixo para alterar §7<<" } ,2);
	      AddItemActive(p,inv,"MsgPrivate", new String[] {  "§6Não receber mensagens particulares de outros jogadores.","§3","§7>> Click para alterar §7<<" } ,2);
	      AddItem(inv, false, Material.PAPER,1,(byte) 0, "§aChat Global", new String[] {  man.Ativo("ChatGlobal"),"§3","§7>> Click em baixo para alterar §7<<" } ,4);
	      AddItemActive(p,inv,"ChatGlobal", new String[] {  "§6Não receber mensagens no chat global deste servidor.","§3","§7>> Click para alterar §7<<" } ,4);
	      AddItem(inv, false, Material.REDSTONE,1,(byte) 0, "§aServer Notificações", new String[] {  man.Ativo("SvNot"),"§3","§7>> Click em baixo para alterar §7<<" } ,6);
	      AddItemActive(p,inv,"SvNot", new String[] {  "§6Não receber mensagens do servidor (Comandos,alertas,etc..).","§3","§7>> Click para alterar §7<<" } ,6);
	      
	}
	
	 @EventHandler
	  public void Iventarioitens(InventoryClickEvent e)
	  {
	    Player jogador = (Player)e.getWhoClicked();

	    if ((e.getInventory().getTitle().contains("Preferencias")) && 
	    	      (e.getCurrentItem() != null) && (e.getCurrentItem().getTypeId() != 0))
	    	    {
	    	        if (itens.containsKey(e.getSlot()))
	    	        {
	    	        	  Manager man = new Manager(jogador);
	    	        	  man.Update(itens.get(e.getSlot()));
	    	        	  Update(e.getInventory(), jogador);
	    	        }
	    	    }
	  }
	@EventHandler
	public void Abrir(PlayerInteractEvent e)
	{
		Player jogador = e.getPlayer();
	    if (jogador.getGameMode() == GameMode.CREATIVE)
	      return;
	    if (e.getItem() == null)
	      return;
	    if (e.getItem().getItemMeta().getDisplayName() == null)
	      return;

	    if ((e.getItem().getType() == Material.REDSTONE_COMPARATOR) && (e.getItem().getItemMeta().getDisplayName().equals("§aPreferencias")))
	    {
	      PerfilOPEN(jogador);
	      e.setCancelled(true);
	    }
	}
    
    private static void AddItem(Inventory inv, boolean encantado, Material item,int contidade, byte data,String nome, String[] strings, int slot)
    {
    	      ItemStack bau = new ItemStack(item, contidade , data);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName(nome);
    	      metae.setLore(Arrays.asList(strings));
    	      bau.setItemMeta(metae);
    	      if (encantado)
    	      inv.setItem(slot, Enchant.addGlow(bau));
    	      else
    	          inv.setItem(slot, bau);
    }
    
    private static void AddItemActive(Player p ,Inventory inv,String nome, String[] strings, int slot)
    {
    	Manager man = new Manager(p); 
    	itens.put(slot+9, nome);
    	if (man.verficar(nome)) {
    	      ItemStack bau = new ItemStack(351 , 1 ,(short) 10);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName("§e>> §aAtivado§e <<");
    	      metae.setLore(Arrays.asList(strings));
    	      bau.setItemMeta(metae);
    	      inv.setItem(slot+9, bau);
    	}
    	else
    	{
    	      ItemStack bau = new ItemStack(351 , 1 ,(short) 8);
    	      ItemMeta metae = bau.getItemMeta();
    	      metae.setDisplayName("§c>> §6Desativado§c <<");
    	      metae.setLore(Arrays.asList(strings));
    	      bau.setItemMeta(metae);
    	      inv.setItem(slot+9, bau);
    	}
    }
 
    
    
    	

        
        
        
    
}
