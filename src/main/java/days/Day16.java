package days;

import fft.*;
import util.*;

import java.io.*;


public class Day16
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input16.txt";
   
   private static final int PHASE_COUNT = 100;
   
   
   public static void main(String... args) throws IOException
   {
      short[] digits = FFT.getDigitsFromString(ParseUtil.getFileString(INPUT_FILE_LOC));
      
      for (int phase = 0; phase < PHASE_COUNT; phase++)
      {
         digits = FFT.process(digits);
      }
      
      System.out.println(FFT.stringifyDigits(digits).substring(0,8));
   }
}
