package restclient.restclient;

public class Stock {
    private int quantity;

    public Stock() {
    }

    public Stock(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "quantity=" + quantity +
                '}';
    }
}
