package service.product;

import model.Brand;
import model.Origin;
import model.Product;
import model.TypeProduct;
import service.product.MyProductManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagementIPL implements MyProductManagement {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy3?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword = "MySQL1410";

    private static final String SELECT_ALL_PRODUCT = "{call findAll()}";
    private static final String FIND_TYPE_BY_ID = "select * from typeProduct where id = ? ;";
    private static final String FIND_BRAND_BY_ID = "select * from brand where id = ? ;";
    private static final String FIND_ORIGIN_BY_ID = "select * from origin where id = ? ;";
    private static final String INSERT_PRODUCT_SQL = "call createProduct(?,?,?,?,?,?,?)";
    private static final String FIND_PRODUCT_BY_ID = "{call findProductById(?)}";
    private static final String UPDATE_PRODUCT_SPL = "{call updateProduct(?,?,?,?,?,?,?,?)}";
    private static final String DELETE_PRODUCT_SQL = "{call deleteProduct(?)}";
    private static final String SEARCH_BY_NAME = "{call searchByName(?)}";
    private static final String SEARCH_BY_TYPE = "{call sortByType(?)}";


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
    public TypeProduct findTypeById(int id){
        TypeProduct typeProduct = null;
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_TYPE_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String nameType = rs.getString("type_name");
                typeProduct = new TypeProduct(id,nameType);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return typeProduct;
    }
    public Brand findBrandById(int id){
        Brand brand = null;
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BRAND_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name= rs.getString("name");
                brand = new Brand(id,name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return brand;
    }
    @Override
    public Origin findOriginById(int id){
        Origin origin = null;
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ORIGIN_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("country");
                origin = new Origin(id,name);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return origin;
    }


    @Override
    public List<Product> fillAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(SELECT_ALL_PRODUCT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int type_id = rs.getInt("type_id");
                int brand_id = rs.getInt("brand_id");
                int origin_id = rs.getInt("origin_id");
                String description = rs.getString("description");
                TypeProduct typeProduct = findTypeById(type_id);
                Brand brand = findBrandById(brand_id);
                Origin origin = findOriginById(origin_id);
                products.add(new Product(id,name,price,image,typeProduct,brand,origin,description));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }
    @Override
    public void insert(Product product) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(INSERT_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getImage());
            statement.setInt(4,product.getType().getId());
            statement.setInt(5,product.getBrand().getId());
            statement.setInt(6,product.getOrigin().getId());
            statement.setString(7,product.getDesc());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void update(Product product) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall(UPDATE_PRODUCT_SPL);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImage());
            statement.setInt(5,product.getType().getId());
            statement.setInt(6,product.getBrand().getId());
            statement.setInt(7,product.getOrigin().getId());
            statement.setString(8,product.getDesc());
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
            CallableStatement statement = connection.prepareCall(DELETE_PRODUCT_SQL);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public Product findById(int id) {
        Connection connection = getConnection();
        Product product = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int type_id = rs.getInt("type_id");
                int brand_id = rs.getInt("brand_id");
                int origin_id = rs.getInt("origin_id");
                String description = rs.getString("description");
                TypeProduct typeProduct = findTypeById(type_id);
                Brand brand = findBrandById(brand_id);
                Origin origin = findOriginById(origin_id);
               product =  new Product(id, name, price, image, typeProduct, brand, origin, description);
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return product;
    }

        @Override
    public List<Product> searchByName(String s) {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
            try {
                CallableStatement statement = connection.prepareCall(SEARCH_BY_NAME);
                statement.setString(1,s);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    int type_id = rs.getInt("type_id");
                    int brand_id = rs.getInt("brand_id");
                    int origin_id = rs.getInt("origin_id");
                    String description = rs.getString("description");
                    TypeProduct typeProduct = findTypeById(type_id);
                    Brand brand = findBrandById(brand_id);
                    Origin origin = findOriginById(origin_id);
                    products.add(new Product(id,name,price,image,typeProduct,brand,origin,description));
                }
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return products;
        }

    @Override
    public List<Product> searchByType(int type_id) {
        Connection connection = getConnection();
        List<Product> products= new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(SEARCH_BY_TYPE);
            statement.setInt(1,type_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                 type_id = rs.getInt("type_id");
                int brand_id = rs.getInt("brand_id");
                int origin_id = rs.getInt("origin_id");
                String description = rs.getString("description");
                TypeProduct typeProduct = findTypeById(type_id);
                Brand brand = findBrandById(brand_id);
                Origin origin = findOriginById(origin_id);
                products.add(new Product(id,name,price,image,typeProduct,brand,origin,description));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }
}
