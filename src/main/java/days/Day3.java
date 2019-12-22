package days;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;


public class Day3
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input3.txt";
   
   private static final char UP    = 'U';
   private static final char DOWN  = 'D';
   private static final char LEFT  = 'L';
   private static final char RIGHT = 'R';
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      HashMap <Map.Entry <Integer, Integer>, Boolean> map = new HashMap <>();
      
      String[] wires = fileString.split("\n");
      String[] wire1 = wires[0].split(",");
      String[] wire2 = wires[1].split(",");
      
      int distance = Integer.MAX_VALUE;
      int posX = 0, posY = 0;
      
      for (String direction : wire1)
      {
         char dir = direction.charAt(0);
         int time = Integer.parseInt(direction.substring(1).trim());
         
         for (int i = 0; i < time; i++)
         {
            switch (dir)
            {
               case UP:
                  posY++;
                  break;
               case DOWN:
                  posY--;
                  break;
               case RIGHT:
                  posX++;
                  break;
               case LEFT:
                  posX--;
                  break;
            }
            
            map.put(Map.entry(posX, posY), true);
         }
      }
   
      posX = 0;
      posY = 0;
   
      for (String direction : wire2)
      {
         char dir = direction.charAt(0);
         int time = Integer.parseInt(direction.substring(1));
      
         for (int i = 0; i < time; i++)
         {
            switch (dir)
            {
               case UP:
                  posY++;
                  break;
               case DOWN:
                  posY--;
                  break;
               case RIGHT:
                  posX++;
                  break;
               case LEFT:
                  posX--;
                  break;
            }
         
            Boolean collision = map.get(Map.entry(posX, posY));
            if (collision != null && collision)
            {
               int dist = Math.abs(posX) + Math.abs(posY);
               if (dist < distance)
                  distance = dist;
            }
         }
      }
   
      System.out.println(distance);
   }
}
