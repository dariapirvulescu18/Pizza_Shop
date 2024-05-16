package Repositories;

import DataBaseManager.DataBaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRepository {
    private DataBaseManager db;

    private static EmployeeRepository instance = null;

    public static EmployeeRepository getInstance(){
        if(instance==null){
            instance = new EmployeeRepository();
        }
        return instance;
    }
    private EmployeeRepository(){
        db=DataBaseManager.getInstance();
    }
    public int getNextIdSkill() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT SEQ_EMPSKILL.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }

    public void addRating(int rating,int idCustomer, int idEmployee) throws SQLException {
        String query = "INSERT INTO EMPLOYEERATING VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.setInt(2, idCustomer);
            preparedStatement.setInt(3,rating);
            preparedStatement.executeUpdate();
            System.out.println("Rating inserted successfully");
        }
    }
    public void addSkill(int id,String skill,int CustomerId,int EmployeeId ) throws SQLException {
        String query = "INSERT INTO EMPLOYEESKILLS VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2, EmployeeId);
            preparedStatement.setInt(3, CustomerId);
            preparedStatement.setString(4,skill);
            preparedStatement.executeUpdate();
            System.out.println("Skill inserted successfully");
        }
    }

    public void promoteEmployee() throws SQLException {
        String query = "SELECT employee_id, AVG(rating) AS average_rating " +
                "FROM EmployeeRating " +
                "GROUP BY employee_id " +
                "HAVING AVG(rating) = (" +
                "    SELECT MAX(avg_rating) " +
                "    FROM (" +
                "        SELECT employee_id, AVG(rating) AS avg_rating " +
                "        FROM EmployeeRating " +
                "        GROUP BY employee_id" +
                "    ) " +
                ")";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                int emplId = rs.getInt("employee_id");
                String query2 = "UPDATE EMPLOYEE SET salary = salary + salary * 0.1 WHERE id = ?";
                try (PreparedStatement pstmt = db.prepareStatement(query2)) {
                    pstmt.setInt(1, emplId);
                    pstmt.executeUpdate();
                    System.out.println("The employee " + emplId + " has been promoted!");
                }
            }
        }
    }

    public void demoteEmployee() throws SQLException {
        String query = "SELECT employee_id, AVG(rating) AS average_rating " +
                "FROM EmployeeRating " +
                "GROUP BY employee_id " +
                "HAVING AVG(rating) = (" +
                "    SELECT MIN(avg_rating) " +
                "    FROM (" +
                "        SELECT employee_id, AVG(rating) AS avg_rating " +
                "        FROM EmployeeRating " +
                "        GROUP BY employee_id" +
                "    ) " +
                ")";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int emplId = rs.getInt("employee_id");
                String query2 = "SELECT COUNT(*) AS skill_count " +
                        "FROM EmployeeSkills " +
                        "WHERE employeeID = ? " +
                        "GROUP BY employeeID";
                try (PreparedStatement preparedStatement2 = db.prepareStatement(query2)) {
                    preparedStatement2.setInt(1, emplId);
                    ResultSet rs2 = preparedStatement2.executeQuery();
                    if (rs2.next()) {
                        int skillCount = rs2.getInt("skill_count");
                        if (skillCount > 0) {
                            String query3 = "UPDATE EMPLOYEE SET salary = salary - salary * 0.1 WHERE id = ?";
                            try (PreparedStatement pstmt = db.prepareStatement(query3)) {
                                pstmt.setInt(1, emplId);
                                pstmt.executeUpdate();
                                System.out.println("The employee " + emplId + " has been demoted!");
                            }
                        } else {
                            String query3 = "DELETE FROM EMPLOYEE WHERE id = ?";
                            try (PreparedStatement pstmt = db.prepareStatement(query3)) {
                                pstmt.setInt(1, emplId);
                                pstmt.executeUpdate();
                                System.out.println("The employee " + emplId + " has been fired!");
                            }
                        }
                    }
                }
            }
        }
    }
    public String chooseEmployee(int id)throws SQLException{
        String query = "SELECT name FROM employee where id=?";
        String employeeName = "";
        try (PreparedStatement preparedStatement = db.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 employeeName = rs.getString("name");
            }
        }
        return employeeName;
    }
    public int findId(String nameEmployee) throws SQLException{
        String query = "SELECT id FROM employee WHERE name = ?";
        int employeeId=0;
        try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
            preparedStatement.setString(1, nameEmployee);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeId = resultSet.getInt("id");
            }
        }
        return employeeId;
    }

    public ArrayList<Integer> allIds() throws SQLException{
        String query ="SELECT id from employee";
        ArrayList<Integer> idList = new ArrayList<>();
        try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                idList.add(id);
            }
        }
        return idList;
    }


}
