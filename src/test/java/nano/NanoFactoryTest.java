package nano;

import org.junit.jupiter.api.*;
import util.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class NanoFactoryTest
{
   private static final String FILE_LOC_PREFIX = "src/test/resources/nano/";
   
   
   @Test
   void testA()
   {
      testFunc("a.txt", 6, 31);
   }
   
   
   @Test
   void testB()
   {
      testFunc("b.txt", 7, 165);
   }
   
   
   @Test
   void testC()
   {
      testFunc("c.txt", 9, 13312);
   }
   
   
   @Test
   void testD()
   {
      testFunc("d.txt", 12, 180697);
   }
   
   
   @Test
   void testE()
   {
      testFunc("e.txt", 17, 2210736);
   }
   
   
   private static void testFunc(String filename, int expectRecipes, int expectCost)
   {
      try
      {
         NanoFactory fact =
           NanoFactory.fromString(ParseUtil.getFileString(getFullFilename(filename)));
         
         System.out.println(fact);
         
         System.out.println(fact.toFormatString());
         
         assertEquals(expectRecipes, fact.getRecipeCount());
         
         assertEquals(expectCost, fact.getOreCostOfFuel());
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static String getFullFilename(String filename)
   {
      return FILE_LOC_PREFIX + filename;
   }
}