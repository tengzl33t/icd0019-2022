package message;

import composer.EmailInvoiceComposer;
import order.Order;

public class EmailInvoiceSender implements InvoiceSender {

    @Override
    public void sendInvoiceMessage(Order order) {
        EmailInvoiceComposer invoice = new EmailInvoiceComposer(order);

        sendEmail(invoice.getTitle(), invoice.getBody());
    }

    private void sendEmail(String title, String contents) {
        System.out.println("Sending email ...");
        System.out.println("Title: " + title);
        System.out.println("Body: " + contents);
    }

}

