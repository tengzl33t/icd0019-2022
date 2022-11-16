package composer;

import order.Order;

public class SmsInvoiceComposer extends AbstractInvoiceComposer {

    public SmsInvoiceComposer(Order order) {
        super(order);
    }

    public String getTitle() {
        return "Teavitus arvest";
    }

    public String getBody() {
        return "Teile on koostatud arve summas: " + getOrderTotal(order) + " eurot";
    }

}
