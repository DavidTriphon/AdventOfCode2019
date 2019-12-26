package fft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class FFTTest
{
   
   @Test
   void process()
   {
      short[] digits = FFT.getDigitsFromString("12345678");
      short[] result = FFT.process(digits);
      
      System.out.printf("phase 0: %s\n", FFT.stringifyDigits(digits));
      System.out.printf("phase 1: %s\n", FFT.stringifyDigits(result));
      
      assertArrayEquals(FFT.getDigitsFromString("48226158"), result);
   }
   
   
   @Test
   void getDigitsFromString()
   {
      short[] digits = FFT.getDigitsFromString("1234567890");
      
      assertArrayEquals(new short[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, digits);
   }
   
   
   @Test
   void stringifyDigits()
   {
      String string = FFT.stringifyDigits(new short[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
      
      assertEquals("1234567890", string);
   }
}