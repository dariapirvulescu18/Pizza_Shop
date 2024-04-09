package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class receipt {
    private customer client;
    private LocalDate date;

    private int final_cost;
    private int tip;

    public receipt(customer client) {
        this.client = client;
        this.date = LocalDate.now();
        this.tip=0;
    }

    public int getFinal_cost() {
        return final_cost;
    }

    public void calculate_final_cost(){
        for(pizza pi :this.client.getOrder()){
            this.final_cost+=pi.getCost();
        }
        this.final_cost+=this.client.get_cost_drink();
    }
    public void calulate_final_cost_tip(int tipp) throws Exception {
        int tip_money = (int)(this.final_cost*(tipp*0.01));
        int tip_cost = this.final_cost+ (int) (this.final_cost*(tipp*0.01));
        if(tip_money>this.client.getMoney()){
            this.tip=0;
            throw new Exception("You don't have enough money to tip that much!");
        }
        this.tip=tipp;
        this.final_cost=tip_cost;
    }
    @Override
    public String toString() {
        ArrayList<String> receipt = new ArrayList<>();
        receipt.add("On the date "+ date + " you ordered: \n");
        List<pizza> pizza = client.getOrder();
        if (pizza != null) {
            for(pizza pi:pizza){
                receipt.add("Pizza "+ pi.getType()+ " worth " + pi.getCost() +"\n");
            }
        }
        else
            receipt.add("You didn't order any pizza!");
        String drink = client.getDrink();
        if(drink!=null)
             receipt.add("Drink: "+ drink+ " worth "+ client.get_cost_drink()+ "\n");
        else
            receipt.add("You didn't order any drinks!\n");
        if(tip>0){
            receipt.add("Tip: "+tip+ "\n");
        }
        else receipt.add("No tip \n");
        receipt.add("Your final cost is: "+ this.final_cost+"\n");
        return String.join("",receipt);
    }
}
