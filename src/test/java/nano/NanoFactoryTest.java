package nano;

import org.junit.jupiter.api.*;
import util.*;

import java.io.*;

import static nano.NanoFactory.*;
import static org.junit.jupiter.api.Assertions.*;


class NanoFactoryTest
{
   private static final String FILE_LOC_PREFIX = "src/test/resources/nano/";
   
   private static final long ORE_GATHERED = 1000000000000L;
   
   
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
   
   
   @Test
   void testCBuild()
   {
      testBuildUp("c.txt", 82892753);
   }
   
   
   @Test
   void testDBuild()
   {
      testBuildUp("d.txt", 5586022);
   }
   
   
   @Test
   void testEBuild()
   {
      testBuildUp("e.txt", 460664);
   }
   
   
   private static void testFunc(String filename, int expectRecipes, int expectCost)
   {
      try
      {
         NanoFactory fact = getFactFromFilename(filename);
         
         assertEquals(expectRecipes, fact.getRecipeCount());
         
         assertEquals(expectCost, fact.getOreCostOfFuel());
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static void testBuildUp(String filename, long from1tril)
   {
      try
      {
         NanoFactory fact = getFactFromFilename(filename);
         
         assertTrue(ORE_GATHERED >= fact.getOreRequired(new Ingredient(FUEL, from1tril)));
         assertTrue(ORE_GATHERED < fact.getOreRequired(new Ingredient(FUEL, from1tril + 1)));
         
         assertEquals(from1tril, fact.getMaxFuelFromOre(ORE_GATHERED),
           "returned fuel amount is wrong");
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static NanoFactory getFactFromFilename(String filename) throws IOException
   {
      return NanoFactory.fromString(ParseUtil.getFileString(getFullFilename(filename)));
   }
   
   
   private static String getFullFilename(String filename)
   {
      return FILE_LOC_PREFIX + filename;
   }
}