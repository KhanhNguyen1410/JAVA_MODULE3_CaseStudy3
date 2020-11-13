package model;

public class Comment {
    private int id;
    private String content;
    private Product product;
    private Customer customer;

    public Comment() {
    }

    public Comment(int id, String content, Customer customer , Product product) {
        this.id = id;
        this.content = content;
        this.product = product;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
