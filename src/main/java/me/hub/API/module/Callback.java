package me.hub.API.module;

public abstract interface Callback<T>
{
  public abstract void run(T paramT);
}