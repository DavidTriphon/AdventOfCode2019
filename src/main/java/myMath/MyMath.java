package myMath;

public class MyMath
{
   public static long gcd(long a, long b)
   {
      if (b == 0)
         return a;
      return gcd(b, a % b);
   }
   
   
   public static long lcm(long a, long b)
   {
      return a * b / gcd(a, b);
   }
   
   
   public static long lcm(long[] vals)
   {
      long lcm = vals[0];
      
      for (int i = 1; i < vals.length; i++)
      {
         lcm = lcm(lcm, vals[i]);
      }
      
      return lcm;
   }
}
