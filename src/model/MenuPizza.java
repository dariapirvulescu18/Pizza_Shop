package model;

import Repositories.PizzaRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuPizza extends Menu {

    private ArrayList<Pizza> pizzas;

    public MenuPizza() throws SQLException {
        pizzas = PizzaRepository.getInstance().selectAllPizzas();
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
