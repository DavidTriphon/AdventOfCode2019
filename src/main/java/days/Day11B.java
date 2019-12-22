package days;

import cpu.*;
import map.*;
import paint.*;

import java.awt.*;
import java.io.*;


public class Day11B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input11.txt";
   
   
   public static void main(String... args) throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
      
      Program program = new Program();
      program.setMemory(opCodes);
      
      InfiniteGridMap <Integer> map = new InfiniteGridMap <>(EmergencyPaintingRobot.PAINT_OLD);
      map.set(new Point(), EmergencyPaintingRobot.PAINT_WHITE);
      
      EmergencyPaintingRobot robot = new EmergencyPaintingRobot(program, map);
      
      robot.run();
   
      System.out.println(map.toMapString(
        (i) -> {
           switch (i)
           {
              case EmergencyPaintingRobot.PAINT_BLACK:
              case EmergencyPaintingRobot.PAINT_OLD:
              {
                 return ' ';
              }
              case EmergencyPaintingRobot.PAINT_WHITE:
              {
                 return 'X';
              }
              default:
                 return '.';
           }
        }
      ));
   }
}
