package me.hub.atualizar;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class Atualizar extends Event
{
  private static final HandlerList handlers = new HandlerList();
  private ModosUpdate _type;

  public Atualizar(ModosUpdate example)
  {
    this._type = example;
  }

  public ModosUpdate getType()
  {
    return this._type;
  }

  public HandlerList getHandlers()
  {
    return handlers;
  }

  public static HandlerList getHandlerList()
  {
    return handlers;
  }
}