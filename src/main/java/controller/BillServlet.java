package controller;

import model.Bill;
import model.Customer;
import model.Orders;
import model.Product;
import service.bill.BillServiceIPL;
import service.bill.MyBillService;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;
import service.orders.MyOrdersService;
import service.orders.OrdersServiceIPL;
import service.product.MyProductManagement;
import service.product.ProductManagementIPL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillServlet", urlPatterns = "/bill")
public class BillServlet extends HttpServlet {
    MyOrdersService ordersService = new OrdersServiceIPL();
    MyBillService billService = new BillServiceIPL();
    MyCustomerService customerService = new CustomerServiceIPL();
    MyProductManagement productManagement = new ProductManagementIPL();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "addToCart":
                insertOrders(request,response);
                break;
            case "pay":
                showPaySuccess(request,response);
                break;
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "addToCart":
                insertOrders(request,response);
                break;
        }
    }
    private void insertBill(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String cus_name = request.getParameter("name");
        Customer customer = customerService.findCustomerByName(cus_name);
        Bill bill = new Bill(id, customer);
        billService.insert(bill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/cart.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void insertOrders(HttpServletRequest request, HttpServletResponse response) {
//        int bill_id = Integer.parseInt(request.getParameter("bill_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productManagement.findById(product_id);
        Bill bill = billService.findBillByCustomerId((Customer) request.getSession().getAttribute("customer"), 0);
//        Bill bill = billService.findBillByCustomerId(customer.getId(),0);
        Orders orders = new Orders(bill, product, quantity);
        ordersService.create(orders);
        List<Orders> ordersList = ordersService.showAllOrders(bill.getId());
        request.setAttribute("orders", ordersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/cart.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPaySuccess(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/paySuccess.jsp");
        billService.updateAfterAddToCart();
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
