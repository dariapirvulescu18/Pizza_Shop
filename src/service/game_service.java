package service;

import model.*;
import packmen.packmen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class game_service {
    public void start_game() throws InterruptedException {
        //cream ingrediente
        ingredient tomato = ingredient_factory.createTomato(50);
        ingredient salami = ingredient_factory.createSalami(60);
        ingredient mushrooms = ingredient_factory.createMushrooms(20);
        ingredient flour = ingredient_factory.createFlour(5);
        ingredient cheese = ingredient_factory.createCheese(7);
        ingredient ton = ingredient_factory.createTon(10);
        ingredient spicy_pepper = ingredient_factory.createSpicyPepper(15);
        ingredient prosciutto = ingredient_factory.createProsciutto(30);


        ArrayList<ingredient> ingredients = new ArrayList<>();
        ingredients.add(tomato);
        ingredients.add(salami);
        ingredients.add(mushrooms);
        ingredients.add(flour);
        ingredients.add(cheese);
        ingredients.add(ton);
        ingredients.add(spicy_pepper);
        ingredients.add(prosciutto);

        fridge Gelu = new fridge(ingredients);

        //cream un client
        customer Daria = new customer("Daria",300);
        System.out.println(Daria);
        System.out.println("Hello "+ Daria.getName() +" !");
        System.out.println("Would you like to see the menu?");
        Scanner scanner = new Scanner(System.in);
        String response= scanner.nextLine();
        if(response.equals("No")){
            System.exit(0);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new menu_principal().init_menu_frame();
                }
            });

            System.out.println("Would you like a drink first?");
            String response2= scanner.nextLine();
            if(response2.equals("Yes")){
                String drink =Daria.response_drinks();
                System.out.println("I would like  "+drink);
                menu_drinks drinks = new menu_drinks();
                drinks.prepare(drink);
                System.out.println("Your remaining money: "+ Daria.getMoney());
            }
            System.out.println("Would you like to order pizza?");
            String response3= scanner.nextLine();
            if(response3.equals("Yes")){
                menu_pizza pi = new menu_pizza();
                ArrayList<pizza> lista_pizza = new ArrayList<pizza>();
                lista_pizza= pi.getPizzas();
                System.out.println("How many pizzas would you like to order?");
                int response4= scanner.nextInt();
                for(int i=0;i<response4;i++){
                    try {
                        System.out.println("What pizza would you like?");
                        boolean valid_name = false;
                        pizza available_pizza;
                        String resonse_pizza ;
                        do {
                            resonse_pizza=scanner.next();
                            available_pizza =pi.check_existance(resonse_pizza);
                            if(available_pizza!=null)
                                valid_name=true;
                            else
                                System.out.println("Please enter a valid name!");
                        }while (!valid_name);
                        boolean available=Gelu.check_availability_pizza(available_pizza);
                        if(!available){
                            System.out.println("We currently don't have the ingredients to provide you this meal!");
                            continue;
                        }
                        System.out.println("What size for your "+ (i+1) +" pizza"+" ? We have small, medium and big");
                        boolean valid_size=false;
                        String response5;
                        do {
                            response5 = scanner.next();
                            if(response5.equals("small")|| response5.equals("medium") ||response5.equals("big"))
                                valid_size=true;
                            else
                                System.out.println("Please enter a valid size!");
                        }while(!valid_size);

                        Daria.response_pizza(available_pizza,response5);
                        Daria.add_pizza(available_pizza);
                        available_pizza.cookPizza(response5);
                        System.out.println();
                        System.out.println("Your remaining money: "+ Daria.getMoney());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }

            }
            receipt receipt_Daria= new receipt(Daria);
            receipt_Daria.calculate_final_cost();
            System.out.println("Your final cost is: "+ receipt_Daria.getFinal_cost());
            System.out.println("Would you like to leave a tip?");
            System.out.println("You have "+ Daria.getMoney()+" money left!");
            String response7 = scanner.next();
            if(response7.equals("Yes")){
                System.out.println("How much?");
                int response8=scanner.nextInt();
                try {
                    receipt_Daria.calulate_final_cost_tip(response8);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(receipt_Daria);
        }

        Gelu.display_ingredients();

        System.out.println("Would you like to play a game to earn some money?");
        String response9 = scanner.next();
        if(response9.equals("Yes")){
            play_packmen(Daria);
        }
    }
    public void play_packmen(customer Daria) throws InterruptedException {
        packmen pac = new packmen(Daria);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380, 420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        while (!pac.isGameOver()){
            Thread.sleep(100);
        }

        System.out.println("You earned "+ pac.getScore() + " money");
    }
}
