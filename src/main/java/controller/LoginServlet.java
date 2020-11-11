package controller;

import model.Bill;
import model.Customer;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signInWithClient(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void signInWithClient(HttpServletRequest request, HttpServletResponse response) {
        String customerName = request.getParameter("userName");
        String passWord = request.getParameter("password");

        Customer customer = customerService.findCustomerByName(customerName);
        boolean isValid = customerService.checkCustomer(customerName, passWord);
        boolean billIsValid = billService.checkUnfinishedBill(customer.getId());
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);

        try {
            if (customerName.equals("admin") && (passWord.equals("admin"))) {
                dispatcher = request.getRequestDispatcher("/home?action=admin");
            }
            else if (isValid) {
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
