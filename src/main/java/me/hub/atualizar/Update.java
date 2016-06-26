package me.hub.atualizar;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Update
  implements Runnable
{
  private JavaPlugin _plugin;

  public Update(JavaPlugin plugin)
  {
    this._plugin = plugin;
    this._plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this._plugin, this, 0L, 1L);
  }

  public void run()
  {
    for (ModosUpdate updateType : ModosUpdate.values())
    {
      if (!updateType.Elapsed())
        continue;
      this._plugin.getServer().getPluginManager().callEvent(new Atualizar(updateType));
    }
  }
}