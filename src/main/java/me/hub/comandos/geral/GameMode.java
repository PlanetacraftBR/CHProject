package me.hub.comandos.geral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

import me.acf.FormatText.Format;
import me.hub.API.ModoDeJogo;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.rank.Rank;

public class GameMode implements CommandExecutor, TabCompleter {


	private static final List<String> GAMEMODE_NAMES = ImmutableList.of("adventure", "creative", "survival", "spectator");
	public String[] atalhos = new String[] { "gm" };
    public String desc = "Executar GameModes";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		   Player jogador = (Player) sender;
	
		   if ((Account.getRank(jogador)).Has(jogador, Rank.STAFFM, false))
		   {
			if (args.length  <= 0)
			{
				Format.Comando("GM", "/gm (0 | 1 | 2 | 3)", jogador);
		
				return true;
			}
			if (args.length == 1) {
			if ((args[0].equals("0")) || (args[0].equals("survival")))
			{
              ComandosAPI.setGameMode(jogador, ModoDeJogo.SOBREVIVENCIA);
      		Format.Comando("GM", "§7Você está agora no modo §aSobrevivencia", jogador);
			}
			else
			if ((args[0].equals("1")) || (args[0].equals("criativo")))
			{
              ComandosAPI.setGameMode(jogador, ModoDeJogo.CRIATIVO);
      		Format.Comando("GM", "§7Você está agora no modo §aCriativo", jogador);
			}
			else
			if ((args[0].equals("2")) || (args[0].equals("adventure")))
			{
              ComandosAPI.setGameMode(jogador, ModoDeJogo.AVENTURA);
      		Format.Comando("GM", "§7Você está agora no modo §aAventura" ,jogador);
			}
			else
			if ((args[0].equals("3")) || (args[0].equals("spectador")))
			{
              ComandosAPI.setGameMode(jogador, ModoDeJogo.ESPECTADOR);
             		Format.Comando("GM", "§7Você está agora no modo §aEspectador", jogador);
			}
			else
				Format.Comando("GM", "/gm (0 | 1 | 2 | 3)", jogador);
			}
			if (args.length >= 2)
			{
				if (args[0].equals("@a"))
				{
				
					
					if ((args[1].equals("0")) || (args[1].equals("survival")))
					{
						for (Player alvo : Bukkit.getOnlinePlayers()) 
		              ComandosAPI.setGameMode(alvo, ModoDeJogo.SOBREVIVENCIA);
		             		Format.Comando("GM", "§7Todos jogadores está agora no modo §aSobrevivencia", jogador);
					}
					else
					if ((args[1].equals("1")) || (args[1].equals("criativo")))
					{
						for (Player alvo : Bukkit.getOnlinePlayers()) 
		              ComandosAPI.setGameMode(alvo, ModoDeJogo.CRIATIVO);
		             		Format.Comando("GM", "§7Todos jogadores está agora no modo §aCriativo", jogador);
					}
					else
					if ((args[1].equals("2")) || (args[1].equals("adventure")))
					{
						for (Player alvo : Bukkit.getOnlinePlayers()) 
		              ComandosAPI.setGameMode(alvo, ModoDeJogo.AVENTURA);
		             		Format.Comando("GM", "§7Todos jogadores está agora no modo §aAventura", jogador);
					}
					else
						if ((args[1].equals("3")) || (args[1].equals("spectador")))
						{
							for (Player alvo : Bukkit.getOnlinePlayers()) 
			              ComandosAPI.setGameMode(alvo, ModoDeJogo.ESPECTADOR);
			             		Format.Comando("GM", "§7Todos jogadores está agora no modo §aSpectador", jogador);
						}
					else
						Format.Comando("GM", "/gm @a (0 | 1 | 2 | 3)", jogador);
					
					return true;
				}
				
		      if (ComandosAPI.JogadorOnline(args[0]).equals("sim"))
		      {
		    	  Player alvo = Bukkit.getPlayerExact(args[0]);
		  		if ((args[1].equals("0")) || (args[1].equals("survival")))
				{
	              ComandosAPI.setGameMode(alvo, ModoDeJogo.SOBREVIVENCIA);
	             		Format.Comando("GM", "§7"+ args[0] +  " está agora no modo §aSobrevivencia", jogador);
				}
		  		else
				if ((args[1].equals("1")) || (args[1].equals("criativo")))
				{
	              ComandosAPI.setGameMode(alvo, ModoDeJogo.CRIATIVO);
	             		Format.Comando("GM", "§7"+ args[0] +  " está agora no modo §aCriativo", jogador);
				}
				else
				if ((args[1].equals("2")) || (args[1].equals("adventure")))
				{
	              ComandosAPI.setGameMode(alvo, ModoDeJogo.AVENTURA);
	             		Format.Comando("GM", "§7"+ args[0] +  " está agora no modo §aAventura" ,jogador);
				}
				else
					if ((args[1].equals("3")) || (args[1].equals("spectador")))
					{
		              ComandosAPI.setGameMode(alvo, ModoDeJogo.ESPECTADOR);
		             		Format.Comando("GM", "§7"+ args[0] +  " está agora no modo §aEspectador", jogador);
					}
				else
					Format.Comando("GM", "/gm <jogador> (0 | 1 | 2 | 3)", jogador);
		      }
		      else
		      {
		    	  
		    	  Format.Comando("GM", "Este jogador §a" + args[0] + "§7 não esta neste server.", jogador);
		      }
					
			}

			
		
		   }
		return false;
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
            final List<String> materials = GameMode.GAMEMODE_NAMES;
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

