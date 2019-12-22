package map;

import java.awt.*;


public interface GridMap <T>
{
   T get(Point pos);
   
   void set(Point pos, T obj);
}
