package days;

import cpu.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day2
{
   private static final String inputFileLoc = "src/main/resources/input2.txt";
   
   public static void main(String... args) throws IOException
   {
      String fileString = Files.readString(Path.of(inputFileLoc), StandardCharsets.US_ASCII).trim();
      
      ArrayList <Integer> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Integer.parseInt(i)));
      Integer[] opCodes = opCodesArray.toArray(new Integer[0]);
      
      Integer[] newMemory = Computer.compute(opCodes);
   
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < newMemory.length; i++)
      {
         sb.append(newMemory[i]);
         
         if (i + 1 < newMemory.length)
         {
            sb.append(',');
         }
      }
      
      System.out.println(sb.toString());
   }
}
