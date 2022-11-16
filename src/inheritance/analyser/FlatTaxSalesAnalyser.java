package inheritance.analyser;

import java.util.List;

public final class FlatTaxSalesAnalyser extends AbstractSalesAnalyser {

    public FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }
    @Override
    protected boolean isDifferentiated(){ // to check if it has differentiated rate
        return false;
    }
}
