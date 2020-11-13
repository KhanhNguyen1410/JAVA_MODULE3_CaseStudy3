package service.admin;

import java.sql.*;

public class AdminServiceIPL implements MyAdminService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";
    private static final String CHECK_ADMIN = "{call checkAdmin(?,?)}";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public boolean checkAdmin(String name, String pass) {
        Connection connection = getConnection();
        boolean isValid = false;
        try {
            CallableStatement statement = connection.prepareCall(CHECK_ADMIN);
            statement.setString(1,name);
            statement.setString(2,pass);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                isValid = true;
            }
            else {
                isValid = false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isValid;
    }
}
