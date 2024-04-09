package model;

import java.util.*;

public class pizza {
    private String type;
    private String size;
    private ArrayList<ingredient> ingredients;
    private boolean cooked=false;

    private int cost;

    public pizza (String type,  int cost,ArrayList<ingredient> ingredients)  {
       this.type=type;
       this.cost=cost;
       this.size = "";
       this.ingredients = ingredients;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredients;
    }

    public pizza(pizza p){
        this.type=p.type;
        this.size= p.size;
        this.cost=p.cost;
        this.cooked=p.cooked;
        this.ingredients = new ArrayList<>();
        Collections.copy(this.ingredients, p.ingredients);
    }

    public void setSize(String size) throws Exception {
        this.size = size;
    }

    public void setExtra_toppings(ArrayList<ingredient> extra_toppings) {
        Collections.copy(this.ingredients, extra_toppings);
    }

    public int getCost() {
        return cost;
    }
    public String getSize(){
        return size;
    }
    public String getType() {
        return type;
    }

    public void cookPizza(String size) throws InterruptedException {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                cooked = true;
                System.out.println("\nYour " + type + " pizza 🍕 is now cooked!");
                timer.cancel();
            }
        };

        int cookingTime = calculateCookingTime();
        System.out.println("Your waiting time for " + type +" "+ size +" pizza  is " + cookingTime + " minutes!");
        timer.schedule(task, (long) cookingTime * 1000);

        String[] items = {".", "🧀", "🍅", "🍞", "🥩", "🍄"};
        int length = items.length;

        for (int i = 0; i < 20; i++) {
            StringBuilder line = new StringBuilder();

            for (int j = 0; j < 20; j++) {
                if (j < i) {
                    line.append(".");
                } else if (j < i + length) {
                    line.append(items[j - i]);
                } else {
                    line.append(".");
                }

            }

            System.out.print("\r" + line.toString());
            Thread.sleep(500);
        }

        String[] items2 = {"🍕"};
        int length2 = items2.length;

        for (int i = 19; i >=0; i--) {
            StringBuilder line2 = new StringBuilder();


            for (int j = 0; j < 20; j++) {
                if (j < i) {
                    line2.append(".");
                } else if (j < i + length2) {
                    line2.append(items2[j - i]);
                } else {
                    line2.append(".");
                }
            }

            System.out.print("\r" + line2.toString());
            Thread.sleep(500);
        }
    }

    public void update_cost(int cost) {
        this.cost = cost;
    }

    private int calculateCookingTime() {
        return switch (size.toLowerCase()) {
            case "big" -> 20;
            case "medium" -> 15;
            case "small" -> 10;
            default -> 0;
        };
    }
}
