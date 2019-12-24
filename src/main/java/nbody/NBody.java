package nbody;

import util.*;

import java.io.*;
import java.util.*;


public class NBody
{
   // instance fields
   
   private ArrayList <Body> _bodies;
   
   // constructor
   
   
   public NBody()
   {
      _bodies = new ArrayList <>();
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
   
   
   public void timeStep(int count)
   {
      for (int i = 0; i < count; i++)
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
}
