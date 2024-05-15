package Repositories;

import DataBaseManager.DataBaseManager;
import model.Ingredient;
import model.Pizza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PizzaRepository {
    private DataBaseManager db;

    private static PizzaRepository instance = null;

    public static PizzaRepository getInstance(){
        if(instance==null){
            instance = new PizzaRepository();
        }
        return instance;
    }

    private PizzaRepository(){
        db=DataBaseManager.getInstance();
    }
    public int getId(String name1) throws SQLException {
        String query ="SELECT id FROM PIZZA WHERE type=?";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setString(1, name1);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        }
    }


    public ArrayList<Pizza> selectAllPizzas() throws SQLException {
        String query = "SELECT * FROM PIZZA";
        ArrayList<Pizza> allPizzas = new ArrayList<Pizza>();
        PreparedStatement pstmt = db.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int pizzaId = rs.getInt("ID");
            String type = rs.getString("TYPE");
            int cost = rs.getInt("COST");
            ArrayList<Ingredient> ing = new ArrayList<Ingredient>();
            String query2 = "SELECT NAME, p.QUANTITY " +
                    "FROM INGREDIENT i JOIN PIZZA_INGREDIENT p ON (p.INGREDIENT_ID= i.ID) " +
                    "WHERE pizza_id = ? ";
            PreparedStatement pstmt2 = db.prepareStatement(query2);
            pstmt2.setInt(1, pizzaId);
            ResultSet rs2 = pstmt2.executeQuery(); // Executăm pstmt2 aici
            while (rs2.next()) {
                String nameIngredient = rs2.getString(1);
                int quantity = rs2.getInt(2);
                ing.add(new Ingredient(nameIngredient, quantity));
            }
            allPizzas.add(new Pizza(type, cost, ing));
            // Închidem ResultSet și PreparedStatement pentru a elibera resursele
            rs2.close();
            pstmt2.close();
        }
        // Închidem ResultSet și PreparedStatement pentru a elibera resursele
        rs.close();
        pstmt.close();
        return allPizzas;
    }


}
