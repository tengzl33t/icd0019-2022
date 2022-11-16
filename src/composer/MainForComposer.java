package composer;

import order.Order;
import order.OrderRow;

public class MainForComposer {

    public static void main(String[] args) {

        Order order = new Order("A123");
        order.add(new OrderRow("CPU", 2, 100));
        order.add(new OrderRow("Motherboard", 3, 60));

        SmsInvoiceComposer composer = new SmsInvoiceComposer(order);

        System.out.println(composer.getTitle());
        System.out.println(composer.getBody());

        EmailInvoiceComposer composerEmail = new EmailInvoiceComposer(order);

        System.out.println(composerEmail.getTitle());
        System.out.println(composerEmail.getBody());
    }

}
