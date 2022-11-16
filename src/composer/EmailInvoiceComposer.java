package composer;

import order.Order;
import order.OrderRow;

public class EmailInvoiceComposer extends AbstractInvoiceComposer {

    public EmailInvoiceComposer(Order order) {
        super(order);
    }

    public static final String EMAIL_LINE_FORMAT = "%-20s%-20s%s\r\n";

    public String getTitle() {
        return "Arve Nr. " + order.getOrderNumber();
    }

    public String getBody() {
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

}
