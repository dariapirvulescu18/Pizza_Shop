package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Customer {
    private String name;

    private int money;

    private List<Pizza> orderPizza;
    private String drink;
    private int costDrink;

    private int scoreGame;
    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
        this.orderPizza = new ArrayList<Pizza>(10);
    }
    public String getDrink() {
        return drink;
    }
    public List<Pizza> getOrder() {
        return orderPizza;
    }

    public void setScore(int score){
        this.scoreGame =score;
        this.money+= scoreGame;
    }
    public String getName() {
        return name;
    }
    public int getCostDrink(){
        return costDrink;
    }
    public String responseDrinks(){
        MenuDrinks drinks1=new MenuDrinks();
        HashMap<String,Integer[]> options = drinks1.getDrinks();
        Random rand = new Random();
        int randomNumber = rand.nextInt(5) + 1;
        for(String drink:options.keySet()){
            Integer[] details=options.get(drink);
            if(money>=details[0] && details[1]==randomNumber){
                money-=details[0];
                this.drink=drink;
                costDrink =details[0];
                return drink;
            }

        }
        System.out.println("You don't have enough money for a drink!");
        return "";
    }

    public int getMoney() {
        return money;
    }

    public void responsePizza(Pizza pizzaAvailable, String sizeResponse) throws Exception {

        int sizeCost = switch (sizeResponse) {
            case "small" -> 10;
            case "medium" -> 20;
            case "big" -> 30;
            default -> 0;
        };
        int finalCost =pizzaAvailable.getCost()+ (int) (pizzaAvailable.getCost()*(sizeCost*0.01));
        if(this.money<finalCost){
            throw new Exception("You don't have enough money to buy " +pizzaAvailable.getSize() +" "+ pizzaAvailable.getType() +" pizza!");

        }
        else {
            pizzaAvailable.updateCost(finalCost);
            pizzaAvailable.setSize(sizeResponse);
            this.money-=pizzaAvailable.getCost();
        }
    }
    public void add_pizza(Pizza responsePizza){

        orderPizza.add(responsePizza);
    }
    @Override
    public String toString() {
        return "My name is "+ name + " and I have "+ money+ " money!";
    }
}
