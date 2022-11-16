package inheritance.analyser;

import java.util.List;

public final class DifferentiatedTaxSalesAnalyser extends AbstractSalesAnalyser {

    public DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected boolean isDifferentiated(){ // to check if it has differentiated rate
        return true;
    }
}
