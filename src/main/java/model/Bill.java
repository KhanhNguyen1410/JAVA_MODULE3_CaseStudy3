package model;

import java.sql.Timestamp;
import java.util.List;

public class Bill {
    private int id;
    private Customer customer;
    private List<Orders> orders;
    private double amount;
    private Timestamp dayCreated;
    private int status;

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
        this.orders = orders;
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
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
