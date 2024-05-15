package Repositories;

import DataBaseManager.DataBaseManager;
import model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientRepository {
    private DataBaseManager db;

    private static IngredientRepository instance = null;

    public static IngredientRepository getInstance(){
        if(instance==null){
            instance = new IngredientRepository();
        }
        return instance;
    }

    private IngredientRepository(){
        db=DataBaseManager.getInstance();
    }
    public void insert (int id, String name, int quantity) throws SQLException {
        String query = "INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        }
    }
    public int getQuantity(String name) throws SQLException {
        String query = "SELECT QUANTITY FROM INGREDIENT WHERE NAME = ?";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("QUANTITY");
                } else {
                    return 0;
                }
            }
        }
    }
    public void updateQuantity(String nume, int quantity) throws SQLException {
        String query = "UPDATE INGREDIENT SET QUANTITY = ? WHERE name = ?";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, quantity);
            pstmt.setString(2, nume);
            pstmt.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM INGREDIENT WHERE ID = ?";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Ingredient select(int id) throws SQLException {
        String query = "SELECT ID, NAME, QUANTITY FROM INGREDIENT WHERE ID = ?";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("NAME");
                    int quantity = rs.getInt("QUANTITY");
                    return new Ingredient(name, quantity);
                } else {
                    return null;
                }
            }
        }
    }
    public ArrayList<Ingredient> selectAllIngredients() throws SQLException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String query = "SELECT * FROM INGREDIENT";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                String name = rs.getString("NAME");
                int quantity = rs.getInt("QUANTITY");
                Ingredient ingredient = new Ingredient(name, quantity);
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

}
