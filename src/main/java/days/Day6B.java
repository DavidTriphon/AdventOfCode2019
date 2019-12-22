package days;

import solarMap.*;

import java.io.*;
import java.util.*;


public class Day6B
{
   private static final String inputFileLoc = "src/main/resources/input6.txt";
   
   public static void main(String... args) throws IOException
   {
      BufferedReader inputReader = new BufferedReader(new FileReader(inputFileLoc));
      
      String line = null;
   
      HashMap<String, String> orbitMap = new HashMap <>();
      
      while ((line = inputReader.readLine()) != null)
      {
         String[] bodies = line.split("\\)");
         
         orbitMap.put(bodies[1], bodies[0]);
      }
   
      SolarMap map = new SolarMap(orbitMap);
   
   
      System.out.println(map.getDistanceBetween("YOU", "SAN") - 2);
   }
}
