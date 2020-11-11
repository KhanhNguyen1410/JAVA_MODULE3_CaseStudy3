package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private TypeProduct type;
    private Brand brand;
    private Origin origin;
    private String desc;
    public Product() {
    }

    public Product(int id, String name, double price, String image, TypeProduct type, Brand brand, Origin origin, String desc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
        this.brand = brand;
        this.origin = origin;
        this.desc = desc;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public TypeProduct getType() {
        return type;
    }

    public void setType(TypeProduct type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDecs(String decs) {
        this.desc = decs;
    }
}
