package map;

import java.awt.*;
import java.util.*;
import java.util.function.*;


public class InfiniteGridMap <T> implements GridMap <T>
{
   private T _defaultValue;
   
   private HashMap <Point, T> _map;
   
   
   public InfiniteGridMap(T defaultValue)
   {
      _map = new HashMap <>();
      _defaultValue = defaultValue;
   }
   
   
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
   
   
   public String toMapString(Function <T, Character> translator)
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
            char rep = translator.apply(get(new Point(x, y)));
            sb.append(rep);
         }
         
         sb.append('\n');
      }
      
      return sb.toString();
   }
}
