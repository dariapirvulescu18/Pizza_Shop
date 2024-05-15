package DataBaseManager;

import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager{
    private static final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String username = "c##dariapirvulescu18";
    private static final String password = "oracle";
    private static Connection connection;
    //Make it Singleton
    private static DataBaseManager instance = null;
    private DataBaseManager(){
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(username);
            ods.setPassword(password);
            connection = ods.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DataBaseManager getInstance(){
        if(instance == null){
            instance = new DataBaseManager();
            return instance;
        }
        else
            return instance;
    }

    public ResultSet executeQuery(String q) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement(q);
            return stmt.executeQuery();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String q) throws SQLException{
        try {
            PreparedStatement stmt = connection.prepareStatement(q);
            return stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        return stmt;
    }
}