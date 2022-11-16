package oo;

import message.EmailInvoiceSender;
import message.SmsInvoiceSender;
import order.Order;
import order.OrderRow;

public class MainForNotifier {

    public static void main(String[] args) {

        Order order = new Order("A123");
        order.setChannel("email");
        order.add(new OrderRow("CPU", 2, 100));
        order.add(new OrderRow("Motherboard", 3, 60));

        OrderNotifier notifier = new OrderNotifier();

        notifier.registerChannelNotifier("sms", new SmsInvoiceSender());
        notifier.registerChannelNotifier("email", new EmailInvoiceSender());

        notifier.notifyAbout(order);
    }

}
