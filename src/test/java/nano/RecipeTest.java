package nano;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class RecipeTest
{
   @Test
   public void testFunc()
   {
      Recipe recipeA = Recipe.fromString("9 ORE => 2 A");
      assertEquals(new Ingredient("A", 2), recipeA.getOutput());
      assertEquals(new Ingredient("ORE", 9), recipeA.getInputs().get(0));
   }
   
   @Test
   public void testFuncB()
   {
      Recipe recipeB = Recipe.fromString("9 ORE, 4 B, 3 C => 2 A");
      assertEquals(new Ingredient("A", 2), recipeB.getOutput());
      assertEquals(new Ingredient("ORE", 9), recipeB.getInputs().get(0));
      assertEquals(new Ingredient("B", 4), recipeB.getInputs().get(1));
      assertEquals(new Ingredient("C", 3), recipeB.getInputs().get(2));
   }
}