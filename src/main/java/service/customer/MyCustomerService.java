package service.customer;

import model.Customer;

import java.util.List;

public interface MyCustomerService {
    void create(Customer customer);
    void update(Customer customer);
    void delete(int id);
    Customer findById(int id);
    Customer findCustomerByName(String name);
    Customer forgotPass(String name, String phone);
    List<Customer> findAll();
    boolean checkCustomer(String userName, String passWord);
    boolean checkCustomerName(String name, int status);
    boolean checkAccountCustomer(String name, String phone);
    boolean checkStatus(String name);
    void disableCustomer(int id);
    void ableCustomer(int id);
}
