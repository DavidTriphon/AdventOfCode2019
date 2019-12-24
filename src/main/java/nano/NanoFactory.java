package nano;

import util.*;

import java.io.*;
import java.util.*;


public class NanoFactory
{
   // constants
   
   public static final String ORE  = "ORE";
   public static final String FUEL = "FUEL";
   
   // fields
   
   private HashMap <String, Recipe> _recipes = new HashMap <>();
   
   // constructors
   
   
   public NanoFactory() {}
   
   // public methods
   
   
   public void addRecipe(Recipe recipe)
   {
      _recipes.put(recipe.getOutput().name, recipe);
   }
   
   
   public int getRecipeCount()
   {
      return _recipes.size();
   }
   
   
   public int getOreCostOfFuel()
   {
      return getOreRequired(new Ingredient(FUEL, 1));
   }
   
   
   public int getOreRequired(Ingredient result)
   {
      return getOreRequired(result, new HashMap <>());
   }
   
   // private methods
   
   
   private int getOreRequired(Ingredient reqResult,
     HashMap <String, Integer> spareIngredients)
   {
      if (reqResult.name.equals(ORE))
         return reqResult.amount;
      
      int ore = 0;
      
      int reqResultAmount = reqResult.amount;
      
      if (spareIngredients.containsKey(reqResult.name))
      {
         int available = spareIngredients.get(reqResult.name);
         
         if (reqResultAmount < available)
         {
            spareIngredients.put(reqResult.name, available - reqResultAmount);
            return 0;
         }
         else if (reqResultAmount == available)
         {
            spareIngredients.remove(reqResult.name);
            return 0;
         }
         else
         {
            reqResultAmount -= available;
            spareIngredients.remove(reqResult.name);
         }
      }
      
      Recipe recipe = _recipes.get(reqResult.name);
      
      int multRecipe = (int) Math.ceil((1.0 * reqResultAmount) / (1.0 * recipe.getOutput().amount));
      
      int remainder = (recipe.getOutput().amount * multRecipe) - reqResultAmount;
      
      for (Ingredient reqIng : recipe.getInputs())
      {
         ore += getOreRequired(new Ingredient(reqIng.name, reqIng.amount * multRecipe),
           spareIngredients);
      }
      
      if (remainder > 0)
         spareIngredients.put(reqResult.name, remainder);
      
      return ore;
   }
   
   
   public String toFormatString()
   {
      StringBuilder sb = new StringBuilder();
      
      for (Recipe recipe : _recipes.values())
      {
         sb.append(recipe.toFormatString());
         sb.append('\n');
      }
      
      return sb.toString();
   }
   
   // overridden methods
   
   
   @Override
   public String toString()
   {
      return "NanoFactory{" +
        "recipes=" + _recipes.values() +
        '}';
   }
   
   // static methods
   
   
   public static NanoFactory fromString(String string) throws IOException
   {
      BufferedReader reader = ParseUtil.getStringReader(string);
      
      NanoFactory fact = new NanoFactory();
      
      String line;
      while ((line = reader.readLine()) != null)
      {
         fact.addRecipe(Recipe.fromString(line));
      }
      
      return fact;
   }
}
