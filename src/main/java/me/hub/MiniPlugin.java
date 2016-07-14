package me.hub;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.hub.API.Util.UtilTime;



public abstract class MiniPlugin
  implements Listener
{
  protected String _moduleName = "Default";
  protected JavaPlugin _plugin;

  public MiniPlugin(String moduleName, JavaPlugin plugin)
  {
    String tempo = UtilTime.convertString(UtilTime.DataTempo(UtilTime.TimeData(), Main.servidor_ligado), 0, UtilTime.TimeUnit.FIT);
    this._moduleName = moduleName;
    this._plugin = plugin;

    onEnable();

    RegisterEvents(this);
    System.out.print("Class @" + moduleName + " iniciada! [@CHProject] em " + tempo);
    
  }

  public PluginManager GetPluginManager()
  {
    return this._plugin.getServer().getPluginManager();
  }

  public BukkitScheduler GetScheduler()
  {
    return this._plugin.getServer().getScheduler();
  }

  public JavaPlugin GetPlugin()
  {
    return this._plugin;
  }

  public void RegisterEvents(Listener listener)
  {
    this._plugin.getServer().getPluginManager().registerEvents(listener, this._plugin);
  }

  public final void onEnable()
  {
    Enable();
    AddComandos();
  }

  public final void onDisable()
  {
    Disable();

    Log("Desativado");
  }
  public void Enable() {
  }
  public void Disable() {
  }

  public void AddComandos() {
  }

  public final String GetName() {
    return this._moduleName;
  }

  public final void AddComandos(String teste)
  {
	  
  }



  protected void Log(String message)
  {
    System.out.println(message);
  }
}