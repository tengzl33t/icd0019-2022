package junit.sales;

public class SalesRecordsFormatted {
    private int index;
    private String productId;
    private int profit;

    public SalesRecordsFormatted(String productId, int profit) {
        this.productId = productId;
        this.profit = profit;
    }

    public String getProductId() {
        return productId;
    }

    public int getProfit() {
        return profit;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int value) {
        index = value;
    }
}
