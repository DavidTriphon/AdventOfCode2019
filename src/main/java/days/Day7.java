package days;

import cpu.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day7
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input7.txt";
   
   private static final int PHASE_MIN = 0;
   private static final int PHASE_MAX = 4;
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
      ArrayList <Long> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Long.parseLong(i)));
      Long[] opCodes = opCodesArray.toArray(new Long[0]);
      
      ArrayList <ArrayList <Long>> phaseCombinations = new ArrayList <>();
      
      generatePhaseCombos(phaseCombinations, new ArrayList <>());
      
      Program program = new Program();
      
      ArrayList <Long> bestPhaseCombo = null;
      long highestOutput = 0;
      
      for (ArrayList <Long> phaseCombo : phaseCombinations)
      {
         long currentSignalStrength = 0;
         
         for (long phase : phaseCombo)
         {
            Long[] inputs = new Long[2];
            inputs[0] = phase;
            inputs[1] = currentSignalStrength;
            
            program.setMemory(opCodes);
            program.setInput(inputs);
            program.compute();
            Long[] output = program.getOutput();
            currentSignalStrength = output[output.length - 1];
         }
         
         if (currentSignalStrength > highestOutput)
         {
            highestOutput = currentSignalStrength;
            bestPhaseCombo = phaseCombo;
         }
      }
      
      System.out.println(bestPhaseCombo);
      System.out.println(highestOutput);
   }
   
   
   private static void generatePhaseCombos(ArrayList <ArrayList <Long>> phaseCombinations,
     ArrayList <Long> currentCombination)
   {
      if (currentCombination.size() > PHASE_MAX - PHASE_MIN)
      {
         phaseCombinations.add(new ArrayList <>(currentCombination));
      }
      else
      {
         for (long phase = PHASE_MIN; phase <= PHASE_MAX; phase++)
         {
            if (!currentCombination.contains(phase))
            {
               currentCombination.add(phase);
               generatePhaseCombos(phaseCombinations, currentCombination);
               currentCombination.remove(currentCombination.size() - 1);
            }
         }
      }
   }
}
