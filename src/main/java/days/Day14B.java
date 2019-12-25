package days;

import nano.*;
import util.*;

import java.io.*;


public class Day14B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input14.txt";
   
   private static final long MINED_ORE = 1000000000000L;
   
   public static void main(String... args) throws IOException
   {
      String fileString = ParseUtil.getFileString(INPUT_FILE_LOC);
      
      NanoFactory fact = NanoFactory.fromString(fileString);
      
      System.out.println(fact.getMaxFuelFromOre(MINED_ORE));
   }
}
