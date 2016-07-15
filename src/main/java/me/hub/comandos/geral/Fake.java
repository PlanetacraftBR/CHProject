package me.hub.comandos.geral;

import me.acf.FormatText.Format;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.site.account.Account;
import me.site.account.rank.Rank;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Fake implements CommandExecutor {

	public String[] atalhos = new String[] { "fakenick","nick" };
    public String desc = "Ver o inventario de um jogador";
    private static Pattern VALIDO = Pattern.compile("[A-Za-z0-9_]");
    public static HashMap Fake = new HashMap<Player, String>();
    public static HashMap<String, Player> PFake = new HashMap<String, Player>();
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   Player jogador = (Player) sender;
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFF, true))
		   {
		   if (args.length  <= 0)
			{
			    Format.Comando("§a§lFAKE", "/fake nick", jogador);
				return true;
			}
		   String invalidChars = getInvalidChars(args[0]);
		   if (!invalidChars.isEmpty())
		    {
			   Format.Erro("Você não pode usar um nome assim", jogador);
			   return true;
		   }
		   if (args[0].equals(jogador.getName()))
		   {
			   if (DisguiseAPI.isDisguised(jogador)){
	           DisguiseAPI.undisguiseToAll(jogador);
			   }
			   jogador.setDisplayName(jogador.getName());
			   jogador.setCustomName(jogador.getName());
			   jogador.setPlayerListName(jogador.getName());
			   Format.Comando("§a§lFAKE", "Voltou para seu nick original", jogador);
	           Fake.remove(jogador);
	           PFake.remove(jogador);
			   return true;
		   }
		   jogador.setDisplayName(args[0]);
		   jogador.setCustomName(args[0]);
		   jogador.setPlayerListName(args[0]);
		   PlayerDisguise playerdj = new PlayerDisguise(args[0]);
		   playerdj.setEntity(jogador);
		   playerdj.startDisguise();
		   Format.Comando("FAKE", "Você mudou seu nick para§a " + args[0], jogador);
           Fake.put(jogador, args[0]);
           PFake.put(args[0], jogador);
		          
		   }
		
		return false;
	  }
	

	  public static String getInvalidChars(String s)
	  {
	    return VALIDO.matcher(s).replaceAll("");
	  }

}

