package model;

import java.util.ArrayList;

public class menu_pizza extends menu {

    private ArrayList<pizza> pizzas;

    public menu_pizza()  {
        ingredient tomato = ingredient_factory.createTomato(5);
        ingredient salami_i = ingredient_factory.createSalami(10);
        ingredient mushrooms = ingredient_factory.createMushrooms(2);
        ingredient flour = ingredient_factory.createFlour(1);
        ingredient cheese = ingredient_factory.createCheese(3);
        ingredient ton = ingredient_factory.createTon(1);
        ingredient spicy_pepper = ingredient_factory.createSpicyPepper(1);
        ingredient prosciutto_i = ingredient_factory.createProsciutto(5);
        ArrayList<ingredient> ingredients1 = new ArrayList<>();
        ArrayList<ingredient> ingredients2 = new ArrayList<>();
        ArrayList<ingredient> ingredients3 = new ArrayList<>();
        ArrayList<ingredient> ingredients4 = new ArrayList<>();
        ArrayList<ingredient> ingredients5 = new ArrayList<>();
        ingredients1.add(flour);
        ingredients1.add(tomato);
        ingredients1.add(cheese);
        ingredients2.add(flour);
        ingredients2.add(salami_i);
        ingredients2.add(cheese);
        ingredients3.add(flour);
        ingredients3.add(prosciutto_i);
        ingredients3.add(cheese);
        ingredients3.add(mushrooms);
        ingredients4.add(cheese);
        ingredients4.add(flour);
        ingredients4.add(ton);
        ingredients5.add(flour);
        ingredients5.add(salami_i);
        ingredients5.add(cheese);
        ingredients5.add(spicy_pepper);
        pizza margherita = new pizza("margherita",100,ingredients1);
        pizza salami = new pizza("salami",200,ingredients2);
        pizza prosciutto = new pizza("prosciutto",150,ingredients3);
        pizza tonno = new pizza("tonna",400,ingredients4);
        pizza diavola = new pizza ("diavola",250,ingredients5);
        ArrayList<pizza> pizzas1= new ArrayList<>();
        pizzas1.add(margherita);
        pizzas1.add(salami);
        pizzas1.add(prosciutto);
        pizzas1.add(tonno);
        pizzas1.add(diavola);
        this.pizzas=pizzas1;
    }

    public ArrayList<pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public void choose() {
        System.out.println("Please choose a pizza from our menu!");
    }

    public pizza check_existance(String name_pizza){
        for(var i:pizzas){
            if(i.getType().equals(name_pizza))
                return i;
        }
        return null;
    }
    @Override
    public void init_menu_frame() {

    }
}
