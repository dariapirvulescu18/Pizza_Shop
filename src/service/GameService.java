package service;

import Repositories.CustomerRepository;
import Repositories.IngredientRepository;
import Repositories.ReceiptRepository;
import model.*;
import packmen.PacMan;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameService {
    private Fridge createIngredients() throws SQLException {
        ArrayList<Ingredient> ingredients = IngredientRepository.getInstance().selectAllIngredients();
        Fridge Gelu = new Fridge(ingredients);
        return Gelu;
    }
    private Customer createClient(){
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        AuditService.log("The player chose a name "+name);
        System.out.println("Enter your money: ");
        int money = scanner.nextInt();
        Customer Daria = new Customer(name,money);
        System.out.println(Daria);
        System.out.println("Hello "+ Daria.getName() +" !");
        return Daria;
    }
    private void orderDrink(Customer Daria) throws InterruptedException {
        String drink =Daria.responseDrinks();
        System.out.println("I would like  "+drink);
        MenuDrinks drinks = new MenuDrinks();
        drinks.prepare(drink);
        AuditService.log("The player orderded a drink "+drink);
        System.out.println("Your remaining money: "+ Daria.getMoney());
    }
    private  void orderPizza(Fridge Gelu, Customer Daria, Scanner scanner) throws SQLException {
        MenuPizza menuPizza = new MenuPizza();
        ArrayList<Pizza> lista_pizza = new ArrayList<Pizza>();
        lista_pizza= menuPizza.getPizzas();
        System.out.println("How many pizzas would you like to order?");
        int response4= scanner.nextInt();
        for(int i=0;i<response4;i++){
            try {
                System.out.println("What pizza would you like?");
                boolean valid_name = false;
                Pizza available_pizza;
                String resonse_pizza ;
                do {
                    resonse_pizza=scanner.next();
                    available_pizza =menuPizza.checkExistance(resonse_pizza);
                    if(available_pizza!=null)
                        valid_name=true;
                    else
                        System.out.println("Please enter a valid name!");
                }while (!valid_name);
                AuditService.log("The player chose a pizza "+resonse_pizza);
                boolean available=Gelu.checkAavailabilityPizza(available_pizza);
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

                Daria.responsePizza(available_pizza,response5);
                Daria.add_pizza(available_pizza);
                available_pizza.cookPizza(response5);
                System.out.println();
                System.out.println("Your remaining money: "+ Daria.getMoney());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
    private Receipt createReceipt(Scanner scanner, Customer Daria){
        Receipt receipt_Daria= new Receipt(Daria);
        receipt_Daria.calculateFinalCost();
        System.out.println("Your final cost is: "+ receipt_Daria.getFinalCost());
        System.out.println("Would you like to leave a tip?");
        System.out.println("You have "+ Daria.getMoney()+" money left!");
        String response7 = scanner.next();
        if(response7.equals("Yes")){
            System.out.println("How much?");
            int response8=scanner.nextInt();
            try {
                receipt_Daria.calulateFinalCostTip(response8);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(receipt_Daria);
        AuditService.log("The player now has a receipt ");
        return receipt_Daria;
    }
    public void startGame() throws InterruptedException, SQLException {
        Fridge Gelu = createIngredients();
        Customer Daria = createClient();

        System.out.println("Would you like to see the menu?");
        Scanner scanner = new Scanner(System.in);
        String response= scanner.nextLine();
        if(response.equals("No")){
            System.exit(0);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().initMenuFrame();
            }
        });

        System.out.println("Would you like a drink first?");
        String response2= scanner.nextLine();
        if(response2.equals("Yes")){
            orderDrink(Daria);
        }
        System.out.println("Would you like to order pizza?");
        String response3= scanner.nextLine();
        if(response3.equals("Yes")){

            orderPizza(Gelu,Daria,scanner);
        }


        Receipt receipt =createReceipt(scanner,Daria);
        Gelu.displayIngredients();

        System.out.println("Would you like to play a game to earn some money?");
        String response9 = scanner.next();
        if(response9.equals("Yes")){
            playPackmen(Daria,receipt);
            AuditService.log("The player is playing pacman ");
        }
        else{
            ReceiptRepository r = ReceiptRepository.getInstance();
            CustomerRepository c = CustomerRepository.getInstance();
            int CustomerId = c.getNextId();
            int ReceiptId = r.getNextId();
            c.insert(CustomerId,Daria);
            r.insert(ReceiptId,CustomerId,Daria, receipt.getDate(),receipt.getFinalCost(),receipt.getTip());
        }
    }
    public void playPackmen(Customer Daria,Receipt receipt) throws InterruptedException, SQLException {
        PacMan pac = new PacMan(Daria);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380, 420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        while (!pac.isGameOver()){
            Thread.sleep(100);
        }
        System.out.println("You earned "+ pac.getScore() + " money");
        System.out.println(Daria);
        ReceiptRepository r = ReceiptRepository.getInstance();
        CustomerRepository c = CustomerRepository.getInstance();
        int CustomerId = c.getNextId();
        int ReceiptId = r.getNextId();
        c.insert(CustomerId,Daria);
        r.insert(ReceiptId,CustomerId,Daria, receipt.getDate(),receipt.getFinalCost(),receipt.getTip());
    }
}
