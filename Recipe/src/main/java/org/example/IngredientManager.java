package org.example;

import java.util.List;

interface IngredientManager {
    void addIngredient(String ingredient);
    void removeIngredient(String ingredient);

    void deleteIngredient(String ingredient);

    List<String> getIngredients();
}