package org.example;

interface RecipeAdjuster {
    void adjustForServings(int servings);
    void substituteIngredient(String oldIngredient, String newIngredient);
    String generateRecipe();
}