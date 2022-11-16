package monolith;

import order.Order;
import order.OrderRow;

public class MonolithNotifier {

    public static final String EMAIL_LINE_FORMAT = "%-20s%-20s%s\r\n";

    public void notifyAbout(Order order) {

        if ("sms".equals(order.getChannel())) {
            String title = createSmsInvoiceTitle();
            String body = createSmsInvoiceBody(order);

            sendSms(title, body);

        } else if ("email".equals(order.getChannel())) {

            String title = createEmailInvoiceTitle(order);
            String body = createEmailInvoiceBody(order);

            EmailSender sender = new EmailSender(title, body);

            sender.send();

        } else {
            throw new IllegalStateException(
                    "no notifier for channel: " + order.getChannel()) ;
        }

    }

    private String createSmsInvoiceTitle() {
        return "Teavitus arvest";
    }

    private String createSmsInvoiceBody(Order order) {
        return "Teile on koostatud arve summas: " + getOrderTotal(order) + " eurot";
    }

    private String createEmailInvoiceTitle(Order order) {
        return "Arve Nr. " + order.getOrderNumber();
    }

    private String createEmailInvoiceBody(Order order) {
        StringBuilder sb = new StringBuilder();

        sb.append("Arve Nr. ");
        sb.append(order.getOrderNumber());
        sb.append("\r\n");
        sb.append(String.format(EMAIL_LINE_FORMAT, "Toode", "Kogus", "Hind"));
        for (OrderRow orderRow : order.getOrderRows()) {
            sb.append(formatEmailOrderRowLine(orderRow));
        }
        sb.append("Kokku: ");
        sb.append(getOrderTotal(order));
        sb.append(" eurot");

        return sb.toString();
    }

    private String formatEmailOrderRowLine(OrderRow orderRow) {
        return String.format(EMAIL_LINE_FORMAT,
                orderRow.getItemName(),
                orderRow.getQuantity(),
                orderRow.getPrice());
    }

    private Integer getOrderTotal(Order order) {
        int total = 0;
        for (OrderRow orderRow : order.getOrderRows()) {
            total += orderRow.getPrice() * orderRow.getQuantity();
        }
        return total;
    }

    private void sendSms(String title, String contents) {
        System.out.println("Sending sms ...");
        System.out.println("Title: " + title);
        System.out.println("Body: " + contents);
    }

    private static class EmailSender {
        private String title;
        private String body;

        public EmailSender(String title, String body) {
            this.title = title;
            this.body = body;
        }

        public void send() {
            System.out.println("Sending email ...");
            System.out.println("Title: " + title);
            System.out.println("Body: " + body);
        }
    }


}
