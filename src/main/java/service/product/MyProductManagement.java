package service.product;

import model.Brand;
import model.Origin;
import model.Product;
import model.TypeProduct;

import java.util.List;

public interface MyProductManagement {
    List<Product> fillAll();

    void insert(Product product);

    void update(Product product);

    void delete(int id);

    Product findById(int id);

    TypeProduct findTypeById(int id);

    Brand findBrandById(int id);

    Origin findOriginById(int id);

    List<Product> searchByName(String name);
    List<Product> searchByType(int id);

}
