package service.bill;

import model.Bill;
import model.Customer;
import model.Product;

import java.util.List;

public interface MyBillService {
    void insert(Bill bill);

    void delete(int id);

    void updateAfterAddToCart();

    void updateAfterBuy();

    List<Bill> findAll();

    Bill findBillById(int id);

    Bill findBillByCustomerId(Customer cus_id, int status);

    boolean checkUnfinishedBill(int td);
}
