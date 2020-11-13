package controller;

import model.Bill;
import model.Customer;
import service.admin.AdminServiceIPL;
import service.admin.MyAdminService;
import service.bill.BillServiceIPL;
import service.bill.MyBillService;
import service.customer.CustomerServiceIPL;
import service.customer.MyCustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    MyCustomerService customerService = new CustomerServiceIPL();
    MyBillService billService = new BillServiceIPL();
    MyAdminService adminService = new AdminServiceIPL();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signInWithClient(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void signInWithClient(HttpServletRequest request, HttpServletResponse response) {
        String signInName = request.getParameter("userName");
        String passWord = request.getParameter("password");

//        Customer customerStatus = customerService.findCustomerByStatus(customerName);
        Customer customer = customerService.findCustomerByName(signInName);
        boolean isValid = customerService.checkCustomer(signInName, passWord);
        boolean billIsValid = billService.checkUnfinishedBill(customer.getId());
        boolean customerIsValid = customerService.checkStatus(signInName);
        boolean adminIsValid = adminService.checkAdmin(signInName,passWord);
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        try {
            if (adminIsValid) {
                dispatcher = request.getRequestDispatcher("/home?action=admin");
            }
            else if (isValid && customerIsValid) {
                Bill bill = new Bill(customer);
                if (!billIsValid){
                    billService.insert(bill);
                }
//               session.setAttribute("bill", bill);
                dispatcher = request.getRequestDispatcher("/home?action=customer");
            }
            else {
                dispatcher = request.getRequestDispatcher("/home");
                request.setAttribute("message", "user name or passWord wrong");
            }
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
