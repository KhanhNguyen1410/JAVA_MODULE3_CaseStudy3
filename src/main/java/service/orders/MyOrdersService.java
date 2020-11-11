package service.orders;

import model.Orders;

import java.util.List;

public interface MyOrdersService {
    void create(Orders orders);
    List<Orders> showAllOrders(int id);


}
