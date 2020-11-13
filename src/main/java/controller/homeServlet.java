package controller;

import model.Brand;
import model.Origin;
import model.Product;
import model.TypeProduct;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "homeServlet", urlPatterns = "/home")
public class homeServlet extends HttpServlet {

    MyProductManagement productManagement = new ProductManagementIPL();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(request, response);
                break;
            case "edit":
                try {
                    updateProduct(request, response);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "searchByClient":
                showListSearchByClient(request, response);
                break;
            case "searchByAdmin":
                showListSearchByAdmin(request, response);
                break;
            case "admin":
                listProductWithAdmin(request, response);
                break;
            case "customer":
                listProductWithCustomer(request, response);
                break;
            default:
                listProductWithClient(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "type":
                showListLipstick(request, response);
                break;
            case "edit":
                break;
            case "delete":
//                showDeleteForm(request, response);
                break;
            case "searchByClient":
//                showListSearch(request, response);
                break;
//            case "admin":
//                showListSearch(request, response);
//                break;
            default:
                listProductWithClient(request, response);
        }
    }


    private void listProductWithClient(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        session.invalidate();
        List<Product> products = productManagement.fillAll();
        request.setAttribute("product", products);
        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getRequestDispatcher("product/home.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProductWithCustomer(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Product> products = productManagement.fillAll();
        request.setAttribute("product", products);
        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getRequestDispatcher("product/customerHome.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProductWithAdmin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Product> products = productManagement.fillAll();
        request.setAttribute("product", products);
        RequestDispatcher dispatcher;
        try {
            dispatcher = request.getRequestDispatcher("product/adminHome.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int type_id = Integer.parseInt(request.getParameter("type"));
        int brand_id = Integer.parseInt(request.getParameter("brand"));
        int origin_id = Integer.parseInt(request.getParameter("origin"));
        String desc = request.getParameter("desc");
        TypeProduct typeProduct = productManagement.findTypeById(type_id);
        Brand brand = productManagement.findBrandById(brand_id);
        Origin origin = productManagement.findOriginById(origin_id);
        Product product = new Product(id, name, price, image, typeProduct, brand, origin, desc);

        try {
            productManagement.insert(product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home?action=admin");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int type_id = Integer.parseInt(request.getParameter("type"));
        int brand_id = Integer.parseInt(request.getParameter("brand"));
        int origin_id = Integer.parseInt(request.getParameter("origin"));
        String desc = request.getParameter("desc");
        TypeProduct typeProduct = productManagement.findTypeById(type_id);
        Brand brand = productManagement.findBrandById(brand_id);
        Origin origin = productManagement.findOriginById(origin_id);
        Product product = new Product(id, name, price, image, typeProduct, brand, origin, desc);
        productManagement.update(product);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("error.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/home?action=admin");
//            request.setAttribute("message", "product has updated");
        }
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        productManagement.delete(id);
        dispatcher = request.getRequestDispatcher("/home?action=admin");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListSearchByClient(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        if (name == null) {
            name = "";
        }
        RequestDispatcher dispatcher;
        try {
            List<Product> products = productManagement.searchByName(name);
            request.setAttribute("product", products);
            if (products != null) {
                dispatcher = request.getRequestDispatcher("product/home.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("/error.jsp");
            }
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListSearchByAdmin(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        if (name == null) {
            name = "";
        }
        RequestDispatcher dispatcher;
        try {
            List<Product> products = productManagement.searchByName(name);
            request.setAttribute("product", products);
            if (products != null) {
                dispatcher = request.getRequestDispatcher("/home?action=admin");
            } else {
                dispatcher = request.getRequestDispatcher("/error.jsp");
            }
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListLipstick(HttpServletRequest request, HttpServletResponse response) {
        int type_id = Integer.parseInt(request.getParameter("type_id"));
        RequestDispatcher dispatcher;
        try {
            List<Product> products = productManagement.searchByType(type_id);
            request.setAttribute("product", products);
            if (products != null) {
                dispatcher = request.getRequestDispatcher("product/home.jsp");
            }
            else {
                dispatcher = request.getRequestDispatcher("/error.jsp");
            }
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
