package inheritance.analyser;

import java.util.List;

public sealed abstract class AbstractSalesAnalyser
        permits DifferentiatedTaxSalesAnalyser, FlatTaxSalesAnalyser, TaxFreeSalesAnalyser {

    List<SalesRecord> salesRecords;
    public static final Double TAX_RATE = 1.2;
    public static final Double REDUCED_RATE = 1.1;

    protected AbstractSalesAnalyser(List<SalesRecord> records) {
        salesRecords = records;
    }

    protected Double getTotalSales() {
        double result = 0;
        for (SalesRecord sale : salesRecords){
            int profit = sale.getItemsSold() * sale.getProductPrice();

            if (isDifferentiated() && sale.hasReducedRate()){
                result += profit / getReducedRate();
            }else{
                if (getTaxRate() != 0){
                    result += profit / getTaxRate();
                }else{
                    result += profit;
                }
            }
        }
        return result;
    }

    protected Double getTotalSalesByProductId(String id) {
        double result = 0;
        for (SalesRecord sale : salesRecords){
            int profit = sale.getItemsSold() * sale.getProductPrice();

            if (sale.getProductId().equals(id)){
                if (getTaxRate() != 0){
                    result += profit / getTaxRate();
                }else{
                    result += profit;
                }
            }
        }
        return result;
    }

    protected String getIdOfMostPopularItem() {
        int counter = 0;
        String result = "";

        for (SalesRecord sale : salesRecords){
            if (!result.equals(sale.getProductId())) {
                if (sale.getItemsSold() > counter) {
                    counter = sale.getItemsSold();
                    result = sale.getProductId();
                }
            }else{ // else here to sum itemsSold with same id.
                counter += sale.getItemsSold();
            }

        }

        return result;
    }

    protected String getIdOfItemWithLargestTotalSales() {
        Double counter = 0.0;
        String result = "";

        for (SalesRecord sale : salesRecords){
            Double profit = getTotalSalesByProductId(sale.getProductId());
            if (!result.equals(sale.getProductId()) && profit > counter) {
                counter = profit;
                result = sale.getProductId();
            } // don't need else here because profit already check all same results.

        }

        return result;
    }

    protected Double getTaxRate(){
        return TAX_RATE;
    }

    private Double getReducedRate(){
        return REDUCED_RATE;
    }

    protected abstract boolean isDifferentiated();

}
