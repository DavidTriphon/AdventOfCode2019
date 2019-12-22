package asteroids;

import java.util.*;


public class AsteroidMap
{
   // constants
   
   private static final char ASTEROID = '#';
   private static final char EMPTY    = '.';
   private static final char STATION  = 'X';
   
   private static final double ANGLE_INVALID = -10;
   private static final double ANGLE_START   = 0;
   
   // instance fields
   
   public final int width, height;
   
   private HashSet <Map.Entry <Integer, Integer>> _asteroidSet;
   private Map.Entry <Integer, Integer>           _stationPos = null;
   
   private double _stationFacing = ANGLE_INVALID;
   
   // constructor
   
   
   public AsteroidMap(int width, int height)
   {
      this.width = width;
      this.height = height;
      
      _asteroidSet = new HashSet <>();
   }
   
   // public methods
   
   
   public void addAsteroidAt(Map.Entry <Integer, Integer> pos)
   {
      _asteroidSet.add(pos);
   }
   
   
   public void removeAsteroidAt(Map.Entry <Integer, Integer> pos)
   {
      _asteroidSet.remove(pos);
   }
   
   
   public boolean isAsteroidAt(Map.Entry <Integer, Integer> pos)
   {
      return _asteroidSet.contains(pos);
   }
   
   
   public HashSet <Map.Entry <Integer, Integer>> getAsteroidLocations()
   {
      return new HashSet <>(_asteroidSet);
   }
   
   
   public Map.Entry <Integer, Integer> mostVisibleAsteroid()
   {
      Map.Entry <Integer, Integer> asteroid = null;
      int maxVisibleCount = 0;
      
      for (Map.Entry <Integer, Integer> pos1 : _asteroidSet)
      {
         int visibleCount = visibility(pos1);
         
         if (visibleCount > maxVisibleCount)
         {
            maxVisibleCount = visibleCount;
            asteroid = pos1;
         }
      }
      
      return asteroid;
   }
   
   
   public int visibility(Map.Entry <Integer, Integer> pos)
   {
      int visibleCount = 0;
      
      for (Map.Entry <Integer, Integer> pos2 : _asteroidSet)
      {
         if (!pos.equals(pos2))
         {
            if (canSee(pos, pos2))
               visibleCount++;
         }
      }
      
      return visibleCount;
   }
   
   
   public void setStationPosition(Map.Entry <Integer, Integer> pos)
   {
      _stationPos = pos;
   }
   
   
   public Map.Entry <Integer, Integer> getStationPos()
   {
      return _stationPos;
   }
   
   
   public Map.Entry <Integer, Integer> removeNextAsteroid()
   {
      Map.Entry <Integer, Integer> closest = nextVisibleAsteroid();
      if (closest == null)
         return null;
      
      _stationFacing = getAngleTo(closest);
      removeAsteroidAt(closest);
      return closest;
   }
   
   
   private double getAngleTo(Map.Entry <Integer, Integer> pos)
   {
      int dx = pos.getKey() - _stationPos.getKey();
      int dy = pos.getValue() - _stationPos.getValue();
      
      // the dx to dy swap is not a mistake!
      // This determines the rotation direction and theta=0 direction
      return Math.atan2(-dx, dy);
   }
   
   // private methods
   
   
   private boolean canSee(Map.Entry <Integer, Integer> pos1, Map.Entry <Integer, Integer> pos2)
   {
      int dx = pos2.getKey() - pos1.getKey();
      int dy = pos2.getValue() - pos1.getValue();
      
      int gcd = gcd(Math.abs(dx), Math.abs(dy));
      
      dx /= gcd;
      dy /= gcd;
      
      for (int mul = 1; mul < gcd; mul++)
      {
         int mx = pos1.getKey() + dx * mul;
         int my = pos1.getValue() + dy * mul;
         
         if (isAsteroidAt(Map.entry(mx, my)))
            return false;
      }
      
      return true;
   }
   
   
   private Map.Entry <Integer, Integer> nextVisibleAsteroid()
   {
      if (_asteroidSet.size() == 0)
         return null;
      
      if (_stationFacing == ANGLE_INVALID)
         return firstVisibleAsteroid();
      
      Map.Entry <Integer, Integer> closest = null;
      double closeRotationTo = 10; // at least greater than 2 pi
      
      while (closest == null)
      {
         for (Map.Entry <Integer, Integer> pos : _asteroidSet)
         {
            if (!pos.equals(_stationPos) && canSee(_stationPos, pos))
            {
               double rotationTo = getAngleTo(pos) - _stationFacing;
               
               if (rotationTo > 0 && rotationTo < closeRotationTo)
               {
                  closeRotationTo = rotationTo;
                  closest = pos;
               }
            }
         }
         
         if (closest == null)
         {
            _stationFacing -= 2 * Math.PI;
         }
      }
      return closest;
   }
   
   
   private Map.Entry <Integer, Integer> firstVisibleAsteroid()
   {
      _stationFacing = ANGLE_START;
      
      final int x = _stationPos.getKey();
      int y = _stationPos.getValue();
      
      while (--y >= 0)
      {
         if (_asteroidSet.contains(Map.entry(x, y)))
         {
            return Map.entry(x, y);
         }
      }
      
      return nextVisibleAsteroid();
   }
   
   // public static methods
   
   
   public static AsteroidMap fromString(String mapString)
   {
      String[] rows = mapString.trim().split("\r\n");
      
      int width = rows[0].length();
      int height = rows.length;
      
      AsteroidMap map = new AsteroidMap(width, height);
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char loc = rows[y].charAt(x);
            
            if (loc == ASTEROID)
            {
               map.addAsteroidAt(Map.entry(x, y));
            }
            else if (loc == STATION)
            {
               map.addAsteroidAt(Map.entry(x, y));
               map.setStationPosition(Map.entry(x, y));
            }
         }
      }
      
      return map;
   }
   
   // private static methods
   
   
   private static int gcd(int a, int b)
   {
      if (b == 0)
         return a;
      return gcd(b, a % b);
   }
}
