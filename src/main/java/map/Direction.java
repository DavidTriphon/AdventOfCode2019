package map;

import java.awt.*;


public enum Direction
{
   UP(0, 1),
   RIGHT(1, 0),
   DOWN(0, -1),
   LEFT(-1, 0);
   
   
   static
   {
      UP._ccw = LEFT;
      UP._cw = RIGHT;
      
      RIGHT._ccw = UP;
      RIGHT._cw = DOWN;
      
      DOWN._ccw = RIGHT;
      DOWN._cw = LEFT;
      
      LEFT._ccw = DOWN;
      LEFT._cw = UP;
   }
   
   
   public final int x, y;
   private Direction _ccw, _cw;
   
   
   Direction(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   
   public Direction ccw()
   {
      return _ccw;
   }
   
   
   public Direction cw()
   {
      return _cw;
   }
   
   
   public void move(Point pos)
   {
      move(pos, 1);
   }
   
   
   public void move(Point pos, int mag)
   {
      pos.x += x * mag;
      pos.y += y * mag;
   }
}
