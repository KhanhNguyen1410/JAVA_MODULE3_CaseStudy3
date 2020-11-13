package model;

import java.sql.Timestamp;
import java.util.List;

public class Bill {
    private int id;
    private Customer customer;
    private List<Orders> ordersList;
    private double amount;
    private Timestamp dayCreated;
    private int status;
    private Orders orders;
    private Product product;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill(int id, Timestamp dayCreated, Customer customer, Product product, Orders orders , int status) {
        this.id = id;
        this.customer = customer;
        this.dayCreated = dayCreated;
        this.status = status;
        this.product = product;
        this.orders = orders;
    }

    public Bill() {
    }
    public Bill(int id, Customer customer){
        this.id = id;
        this.customer = customer;
    }
    public Bill( Customer customer){
        this.customer = customer;
    }

    public Bill(int id, Customer customer, List<Orders> orders, Timestamp dayCreated, int status) {
        this.id = id;
        this.customer = customer;
        this.ordersList = orders;
        this.dayCreated = dayCreated;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDayCreated() {
        return dayCreated;
    }

    public void setDayCreated(Timestamp dayCreated) {
        this.dayCreated = dayCreated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
