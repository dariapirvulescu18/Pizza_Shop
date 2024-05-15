package Repositories;

import DataBaseManager.DataBaseManager;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository {
    private DataBaseManager db;

    private static CustomerRepository instance = null;

    public static CustomerRepository getInstance(){
        if(instance==null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    private CustomerRepository(){
        db=DataBaseManager.getInstance();
    }
    public void insert(int id, Customer c) throws SQLException {
        String query = "INSERT INTO CUSTOMER (ID, NAME, MONEY, DRINK, COST_DRINK, SCORE_GAME) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, c.getName());
            preparedStatement.setDouble(3, c.getMoney());
            preparedStatement.setString(4, c.getDrink());
            preparedStatement.setDouble(5, c.getCostDrink());
            preparedStatement.setDouble(6, c.getScoreGame());
            preparedStatement.executeUpdate();
            System.out.println("Customer inserted successfully.");
        }

    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT SEQ_CUSTOMER.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM CUSTOMER WHERE ID = ?";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer with ID " + id + " not found.");
            }
        }
    }

}
