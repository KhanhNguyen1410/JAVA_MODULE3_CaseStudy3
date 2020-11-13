package model;

public class TotalCart {
    private String pName;
    private double pPrice;
    private int oQuantity;
    private double total;

    public TotalCart() {
    }

    public TotalCart(String pName, double pPrice, int oQuantity, double total) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.oQuantity = oQuantity;
        this.total = total;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public int getoQuantity() {
        return oQuantity;
    }

    public void setoQuantity(int oQuantity) {
        this.oQuantity = oQuantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
