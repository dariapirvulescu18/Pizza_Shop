package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private Customer client;
    private LocalDate date;

    private int finalCost;
    private int tip;

    public Receipt(Customer client) {
        this.client = client;
        this.date = LocalDate.now();
        this.tip=0;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTip() {
        return tip;
    }

    public void calculateFinalCost(){
        for(Pizza pi :this.client.getOrder()){
            this.finalCost +=pi.getCost();
        }
        this.finalCost +=this.client.getCostDrink();
    }
    public void calulateFinalCostTip(int tipp) throws Exception {
        int tipMoney = (int)(this.finalCost *(tipp*0.01));
        int tipCost = this.finalCost + (int) (this.finalCost *(tipp*0.01));
        if(tipMoney>this.client.getMoney()){
            this.tip=0;
            throw new Exception("You don't have enough money to tip that much!");
        }
        this.tip=tipp;
        this.finalCost =tipCost;
    }
    @Override
    public String toString() {
        ArrayList<String> receipt = new ArrayList<>();
        receipt.add("On the date "+ date + " you ordered: \n");
        List<Pizza> pizza = client.getOrder();
        if (pizza != null) {
            for(Pizza pi:pizza){
                receipt.add("Pizza "+ pi.getType()+ " worth " + pi.getCost() +"\n");
            }
        }
        else
            receipt.add("You didn't order any pizza!");
        String drink = client.getDrink();
        if(drink!=null)
             receipt.add("Drink: "+ drink+ " worth "+ client.getCostDrink()+ "\n");
        else
            receipt.add("You didn't order any drinks!\n");
        if(tip>0){
            receipt.add("Tip: "+tip+ "\n");
        }
        else receipt.add("No tip \n");
        receipt.add("Your final cost is: "+ this.finalCost +"\n");
        return String.join("",receipt);
    }
}
