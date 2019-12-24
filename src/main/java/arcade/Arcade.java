package arcade;

import cpu.*;
import map.*;

import java.awt.*;
import java.io.*;
import java.util.function.*;


public class Arcade
{
   // constants
   
   public static final int TILE_EMPTY  = 0;
   public static final int TILE_WALL   = 1;
   public static final int TILE_BLOCK  = 2;
   public static final int TILE_PADDLE = 3;
   public static final int TILE_BALL   = 4;
   
   private static final String PROGRAM_FILE_LOC = "src/main/resources/input13.txt";
   
   private static final Function <Integer, String> TRANSLATOR = (i) ->
   {
      switch (i)
      {
         case TILE_EMPTY:
            return "  ";
         case TILE_WALL:
            return "  ";
         case TILE_BLOCK:
            return "[]";
         case TILE_PADDLE:
            return "--";
         case TILE_BALL:
            return "()";
         default:
            return "??";
      }
   };
   
   // fields
   
   private final Program _program = new Program();
   
   private InfiniteGridMap <Integer> _screen = new InfiniteGridMap <>(TILE_EMPTY);
   
   private int outIndex = 0;
   
   // constructors
   
   
   public Arcade()
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
   }
   
   // public methods
   
   
   public void run()
   {
      _program.compute();
      
      Long[] out = _program.getOutput();
      
      while (outIndex < out.length)
      {
         long x = out[outIndex++];
         long y = out[outIndex++];
         long tile = out[outIndex++];
         
         _screen.set(new Point((int) x, (int) y), (int) tile);
      }
   }
   
   
   public InfiniteGridMap <Integer> getScreen()
   {
      return new InfiniteGridMap <>(_screen);
   }
   
   
   public String getScreenString()
   {
      return _screen.toMapStringS(TRANSLATOR);
   }
}
