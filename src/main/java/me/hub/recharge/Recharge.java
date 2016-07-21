package me.hub.recharge;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FormatText.Format;
import me.hub.MiniPlugin;
import me.hub.API.NautHashMap;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.UtilTime;
import me.hub.API.Util.UtilTime.TimeUnit;
import me.hub.API.module.F;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;


public class Recharge extends MiniPlugin
{
	public static Recharge Instance;
	
	public HashSet<String> informSet = new HashSet<String>();
	public NautHashMap<String, NautHashMap<String, RechargeData>> _recharge = new NautHashMap<String, NautHashMap<String, RechargeData>>();
	
	protected Recharge(JavaPlugin plugin)
	{
		super("Recharge", plugin);
	}
	
	public static void Initialize(JavaPlugin plugin)
	{
		Instance = new Recharge(plugin);
	}
	
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent event)
	{
		Get(event.getEntity().getName()).clear();
	}
	
	public NautHashMap<String, RechargeData> Get(String name)
	{
		if (!_recharge.containsKey(name))
			_recharge.put(name, new NautHashMap<String, RechargeData>());
		
		return _recharge.get(name);
	}

	public NautHashMap<String, RechargeData> Get(Player player)
	{
		return Get(player.getName());
	}
	
	@EventHandler
	public void update(Atualizar event)
	{
		if (event.getType() != ModosUpdate.TICK)
			return; 
		
		recharge();
	}
	
	public void recharge()
	{
		for (Player cur : UtilServer.Jogadores())
		{
			LinkedList<String> rechargeList = new LinkedList<String>();

	

			for (String ability : Get(cur).keySet())
			{
				if (Get(cur).get(ability).Update())
					rechargeList.add(ability);
			}

			
			for (String ability : rechargeList)
			{
				Get(cur).remove(ability);
				
				RechargedEvent rechargedEvent = new RechargedEvent(cur, ability);
				UtilServer.getServer().getPluginManager().callEvent(rechargedEvent);
				
				if (informSet.contains(ability))
					Format.Comando("Tempo", "Você já pode utilizar o §a" + F.skill(ability) + "§7.", cur);
			}
		}
	}
	
	public boolean use(Player player, String ability, long recharge, boolean inform, boolean attachItem)
	{
		return use(player, ability, ability, recharge, inform, attachItem);
	}
	
	public boolean use(Player player, String ability, String abilityFull, long recharge, boolean inform, boolean attachItem)
	{
		return use(player, ability, abilityFull, recharge, inform, attachItem, false);
	}
	
	public boolean use(Player player, String ability, long recharge, boolean inform, boolean attachItem, boolean attachDurability)
	{
		return use(player, ability, ability, recharge, inform, attachItem, attachDurability);
	}
	
	public boolean use(Player player, String ability, String abilityFull, long recharge, boolean inform, boolean attachItem, boolean attachDurability)
	{
		if (recharge == 0)
			return true;

		recharge();
		
		if (inform && recharge > 1000)
			informSet.add(ability);
	
		if (Get(player).containsKey(ability))
		{
			if (inform)
			{
				Format.Comando("Tempo", "Você deve esperar §a" + F.time(UtilTime.convertString((Get(player).get(ability).GetRemaining()), 1, TimeUnit.FIT)) + " para utitlizar o §a" + F.skill(abilityFull) + "§7.", player);
			}

			return false;
		}

		UseRecharge(player, ability, recharge, attachItem, attachDurability);

		return true;
	}
	
	public void useForce(Player player, String ability, long recharge)
	{
		useForce(player, ability, recharge, false);
	}
	
	public void useForce(Player player, String ability, long recharge, boolean attachItem)
	{
		UseRecharge(player, ability, recharge, attachItem, false);
	}
	
	public boolean usable(Player player, String ability)
	{
		return usable(player, ability, false);
	}
	
	public boolean usable(Player player, String ability, boolean inform)
	{
		if (!Get(player).containsKey(ability))
			return true;
		
		if (Get(player).get(ability).GetRemaining() <= 0)
		{
			return true;
		}
		else
		{
			if (inform)
			Format.Comando("Tempo", "Você deve esperar §a" + F.time(UtilTime.convertString((Get(player).get(ability).GetRemaining()), 1, TimeUnit.FIT)) + " para utitlizar o §a" + F.skill(ability) + "§7.", player);
			return false;
		}
	}
	
	public void UseRecharge(Player player, String ability, long recharge, boolean attachItem, boolean attachDurability)
	{
		RechargeEvent rechargeEvent = new RechargeEvent(player, ability, recharge);
		UtilServer.getServer().getPluginManager().callEvent(rechargeEvent);
		
		Get(player).put(ability, new RechargeData(this, player, ability, player.getItemInHand(), 
				rechargeEvent.GetRecharge(), attachItem, attachDurability));
	}
	
	public void recharge(Player player, String ability)
	{
		Get(player).remove(ability);
	}

	@EventHandler
	public void clearPlayer(PlayerQuitEvent event)
	{
		_recharge.remove(event.getPlayer().getName());
	}
	
	public void setDisplayForce(Player player, String ability, boolean displayForce)
	{
		if (!_recharge.containsKey(player.getName()))
			return;

		if (!_recharge.get(player.getName()).containsKey(ability))
			return;

		_recharge.get(player.getName()).get(ability).DisplayForce = displayForce;
	}
	
	public void setCountdown(Player player, String ability, boolean countdown)
	{
		if (!_recharge.containsKey(player.getName()))
			return;

		if (!_recharge.get(player.getName()).containsKey(ability))
			return;

		_recharge.get(player.getName()).get(ability).Countdown = countdown; 
	}
	
	public void Reset(Player player) 
	{
		_recharge.put(player.getName(), new NautHashMap<String, RechargeData>());
	}
	
	public void Reset(Player player, String stringContains) 
	{
		NautHashMap<String, RechargeData> data = _recharge.get(player.getName());
		
		if (data == null)
			return;
		
		Iterator<String> rechargeIter = data.keySet().iterator();
		
		while (rechargeIter.hasNext())
		{
			String key = rechargeIter.next();
			
			if (key.toLowerCase().contains(stringContains.toLowerCase()))
			{
				rechargeIter.remove();
			}
		}
	}

	public void debug(Player player, String ability)
	{
		if (!_recharge.containsKey(player.getName()))
		{
			player.sendMessage("No Recharge Map.");
			return;
		}
		
		if (!_recharge.get(player.getName()).containsKey(ability))
		{
			player.sendMessage("Ability Not Found.");
			return;
		}
		
		_recharge.get(player.getName()).get(ability).debug(player);
	}
}
