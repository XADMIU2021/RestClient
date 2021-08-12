package restclient.restclient;

import java.util.List;

public class Order {
    private String id;
    private List<OrderLine> orderLines;
    private String status;
    private String timeStamp;
    private String customerId;

    public Order() {
    }

    public Order(List<OrderLine> orderLines, String status, String timeStamp, String customerId) {
        this.orderLines = orderLines;
        this.status = status;
        this.timeStamp = timeStamp;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderLines=" + orderLines +
                ", status='" + status + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
