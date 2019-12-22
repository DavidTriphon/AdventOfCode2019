package days;

import asteroids.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day10B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input10.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      AsteroidMap map = AsteroidMap.fromString(fileString);
      
      map.setStationPosition(map.mostVisibleAsteroid());
      
      int count = 1;
      
      while (map.getAsteroidLocations().size() > 1)
      {
         Map.Entry <Integer, Integer> pos = map.removeNextAsteroid();
         System.out.printf("%03d: (%d, %d)\n", count++, pos.getKey(), pos.getValue());
      }
   }
}
