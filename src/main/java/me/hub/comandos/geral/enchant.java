package me.hub.comandos.geral;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class enchant implements CommandExecutor {

	public String[] atalhos = new String[] { "encantar" };
    public String desc = "Encantar itens";
    
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

			if (args.length  <= 1)
			{
				String lista = "";
				Format.Comando("Enchant", "/enchant (nome) (level)", jogador);
				for (Enchantment enchant : Enchantment.values())
				{
					lista = lista + " " + enchant.getName();
				}
				jogador.sendMessage("§a" + lista);
				return true;
			}
			if (args.length == 2) {
			
				try {
				int level =  Integer.parseInt(args[1]);
				ItemStack item = jogador.getItemInHand();
				jogador.getInventory().removeItem(item);
				ItemMeta im = item.getItemMeta();
				
				im.addEnchant(Enchantment.getByName(args[0]), level, true);
			    item.setItemMeta(im);
				jogador.getInventory().addItem(item);
				}catch (Exception exception)
    		    {

				Format.Erro("Não foi possivel encantar", jogador);
				}
		   
			}
		   }
		
		return false;
	  }
	

	

}

