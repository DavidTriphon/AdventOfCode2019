package nbody;

import math.*;

import java.util.regex.*;


public class Body
{
   // constants
   
   public static final String  REGEX   =
     "pos=" + Coord3D.REGEX + ", vel=" + Coord3D.REGEX;
   public static final Pattern PATTERN = Pattern.compile(REGEX);
   
   private static final String  SELECTOR_REGEX   =
     "pos=(" + Coord3D.REGEX + "), vel=(" + Coord3D.REGEX + ")";
   private static final Pattern SELECTOR_PATTERN = Pattern.compile(SELECTOR_REGEX);
   
   // instance
   
   private Coord3D _pos;
   private Coord3D _vel;
   
   // constructors
   
   
   public Body(Body body)
   {
      this(body._pos, body._vel);
   }
   
   
   public Body()
   {
      this(new Coord3D());
   }
   
   
   public Body(Coord3D pos)
   {
      this(pos, new Coord3D());
   }
   
   
   public Body(Coord3D pos, Coord3D vel)
   {
      _pos = new Coord3D(pos);
      _vel = new Coord3D(vel);
   }
   
   // public methods
   
   
   public Coord3D getPos()
   {
      return new Coord3D(_pos);
   }
   
   
   public Coord3D getVel()
   {
      return new Coord3D(_vel);
   }
   
   
   public void move()
   {
      _pos.add(_vel);
   }
   
   
   public void accel(Coord3D force)
   {
      _vel.add(force);
   }
   
   
   public int getEnergy()
   {
      int potential = 0;
      
      for (int place : _pos.places())
      {
         potential += Math.abs(place);
      }
      
      int kinetic = 0;
      
      for (int place : _vel.places())
      {
         kinetic += Math.abs(place);
      }
      
      return potential * kinetic;
   }
   
   // overridden methods
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Body body = (Body) o;
      return _pos.equals(body._pos) &&
        _vel.equals(body._vel);
   }
   
   
   @Override
   public int hashCode()
   {
      return 0;
   }
   
   // static methods
   
   
   public static void attract(Body body1, Body body2)
   {
      int[] places1 = body1.getPos().places();
      int[] places2 = body2.getPos().places();
      
      Coord3D force1 = new Coord3D();
      Coord3D force2 = new Coord3D();
      
      for (int i = 0; i < places1.length; i++)
      {
         int place1 = places1[i];
         int place2 = places2[i];
         
         if (place1 < place2)
         {
            force1.set(i, +1);
            force2.set(i, -1);
         }
         else if (place1 > place2)
         {
            force1.set(i, -1);
            force2.set(i, +1);
         }
      }
      
      body1.accel(force1);
      body2.accel(force2);
   }
   
   
   public static Body fromString(String string)
   {
      Matcher m;
      
      if ((m = SELECTOR_PATTERN.matcher(string)).matches())
      {
         Coord3D pos = Coord3D.fromString(m.group(1));
         Coord3D vel = Coord3D.fromString(m.group(2));
         
         return new Body(pos, vel);
      }
      else if ((m = Coord3D.PATTERN.matcher(string)).matches())
      {
         Coord3D pos = Coord3D.fromString(string);
         
         return new Body(pos);
      }
      else
      {
         throw new IllegalArgumentException("Invalid body string");
      }
   }
}
