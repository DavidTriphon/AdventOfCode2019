package days;

import arcade.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


public class Day13B
{
   
   private static final char LEFT   = 'j';
   private static final char MIDDLE = 'k';
   private static final char RIGHT  = 'l';
   
   
   public static void main(String... args) throws IOException
   {
      Arcade arcade = new Arcade();
      
      arcade.hackQuarters();
      arcade.run();
      
      playAutomatically(arcade, 0);
      
      System.out.println((arcade.isWon() ? "Game Won!" : "Game Over..."));
      
      System.out.println(arcade.getScreenString());
      System.out.println("Score: " + arcade.getCurrentScore());
   }
   
   
   private static void playManually(Arcade arcade)
   {
      Scanner in = new Scanner(System.in);
      
      while (arcade.isPlaying())
      {
         System.out.println(arcade.getScreenString());
         System.out.println("Score: " + arcade.getCurrentScore());
         
         String input = in.nextLine();
         
         if (input.length() > 0)
         {
            switch (input.charAt(0))
            {
               case LEFT:
                  arcade.setJoystick(Arcade.JOYSTICK_LEFT);
                  break;
               case MIDDLE:
                  arcade.setJoystick(Arcade.JOYSTICK_NEUTRAL);
                  break;
               case RIGHT:
                  arcade.setJoystick(Arcade.JOYSTICK_RIGHT);
                  break;
            }
         }
         else
         {
            arcade.setJoystick(Arcade.JOYSTICK_NEUTRAL);
         }
         
         arcade.run();
      }
   }
   
   
   private static void playAutomatically(Arcade arcade, int delayMillis)
   {
      while (arcade.isPlaying())
      {
         if (delayMillis > 0)
         {
            System.out.println(arcade.getScreenString());
            System.out.println("Score: " + arcade.getCurrentScore());
         }
         
         try
         {
            TimeUnit.MILLISECONDS.sleep(delayMillis);
         }
         catch (InterruptedException exc)
         {
            // I don't care
         }
   
         int paddleX = arcade.getPaddleX();
         int ballX = arcade.getBallX();
         
         if (paddleX < ballX)
         {
            arcade.setJoystick(Arcade.JOYSTICK_RIGHT);
         }
         else if (paddleX > ballX)
         {
            arcade.setJoystick(Arcade.JOYSTICK_LEFT);
         }
         else
         {
            arcade.setJoystick(Arcade.JOYSTICK_NEUTRAL);
         }
         
         arcade.run();
      }
   }
}
