package util;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;


public class ParseUtil
{
   public static String getFileString(String fileName) throws IOException
   {
      return Files.readString(Path.of(fileName), StandardCharsets.US_ASCII).trim();
   }
   
   
   public static BufferedReader getStringReader(String string)
   {
      return new BufferedReader(new StringReader(string));
   }
}
