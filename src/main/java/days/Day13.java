package days;

import arcade.*;

import java.io.*;


public class Day13
{
   
   public static void main(String... args) throws IOException
   {
      Arcade arcade = new Arcade();
      
      arcade.run();
   
      System.out.println(arcade.getScreen().countOf(Arcade.TILE_BLOCK));
   }
}
