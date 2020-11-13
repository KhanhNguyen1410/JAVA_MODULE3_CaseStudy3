package controller;

import model.Comment;
import model.Customer;
import model.Product;
import service.comment.CommentServiceIPL;
import service.comment.MyCommentService;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;
import service.product.MyProductManagement;
import service.product.ProductManagementIPL;

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
    MyProductManagement productManagement = new ProductManagementIPL();
    MyCommentService commentService = new CommentServiceIPL();

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
                disableCustomer(request,response);
                break;
            case "able":
                ableCustomer(request,response);
                break;
            case "profile":
                updateCustomer(request,response);
                break;
            case "forgotPass":
                getPass(request,response);
                break;
            case "comment":
                createComment(request,response);
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
                case "profile":
                    showProfile(request, response);
                    break;
                case "details":
                    showDetails(request, response);
                    break;
                case "forgotPass":
                    showForgotPass(request, response);
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
    private void disableCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        customerService.disableCustomer(id);
        dispatcher = request.getRequestDispatcher("/customer?action=listCustomer");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ableCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        customerService.ableCustomer(id);
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
        int status = 1;
        boolean isValid = customerService.checkCustomerName(name,status);
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

    private void showProfile(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        request.setAttribute("customer",customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/profile.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Customer customer = new Customer(id, name, pass,address, phone);
        this.customerService.update(customer);
        try {
            response.sendRedirect("/home?action=customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showDetails(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productManagement.findById(id);
        List<Comment> comments = commentService.findAll(id);
//        System.out.println(comments.get(1).getContent());
        request.setAttribute("product",product);
        request.setAttribute("comments",comments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/details.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createComment(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("comment");
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        Customer customer = customerService.findById(customer_id);
        Product product = productManagement.findById(product_id);
        Comment comment = new Comment(id,content,customer,product);
        commentService.create(comment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/details.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void showForgotPass(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/forgotPage.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getPass(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        Customer customer = customerService.forgotPass(name,phone);
        boolean idValid = customerService.checkAccountCustomer(name,phone);
        RequestDispatcher dispatcher;
//        request.setAttribute("customer",customer);
        if (idValid){
            request.setAttribute("message",customer.getPass());
        }
        else {
            request.setAttribute("message","Incorrect information");
        }
        dispatcher = request.getRequestDispatcher("customer/forgotPage.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
