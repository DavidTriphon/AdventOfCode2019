package days;

import asteroids.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day10
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input10.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      AsteroidMap map = AsteroidMap.fromString(fileString);
      
      Map.Entry <Integer, Integer> pos = map.mostVisibleAsteroid();
      
      System.out.printf("(%d,%d)\n", pos.getKey(), pos.getValue());
      System.out.println("Visibility: " + map.visibility(pos));
   }
}
