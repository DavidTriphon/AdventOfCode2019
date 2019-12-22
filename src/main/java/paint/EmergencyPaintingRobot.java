package paint;

import cpu.*;
import map.*;

import java.awt.*;


public class EmergencyPaintingRobot
{
   // constants
   
   public static final int PAINT_OLD   = -1;
   public static final int PAINT_BLACK = 0;
   public static final int PAINT_WHITE = 1;
   
   private static final int TURN_LEFT  = 0;
   private static final int TURN_RIGHT = 1;
   
   // instance fields
   
   private Program _program;
   
   private InfiniteGridMap <Integer> _map;
   
   private Point     _pos;
   private Direction _dir;
   
   // constructor
   
   
   public EmergencyPaintingRobot(Program program, InfiniteGridMap <Integer> map, Point pos)
   {
      _program = program;
      _map = map;
      _pos = new Point(pos);
      _dir = Direction.UP;
   }
   
   
   public EmergencyPaintingRobot(Program program, InfiniteGridMap <Integer> map)
   {
      this(program, map, new Point());
   }
   
   // public methods
   
   
   public void run()
   {
      while (!_program.isHalted())
      {
         // get input of current space
         int currentPaint = _map.get(_pos);
         
         // old paint is black paint
         if (currentPaint == PAINT_OLD)
            currentPaint = PAINT_BLACK;
         
         // give input to program
         _program.setInput(new Long[] {(long) currentPaint});
         
         // run program
         _program.compute();
         
         // get returned results
         Long[] out = _program.getOutput();
         int newPaint = out[out.length - 2].intValue();
         long dir = out[out.length - 1];
         
         // color the current space
         _map.set(_pos, newPaint);
         
         // turn
         if (dir == TURN_LEFT)
         {
            _dir = _dir.ccw();
         }
         else if (dir == TURN_RIGHT)
         {
            _dir = _dir.cw();
         }
         
         // move 1 space
         _dir.move(_pos);
      }
   }
}
