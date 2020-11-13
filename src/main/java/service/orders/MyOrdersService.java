package service.orders;

import model.Orders;
import model.TotalCart;

import java.util.List;

public interface MyOrdersService {
    void create(Orders orders);
    List<Orders> showAllOrders(int id);
    TotalCart totalCart(int p_id);
    Orders findById(int bill_id);


}
