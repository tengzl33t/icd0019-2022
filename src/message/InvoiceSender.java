package message;

import order.Order;

public interface InvoiceSender {

    void sendInvoiceMessage(Order order);
}
