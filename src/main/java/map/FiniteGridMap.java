package map;

import java.awt.*;
import java.util.*;
import java.util.function.*;


public class FiniteGridMap <T> implements GridMap <T>
{
   // fields
   
   private Object[][] _map;
   
   public final int width;
   public final int height;
   
   // constructors
   
   
   public FiniteGridMap(FiniteGridMap <T> map)
   {
      width = map.width;
      height = map.height;
      
      _map = Arrays.copyOf(map._map, width * height);
   }
   
   
   public FiniteGridMap(int width, int height)
   {
      this.width = width;
      this.height = height;
      _map = new Object[width][height];
   }
   
   
   public FiniteGridMap(int width, int height, T defaultValue)
   {
      this(width, height);
      
      for (int x = 0; x < width; x++)
      {
         for (int y = 0; y < height; y++)
         {
            _map[x][y] = defaultValue;
         }
      }
   }
   
   // public methods
   
   
   @SuppressWarnings("unchecked")
   public T get(Point pos)
   {
      checkBounds(pos);
      return (T) _map[pos.x][pos.y];
   }
   
   
   public void set(Point pos, T obj)
   {
      checkBounds(pos);
      _map[pos.x][pos.y] = obj;
   }
   
   
   public int getCountOf(T obj)
   {
      int count = 0;
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            if (_map[x][y].equals(obj))
               count++;
         }
      }
      
      return count;
   }
   
   
   public String toMapString(Function <T, Character> translator)
   {
      return toMapStringS((i) -> (translator.apply(i).toString()));
   }
   
   
   public String toMapStringS(Function <T, String> translator)
   {
      // generate string grid using translator
      StringBuilder sb = new StringBuilder();
      
      for (int y = height - 1; y >= 0; y--)
      {
         for (int x = 0; x < width; x++)
         {
            String rep = translator.apply((T)_map[x][y]);
            sb.append(rep);
         }
         
         sb.append('\n');
      }
      
      return sb.toString();
   }
   
   // private methods
   
   
   private void checkBounds(Point pos)
   {
      if (!(0 <= pos.x && pos.x < width))
         throw new IllegalArgumentException(
           String.format("X pos (%d) is out of bounds !(0 <= x && x < %d)", pos.x, width));
      if (!(0 <= pos.y && pos.y < height))
         throw new IllegalArgumentException(
           String.format("Y pos (%d) is out of bounds !(0 <= x && x < %d)", pos.y, height));
   }
   
   // static methods
   
   
   public static <T> FiniteGridMap <T> fromString(String mapString,
     Function <Character, T> translator)
   {
      String[] rows = mapString.trim().split("\n");
      
      // trim every row
      for (int i = 0; i < rows.length; i++)
      {
         rows[i] = rows[i].trim();
      }
      
      int width = rows[0].length();
      int height = rows.length;
      
      FiniteGridMap <T> map = new FiniteGridMap <>(width, height);
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char c = rows[y].charAt(x);
            
            map.set(new Point(x, y), translator.apply(c));
         }
      }
      
      return map;
   }
}
