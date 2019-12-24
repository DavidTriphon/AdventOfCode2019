package days;

import nbody.*;
import util.*;

import java.io.*;


public class Day12
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input12.txt";
   
   private static final String VECTOR_FORMAT = "<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>";
   
   private static final int STEP_COUNT = 1000;
   
   
   public static void main(String... args) throws IOException
   {
      String fileString = ParseUtil.getFileString(INPUT_FILE_LOC);
      
      NBody nBody = NBody.fromString(fileString);
      
      nBody.timeStep(STEP_COUNT);
      
      System.out.println(nBody.getEnergy());
   }
}
