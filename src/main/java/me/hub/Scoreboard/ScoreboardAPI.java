package me.hub.Scoreboard;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.site.account.rank.Rank;
 
public class ScoreboardAPI {
    public static Scoreboard scoreboard;
    private static Map<String, Integer> scores;
    private static List<Team> teams;
 
    public ScoreboardAPI() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();

        for (Rank rank : Rank.values())
        {
	        scoreboard.registerNewTeam(rank.Name.replace("+", "M")).setPrefix(rank.GetTag(true) + " §7");
	        scoreboard.getTeam(rank.Name.replace("+", "M")).setAllowFriendlyFire(true);
	        scoreboard.getTeam(rank.Name.replace("+", "M")).setCanSeeFriendlyInvisibles(true);
	        System.out.print("Team(scoreboard) adicionado(atualizado) " + rank.Name.replace("+", "M"));
	        }
    }
    
 
    public static  void blankLine() {
        add(" ");
    }
 
    public static void add(String text) {
        add(text, null);
    }
 
    public static void add(String text, Integer score) {
        Preconditions.checkArgument(text.length() < 48, "Text cannot be over 48 characters in length!");
        scores.put(text, score);
    }
 

 
    private static Map.Entry<Team, String> createTeam(String text) {
        String result = "";
        if (text.length() <= 16) {
            return new AbstractMap.SimpleEntry<>(null, text);
        }
        Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
        Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
        team.setPrefix(iterator.next());
        result = iterator.next();
        if (text.length() > 32) {
            team.setSuffix(iterator.next());
        }
        teams.add(team);
        return new AbstractMap.SimpleEntry<>(team, result);
    }
 
    public static void remover(Player p, String texto) {
      	for (String entry : p.getScoreboard().getEntries()) {
      		if (entry.contains(texto)) {
      		p.getScoreboard().resetScores(entry);
      		}
      	}
        
    }

    public static boolean contem(Player p, String texto) {
      	for (String entry : p.getScoreboard().getEntries()) {
      		if (entry.contains(texto)) {
      		return true;
      		}
      	}
        return false;
    }
    
    public static void remover(Player p) {
      	for (String entry : p.getScoreboard().getEntries()) {
 
      		p.getScoreboard().resetScores(entry);
      		
      	}
        
    }
   
    @EventHandler
    public void InventoryCancel(InventoryClickEvent event)
    {
      if (((event.getWhoClicked() instanceof Player)) && (((Player)event.getWhoClicked()).getGameMode() != GameMode.CREATIVE)) {
        event.setCancelled(true);
      }
    }
    
    public static void build(Player p, String texto) {
        final Objective obj = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        obj.setDisplayName(texto);
 
        int line = 15;


               for (Map.Entry<String, Integer> text : scores.entrySet())
        	        {
        	            Map.Entry<Team, String> team = createTeam(text.getKey());
        	            Integer score = text.getValue() != null ? text.getValue() : line;
        	            OfflinePlayer player = Bukkit.getOfflinePlayer((String)team.getValue());
        	            if (team.getKey() != null)
        	                team.getKey().addPlayer(player);
        	            if (!ScoreboardAPI.contem(p, player.getName())) 
        	            obj.getScore(player).setScore(score);
        	            
        	            line--;
        	      }
               
        /*
        for (final Map.Entry<String, Integer> text : scores.entrySet()) {
            final Map.Entry<Team, String> team = createTeam(text.getKey());
      
            String value = team.getValue();
            Integer score = text.getValue() != null ? text.getValue() : index;
            if (team.getKey() != null) {
            	if (!ScoreboardAPI.contem(p, value))
                team.getKey().addEntry(value);
            }
                     
            line--;
                       if (!ScoreboardAPI.contem(p, value)) {
                    	   if (scores.get())
          			 obj.getScore(value).setScore(line);
                       }
          			 
          		}
          		*/
               
        scores.clear();
          	
        
    

    }
 
    public void reset() {
        scores.clear();
        for (Team t : teams) {
            t.unregister();
        }
        teams.clear();
    }
 
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
 
    public void send(Player p) {
    	  Objective obj = scoreboard.registerNewObjective("§cPlayer Data", "dummy");
          obj.setDisplaySlot(DisplaySlot.SIDEBAR);

          p.setScoreboard(scoreboard);
        
    }
}