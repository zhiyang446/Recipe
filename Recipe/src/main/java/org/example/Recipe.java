package org.example;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements IngredientManager, RecipeAdjuster, Comparable<Recipe>, Cloneable {
    private String name;
    private List<String> ingredients;
    private int servings;

    public Recipe(String name, int servings) {
        this.name = name;
        this.servings = servings;
        this.ingredients = new ArrayList<>();
    }

    // Implementing IngredientManager methods
    @Override
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }

    @Override
    public void deleteIngredient(String ingredient) {

    }

    @Override
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    // Implementing RecipeAdjuster methods
    @Override
    public void adjustForServings(int newServings) {
        this.servings = newServings;
    }

    @Override
    public void substituteIngredient(String oldIngredient, String newIngredient) {
        int index = ingredients.indexOf(oldIngredient);
        if (index != -1) {
            ingredients.set(index, newIngredient);
        }
    }

    @Override
    public String generateRecipe() {
        StringBuilder recipe = new StringBuilder();
        recipe.append("Recipe: ").append(name).append("\n");
        recipe.append("Servings: ").append(servings).append("\n");
        recipe.append("Ingredients: \n");
        for (String ingredient : ingredients) {
            recipe.append("- ").append(ingredient).append("\n");
        }
        return recipe.toString();
    }

    // Implementing Comparable interface to compare recipes by name
    @Override
    public int compareTo(Recipe other) {
        return this.name.compareTo(other.name);
    }

    // Implementing Cloneable interface
    @Override
    public Recipe clone() {
        try {
            Recipe cloned = (Recipe) super.clone();
            cloned.ingredients = new ArrayList<>(this.ingredients);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported");
        }
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        Recipe recipe1 = new Recipe("Pasta", 2);
        recipe1.addIngredient("Pasta");
        recipe1.addIngredient("Tomato Sauce");
        recipe1.addIngredient("Cheese");

        Recipe recipe2 = new Recipe("Salad", 1);
        recipe2.addIngredient("Lettuce");
        recipe2.addIngredient("Tomato");
        recipe2.addIngredient("Cucumber");

        System.out.println("Original Recipe 1:");
        System.out.println(recipe1.generateRecipe());

        // Clone the recipe and make adjustments
        Recipe clonedRecipe = recipe1.clone();
        clonedRecipe.adjustForServings(4);
        clonedRecipe.substituteIngredient("Tomato Sauce", "Alfredo Sauce");

        System.out.println("Cloned and Adjusted Recipe:");
        System.out.println(clonedRecipe.generateRecipe());

        // Comparing recipes
        int comparisonResult = recipe1.compareTo(recipe2);
        if (comparisonResult < 0) {
            System.out.println(recipe1.name + " comes before " + recipe2.name);
        } else if (comparisonResult > 0) {
            System.out.println(recipe1.name + " comes after " + recipe2.name);
        } else {
            System.out.println(recipe1.name + " is the same as " + recipe2.name);
        }
    }
}