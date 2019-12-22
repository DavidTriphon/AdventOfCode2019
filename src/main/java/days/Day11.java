package days;

import cpu.*;
import map.*;
import paint.*;

import java.io.*;


public class Day11
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input11.txt";
   
   
   public static void main(String... args) throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
      
      Program program = new Program();
      program.setMemory(opCodes);
      
      InfiniteGridMap <Integer> map = new InfiniteGridMap <>(EmergencyPaintingRobot.PAINT_OLD);
      
      EmergencyPaintingRobot robot = new EmergencyPaintingRobot(program, map);
      
      robot.run();
      
      long black = map.countOf(EmergencyPaintingRobot.PAINT_BLACK);
      long white = map.countOf(EmergencyPaintingRobot.PAINT_WHITE);
      
      long paintUsed = black + white;
      
      System.out.println(paintUsed);
   }
}
