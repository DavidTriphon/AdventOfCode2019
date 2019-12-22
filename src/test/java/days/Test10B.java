package days;

import asteroids.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class Test10B
{
   private static final String FILE_LOC_PREFIX = "src/test/resources/10B/";
   private static final String FILE_LOC_SUFFIX = ".txt";
   
   
   @Test
   void asteroidTestA()
   {
      testAsteroidMap("A", Map.entry(8, 3), 17, 5,
        List.of(
          Map.entry(8, 1),  // 1
          Map.entry(9, 0),  // 2
          Map.entry(9, 1),  // 3
          Map.entry(10, 0), // 4
          Map.entry(9, 2),  // 5
          Map.entry(11, 1), // 6
          Map.entry(12, 1), // 7
          Map.entry(11, 2), // 8
          Map.entry(15, 1), // 9
          Map.entry(12, 2), // 10
          Map.entry(13, 2), // 11
          Map.entry(14, 2), // 12
          Map.entry(15, 2), // 13
          Map.entry(12, 3), // 14
          Map.entry(16, 4), // 15
          Map.entry(15, 4), // 16
          Map.entry(10, 4), // 17
          Map.entry(4, 4), // 18
          Map.entry(2, 4), // 19
          Map.entry(2, 3), // 20
          Map.entry(0, 2), // 21
          Map.entry(1, 2), // 22
          Map.entry(0, 1), // 23
          Map.entry(1, 1), // 24
          Map.entry(5, 2), // 25
          Map.entry(1, 0), // 26
          Map.entry(5, 1), // 27
          Map.entry(6, 1), // 28
          Map.entry(6, 0), // 29
          Map.entry(7, 0), // 30
          Map.entry(8, 0), // 31
          Map.entry(10, 1), // 32
          Map.entry(14, 0), // 33
          Map.entry(16, 1), // 34
          Map.entry(13, 3), // 35
          Map.entry(14, 3) // 36
        )
      );
   }
   
   @Test
   void testATan2()
   {
      double a = Math.atan2(0,0);
      System.out.println("cntr: " + a);
      
      a = Math.atan2(0,1);
      System.out.println("x+: " + a);
      
      a = Math.atan2(1,0);
      System.out.println("y+: " + a);
      
      a = Math.atan2(0,-1);
      System.out.println("x-: " + a);
      
      a = Math.atan2(-1,0);
      System.out.println("y-: " + a);
   }
   
   
   private static void testAsteroidMap(String fileLoc,
     Map.Entry <Integer, Integer> stationPos, int width, int height,
     List <Map.Entry <Integer, Integer>> vaporizeOrder)
   {
      try
      {
         AsteroidMap map = getMap(fileLoc);
   
         assertEquals(width, map.width);
         assertEquals(height, map.height);
         assertEquals(stationPos, map.getStationPos());
         
         int i = 1;
         
         while (map.getAsteroidLocations().size() > 1)
         {
            Map.Entry <Integer, Integer> pos = map.removeNextAsteroid();
            Map.Entry <Integer, Integer> expected = vaporizeOrder.get(i - 1);
            
            System.out.printf("%02d: (%d, %d)\n", i, pos.getKey(), pos.getValue());
            assertEquals(expected, pos,
              String.format("vaporized asteroid #%d should be (%d,%d); not (%d,%d)", i,
                expected.getKey(), expected.getValue(), pos.getKey(), pos.getValue()));
            
            i++;
         }
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static AsteroidMap getMap(String fileLoc) throws IOException
   {
      String fileString = Files.readString(Path.of(FILE_LOC_PREFIX + fileLoc + FILE_LOC_SUFFIX),
        StandardCharsets.US_ASCII).trim();
      
      return AsteroidMap.fromString(fileString);
   }
}
