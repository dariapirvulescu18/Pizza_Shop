package Repositories;

import DataBaseManager.DataBaseManager;
import model.Customer;
import model.Pizza;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReceiptRepository {
    private DataBaseManager db;

    private static ReceiptRepository instance = null;

    public static ReceiptRepository getInstance(){
        if(instance==null){
            instance = new ReceiptRepository();
        }
        return instance;
    }

    private ReceiptRepository(){
        db=DataBaseManager.getInstance();
    }
    public void insert(int id, int customerId, Customer c, LocalDate dateReceipt, int finalCost, int tip) throws SQLException {
        String query = "INSERT INTO RECEIPT (ID, CUSTOMER_ID, DATE_RECEIPT, FINAL_COST, TIP) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setDate(3, Date.valueOf(dateReceipt));
            preparedStatement.setInt(4, finalCost);
            preparedStatement.setInt(5, tip);
            preparedStatement.executeUpdate();
            System.out.println("Receipt inserted successfully.");
        }
        String query2 = "INSERT INTO RECEIPT_PIZZA (RECEIPT_ID, PIZZA_ID, PIZZA_SIZE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement2 = db.prepareStatement(query2)) {
            for (Pizza pizza : c.getOrderPizza()) {
                int pizzaId = PizzaRepository.getInstance().getId(pizza.getType());
                preparedStatement2.setInt(1, id);
                preparedStatement2.setInt(2, pizzaId);
                preparedStatement2.setString(3, pizza.getSize());
                preparedStatement2.executeUpdate();
            }
        }
    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT SEQ_RECEIPT.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }

}
