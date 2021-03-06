package days;

import cpu.*;

import java.io.*;
import java.util.*;


public class Day9B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input9.txt";
   
   
   public static void main(String... args) throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
   
      Program program = new Program();
      
      program.setMemory(opCodes);
      program.setInput(new Long[]{2L});
      program.compute();
      System.out.println(Arrays.asList(program.getOutput()));
   }
}
