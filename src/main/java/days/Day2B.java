package days;

import cpu.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day2B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input2.txt";
   
   private static final int DESIRED_RESULT = 19690720;
   
   private static final int NOUN_LOC = 1;
   private static final int VERB_LOC = 2;
   
   private static final int UPPER_LIMIT = 100;
   private static final int LOWER_LIMIT = 0;
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      ArrayList <Integer> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Integer.parseInt(i)));
      Integer[] opCodes = opCodesArray.toArray(new Integer[0]);
      
      Integer[] newMemory;
      
      int result;
      int noun = LOWER_LIMIT;
      int verb = LOWER_LIMIT;
      
      opCodes[NOUN_LOC] = noun;
      opCodes[VERB_LOC] = verb;
      newMemory = Computer.compute(opCodes);
      result = newMemory[0];
      
      while (result != DESIRED_RESULT)
      {
         verb++;
         
         if (verb >= UPPER_LIMIT)
         {
            verb = 0;
            noun++;
         }
         
         opCodes[NOUN_LOC] = noun;
         opCodes[VERB_LOC] = verb;
         newMemory = Computer.compute(opCodes);
         result = newMemory[0];
      }
   
      System.out.printf("Noun: %d, Verb: %d", noun, verb);
   }
}
