package restclient.restclient;

import java.util.List;

public class ShoppingCarts {
    private List<ShoppingCart> carts;

    public ShoppingCarts() {
    }

    public ShoppingCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "ShoppingCarts{" +
                "carts=" + carts +
                '}';
    }
}
