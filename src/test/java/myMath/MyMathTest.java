package myMath;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class MyMathTest
{
   
   @Test
   void gcd()
   {
      assertEquals(1, MyMath.gcd(2, 5));
      assertEquals(0, MyMath.gcd(0, 0));
      assertEquals(1, MyMath.gcd(0, 1));
      assertEquals(1, MyMath.gcd(1, 0));
      assertEquals(1, MyMath.gcd(1, 1));
      assertEquals(2, MyMath.gcd(0, 2));
      assertEquals(2, MyMath.gcd(2, 0));
      assertEquals(1, MyMath.gcd(2, 1));
   }
   
   
   @Test
   void lcm()
   {
      assertEquals(10, MyMath.lcm(2, 5));
      assertEquals(10, MyMath.lcm(new long[] {2, 5}));
      
      assertEquals(0, MyMath.lcm(0, 1));
      assertEquals(1, MyMath.lcm(1, 1));
      assertEquals(10, MyMath.lcm(10, 1));
      assertEquals(10, MyMath.lcm(10, 2));
      assertEquals(30, MyMath.lcm(10, 3));
   }
}