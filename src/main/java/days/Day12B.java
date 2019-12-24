package days;

import nbody.*;
import util.*;

import java.io.*;


public class Day12B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input12.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString = ParseUtil.getFileString(INPUT_FILE_LOC);
      
      NBody nBody = NBody.fromString(fileString);
      
      System.out.print(nBody.getLoopTime());
   }
}
