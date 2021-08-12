package restclient.restclient;

public class CartLine {
    private String productNumber;
    private int quantity;

    public CartLine() {
    }

    public CartLine(String productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartLine{" +
                "productNumber='" + productNumber + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
