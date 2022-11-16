package monolith;

import order.Order;
import order.OrderRow;

public class MainForMonolith {

    public static void main(String[] args) {

        Order order = new Order("A123");
        order.setChannel("sms");
        order.add(new OrderRow("CPU", 2, 100));
        order.add(new OrderRow("Motherboard", 3, 60));

        MonolithNotifier notifier = new MonolithNotifier();

        notifier.notifyAbout(order);
    }

}
