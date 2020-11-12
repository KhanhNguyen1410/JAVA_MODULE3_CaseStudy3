package service.customer;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceIPL implements MyCustomerService {


    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";
    private static String INSERT_INTO_CUSTOMER = "{call createCustomer(?,?,?,?)}";
    private static String UPDATE_CUSTOMER = "{call updateCustomer(?,?,?,?,?)}";
    private static String FIND_CUSTOMER_BY_ID = "{call findCustomerByID(?)}";
    private static String FIND_ALL_CUSTOMER = "{call findAllCustomer()}";
    private static String CHECK_CUSTOMER = "{call checkUser(?,?)}";
    private static String CHECK_CUSTOMER_NAME = "{call checkUserName(?)}";
    private static final String DELETE_CUSTOMER_SQL = "{call deleteCustomer(?)}";

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
    public void create(Customer customer) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(INSERT_INTO_CUSTOMER);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getPass());
            statement.setString(3,customer.getAddress());
            statement.setString(4,customer.getPhone());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
            Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(UPDATE_CUSTOMER);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getPass());
            statement.setString(3,customer.getAddress());
            statement.setString(4,customer.getPhone());
            statement.setInt(5,customer.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(DELETE_CUSTOMER_SQL);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
        Connection connection = getConnection();
        Customer customer = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_CUSTOMER_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
               String name = rs.getString("name");
               String pass = rs.getString("pass");
               String address = rs.getString("address");
               String phone = rs.getString("phone");
               customer = new Customer( id, name, pass,address, phone);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
        }
        return customer;
    }

    @Override
    public Customer findCustomerByName(String Cus_name) {
        Customer customer = new Customer();
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(CHECK_CUSTOMER_NAME);
            statement.setString(1,Cus_name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                String address = rs.getString("address");
                String phone  = rs.getString("phone");
                customer = new Customer(id,name,pass,address,phone);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        Connection connection = getConnection();
        List<Customer> customers = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_CUSTOMER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                customers.add(new Customer( id, name, pass, address, phone));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean checkCustomer(String userName, String passWord) {
        boolean isValid = false;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(CHECK_CUSTOMER);
            statement.setString(1,userName);
            statement.setString(2,passWord);
            ResultSet rs =statement.executeQuery();
            if (rs.next()){
                isValid = true;
            }
            else {
                isValid = false;
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isValid;
    }

    @Override
    public boolean checkCustomerName(String userName) {
        boolean isValid = false;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(CHECK_CUSTOMER_NAME);
            statement.setString(1,userName);
            ResultSet rs =statement.executeQuery();
            if (rs.next()){
                isValid = true;
            }
            else {
                isValid = false;
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isValid;
    }
}
