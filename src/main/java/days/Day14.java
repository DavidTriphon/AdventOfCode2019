package days;

import nano.*;
import util.*;

import java.io.*;


public class Day14
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input14.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString = ParseUtil.getFileString(INPUT_FILE_LOC);
      
      NanoFactory fact = NanoFactory.fromString(fileString);
      
      System.out.println(fact.getOreCostOfFuel());
   }
}
