package message;

import order.Order;
import order.OrderRow;

public class MainForMessageSender {

    public static void main(String[] args) {

        Order order = new Order("A123");
        order.add(new OrderRow("CPU", 2, 100));
        order.add(new OrderRow("Motherboard", 3, 60));

        SmsInvoiceSender sender = new SmsInvoiceSender();
        EmailInvoiceSender senderEmail = new EmailInvoiceSender();

        sender.sendInvoiceMessage(order);

        senderEmail.sendInvoiceMessage(order);

    }

}
