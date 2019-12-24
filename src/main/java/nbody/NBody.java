package nbody;

import math.*;
import util.*;

import java.io.*;
import java.util.*;

import static myMath.MyMath.*;


public class NBody
{
   // instance fields
   
   private ArrayList <Body> _bodies = new ArrayList <>();
   
   // constructor
   
   
   public NBody() {}
   
   
   public NBody(NBody nBody)
   {
      for (Body body : nBody._bodies)
      {
         _bodies.add(new Body(body));
      }
   }
   
   // public methods
   
   
   public void addBody(Body body)
   {
      _bodies.add(new Body(body));
   }
   
   
   public ArrayList <Body> getBodies()
   {
      return new ArrayList <>(_bodies);
   }
   
   
   public void timeStep()
   {
      applyGravity();
      applyVelocity();
   }
   
   
   public void timeStep(long count)
   {
      for (long i = 0; i < count; i++)
      {
         timeStep();
      }
   }
   
   
   public int getEnergy()
   {
      int totalEnergy = 0;
      
      for (Body body : _bodies)
      {
         totalEnergy += body.getEnergy();
      }
      
      return totalEnergy;
   }
   
   
   public long getLoopTime()
   {
      NBody nBody = new NBody(this);
      NBody state0 = new NBody(nBody);
      
      long[] times = new long[] {-1, -1, -1};
      
      long steps = 0;
      
      while (times[0] < 0 || times[1] < 0 || times[2] < 0)
      {
         steps++;
         nBody.timeStep();
         
         for (int place = 0; place < Coord3D.INDEX_COUNT; place++)
         {
            if (equalsPlace(state0, nBody, place) && times[place] < 0)
            {
               times[place] = steps;
            }
         }
      }
      
      return lcm(times);
   }
   
   // private methods
   
   
   private void applyGravity()
   {
      for (int i = 0; i < _bodies.size(); i++)
      {
         for (int j = i + 1; j < _bodies.size(); j++)
         {
            Body bodyI = _bodies.get(i);
            Body bodyJ = _bodies.get(j);
            
            Body.attract(bodyI, bodyJ);
         }
      }
   }
   
   
   private void applyVelocity()
   {
      for (Body body : _bodies)
      {
         body.move();
      }
   }
   
   // overridden methods
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      NBody nBody = (NBody) o;
      return _bodies.equals(nBody._bodies);
   }
   
   
   @Override
   public int hashCode()
   {
      return 0;
   }
   
   // static methods
   
   
   public static NBody fromString(String nbodyString) throws IOException
   {
      BufferedReader reader = ParseUtil.getStringReader(nbodyString);
      
      NBody nBody = new NBody();
      
      String line;
      while ((line = reader.readLine()) != null)
      {
         nBody.addBody(Body.fromString(line));
      }
      
      return nBody;
   }
   
   
   private static boolean equalsPlace(NBody state1, NBody state2, int place)
   {
      ArrayList <Body> bodies1 = state1.getBodies();
      ArrayList <Body> bodies2 = state2.getBodies();
      
      boolean isEqual = true;
      
      for (int bi = 0; bi < bodies1.size() && isEqual; bi++)
      {
         Body body1 = bodies1.get(bi);
         Body body2 = bodies2.get(bi);
         
         if (body1.getPos().get(place) != body2.getPos().get(place) ||
           body1.getVel().get(place) != body2.getVel().get(place))
         {
            isEqual = false;
         }
      }
      
      return isEqual;
   }
}
