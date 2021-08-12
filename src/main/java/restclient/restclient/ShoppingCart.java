package restclient.restclient;

import java.util.List;

public class ShoppingCart {
    private String id;
    private String cartNumber;
    private List<CartLine> cartLines;
    private String customerId;

    public ShoppingCart() {
    }

    public ShoppingCart(String cartNumber, List<CartLine> cartLines, String customerId) {
        this.cartNumber = cartNumber;
        this.cartLines = cartLines;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", cartNumber='" + cartNumber + '\'' +
                ", cartLines=" + cartLines +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
