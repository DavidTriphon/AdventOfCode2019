package days;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day8B
{
   private static final String INPUT_FILE_LOC = "src/main/resources/input8.txt";
   
   private static final int IMAGE_HEIGHT = 6;
   private static final int IMAGE_WIDTH = 25;
   
   private static final char PIXEL_BLACK = '0';
   private static final char PIXEL_WHITE = '1';
   private static final char PIXEL_TRANSPARENT = '2';
   
   public static void main(String... args) throws IOException
   {
      String fileString =
        Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
   
      ArrayList<String> layers = getLayersFromImage(fileString);
      
      String image = composeImageFromLayers(layers);
   
      System.out.println(image);
   }
   
   private static String composeImageFromLayers(ArrayList<String> layers)
   {
      String image = "";
      
      for (int y = 0; y < IMAGE_HEIGHT; y++)
      {
         for (int x = 0; x < IMAGE_WIDTH; x++)
         {
            int layerI = 0;
   
            char currentChar;
            do
            {
               currentChar = layers.get(layerI++).charAt(y * IMAGE_WIDTH + x);
            }
            while (currentChar == PIXEL_TRANSPARENT);
   
            if (currentChar == PIXEL_WHITE)
               currentChar = 'X';
            else
               currentChar = ' ';
            
            image += currentChar;
         }
         
         image += '\n';
      }
      
      return image;
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
}
