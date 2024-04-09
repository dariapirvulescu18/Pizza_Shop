package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class customer {
    private String name;

    private int money;

    private List<pizza> order_pizza;
    private String drink;
    private int cost_drink;

    private int score_game;
    public customer(String name, int money) {
        this.name = name;
        this.money = money;
        this.order_pizza= new ArrayList<pizza>(10);
    }
    public String getDrink() {
        return drink;
    }
    public List<pizza> getOrder() {
        return order_pizza;
    }

    public void set_score(int score){
        this.score_game=score;
        this.money+=score_game;
    }
    public String getName() {
        return name;
    }
    public int get_cost_drink(){
        return cost_drink;
    }
    public String response_drinks(){
        menu_drinks drinks1=new menu_drinks();
        HashMap<String,Integer[]> options = drinks1.getDrinks();
        Random rand = new Random();
        int randomNumber = rand.nextInt(5) + 1;
        for(String drink:options.keySet()){
            Integer[] details=options.get(drink);
            if(money>=details[0] && details[1]==randomNumber){
                money-=details[0];
                this.drink=drink;
                cost_drink=details[0];
                return drink;
            }

        }
        System.out.println("You don't have enough money for a drink!");
        return "";
    }

    public int getMoney() {
        return money;
    }

    public void response_pizza(pizza pizza_available,String size_response) throws Exception {

        int size_cost = switch (size_response) {
            case "small" -> 10;
            case "medium" -> 20;
            case "big" -> 30;
            default -> 0;
        };
        int final_cost =pizza_available.getCost()+ (int) (pizza_available.getCost()*(size_cost*0.01));
        if(this.money<final_cost){
            throw new Exception("You don't have enough money to buy " +pizza_available.getSize() +" "+ pizza_available.getType() +" pizza!");

        }
        else {
            pizza_available.update_cost(final_cost);
            pizza_available.setSize(size_response);
            this.money-=pizza_available.getCost();
        }
    }
    public void add_pizza(pizza response_pizza){

        order_pizza.add(response_pizza);
    }
    @Override
    public String toString() {
        return "My name is "+ name + " and I have "+ money+ " money!";
    }
}
