package CalcolatriceRpnDatabase;
import java.sql.*;

public class DB {
    private Connection connect() {
        final String databaseUrl = "jdbc:mysql://localhost:3306/calcolatrice_rpn";
        final String username = "root";
        final String password =  "";
        Connection conn;
        try {
            conn = DriverManager.getConnection(databaseUrl,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public int register (String username, String password){
        Connection conn = connect();
        try {
            Statement statement = conn.createStatement();
            String query = "INSERT INTO users (username, password) VALUES " + "(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int login (String username, String password) {
        Connection conn = connect();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE username =? AND password =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statement.close();
                conn.close();
                return 1;
            } else {
                statement.close();
                conn.close();
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int insertOperation(String username, String operation, String result) {
        Connection conn = connect();
        try {
            Statement statement = conn.createStatement();
            String query = "INSERT INTO operations (username, operation, result) VALUES " + "(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, operation);
            preparedStatement.setString(3, result);
            preparedStatement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String getOperations(String username) {
        Connection conn = connect();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM operations WHERE username =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder operations = new StringBuilder();
            while (resultSet.next()) {
                operations.append(resultSet.getString("operation")).append(" = ").append(resultSet.getString("result")).append("\n");
            }
            statement.close();
            conn.close();
            return operations.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
