package model;

import java.util.ArrayList;
public class fridge {
    private ArrayList<ingredient> ingredients;

    public fridge(ArrayList<ingredient> ingredients) {

        this.ingredients = ingredients;
    }
    public void add_ingredient(ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
    public void remove_ingredient(ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public boolean check_ingredient(ingredient ingredient){
        for(var ing :ingredients){
            if(ing.getName().equals(ingredient.getName())&& ing.getQuantity()>=ingredient.getQuantity())
                return true;

        }
        return false;
    }

    public boolean check_availability_pizza(pizza pizza){
        ArrayList<ingredient> pizza_ingredients= pizza.getIngredients();
        for(var ing :pizza_ingredients){
            if(!check_ingredient(ing))
                return false;
        }
        for(var ing :pizza_ingredients){
            for(var ing_f:this.ingredients){
                if(ing.getName().equals(ing_f.getName())){
                    ing_f.setQuantity(ing_f.getQuantity()-ing.getQuantity());
                }
            }
        }
        return true;
    }
    public void display_ingredients(){
        System.out.println("The cook ingredients in the fridge");
        for(var ing: ingredients){
            System.out.println(ing.getName()+ " ❎ "+ ing.getQuantity());
        }
    }

}
