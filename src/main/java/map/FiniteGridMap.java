package map;

import java.awt.*;
import java.util.function.*;


public class FiniteGridMap <T> implements GridMap <T>
{
   private Object[][] _map;
   
   public final int width;
   public final int height;
   
   
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
   
   
   private void checkBounds(Point pos)
   {
      if (!(0 <= pos.x && pos.x < width))
         throw new IllegalArgumentException(
           String.format("X pos (%d) is out of bounds !(0 <= x && x < %d)", pos.x, width));
      if (!(0 <= pos.y && pos.y < height))
         throw new IllegalArgumentException(
           String.format("Y pos (%d) is out of bounds !(0 <= x && x < %d)", pos.y, height));
   }
   
   
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
