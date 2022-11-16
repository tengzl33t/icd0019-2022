package inheritance.analyser;

import java.util.List;

public final class TaxFreeSalesAnalyser extends AbstractSalesAnalyser {

    public TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTaxRate() {
        return 0.0;
    }

    @Override
    protected boolean isDifferentiated(){ // to check if it has differentiated rate
        return false;
    }

}
