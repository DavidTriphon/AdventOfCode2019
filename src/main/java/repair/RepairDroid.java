package repair;

import cpu.*;
import map.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.function.*;


public class RepairDroid
{
   // public constants
   
   public static final int TILE_UNKNOWN = 0;
   public static final int TILE_PATH    = 1;
   public static final int TILE_BLOCKED = 2;
   public static final int TILE_ROBOT   = 3;
   public static final int TILE_OXYGEN  = 4;
   public static final int TILE_DEADEND = 5;
   
   public static final int RESPONSE_BLOCKED = 0;
   public static final int RESPONSE_MOVED   = 1;
   public static final int RESPONSE_FOUND   = 2;
   
   // private constants
   
   private static final long MOVE_NORTH = 1;
   private static final long MOVE_SOUTH = 2;
   private static final long MOVE_EAST  = 3;
   private static final long MOVE_WEST  = 4;
   
   private static final String PROGRAM_FILE_LOC = "src/main/resources/input15.txt";
   
   private static final Map <Direction, Long> DIR_MAP = Map.ofEntries(
     Map.entry(Direction.UP, MOVE_NORTH),
     Map.entry(Direction.DOWN, MOVE_SOUTH),
     Map.entry(Direction.RIGHT, MOVE_EAST),
     Map.entry(Direction.LEFT, MOVE_WEST)
   );
   
   private static final Function <Integer, String> TRANSLATOR = (i) ->
   {
      switch (i)
      {
         case TILE_UNKNOWN:
            return "~~";
         case TILE_PATH:
            return "  ";
         case TILE_BLOCKED:
            return "##";
         case TILE_ROBOT:
            return "()";
         case TILE_OXYGEN:
            return "!!";
         case TILE_DEADEND:
            return "..";
         default:
            return "??";
      }
   };
   
   // fields
   
   private Program _program = new Program();
   
   private InfiniteGridMap <Integer> _locationInfo = new InfiniteGridMap <>(TILE_UNKNOWN);
   
   private Point _currentPos = new Point(0, 0);
   
   private Point _oxygenLoc = null;
   
   // constructor
   
   
   public RepairDroid()
   {
      // this should never have problems, and I don't want the constructor throwing exceptions
      try
      {
         _program.setMemory(Program.getMemoryFromFile(PROGRAM_FILE_LOC));
      }
      catch (IOException exc)
      {
         System.exit(-1);
      }
      
      _locationInfo.set(_currentPos, TILE_PATH);
   }
   
   // public methods
   
   public void autoFindOxygen()
   {
      Direction dir = Direction.UP;
   
      while (!foundOxygen())
      {
         if (move(dir.right()) != RepairDroid.RESPONSE_BLOCKED)
         {
            move(dir.left());
         }
      
         if (move(dir.left()) != RepairDroid.RESPONSE_BLOCKED)
         {
            move(dir.right());
         }
      
         if (move(dir) == RepairDroid.RESPONSE_BLOCKED)
         {
            dir = dir.right();
         
            if (move(dir) == RepairDroid.RESPONSE_BLOCKED)
            {
               dir = dir.down();
            }
         }
      }
   }
   
   
   public int move(Direction moveDir)
   {
      
      _program.setInput(new Long[] {DIR_MAP.get(moveDir)});
      _program.compute();
      
      Long[] output = _program.getOutput();
      int response = (int) (long) output[output.length - 1];
      
      switch (response)
      {
         case RESPONSE_BLOCKED:
         {
            Point posInFront = new Point(_currentPos);
            moveDir.move(posInFront);
            _locationInfo.set(posInFront, TILE_BLOCKED);
         }
         break;
         case RESPONSE_MOVED:
         {
            Point previousPos = new Point(_currentPos);
            moveDir.move(_currentPos);
            if (_locationInfo.get(_currentPos) == TILE_PATH)
               _locationInfo.set(previousPos, TILE_DEADEND);
            _locationInfo.set(_currentPos, TILE_PATH);
         }
         break;
         case RESPONSE_FOUND:
         {
            Point previousPos = new Point(_currentPos);
            moveDir.move(_currentPos);
            if (_locationInfo.get(_currentPos) == TILE_PATH)
               _locationInfo.set(previousPos, TILE_DEADEND);
            _locationInfo.set(_currentPos, TILE_PATH);
            _oxygenLoc = new Point(_currentPos);
         }
         break;
      }
      
      return response;
   }
   
   
   public Point getRobotPos()
   {
      return new Point(_currentPos);
   }
   
   
   public boolean foundOxygen()
   {
      return _oxygenLoc != null;
   }
   
   
   public Point getOxygenPos()
   {
      return new Point(_oxygenLoc);
   }
   
   
   public String getFormattedMap()
   {
      return getMap().toMapStringS(TRANSLATOR);
   }
   
   
   public InfiniteGridMap <Integer> getMap()
   {
      InfiniteGridMap <Integer> copy = new InfiniteGridMap <>(_locationInfo);
      copy.set(_currentPos, TILE_ROBOT);
      if (foundOxygen())
         copy.set(_oxygenLoc, TILE_OXYGEN);
      return copy;
   }
}
