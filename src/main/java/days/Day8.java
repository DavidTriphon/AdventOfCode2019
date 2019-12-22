package days;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day8
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input8.txt";
   
   private static final int IMAGE_HEIGHT = 6;
   private static final int IMAGE_WIDTH = 25;
   
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
   
      ArrayList<String> layers = getLayersFromImage(fileString);
   
      String chosenLayer = getLayerWithLeastZeroes(layers);
   
      int onesCount = getOccurenceOfDigit(chosenLayer, '1');
      int twosCount = getOccurenceOfDigit(chosenLayer, '2');
   
      System.out.println(onesCount * twosCount);
   }
   
   private static String getLayerWithLeastZeroes(ArrayList<String> layers)
   {
      String chosenLayer = null;
      int minZeroCount = Integer.MAX_VALUE;
      
      for (String layerString : layers)
      {
         int zeroCount = getOccurenceOfDigit(layerString, '0');
         
         if (zeroCount < minZeroCount)
         {
            minZeroCount = zeroCount;
            chosenLayer = layerString;
         }
      }
      
      return chosenLayer;
   }
   
   private static ArrayList<String> getLayersFromImage(String imageString)
   {
      ArrayList<String> layers = new ArrayList <>();
   
      int startIndex = 0;
      int layerSize = IMAGE_HEIGHT * IMAGE_WIDTH;
   
      while (startIndex < imageString.length())
      {
         layers.add(imageString.substring(startIndex, startIndex + layerSize));
         startIndex += layerSize;
      }
      
      return layers;
   }
   
   private static int getOccurenceOfDigit(String layerString, char digit)
   {
      int count = 0;
      
      for (int i = 0; i < layerString.length(); i++)
      {
         if (layerString.charAt(i) == digit)
            count++;
      }
      
      return count;
   }
}
