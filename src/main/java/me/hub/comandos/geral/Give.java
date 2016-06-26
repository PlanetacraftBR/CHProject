package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.acf.FormatText.Format;
import me.hub.API.ModoDeJogo;
import me.hub.API.Util.UtilServer;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;
import net.minecraft.server.v1_8_R3.MojangsonParser;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class Give implements CommandExecutor, TabCompleter{

	private static List<String> materials;
    static {
        ArrayList<String> materialList = new ArrayList<String>();
        for (Material material : Material.values()) {
            materialList.add(material.name());
        }
        Collections.sort(materialList);
        materials = ImmutableList.copyOf(materialList);
    }
    
	private static final List<String> GAMEMODE_NAMES = ImmutableList.of("adventure", "creative", "survival", "spectator");
	public String[] atalhos = new String[] { "dar" };
    public String desc = "Givar Itens";
    String usageMessage = "/give §a<jogador> §a<Item> §a[amount [data]]";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{


     if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
  			if ((args.length < 2)) {
  			    sender.sendMessage("§c§lGIVE §7" + usageMessage);
  			         return true;
  			     }  		   
 
		   }
     else
     {
 		if ((args.length < 2)) {
			    sender.sendMessage("§c§lGIVE §7" + usageMessage);
			         return true;
			     }  
     }
	   if ((Account.getRank((Player) sender)).Has((Player) sender, Rank.STAFFM, true))
	   {
     Player player = Bukkit.getPlayerExact(args[0]);
     if (player != null) {
         Material material = Material.matchMaterial(args[1]);

         if (material == null) {
             material = Bukkit.getUnsafe().getMaterialFromInternalName(args[1]);
         }

         if (material != null) {
             int amount = 1;
             short data = 0;

             if (args.length >= 3) {
            	 try {
                 amount = Integer.parseInt(args[2]);
            	 }
            	   catch (Exception e)
                   {
                     amount = 1;
                   }
                 if (args.length >= 4) {
                     try {
                         data = Short.parseShort(args[3]);
                     } catch (NumberFormatException ex) {}
                 }
             }

             ItemStack stack = new ItemStack(material, amount, data);

             if (args.length >= 5) {
                 try {
                     stack = Bukkit.getUnsafe().modifyItemStack(stack, Joiner.on(' ').join(Arrays.asList(args).subList(4, args.length)));
                	 StringBuilder s = new StringBuilder();;
                    		 
                 } catch (Throwable t) {
                 	 if (ComandosAPI.VerConsole(sender).equals("sim"))
           		   {
                 		sender.sendMessage("Isso não é uma tag válida.") ;
           		   }else
                     Format.Comando("GIVE","Isso não é uma tag válida.",(Player) sender);
                     return true;
                 }
             }

             player.getInventory().addItem(stack);
             UtilServer.AnuncioStaff("§f§oDado ao§a§o " + player.getName() + "§f§o * " + amount + " §a§o" + material);
         } else {
        	 if (ComandosAPI.VerConsole(sender).equals("sim"))
     		   {
           		sender.sendMessage("Isso não é uma item válido.") ;
     		   }else
               Format.Comando("GIVE","Isso não é uma item válido.",(Player) sender);
         }
     } else {
    	 if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
     		sender.sendMessage("Jogador não encontrado.") ;
		   }else
         Format.Comando("GIVE","Jogador não encontrado.",(Player) sender);
     }
	   }
     return true;
	  }


	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {
		Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");

        if (args.length == 1) {
            return null;
        }
        if (args.length == 2) {
            final String arg = args[1];
            final List<String> materials = Give.materials;
            List<String> completion = new ArrayList<String>();

            final int size = materials.size();
            int i = Collections.binarySearch(materials, arg, String.CASE_INSENSITIVE_ORDER);

            if (i < 0) {
                // Insertion (start) index
                i = -1 - i;
            }

            for ( ; i < size; i++) {
                String material = materials.get(i);
                if (StringUtil.startsWithIgnoreCase(material, arg)) {
                    completion.add(material);
                } else {
                    break;
                }
            }

            return Bukkit.getUnsafe().tabCompleteInternalMaterialName(arg, completion);
        }
        return ImmutableList.of();
	}
	

}

