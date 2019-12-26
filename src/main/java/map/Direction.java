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
   
   
   public Direction left()
   {
      return _ccw;
   }
   
   
   public Direction right()
   {
      return _cw;
   }
   
   
   public Direction up()
   {
      return this;
   }
   
   
   public Direction down()
   {
      return right().right();
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
   
   
   public Point offset(Point pos)
   {
      return offset(pos, 1);
   }
   
   
   public Point offset(Point pos, int mag)
   {
      Point ret = new Point(pos);
      move(ret, mag);
      return ret;
   }
}
