package days;

import nbody.*;
import org.junit.jupiter.api.*;
import util.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.*;


public class Test12
{
   private static final String FILE_LOC_PREFIX = "src/test/resources/12/";
   private static final String FILE_LOC_SUFFIX = ".txt";
   
   private static final String EXPECTED = "_exp";
   private static final String START    = "_start";
   
   private static final String  STEP_REGEX   = "After (\\d+) steps?:";
   private static final Pattern STEP_PATTERN = Pattern.compile(STEP_REGEX);
   
   private static final String  ENERGY_STEP_REGEX   = "Energy after (\\d+) steps?:";
   private static final Pattern ENERGY_STEP_PATTERN = Pattern.compile(ENERGY_STEP_REGEX);
   
   private static final String  ENERGY_TOTAL_REGEX   =
     "Sum of total energy: \\d+( \\+ \\d+)* = (\\d+)";
   private static final Pattern ENERGY_TOTAL_PATTERN = Pattern.compile(ENERGY_TOTAL_REGEX);
   
   
   @Test
   public void testA()
   {
      testForm("a");
   }
   
   
   @Test
   public void testB()
   {
      testForm("b");
   }
   
   
   private static void testForm(String filename)
   {
      try
      {
         String expectedFilename = FILE_LOC_PREFIX + filename + EXPECTED + FILE_LOC_SUFFIX;
         String startFilename = FILE_LOC_PREFIX + filename + START + FILE_LOC_SUFFIX;
         
         NBody nBody = NBody.fromString(ParseUtil.getFileString(startFilename));
         Expected exp = getExpected(ParseUtil.getFileString(expectedFilename));
         
         for (int step = 0; step <= exp.endStep; nBody.timeStep(), step++)
         {
            if (exp.map.containsKey(step))
            {
               NBody expNBody = exp.map.get(step);
               
               assertEquals(expNBody, nBody,
                 String.format("At step %d NBody did not match expected state.", step));
            }
            
            if (step == exp.endStep)
            {
               assertEquals(exp.energyAmount, nBody.getEnergy(), "Energy Equation is not correct");
            }
         }
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static Expected getExpected(String fileString) throws IOException
   {
      BufferedReader reader = ParseUtil.getStringReader(fileString);
      Expected exp = new Expected();
      
      int currentStep = -1;
      NBody nBody = null;
      
      String line;
      while ((line = reader.readLine()) != null)
      {
         Matcher m;
         
         if ((m = STEP_PATTERN.matcher(line)).matches())
         {
            if (currentStep != -1)
               exp.map.put(currentStep, nBody);
            nBody = new NBody();
            currentStep = Integer.parseInt(m.group(1));
         }
         else if ((m = Body.PATTERN.matcher(line)).matches())
         {
            nBody.addBody(Body.fromString(line));
         }
         else if ((m = ENERGY_STEP_PATTERN.matcher(line)).matches())
         {
            exp.endStep = Integer.parseInt(m.group(1));
         }
         else if ((m = ENERGY_TOTAL_PATTERN.matcher(line)).matches())
         {
            exp.energyAmount = Integer.parseInt(m.group(m.groupCount()));
         }
      }
      
      return exp;
   }
   
   
   private static class Expected
   {
      public HashMap <Integer, NBody> map = new HashMap <>();
      
      public int endStep      = -1;
      public int energyAmount = -1;
   }
}
