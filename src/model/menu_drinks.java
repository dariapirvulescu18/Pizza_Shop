package model;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class menu_drinks extends menu {
    private HashMap<String, Integer []> drinks;

    public HashMap<String, Integer[]> getDrinks() {
        return drinks;
    }

    public menu_drinks(){
        this.drinks = new HashMap<>();
        drinks.put("Wine", new Integer[]{70, 2});
        drinks.put("Cocktail", new Integer[]{50, 5});
        drinks.put("Beer", new Integer[]{25, 6});
        drinks.put("Tea", new Integer[]{20, 1});
        drinks.put("Soda", new Integer[]{15, 3});
        drinks.put("Water", new Integer[]{10, 4});

    }
    public void prepare(String drink) throws InterruptedException {
        StringBuilder str = new StringBuilder();
        switch (drink){
            case "Tea":
                str.append("🍵");
                break;
            case "Wine":
                str.append("🍷");
                break;
            case "Soda":
                str.append("🧃");
                break;
            case "Water":
                str.append("🥛");
                break;
            case "Cocktail":
                str.append("🍹");
                break;
            case "Beer":
                str.append("🍺");
                break;
            default:
                str.append("Unknown");
                break;
        }

        java.util.Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nYour " + str + " drink is ready!");
                timer.cancel();
            }
        };

        timer.schedule(task, (long) 10 * 1000);

        StringBuilder[] items = { str};
        int length = items.length;

        for (int i = 0; i < 20; i++) {
            StringBuilder line = new StringBuilder();

            // Generate the line content based on the current position
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

    }

    @Override
    public void choose(){
        System.out.println("Please choose a drink!");
        System.out.println("Press 1 for Tea, 2 for Wine, 3 for Soda,4 for Water, 5 for Cocktail and 6 for Beer");
    }

    @Override
    public void init_menu_frame() {
        menuframe.setTitle("Drinks Menu");
        menuframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuframe.setResizable(false);
        menuframe.setSize(400, 600);
        menuframe.setLocationRelativeTo(null);

        ImageIcon originalIcon = new ImageIcon("images/DRINKS.png");
        Image originalImage = originalIcon.getImage();

        // Scale the image to 400x600
        Image scaledImage = originalImage.getScaledInstance(400, 600, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JPanel drinksPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        drinksPanel.setLayout(gridBagLayout); // Set layout to vertical BoxLayout
        gridBagConstraints.insets = new Insets(25, 15, 10, 15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        drinksPanel.add(new JLabel("                             "),gridBagConstraints);
        gridBagConstraints.gridy = 1;
        drinksPanel.add(new JLabel("                                 "),gridBagConstraints);

        int index=2;
        for (String drink : drinks.keySet()) {
            gridBagConstraints.gridy=index;
            index++;
            Integer[] details = drinks.get(drink);
            String drinkDetails = String.format("%s .......................................... $%d", drink, details[0]);
            JLabel drinkLabel = new JLabel(drinkDetails);
            drinksPanel.add(drinkLabel,gridBagConstraints);

        }

        menuframe.add(drinksPanel, BorderLayout.CENTER);

        menuframe.setVisible(true);
    }
}
