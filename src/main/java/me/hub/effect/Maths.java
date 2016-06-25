package me.hub.effect;

import java.awt.Color;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.hub.Main;

public class Maths
{
  public static final HashMap<Player, Integer> arraylist = new HashMap();
  
  public static void stopEffect(final Player p)
  {
    if (arraylist.containsKey(p))
    {
      Bukkit.getScheduler().cancelTask(((Integer)arraylist.get(p)).intValue());
      arraylist.remove(p);
    }
  }
  
  public static boolean EffectActive(final Player p)
  {
    if (!arraylist.containsKey(p)) {
      return true;
    }
    return false;
  }
  
  public static void HaloEffect(final Player p, final String particle, final float speed)
  {
    if (!arraylist.containsKey(p))
    {
      int halo = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          float step = 0.0F;
          final double Angle = 0.5235987755982988D;
          
          public void run()
          {
            Location loc = p.getLocation();
            double x = Math.cos(0.5235987755982988D * this.step) * 0.4000000059604645D;
            double y = 2.200000047683716D;
            double z = Math.sin(0.5235987755982988D * this.step) * 0.4000000059604645D;
            Vector v = new Vector(x, y, z);
            loc.add(v);
            ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, loc, 50.0D);
            this.step += 1.0F;
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(halo));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void CircleEffect(final Player p, final String particle, final float speed)
  {
    if (!arraylist.containsKey(p))
    {
      int circle = Bukkit.getServer().getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public double xRotation;
          public double yRotation;
          public double zRotation = 5.0D;
          public double angularVelocityX = 0.015707963267948967D;
          public double angularVelocityY = 0.018479956785822312D;
          public double angularVelocityZ = 0.02026833970057931D;
          public float radius = 1.5F;
          protected float step = 0.0F;
          public double xSubtract;
          public double ySubtract;
          public double zSubtract;
          public boolean enableRotation = true;
          public int particles = 20;
          
          public void run()
          {
            Location location = p.getLocation();
            location.add(0.0D, 1.0D, 0.0D);
            location.subtract(this.xSubtract, this.ySubtract, this.zSubtract);
            double inc = 6.283185307179586D / this.particles;
            double angle = this.step * inc;
            Vector v = new Vector();
            v.setX(Math.cos(angle) * this.radius);
            v.setZ(Math.sin(angle) * this.radius);
            VectorUtils.rotateVector(v, this.xRotation, this.yRotation, 
              this.zRotation);
            if (this.enableRotation) {
              VectorUtils.rotateVector(v, this.angularVelocityX * 
                this.step, this.angularVelocityY * this.step, 
                this.angularVelocityZ * this.step);
            }
            
            ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, location.add(v), 50.0D);
            ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, location, 50.0D);
            
            
            this.step += 1.0F;
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(circle));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void AtomEffect(final Player p, final String particle, final float speed)
  {
    if (!arraylist.containsKey(p))
    {
      int atom = Bukkit.getServer().getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public int particlesOrbital = 5;
          public int orbitals = 3;
          public double rotation = 15.0D;
          public double angularVelocity = 0.07853981633974483D;
          protected int step = 0;
          public float radiusNucleus = 0.4F;
          public int particlesNucleus = 10;
          
          public void run()
          {
            Location loc = new Location(p.getWorld(), p
              .getLocation().getX(), p.getLocation()
              .getY() + 3.0D, p.getLocation().getZ());
            for (int i = 0; i < this.particlesNucleus; i++)
            {
              Vector v = RandomUtils.getRandomVector()
                .multiply(0.5D * this.radiusNucleus);
              loc.add(v);
              ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 
                0.0F, speed, 1, loc, new Player[] { p });
              loc.subtract(v);
            }
            for (int i = 0; i < this.particlesOrbital; i++)
            {
              double angle = this.step * this.angularVelocity;
              for (int j = 0; j < this.orbitals; j++)
              {
                double xRotation = 3.141592653589793D / this.orbitals * j;
                Vector v = new Vector(Math.sin(angle), 
                  Math.cos(angle), 0.0D).multiply(0.8D);
                VectorUtils.rotateAroundAxisX(v, xRotation);
                VectorUtils.rotateAroundAxisY(v, this.rotation);
                loc.add(v);
                ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, loc, 50.0D);
                loc.subtract(v);
              }
              this.step += 1;
            }
          }
        }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(atom));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void LineEffect(final Player p, final String particle, final float speed)
  {
    if (!arraylist.containsKey(p))
    {
      int line = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public void run()
          {
            Location loc = p.getLocation();
            loc.add(0.0D, 0.30000001192092896D, 0.0D);
            ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, loc, 50.0D);
          }
        }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(line));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void SpiralEffect(final Player p, final String particle, final float speed)
  {
    if (!arraylist.containsKey(p))
    {
      int spiral = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
      {
        float step = 0.0F;
        float i = 0.0F;
        
        public void run()
        {
          if (MovementDetection.isNotMoving(p))
          {
            Location loc = p.getLocation();
            double x = Math.sin(0.3141592653589793D * this.step) * 1.0D;
            double y = 0.3D * this.i;
            double z = Math.cos(0.3141592653589793D * this.step) * 1.0D;
            Vector v = new Vector(x, y, z);
            loc.add(v);
            ParticleEffect.valueOf(particle).display(0.0F, 0.0F, 0.0F, speed, 1, loc, 50.0D);
            this.step += 1.0F;
            this.i = ((float)(this.i + 0.1D));
            if (this.i > 6.0F) {
              this.i = 0.0F;
            }
          }
          else
          {
            ParticleEffect.valueOf(particle).display(0.05F, 0.05F, 0.05F, 0.05F, 2, p.getLocation(), 50.0D);
          }
        }
      }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(spiral));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void bandColored(final Player p, final String particle, final int r, final int g, final int b)
  {
    if (!arraylist.containsKey(p))
    {
      int band = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public void run()
          {
            if (!MovementDetection.isNotMoving(p))
            {
              Location loc = p.getLocation();
              for (int i = 0; i < 15; i++)
              {
                loc.setY(loc.getY() + 0.1D);
                
                ParticleEffect.valueOf(particle).display(new ParticleEffect.OrdinaryColor(r, g, b), loc, 50.0D);
              }
            }
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(band));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void HelixEffect(final Player p, final String particle, final int r, final int g, final int b)
  {
    if (!arraylist.containsKey(p))
    {
      int helix = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
      {
        float step = 2.0F;
        
        public void run()
        {
          if (MovementDetection.isNotMoving(p))
          {
            for (float k = 3.5F; k > 0.0F; k -= 0.1F)
            {
              Location loc = p.getLocation();
              Vector v = new Vector(k * Math.sin(k * this.step) / 3.0D, -k + 3.8D, k * Math.cos(k * this.step) / 3.0D);
              loc.add(v);
              
              ParticleEffect.valueOf(particle).display(new ParticleEffect.OrdinaryColor(r, g, b), loc, 50.0D);
            }
            this.step -= 0.1F;
            if (this.step <= -10.0F) {
              this.step = 10.0F;
            }
          }
          else
          {
            Location l = p.getLocation().add(0.0D, 1.0D, 0.0D);
            ParticleEffect.valueOf(particle).display(new ParticleEffect.OrdinaryColor(r, g, b), l.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(new ParticleEffect.OrdinaryColor(r, g, b), l.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(new ParticleEffect.OrdinaryColor(r, g, b), l.add(RandomUtils.getRandomVector()), 50.0D);
          }
        }
      }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(helix));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowAtom(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int atom = Bukkit.getServer().getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public int particlesOrbital = 5;
          public int orbitals = 3;
          public double rotation = 15.0D;
          public double angularVelocity = 0.07853981633974483D;
          protected int step = 0;
          public float radiusNucleus = 0.4F;
          public int particlesNucleus = 10;
          private float hue = 0.0F;
          
          public void run()
          {
            Location loc = new Location(p.getWorld(), p
              .getLocation().getX(), p.getLocation()
              .getY() + 3.0D, p.getLocation().getZ());
            
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            for (int i = 0; i < this.particlesNucleus; i++)
            {
              Vector v = RandomUtils.getRandomVector()
                .multiply(0.5D * this.radiusNucleus);
              loc.add(v);
              ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc, 50.0D);
              loc.subtract(v);
            }
            for (int i = 0; i < this.particlesOrbital; i++)
            {
              double angle = this.step * this.angularVelocity;
              for (int j = 0; j < this.orbitals; j++)
              {
                double xRotation = 3.141592653589793D / this.orbitals * j;
                Vector v = new Vector(Math.sin(angle), 
                  Math.cos(angle), 0.0D).multiply(0.8D);
                VectorUtils.rotateAroundAxisX(v, xRotation);
                VectorUtils.rotateAroundAxisY(v, this.rotation);
                loc.add(v);
                ParticleEffect.valueOf(particle).display(r, g, b, 
                  1.0F, 0, loc, 50.0D);
                loc.subtract(v);
              }
              this.step += 1;
            }
          }
        }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(atom));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowHalo(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int halo = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          float step = 0.0F;
          final double Angle = 0.5235987755982988D;
          private float hue = 0.0F;
          
          public void run()
          {
            Location loc = p.getLocation();
            
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            
            double x = Math.cos(0.5235987755982988D * this.step) * 0.4000000059604645D;
            double y = 2.200000047683716D;
            double z = Math.sin(0.5235987755982988D * this.step) * 0.4000000059604645D;
            Vector v = new Vector(x, y, z);
            loc.add(v);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc, 50.0D);
            this.step += 1.0F;
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(halo));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowCircle(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int circle = Bukkit.getServer().getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public double xRotation;
          public double yRotation;
          public double zRotation = 5.0D;
          public double angularVelocityX = 0.015707963267948967D;
          public double angularVelocityY = 0.018479956785822312D;
          public double angularVelocityZ = 0.02026833970057931D;
          public float radius = 1.5F;
          protected float step = 0.0F;
          public double xSubtract;
          public double ySubtract;
          public double zSubtract;
          public boolean enableRotation = true;
          public int particles = 20;
          private float hue = 0.0F;
          
          public void run()
          {
            Location location = p.getLocation();
            location.add(0.0D, 1.0D, 0.0D);
            
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            
            location.subtract(this.xSubtract, this.ySubtract, this.zSubtract);
            double inc = 6.283185307179586D / this.particles;
            double angle = this.step * inc;
            Vector v = new Vector();
            v.setX(Math.cos(angle) * this.radius);
            v.setZ(Math.sin(angle) * this.radius);
            VectorUtils.rotateVector(v, this.xRotation, this.yRotation, 
              this.zRotation);
            if (this.enableRotation) {
              VectorUtils.rotateVector(v, this.angularVelocityX * 
                this.step, this.angularVelocityY * this.step, 
                this.angularVelocityZ * this.step);
            }
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, location.add(v), 50.0D);
            this.step += 1.0F;
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(circle));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowBandEffect(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int rainbowband = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          public void run()
          {
            if (!MovementDetection.isNotMoving(p))
            {
              Location to = p.getLocation();
              for (int i = 0; i < 15; i++)
              {
                int argb = 
                  Color.HSBtoRGB(i / 20.0F, 1.0F, 1.0F);
                float r = (argb >> 16 & 0xFF) / 255.0F;
                float g = (argb >> 8 & 0xFF) / 255.0F;
                float b = (argb & 0xFF) / 255.0F;
                r = r == 0.0F ? 0.001F : r;
                ParticleEffect.valueOf(particle).display(r, g, 
                  b, 1.0F, 0, to, 50.0D);
                to.setY(to.getY() + 0.1D);
              }
            }
          }
        }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(rainbowband));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowLine(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int rainbowline = Bukkit.getScheduler()
        .runTaskTimer(Main.plugin, new Runnable()
        {
          private float hue = 0.0F;
          
          public void run()
          {
            Location loc = p.getLocation();
            loc.add(0.0D, 0.30000001192092896D, 0.0D);
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
          }
        }, 1L, 1L).getTaskId();
      arraylist.put(p, Integer.valueOf(rainbowline));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void RainbowSpiralEffect(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int spiral = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
      {
        float step = 0.0F;
        float i = 0.0F;
        private float hue = 0.0F;
        
        public void run()
        {
          if (MovementDetection.isNotMoving(p))
          {
            Location loc = p.getLocation();
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            double x = Math.sin(0.3141592653589793D * this.step) * 1.0D;
            double y = 0.3D * this.i;
            double z = Math.cos(0.3141592653589793D * this.step) * 1.0D;
            Vector v = new Vector(x, y, z);
            loc.add(v);
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 
              1.0F, 0, loc, 50.0D);
            this.step += 1.0F;
            this.i = ((float)(this.i + 0.1D));
            if (this.i > 6.0F) {
              this.i = 0.0F;
            }
          }
          else
          {
            Location loc = p.getLocation();
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
          }
        }
      }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(spiral));
    }
    else
    {
      stopEffect(p);
    }
  }
  
  public static void rainbowHelixEffect(final Player p, final String particle)
  {
    if (!arraylist.containsKey(p))
    {
      int helix = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
      {
        float step = 2.0F;
        private float hue = 0.0F;
        
        public void run()
        {
          if (MovementDetection.isNotMoving(p))
          {
            for (float k = 3.5F; k > 0.0F; k -= 0.1F)
            {
              Location loc =  p.getLocation();
              int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
              float r = (argb >> 16 & 0xFF) / 255.0F;
              float g = (argb >> 8 & 0xFF) / 255.0F;
              float b = (argb & 0xFF) / 255.0F;
              r = r == 0.0F ? 0.001F : r;
              this.hue += 0.01F;
              this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
              Vector v = new Vector(k * Math.sin(k * this.step) / 3.0D, -k + 3.8D, k * Math.cos(k * this.step) / 3.0D);
              loc.add(v);
              
              ParticleEffect.valueOf(particle).display(r, g, b, 
                1.0F, 0, loc, 50.0D);
            }
            this.step -= 0.1F;
            if (this.step <= -10.0F) {
              this.step = 10.0F;
            }
          }
          else
          {
            Location loc = p.getLocation();
            int argb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
            float r = (argb >> 16 & 0xFF) / 255.0F;
            float g = (argb >> 8 & 0xFF) / 255.0F;
            float b = (argb & 0xFF) / 255.0F;
            r = r == 0.0F ? 0.001F : r;
            this.hue += 0.01F;
            this.hue = (this.hue >= 1.0F ? 0.0F : this.hue);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
            ParticleEffect.valueOf(particle).display(r, g, b, 1.0F, 0, loc.add(RandomUtils.getRandomVector()), 50.0D);
          }
        }
      }, 0L, 0L).getTaskId();
      arraylist.put(p, Integer.valueOf(helix));
    }
    else
    {
      stopEffect(p);
    }
  }
}
