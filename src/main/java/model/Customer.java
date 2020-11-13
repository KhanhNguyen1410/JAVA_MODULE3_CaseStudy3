package model;

public class Customer {
    private int id;
    private String name;
    private String pass;
    private String address;
    private String phone;
    private int status;

    public Customer(int id, String name, String pass, String address, String phone, int status) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer() {
    }
    public Customer(String name, String pass, String address, String phone) {

        this.name = name;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
    }

    public Customer(int id, String name, String pass, String address, String phone) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
