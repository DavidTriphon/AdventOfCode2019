package nano;

import java.util.*;
import java.util.regex.*;


public class Recipe
{
   
   // constants
   
   private static final String ING_SEL_REGEX    = "(\\d+) ([A-Z]+)";
   private static final String INGREDIENT_REGEX = "(\\d+ [A-Z]+)";
   private static final String RECIPE_REGEX     =
     INGREDIENT_REGEX + "(, " + INGREDIENT_REGEX + ")* => " + INGREDIENT_REGEX;
   
   private static final Pattern RECIPE_PATTERN     = Pattern.compile(RECIPE_REGEX);
   private static final Pattern INGREDIENT_PATTERN = Pattern.compile(ING_SEL_REGEX);
   
   // fields
   
   private final Ingredient _output;
   
   private final ArrayList <Ingredient> _inputs;
   
   // constructor
   
   
   public Recipe(Ingredient output, ArrayList <Ingredient> inputs)
   {
      _output = output;
      _inputs = new ArrayList <>(inputs);
   }
   
   // public methods
   
   
   public Ingredient getOutput()
   {
      return _output;
   }
   
   
   public ArrayList <Ingredient> getInputs()
   {
      return new ArrayList <>(_inputs);
   }
   
   
   public String toFormatString()
   {
      StringBuilder sb = new StringBuilder();
      
      sb.append(_inputs.get(0).toFormatString());
      
      for (int i = 1; i < _inputs.size(); i++)
      {
         sb.append(String.format(", %s", _inputs.get(i).toFormatString()));
      }
      
      return String.format("%s => %s", sb.toString(), _output.toFormatString());
   }
   
   // overridden methods
   
   
   @Override
   public String toString()
   {
      return "Recipe{" +
        "output=" + _output +
        ", inputs=" + _inputs +
        '}';
   }
   
   // static methods
   
   
   public static Recipe fromString(String string)
   {
      Matcher m = RECIPE_PATTERN.matcher(string);
      
      ArrayList <Ingredient> inputs = new ArrayList <>();
      Ingredient previous = null;
      
      if (m.matches())
      {
         m = INGREDIENT_PATTERN.matcher(string);
         
         while (m.find())
         {
            String group = m.group(0);
            
            if (group != null)
            {
               if (previous != null)
                  inputs.add(previous);
               
               int amount = Integer.parseInt(m.group(1));
               String name = m.group(2);
               previous = new Ingredient(name, amount);
            }
         }
         
         return new Recipe(previous, inputs);
      }
      
      return null;
   }
}
