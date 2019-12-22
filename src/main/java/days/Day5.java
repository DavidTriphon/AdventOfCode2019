package days;

import cpu.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day5
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input5.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      ArrayList <Long> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Long.parseLong(i)));
      Long[] opCodes = opCodesArray.toArray(new Long[0]);
      
      Program program = new Program();
      program.setInput(new Long[]{1L});
      program.setMemory(opCodes);
      
      program.compute();
      
      System.out.println(Arrays.asList(program.getOutput()));
   }
}
