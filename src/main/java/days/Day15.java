package days;

import repair.*;

import java.io.*;


public class Day15
{
   public static void main(String... args) throws IOException
   {
      RepairDroid droid = new RepairDroid();
      
      droid.autoFindOxygen();
      
      System.out.println(droid.getFormattedMap());
      System.out.println(droid.getMap().countOf(RepairDroid.TILE_PATH));
   }
}
