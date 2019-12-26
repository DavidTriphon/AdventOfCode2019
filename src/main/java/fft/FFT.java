package fft;

public class FFT
{
   private static final short[] PATTERN = new short[] {1, 0, -1, 0};
   
   
   public static short[] process(short[] digits)
   {
      short[] resDigits = new short[digits.length];
      
      for (int i = 0; i < resDigits.length; i++)
      {
         long sum = 0;
         
         int patternIndex = 0;
         
         for (int j = i; j < resDigits.length;)
         {
            for (int k = 0; k <= i && j < resDigits.length; k++, j++)
            {
               sum += ((long) digits[j]) * ((long) PATTERN[patternIndex]);
            }
            
            patternIndex = (patternIndex + 1) % PATTERN.length;
         }
         
         if (sum < 0)
            sum = -sum;
         
         resDigits[i] = (short) (sum % 10);
      }
      
      return resDigits;
   }
   
   
   public static short[] getDigitsFromString(String string)
   {
      char[] fftChars = string.toCharArray();
      
      short[] fftDigits = new short[fftChars.length];
      
      for (int i = 0; i < fftChars.length; i++)
      {
         fftDigits[i] = Short.parseShort("" + fftChars[i]);
      }
      
      return fftDigits;
   }
   
   
   public static String stringifyDigits(short[] digits)
   {
      StringBuilder sb = new StringBuilder();
      
      for (short digit : digits)
      {
         sb.append(digit);
      }
      
      return sb.toString();
   }
}
