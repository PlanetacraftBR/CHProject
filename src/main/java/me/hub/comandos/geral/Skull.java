package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {

public String[] atalhos = new String[] { "sk"};
    public String desc = "Pegar a cabeça do player";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFFM, true))
		   {

			if (args.length  <= 0)
			{
				Format.Comando("chat", "/skull §aNick do player", jogador);
				return true;
			}
			if (args.length >= 1) {
			    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			    String name = "" + args[0];
			    skullMeta.setOwner(name);
			    skullMeta.setDisplayName("§e" + name);
			    skull.setItemMeta(skullMeta);
		        jogador.getInventory().addItem(skull);
			}
		   
			
		   }
		
		return false;
	  }
	

	

}

