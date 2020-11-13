package service.comment;

import model.Comment;
import model.Customer;
import model.Product;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;
import service.product.MyProductManagement;
import service.product.ProductManagementIPL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceIPL implements MyCommentService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";
    MyCustomerService customerService = new CustomerServiceIPL();
    MyProductManagement productManagement = new ProductManagementIPL();

    private static final String INSERT_COMMENT = "{call insertComment(?,?,?,?)}";
    private static final String FIND_ALL_COMMENT = "{call findCommentProduct(?)}";

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
    public void create(Comment comment) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(INSERT_COMMENT);
            statement.setInt(1,comment.getId());
            statement.setString(2,comment.getContent());
            statement.setInt(3,comment.getCustomer().getId());
            statement.setInt(4,comment.getProduct().getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public List<Comment> findAll(int product_id) {
        Connection connection = getConnection();
        List<Comment> comments = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_COMMENT);
            statement.setInt(1,product_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String content = rs.getString("content");
                int cus_id = rs.getInt("customer_id");
                int product_id1 = rs.getInt("product_id");
                Product product = productManagement.findById(product_id1);
                Customer customer = customerService.findById(cus_id);
                comments.add(new Comment(id,content,customer,product));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return comments;
    }
}
