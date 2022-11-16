package composer;

import order.Order;
import order.OrderRow;

public abstract class AbstractInvoiceComposer {
    protected Order order;

    protected AbstractInvoiceComposer(Order order) {
        this.order = order;
    }

    protected abstract String getTitle();

    protected abstract String getBody();

    protected Integer getOrderTotal(Order order) {
        int total = 0;
        for (OrderRow orderRow : order.getOrderRows()) {
            total += orderRow.getPrice() * orderRow.getQuantity();
        }
        return total;
    }

}
