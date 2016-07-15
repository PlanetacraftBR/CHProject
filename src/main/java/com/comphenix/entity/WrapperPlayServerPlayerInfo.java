package com.comphenix.entity;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import java.util.List;

public class WrapperPlayServerPlayerInfo
  extends AbstractPacket
{
  public static final PacketType TYPE = PacketType.Play.Server.PLAYER_INFO;
  
  public WrapperPlayServerPlayerInfo()
  {
    super(new PacketContainer(TYPE), TYPE);
    this.handle.getModifier().writeDefaults();
  }
  
  public WrapperPlayServerPlayerInfo(PacketContainer packet)
  {
    super(packet, TYPE);
  }
  
  public EnumWrappers.PlayerInfoAction getAction()
  {
    return (EnumWrappers.PlayerInfoAction)this.handle.getPlayerInfoAction().read(0);
  }
  
  public void setAction(EnumWrappers.PlayerInfoAction value)
  {
    this.handle.getPlayerInfoAction().write(0, value);
  }
  
  public List<PlayerInfoData> getData()
  {
    return (List)this.handle.getPlayerInfoDataLists().read(0);
  }
  
  public void setData(List<PlayerInfoData> value)
  {
    this.handle.getPlayerInfoDataLists().write(0, value);
  }
}
