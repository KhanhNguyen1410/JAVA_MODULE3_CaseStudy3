package controller;

import model.Customer;
import model.Product;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    MyCustomerService customerService = new CustomerServiceIPL();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }
        switch (action){
            case "signUp":
                try {
                    createCustomer(request, response);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            case "delete":
                deleteCustomer(request,response);
                break;
            case "listCustomer":
                showAllCustomer(request,response);
                break;
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null){
                action ="";
            }
            switch (action){
                case "signUp":
                    showCreateForm(request, response);
                    break;
                case "listCustomer":
                    showAllCustomer(request,response);
                    break;

            }
    }

    private void showAllCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Customer> customers = customerService.findAll();
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        customerService.delete(id);
        dispatcher = request.getRequestDispatcher("/customer?action=listCustomer");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/signUpCustomer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String pass2 = request.getParameter("pass2");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        boolean isValid = customerService.checkCustomerName(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/signUpCustomer.jsp");
        if (pass.equals(pass2)){
            Customer customer = new Customer(id,name,pass,address,phone);
            if (isValid){
                request.setAttribute("message","customer name already existed");
            }
            else {
                customerService.create(customer);
                request.setAttribute("message", "create account success");
            }
        }
        else {
            request.setAttribute("message","the two passwords must be the same");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
