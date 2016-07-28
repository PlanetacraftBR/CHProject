package me.hub.API.Util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class UtilDirection
{
  public static enum Direction
  {
    NORTH,  EAST,  WEST,  SOUTH;
  }
  
  public static Direction getCardinalDirection(Player player)
  {
    double rotation = (player.getLocation().getYaw() - 90.0F) % 360.0F;
    if (rotation < 0.0D) {
      rotation += 360.0D;
    }
    if ((0.0D <= rotation) && (rotation < 67.5D)) {
      return Direction.NORTH;
    }
    if ((67.5D <= rotation) && (rotation < 112.5D)) {
      return Direction.EAST;
    }
    if ((157.5D <= rotation) && (rotation < 247.5D)) {
      return Direction.SOUTH;
    }
    if ((247.5D <= rotation) && (rotation < 337.5D)) {
      return Direction.WEST;
    }
    return Direction.NORTH;
  }
}
