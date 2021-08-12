package restclient.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
    @Autowired
    private RestOperations restTemplate;
    String serverUrl = "http://localhost:8080";


    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // ADD PRODUCTS (LAPTOP & TV) --works
        Stock laptopStock = new Stock(100);
        Product laptop = new Product("001", "Dell Laptop", "Dell Laptop", 1250.00, laptopStock);
        Stock tvStock = new Stock(70);
        Product tv = new Product("002", "Samsung TV", "Samsung TV", 456.50, tvStock);
        restTemplate.postForLocation(serverUrl + "/product", tv);
        ResponseEntity<Product> laptopCreatedResult = restTemplate.postForEntity(serverUrl + "/product", laptop, Product.class);
        Product createdLaptop = laptopCreatedResult.getBody();

        this.log("Created 2 products");

        // PRINT ALL PRODUCTS -- works
        this.printAllProducts();

        // MODIFY LAPTOP PRICE to (1100.00) -- works
        laptop.setPrice(1100.00);
        restTemplate.postForLocation(serverUrl + "/product/{productId}", laptop, createdLaptop.getId());

        // PRINT ALL PRODUCTS AGAIN -- works
        this.printAllProducts();

        // Create Customer -- works
        Address address = new Address("1000 N 4th street", "Fairfield", "52557");
        Customer customer = new Customer("John", "Doe", "johndoe@gmail.com", "+16324123900", address);
        ResponseEntity<Customer> customerCreatedResult = restTemplate.postForEntity(serverUrl + "/customer", customer, Customer.class);
        Customer createdCustomer = customerCreatedResult.getBody();
        this.log("Customer john john doe created");

        // ADD CUSTOMER ID ON HEADER FOR SUBSEQUENT CALLS
        // send the customer id with header key "Customer-ID"
        HttpHeaders headers = new HttpHeaders();
        headers.set("Customer-ID", createdCustomer.getId());
        this.log("'Customer-ID' header set to " + createdCustomer.getId());

        // ADD PRODUCT TO SHOPPING CART (COMMAND SERVICE) -- works
        String cartNumber = "900";
        CartLine laptopCartLine = new CartLine(laptop.getProductNumber(), 10);
        CartLine tvCartLine = new CartLine(tv.getProductNumber(), 5);
        HttpEntity<CartLine> laptopRequest = new HttpEntity<CartLine>(laptopCartLine, headers);
        HttpEntity<CartLine> tvRequest = new HttpEntity<CartLine>(tvCartLine, headers);
        restTemplate.postForLocation(serverUrl + "/shopping-command/add-to-cart/" + cartNumber, laptopRequest);
        restTemplate.postForLocation(serverUrl + "/shopping-command/add-to-cart/" + cartNumber, tvRequest);
        this.log("10 Laptop and 5 TV added to CART");

        // wait until query service gets the update (EVENTUAL CONSISTENCY)
        Thread.sleep(1000);

        // VIEW SHOPPING CART --works
        this.viewShoppingCart(headers);

        // REMOVE Laptop from shopping cart
        HttpEntity request = new HttpEntity(headers);
        restTemplate.exchange(serverUrl + "/shopping-command/" + cartNumber + "/" + laptop.getProductNumber(), HttpMethod.DELETE, request, String.class);
        this.log("Laptop removed from the cart");

        // UPDATE tv quantity in cart from 5 to 10
        tvCartLine = new CartLine(tv.getProductNumber(), 10);
        tvRequest = new HttpEntity<CartLine>(tvCartLine, headers);
        restTemplate.postForLocation(serverUrl + "/shopping-command/update-cart/" + cartNumber, tvRequest);
        this.log("TV quantity updated to 10 in the cart");

        // VIEW SHOPPING CART AGAIN -- works
        this.viewShoppingCart(headers);

        // CHECK OUT CUSTOMER'S SHOPPING CART -- works
        ResponseEntity<Order> orderResult = restTemplate.postForEntity(serverUrl + "/shopping-query/" + cartNumber + "/checkout", request, Order.class);
        Order order = orderResult.getBody();
        this.log("Shopping cart with number " + cartNumber + " is checked out by customer");

        // VIEW ORDERS --works
        this.viewOrder(headers);

        // PLACE ORDER -- works
        restTemplate.postForLocation(serverUrl + "/order/" + order.getId() + "/place", request);
        this.log("Order with id " + order.getId() + " is placed by the customer");

        // VIEW ORDERS AFTER PLACED --works
        this.viewOrder(headers);
    }

    public void log(String message) {
        System.out.println("#########################################");
        System.out.println(message);
        System.out.println("#########################################");
    }

    // TODO debug
    public void printAllProducts() {
        ResponseEntity<Products> allProductsResult = restTemplate.getForEntity(serverUrl + "/product", Products.class);
        Products allProducts = allProductsResult.getBody();
        this.log("All products");
        System.out.println(allProducts);
    }

    public void viewShoppingCart(HttpHeaders headers) {
        HttpEntity viewRequest = new HttpEntity(headers);
        ResponseEntity<ShoppingCarts> shoppingCartResult = restTemplate.exchange(serverUrl + "/shopping-query/carts/", HttpMethod.GET, viewRequest,ShoppingCarts.class);
        ShoppingCarts carts = shoppingCartResult.getBody();
        this.log("Shopping Cart view");
        System.out.println(carts);
    }

    public void viewOrder(HttpHeaders headers) {
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<Orders> orderResult = restTemplate.exchange(serverUrl + "/order", HttpMethod.GET, request,Orders.class);
        this.log("Orders View");
        System.out.println(orderResult.getBody());
    }

    @Bean
    RestOperations restTemplate() {
        return new RestTemplate();
    }
}
