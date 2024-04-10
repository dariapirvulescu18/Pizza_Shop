package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPizza extends Menu {

    private ArrayList<Pizza> pizzas;

    public MenuPizza()  {
        Ingredient tomato = IngredientFactory.createTomato(5);
        Ingredient salamiI = IngredientFactory.createSalami(10);
        Ingredient mushrooms = IngredientFactory.createMushrooms(2);
        Ingredient flour = IngredientFactory.createFlour(1);
        Ingredient cheese = IngredientFactory.createCheese(3);
        Ingredient ton = IngredientFactory.createTon(1);
        Ingredient spicyPepper = IngredientFactory.createSpicyPepper(1);
        Ingredient prosciuttoI = IngredientFactory.createProsciutto(5);
        ArrayList<Ingredient> ingredients1 = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ArrayList<Ingredient> ingredients4 = new ArrayList<>();
        ArrayList<Ingredient> ingredients5 = new ArrayList<>();
        ingredients1.add(flour);
        ingredients1.add(tomato);
        ingredients1.add(cheese);
        ingredients2.add(flour);
        ingredients2.add(salamiI);
        ingredients2.add(cheese);
        ingredients3.add(flour);
        ingredients3.add(prosciuttoI);
        ingredients3.add(cheese);
        ingredients3.add(mushrooms);
        ingredients4.add(cheese);
        ingredients4.add(flour);
        ingredients4.add(ton);
        ingredients5.add(flour);
        ingredients5.add(salamiI);
        ingredients5.add(cheese);
        ingredients5.add(spicyPepper);
        Pizza margherita = new Pizza("margherita",100,ingredients1);
        Pizza salami = new Pizza("salami",200,ingredients2);
        Pizza prosciutto = new Pizza("prosciutto",150,ingredients3);
        Pizza tonno = new Pizza("tonna",400,ingredients4);
        Pizza diavola = new Pizza("diavola",250,ingredients5);
        ArrayList<Pizza> pizzas1= new ArrayList<>();
        pizzas1.add(margherita);
        pizzas1.add(salami);
        pizzas1.add(prosciutto);
        pizzas1.add(tonno);
        pizzas1.add(diavola);
        this.pizzas=pizzas1;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public void choose() {
        System.out.println("Please choose a pizza from our menu!");
    }

    public Pizza checkExistance(String namePizza){
        for(var i:pizzas){
            if(i.getType().equals(namePizza))
                return i;
        }
        return null;
    }
    @Override
    public void initMenuFrame() {
        menuFrame.setTitle("Drinks Menu");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setSize(400, 600);
        menuFrame.setLocationRelativeTo(null);

        ImageIcon originalIcon = new ImageIcon("images/pizza.png");
        Image originalImage = originalIcon.getImage();

        // Scale the image to 400x600
        Image scaledImage = originalImage.getScaledInstance(400, 600, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JPanel pizzaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        pizzaPanel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(25, 15, 10, 15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        pizzaPanel.add(new JLabel("                             "),gridBagConstraints);
        gridBagConstraints.gridy = 1;
        pizzaPanel.add(new JLabel("                                 "),gridBagConstraints);

        int index=2;
        for (var piz : pizzas) {
            gridBagConstraints.gridy=index;
            index++;
            String drinkDetails = String.format("%s .......................................... $%d", piz.getType(), piz.getCost());
            JLabel drinkLabel = new JLabel(drinkDetails);
            pizzaPanel.add(drinkLabel,gridBagConstraints);

        }

        menuFrame.add(pizzaPanel, BorderLayout.CENTER);

        menuFrame.setVisible(true);
    }
}
