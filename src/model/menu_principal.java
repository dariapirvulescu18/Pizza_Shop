package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class menu_principal extends menu{
    public menu_principal(){
        super();

    }

    @Override
    public void choose() {
        System.out.println("Please Choose!");
    }

    @Override
    public void init_menu_frame(){
        menu.visits++;

        menuframe.setTitle("Restaurant Menu");
        menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuframe.setSize(600, 600);
        menuframe.setLayout(new BorderLayout());

        ImageIcon meniuIcon = new ImageIcon("images/menu_cover.png");
        JLabel meniuLabel = new JLabel(meniuIcon);
        meniuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        meniuLabel.setVerticalAlignment(SwingConstants.CENTER);
        menuframe.add(meniuLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton bauturiButton = new JButton(" 🍸 Drink Menu 🍸");
        bauturiButton.setPreferredSize(new Dimension(200, 50));
        bauturiButton.setBackground(new Color(101,74,155));
        bauturiButton.setForeground(Color.white); // Setam culoarea textului
        bauturiButton.setFocusPainted(false); // Eliminam evidentierea la focus
        bauturiButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        bauturiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bauturiButton.setBackground(new Color(54, 23, 116));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bauturiButton.setBackground(new Color(101,74,155));
            }
        });
        bauturiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu_drinks().init_menu_frame();
            }
        });
        buttonPanel.add(bauturiButton);

        JButton pizzaButton = new JButton(" 🍕 Pizza Menu 🍕");
        pizzaButton.setPreferredSize(new Dimension(200, 50));
        pizzaButton.setBackground(new Color(101,74,155));
        pizzaButton.setForeground(Color.white);
        pizzaButton.setFocusPainted(false);
        pizzaButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Setam spatierea dintre text si marginile butonului

        pizzaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pizzaButton.setBackground(new Color(54, 23, 116));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pizzaButton.setBackground(new Color(101,74,155));
            }
        });
        pizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu_pizza().menuframe.setVisible(true);
            }
        });
        buttonPanel.add(pizzaButton);

        menuframe.add(buttonPanel, BorderLayout.SOUTH);

        menuframe.setLocationRelativeTo(null);
        menuframe.setVisible(true);
    }
}
