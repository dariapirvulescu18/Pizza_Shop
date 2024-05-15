package model;

import Repositories.IngredientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
public class Fridge {
    private ArrayList<Ingredient> ingredients;

    public Fridge(ArrayList<Ingredient> ingredients) {

        this.ingredients = ingredients;
    }
//    public void add_ingredient(Ingredient ingredient) {
//        this.ingredients.add(ingredient);
//    }
//    public void remove_ingredient(Ingredient ingredient){
//        this.ingredients.remove(ingredient);
//    }

    public boolean checkIngredient(Ingredient ingredient){
        for(var ing :ingredients){
            if(ing.getName().equals(ingredient.getName())&& ing.getQuantity()>=ingredient.getQuantity())
                return true;

        }
        return false;
    }

    public boolean checkAavailabilityPizza(Pizza pizza) throws SQLException {
        ArrayList<Ingredient> pizzaIngredients= pizza.getIngredients();
        for(var ing :pizzaIngredients){
            if(!checkIngredient(ing))
                return false;
        }
        IngredientRepository i =  IngredientRepository.getInstance();

        for(var ing :pizzaIngredients){
            for(var ing_f:this.ingredients){
                if(ing.getName().equals(ing_f.getName())){
                    ing_f.setQuantity(ing_f.getQuantity()-ing.getQuantity());
                    i.updateQuantity(ing.getName(),ing_f.getQuantity());
                }
            }
        }
        return true;
    }
    public void displayIngredients(){
        System.out.println("The cook ingredients in the fridge");
        for(var ing: ingredients){
            System.out.println(ing.getName()+ " ❎ "+ ing.getQuantity());
        }
    }

}
