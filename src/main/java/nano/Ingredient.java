package nano;

import java.util.*;


public class Ingredient
{
   // fields
   
   public final String name;
   public final long    amount;
   
   // constructor
   
   
   public Ingredient(String name, long amount)
   {
      this.name = name;
      this.amount = amount;
   }
   
   
   public Ingredient(String name)
   {
      this(name, 1);
   }
   
   
   public Ingredient(Ingredient ing, long amount)
   {
      this(ing.name, amount);
   }
   
   // public methods
   
   
   public String toFormatString()
   {
      return String.format("%d %s", amount, name);
   }
   
   // overridden methods
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Ingredient that = (Ingredient) o;
      return name.equals(that.name);
   }
   
   
   @Override
   public int hashCode()
   {
      return Objects.hash(name);
   }
   
   
   @Override
   public String toString()
   {
      return "Ingredient{" +
        "'" + name + "', " + amount +
        '}';
   }
}
