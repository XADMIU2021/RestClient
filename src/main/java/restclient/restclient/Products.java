package restclient.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Products {
    List<Product> products;

    public Products() {
    }

    public Products(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
