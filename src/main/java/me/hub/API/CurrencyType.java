package me.hub.API;

import org.bukkit.Material;

public enum CurrencyType
{
  Tokens(" Chaves", Material.HOPPER),  Coins(" Coins", Material.GOLD_INGOT),  Gems("Cash", Material.EMERALD);
  
  private String _prefix;
  private Material _displayMaterial;
  
  private CurrencyType(String prefix, Material displayMaterial)
  {
    this._prefix = prefix;
    this._displayMaterial = displayMaterial;
  }
  
  public String Prefix()
  {
    return this._prefix;
  }
  
  public Material GetDisplayMaterial()
  {
    return this._displayMaterial;
  }
}
