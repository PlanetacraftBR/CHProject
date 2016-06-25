package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.acf.FormatText.Format;
import me.hub.API.Util.message.Message;
import me.hub.Message.Anuncio;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

public class head implements CommandExecutor{

	public String[] atalhos = new String[] { "cabeça", "cabeca", "cab"};
    public String desc = "Pegar a cabeça do player e equipala";
	
	public static boolean desativar = false;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
		   if (desativar)
		   {
			   return true;
		   }
		   if ((Account.getRank(jogador)).Has(jogador, Rank.VIPM, true))
		   {

			if (args.length  <= 0)
			{
				Format.Comando("chat", "/cabeça §aNick do player", jogador);
				return true;
			}
			if (args.length >= 1) {
				if (args[0].contains("normal"))
				{
					jogador.getInventory().setHelmet(null);
					jogador.sendMessage("§aCabeça resetada");
					return true;
				}
			    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			    String name = "" + args[0];
			    skullMeta.setOwner(name);
			    skullMeta.setDisplayName("§eCabeça de §o" + name);
			    skull.setItemMeta(skullMeta);
		        jogador.getInventory().setHelmet(skull);
		        jogador.sendMessage("§aVocê esta usando a cabeça de " + name);
			}
		   
			
		   }
		
		return false;
	  }
	

	

}

