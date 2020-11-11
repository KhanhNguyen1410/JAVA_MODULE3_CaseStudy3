package service.orders;

import model.Bill;
import model.Customer;
import model.Orders;
import model.Product;
import service.bill.BillServiceIPL;
import service.bill.MyBillService;
import service.product.MyProductManagement;
import service.product.ProductManagementIPL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class


OrdersServiceIPL implements MyOrdersService {

    MyProductManagement productManagement = new ProductManagementIPL();
    MyBillService billService = new BillServiceIPL();

    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";

    private static final String INSERT_INTO_ORDERS = "{call insertOrders(?,?,?)}";
    private  static final String FIND_ALL_ORDERS = "call findAllOrders(?)";

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
    public void create(Orders orders) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(INSERT_INTO_ORDERS);
            statement.setInt(1,orders.getBill().getId());
            statement.setInt(2,orders.getProduct().getId());
            statement.setInt(3,orders.getQuantity());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Orders> showAllOrders(int id) {
        Connection connection = getConnection();
        List<Orders> ordersList = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_ORDERS);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int bill_id = rs.getInt("bill_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Bill bill = billService.findBillById(bill_id);
                Product product = productManagement.findById(product_id);
                ordersList.add(new Orders(bill,product,quantity));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return ordersList;
    }
}
