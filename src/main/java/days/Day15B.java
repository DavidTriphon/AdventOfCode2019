package days;

import repair.*;

import java.io.*;


public class Day15B
{
   public static void main(String... args) throws IOException
   {
      RepairDroid droid = new RepairDroid();
      
      droid.autoExploreMap();
      
      System.out.println(droid.getFormattedMap());
      System.out.println(droid.timeToOxygenation());
   }
}
