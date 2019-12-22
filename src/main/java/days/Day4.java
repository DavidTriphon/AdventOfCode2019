package days;

public class Day4
{
   private static final int LOWER_PASSWORD = 130254;
   private static final int UPPER_PASSWORD = 678276;
   
   private static final int DIGIT_COUNT = 6;
   
   public static void main(String... args)
   {
      int validPasswordCount = 0;
      
      for (int currPass = LOWER_PASSWORD; currPass < UPPER_PASSWORD; currPass++)
      {
         boolean hasRepeating = false, isIncremental = true;
   
         int prevDigit = getDigit(currPass, 0);
         
         for (int digit = 1; digit < DIGIT_COUNT; digit++)
         {
            int currDigit = getDigit(currPass, digit);
            if (currDigit > prevDigit)
               isIncremental = false;
            if (currDigit == prevDigit)
               hasRepeating = true;
            prevDigit = currDigit;
         }
         
         if (isIncremental && hasRepeating)
            validPasswordCount++;
      }
      
      System.out.println(validPasswordCount);
   }
   
   
   private static int getDigit(int num, int digit)
   {
      return (num / (int) Math.pow(10, digit)) % 10;
   }
}
