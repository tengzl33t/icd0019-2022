package oo;

import message.InvoiceSender;
import order.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderNotifier {

    private Map<String, InvoiceSender> senders = new HashMap<>();

    public void notifyAbout(Order order) {
        InvoiceSender sender = senders.get(order.getChannel());

        if (sender == null){
            throw new IllegalStateException("no sender for: " + order.getChannel());
        }

        sender.sendInvoiceMessage(order);

    }

    public void registerChannelNotifier(String channelKey, InvoiceSender messageSender) {
        senders.put(channelKey, messageSender);
    }

}
