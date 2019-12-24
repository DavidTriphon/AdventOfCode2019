package map;

import java.awt.*;
import java.util.*;
import java.util.function.*;


public class InfiniteGridMap <T> implements GridMap <T>
{
   // fields
   
   private T _defaultValue;
   
   private HashMap <Point, T> _map;
   
   // constructors
   
   
   public InfiniteGridMap(InfiniteGridMap <T> map)
   {
      _map = new HashMap <>(map._map);
      _defaultValue = map._defaultValue;
   }
   
   
   public InfiniteGridMap(T defaultValue)
   {
      _map = new HashMap <>();
      _defaultValue = defaultValue;
   }
   
   // implemented methods
   
   
   @Override
   public T get(Point pos)
   {
      if (_map.containsKey(pos))
         return _map.get(pos);
      
      return _defaultValue;
   }
   
   
   @Override
   public void set(Point pos, T obj)
   {
      _map.put(new Point(pos), obj);
   }
   
   // public methods
   
   
   public long countOf(T obj)
   {
      if (obj == _defaultValue)
         return Long.MAX_VALUE; // infinity, but not really
      
      long count = 0;
      
      for (T t : _map.values())
      {
         if (obj.equals(t))
            count++;
      }
      
      return count;
   }
   
   public HashMap <Point, T> getLocations()
   {
      HashMap <Point, T> map = new HashMap <>();
      
      for (Map.Entry<Point, T> entry : _map.entrySet())
      {
         map.put(new Point(entry.getKey()), entry.getValue());
      }
      
      return map;
   }
   
   
   public String toMapString(Function <T, Character> translator)
   {
      return toMapStringS((i) -> (translator.apply(i).toString()));
   }
   
   
   public String toMapStringS(Function <T, String> translator)
   {
      // find bounds for rectangle representative
      int minX = Integer.MAX_VALUE;
      int maxX = Integer.MIN_VALUE;
      
      int minY = Integer.MAX_VALUE;
      int maxY = Integer.MIN_VALUE;
      
      for (Point pos : _map.keySet())
      {
         if (pos.x > maxX)
            maxX = pos.x;
         else if (pos.x < minX)
            minX = pos.x;
         
         if (pos.y > maxY)
            maxY = pos.y;
         else if (pos.y < minY)
            minY = pos.y;
      }
      
      // generate string grid using translator
      StringBuilder sb = new StringBuilder();
      
      for (int y = maxY; y >= minY; y--)
      {
         for (int x = minX; x <= maxX; x++)
         {
            String rep = translator.apply(get(new Point(x, y)));
            sb.append(rep);
         }
         
         sb.append('\n');
      }
      
      return sb.toString();
   }
}
