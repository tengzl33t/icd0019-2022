package message;

import composer.SmsInvoiceComposer;
import order.Order;

public class SmsInvoiceSender implements InvoiceSender{

    @Override
    public void sendInvoiceMessage(Order order) {
        SmsInvoiceComposer invoice = new SmsInvoiceComposer(order);

        sendSms(invoice.getTitle(), invoice.getBody());
    }

    private void sendSms(String title, String contents) {
        System.out.println("Sending sms ...");
        System.out.println("Title: " + title);
        System.out.println("Body: " + contents);
    }
}
