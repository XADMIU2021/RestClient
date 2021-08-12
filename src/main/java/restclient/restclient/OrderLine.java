package restclient.restclient;

public class OrderLine {
    private String productNumber;
    private int quantity;

    public OrderLine() {
    }

    public OrderLine(String productNumber, int quantity) {
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
        return "OrderLine{" +
                "productNumber='" + productNumber + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
