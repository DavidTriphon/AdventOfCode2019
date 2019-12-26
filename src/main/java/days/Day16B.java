package days;

import fft.*;
import util.*;

import java.io.*;


public class Day16B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input16.txt";
   
   private static final int PHASE_COUNT = 100;
   
   private static final int SIGNAL_REPEAT_COUNT = 10000;
   
   
   public static void main(String... args) throws IOException
   {
      String signalString = ParseUtil.getFileString(INPUT_FILE_LOC);
   
      System.out.println("File parsed");
      
      int messageOffset = Integer.parseInt(signalString.substring(0, 7));
   
      System.out.println("Offset obtained");
      
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < SIGNAL_REPEAT_COUNT; i++)
      {
         sb.append(signalString);
      }
      
      System.out.println("String constructed");
      
      short[] digits = FFT.getDigitsFromString(sb.toString());
      
      System.out.println("Digits parsed");
      
      for (int phase = 0; phase < PHASE_COUNT; phase++)
      {
         System.out.println("Processing phase " + (phase + 1));
         
         digits = FFT.process(digits);
         
         System.out.println("Phase " + (phase + 1) + " completed.");
      }
      
      System.out.println("Stringifying digits...");
      
      String result = FFT.stringifyDigits(digits);
      
      System.out.println("Stringified digits");
      
      System.out.println(result.substring(messageOffset, messageOffset + 8));
   }
}
