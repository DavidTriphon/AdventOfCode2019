package math;

import java.util.*;
import java.util.regex.*;


public class Coord3D
{
   // constants
   
   public static final int INDEX_X     = 0;
   public static final int INDEX_Y     = 1;
   public static final int INDEX_Z     = 2;
   public static final int INDEX_COUNT = 3;
   
   public static final String  REGEX   = "<x=\\s*-?\\d+,\\s*y=\\s*-?\\d+,\\s*z=\\s*-?\\d+>";
   public static final Pattern PATTERN = Pattern.compile(REGEX);
   
   private static final String  SELECTOR_REGEX   =
     "<x=\\s*(-?\\d+),\\s*y=\\s*(-?\\d+),\\s*z=\\s*(-?\\d+)>";
   private static final Pattern SELECTOR_PATTERN = Pattern.compile(SELECTOR_REGEX);
   // fields
   
   private int[] _vals = new int[INDEX_COUNT];
   
   // constructors
   
   
   public Coord3D()
   {
      this(0, 0, 0);
   }
   
   
   public Coord3D(int x, int y, int z)
   {
      setX(x);
      setY(y);
      setZ(z);
   }
   
   
   public Coord3D(Coord3D coord)
   {
      setX(coord.x());
      setY(coord.y());
      setZ(coord.z());
   }
   
   // setters
   
   
   public void setX(int x)
   {
      _vals[INDEX_X] = x;
   }
   
   
   public void setY(int y)
   {
      _vals[INDEX_Y] = y;
   }
   
   
   public void setZ(int z)
   {
      _vals[INDEX_Z] = z;
   }
   
   
   public void set(int i, int value)
   {
      _vals[i] = value;
   }
   
   // getters
   
   
   public int x()
   {
      return _vals[INDEX_X];
   }
   
   
   public int y()
   {
      return _vals[INDEX_Y];
   }
   
   
   public int z()
   {
      return _vals[INDEX_Z];
   }
   
   
   public int get(int index)
   {
      return _vals[index];
   }
   
   
   public int[] places()
   {
      return _vals.clone();
   }
   // in place math methods
   
   
   public void add(Coord3D coord)
   {
      _vals[INDEX_X] += coord.x();
      _vals[INDEX_Y] += coord.y();
      _vals[INDEX_Z] += coord.z();
   }
   
   
   public void sub(Coord3D coord)
   {
      _vals[INDEX_X] -= coord.x();
      _vals[INDEX_Y] -= coord.y();
      _vals[INDEX_Z] -= coord.z();
   }
   
   
   public void mul(int scalar)
   {
      _vals[INDEX_X] *= scalar;
      _vals[INDEX_Y] *= scalar;
      _vals[INDEX_Z] *= scalar;
   }
   
   
   public void div(int scalar)
   {
      _vals[INDEX_X] /= scalar;
      _vals[INDEX_Y] /= scalar;
      _vals[INDEX_Z] /= scalar;
   }
   
   // overridden methods
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Coord3D coord3D = (Coord3D) o;
      return Arrays.equals(_vals, coord3D._vals);
   }
   
   
   @Override
   public int hashCode()
   {
      return 0;
   }
   
   
   @Override
   public String toString()
   {
      return "Coord3D{" +
        "_vals=" + Arrays.toString(_vals) +
        '}';
   }
   
   // static methods
   
   
   public static Coord3D plus(Coord3D coord1, Coord3D coord2)
   {
      return new Coord3D(
        coord1.x() + coord2.x(),
        coord1.y() + coord2.y(),
        coord1.z() + coord2.z()
      );
   }
   
   
   public static Coord3D minus(Coord3D coord1, Coord3D coord2)
   {
      return new Coord3D(
        coord1.x() - coord2.x(),
        coord1.y() - coord2.y(),
        coord1.z() - coord2.z()
      );
   }
   
   
   public static Coord3D fromString(String string)
   {
      Matcher m = SELECTOR_PATTERN.matcher(string);
      
      if (m.matches())
      {
         int x = Integer.parseInt(m.group(1));
         int y = Integer.parseInt(m.group(2));
         int z = Integer.parseInt(m.group(3));
         
         return new Coord3D(x, y, z);
      }
      else
      {
         throw new IllegalArgumentException("Invalid Coord3D string");
      }
   }
}
