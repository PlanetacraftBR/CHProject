package com.comphenix.entity;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import java.util.UUID;
import org.bukkit.util.Vector;

public class WrapperPlayServerNamedEntitySpawn
  extends AbstractPacket
{
  public static final PacketType TYPE = PacketType.Play.Server.NAMED_ENTITY_SPAWN;
  
  public WrapperPlayServerNamedEntitySpawn()
  {
    super(new PacketContainer(TYPE), TYPE);
    this.handle.getModifier().writeDefaults();
  }
  
  public WrapperPlayServerNamedEntitySpawn(PacketContainer packet)
  {
    super(packet, TYPE);
  }
  
  public int getEntityId()
  {
    return ((Integer)this.handle.getIntegers().read(0)).intValue();
  }
  
  public void setEntityId(int value)
  {
    this.handle.getIntegers().write(0, Integer.valueOf(value));
  }
  
  public UUID getPlayerUuid()
  {
    return (UUID)this.handle.getSpecificModifier(UUID.class).read(0);
  }
  
  public void setPlayerUuid(UUID value)
  {
    this.handle.getSpecificModifier(UUID.class).write(0, value);
  }
  
  public Vector getPosition()
  {
    return new Vector(getX(), getY(), getZ());
  }
  
  public void setPosition(Vector position)
  {
    setX(position.getX());
    setY(position.getY());
    setZ(position.getZ());
  }
  
  public double getX()
  {
    return ((Integer)this.handle.getIntegers().read(1)).intValue() / 32.0D;
  }
  
  public void setX(double value)
  {
    this.handle.getIntegers().write(1, Integer.valueOf((int)Math.floor(value * 32.0D)));
  }
  
  public double getY()
  {
    return ((Integer)this.handle.getIntegers().read(2)).intValue() / 32.0D;
  }
  
  public void setY(double value)
  {
    this.handle.getIntegers().write(2, Integer.valueOf((int)Math.floor(value * 32.0D)));
  }
  
  public double getZ()
  {
    return ((Integer)this.handle.getIntegers().read(3)).intValue() / 32.0D;
  }
  
  public void setZ(double value)
  {
    this.handle.getIntegers().write(3, Integer.valueOf((int)Math.floor(value * 32.0D)));
  }
  
  public float getYaw()
  {
    return ((Byte)this.handle.getBytes().read(0)).byteValue() * 360.0F / 256.0F;
  }
  
  public void setYaw(float value)
  {
    this.handle.getBytes().write(0, Byte.valueOf((byte)(int)(value * 256.0F / 360.0F)));
  }
  
  public float getPitch()
  {
    return ((Byte)this.handle.getBytes().read(1)).byteValue() * 360.0F / 256.0F;
  }
  
  public void setPitch(float value)
  {
    this.handle.getBytes().write(1, Byte.valueOf((byte)(int)(value * 256.0F / 360.0F)));
  }
  
  public int getCurrentItem()
  {
    return ((Integer)this.handle.getIntegers().read(4)).intValue();
  }
  
  public void setCurrentItem(int value)
  {
    this.handle.getIntegers().write(4, Integer.valueOf(value));
  }
  
  public WrappedDataWatcher getMetadata()
  {
    return (WrappedDataWatcher)this.handle.getDataWatcherModifier().read(0);
  }
  
  public void setMetadata(WrappedDataWatcher value)
  {
    this.handle.getDataWatcherModifier().write(0, value);
  }
}
