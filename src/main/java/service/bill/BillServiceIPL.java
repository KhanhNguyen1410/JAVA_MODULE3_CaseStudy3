package service.bill;

import model.*;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;
import service.orders.MyOrdersService;
import service.orders.OrdersServiceIPL;
import service.product.MyProductManagement;
import service.product.ProductManagementIPL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillServiceIPL implements MyBillService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";

    private MyCustomerService customerService = new CustomerServiceIPL();
//    private MyOrdersService ordersService = new OrdersServiceIPL();
    private MyProductManagement productManagement = new ProductManagementIPL();

    private static final String INSERT_BILL_SQL = "{call insertBill(?)}";
    private static final String FIND_BILL_BY_ID = "{call findBillById(?)}";
    private static final String DELETE_BILL_SQL = "{call deleteBill(?)}";
    private static final String FIND_BILL_BY_CUSTOMER = "{call findBillByCustomer(?,?)}";
    private static final String UPDATE_BILL_AFTER_ADD = "{call updateAfterAddToCart()}";
    private static final String UPDATE_BILL_AFTER_BUY = "{call updateAfterBuy()}";
    private static final String CHECK_UNFINISHED_BILL = "{call checkUnfinishedBill(?)}";
    private static final String FIND_ALL_BILL = "{call findAllBill()}";
    private  static final String FIND_BY_ID = "call findOrdersById(?)";

//    private static final String FIND_ALL_BILL = "{call findAllBill()}";

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
    public void insert(Bill bill) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(INSERT_BILL_SQL);
            statement.setInt(1,bill.getCustomer().getId());
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
            CallableStatement statement = connection.prepareCall(DELETE_BILL_SQL);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateAfterAddToCart() {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(UPDATE_BILL_AFTER_ADD);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateAfterBuy() {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(UPDATE_BILL_AFTER_BUY);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Bill> findAll() {
        Connection connection = getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_BILL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                Timestamp dayCreated = rs.getTimestamp("daycreated");
                int cus_id = rs.getInt("customer_id");
                int pro_id = rs.getInt("product_id");
                int orders_id = rs.getInt("bill_id");
                int status = rs.getInt("status");
                Customer customer = customerService.findById(cus_id);
                Product product = productManagement.findById(pro_id);
                Orders orders = findOrdersById(orders_id);
                bills.add(new Bill(id,dayCreated,customer,product,orders,status));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return bills;
    }

    @Override
    public Bill findBillById(int id) {
        Bill bill = null;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(FIND_BILL_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int bill_id = rs.getInt("id");
                int customer_id = rs.getInt("customer_id");
                Customer customer = customerService.findById(customer_id);
                bill = new Bill(bill_id,customer);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return bill;
    }

    @Override
    public Bill findBillByCustomerId(Customer customer, int status) {
        Bill bill = null;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(FIND_BILL_BY_CUSTOMER);
            statement.setInt(1,customer.getId());
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int bill_id = rs.getInt("id");
                bill = new Bill(bill_id,customer);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return bill;
    }

    @Override
    public boolean checkUnfinishedBill(int id) {
        boolean billIsValid = false;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(CHECK_UNFINISHED_BILL);
            statement.setInt(1,id);
            ResultSet rs =statement.executeQuery();
            if (rs.next()){
                billIsValid = true;
            }
            else {
                billIsValid = false;
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return billIsValid;
    }

    @Override
    public Orders findOrdersById(int fBill_id) {
        Connection connection = getConnection();
        Orders orders = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_BY_ID);
            statement.setInt(1,fBill_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int bill_id = rs.getInt("bill_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Bill bill = findBillById(bill_id);
                Product product = productManagement.findById(product_id);
                orders = new Orders(bill,product,quantity);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return orders;
    }


}
