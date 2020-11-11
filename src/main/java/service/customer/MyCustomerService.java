package service.customer;

import model.Customer;

import java.util.List;

public interface MyCustomerService {
    void create(Customer customer);
    void update(Customer customer);
    void delete(int id);
    Customer findById(int id);
    Customer findCustomerByName(String name);
    List<Customer> findAll();
    boolean checkCustomer(String userName, String passWord);
    boolean checkCustomerName(String name);
}
