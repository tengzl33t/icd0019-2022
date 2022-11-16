package order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private String orderNumber;
    private List<OrderRow> orderRows;
    private String channel;

    public Order() {}

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void add(OrderRow orderRow) {
        if (orderRows == null) {
            orderRows = new ArrayList<>();
        }

        orderRows.add(orderRow);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderRow> getOrderRows() {
        return orderRows;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderRows=" + orderRows +
                '}';
    }
}
