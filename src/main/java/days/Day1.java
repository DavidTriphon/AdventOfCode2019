package days;

import java.io.*;


public class Day1
{
   private static final String inputFileLoc = "src/main/resources/input1.txt";
   
   public static void main(String... args) throws IOException
   {
      BufferedReader inputReader = new BufferedReader(new FileReader(inputFileLoc));
      
      String line = null;
      
      long totalFuel = 0;
      
      while ((line = inputReader.readLine()) != null)
      {
         int num = Integer.parseInt(line);
         
         totalFuel += (num / 3) - 2;
      }
   
      System.out.println(totalFuel);
   }
}
