package days;

import math.*;
import nbody.*;
import org.junit.jupiter.api.*;
import util.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.*;


public class Test12
{
   private static final String FILE_LOC_PREFIX = "src/test/resources/12/";
   
   private static final String EXPECTED = "exp.txt";
   private static final String START    = "start.txt";
   private static final String TIME     = "time.txt";
   private static final String PLOT     = "plot.csv";
   
   private static final String  STEP_REGEX   = "After (\\d+) steps?:";
   private static final Pattern STEP_PATTERN = Pattern.compile(STEP_REGEX);
   
   private static final String  ENERGY_STEP_REGEX   = "Energy after (\\d+) steps?:";
   private static final Pattern ENERGY_STEP_PATTERN = Pattern.compile(ENERGY_STEP_REGEX);
   
   private static final String  ENERGY_TOTAL_REGEX   =
     "Sum of total energy: \\d+( \\+ \\d+)* = (\\d+)";
   private static final Pattern ENERGY_TOTAL_PATTERN = Pattern.compile(ENERGY_TOTAL_REGEX);
   
   private static final int PRINT_STEP_COUNT = 1000;
   
   
   @Test
   public void testFuncA()
   {
      testFunc("a");
   }
   
   
   @Test
   public void testFuncB()
   {
      testFunc("b");
   }
   
   
   @Test
   public void printB() throws IOException
   {
      String filename = getFileName("b", START);
      String fileString = ParseUtil.getFileString(filename);
      
      NBody nBody = NBody.fromString(fileString);
      
      Writer out = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(getFileName("b", PLOT)), StandardCharsets.UTF_8));
      
      for (int step = 0; step <= PRINT_STEP_COUNT; step++)
      {
         out.write(String.format("%d, ", step));
         
         for (int place = 0; place < Coord3D.INDEX_COUNT; place++)
         {
            for (Body body : nBody.getBodies())
            {
               out.write(String.format("%d, ", body.getPos().get(place)));
            }
         }
         
         out.write('\n');
         
         nBody.timeStep();
      }
      
      out.close();
   }
   
   
   @Test
   public void testTimeB()
   {
      try
      {
         NBody nBody = NBody.fromString(ParseUtil.getFileString(getFileName("b", START)));
         
         // the clone is expected to be the nbody
         assertEquals(nBody, new NBody(nBody), "A clone should be equal");
         
         long expLoopTime =
           Long.parseLong(ParseUtil.getFileString(getFileName("b", TIME)));
         
         long actLoopTime = nBody.getLoopTime();
         
         assertEquals(expLoopTime, actLoopTime, "Loop time is not correct");
      }
      catch (IOException exc)
      {
         fail();
      }
   }
   
   
   private static String getFileName(String name, String type)
   {
      return String.format("%s%s_%s", FILE_LOC_PREFIX, name, type);
   }
   
   
   private static void testFunc(String filename)
   {
      try
      {
         String expectedFilename = getFileName(filename, EXPECTED);
         String startFilename = getFileName(filename, START);
         
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
