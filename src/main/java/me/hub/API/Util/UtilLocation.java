package me.hub.API.Util;

import java.util.HashSet;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class UtilLocation
{
  public static Entity[] getNearbyEntities(Location location, int radius)
  {
    int chunkRadius = radius < 16 ? 1 : (radius - radius % 16) / 16;
    HashSet<Entity> radiusEntities = new HashSet();
    for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
      for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++)
      {
        int x = (int)location.getX();int y = (int)location.getY();int z = (int)location.getZ();
        Entity[] arrayOfEntity;
        int j = (arrayOfEntity = new Location(location.getWorld(), x + chX * 16, y, z + chZ * 16).getChunk().getEntities()).length;
        for (int i = 0; i < j; i++)
        {
          Entity entity = arrayOfEntity[i];
          if ((entity.getLocation().distanceSquared(location) <= radius) && (entity.getLocation().getBlock() != location.getBlock())) {
            radiusEntities.add(entity);
          }
        }
      }
    }
    return (Entity[])radiusEntities.toArray(new Entity[radiusEntities.size()]);
  }
}
