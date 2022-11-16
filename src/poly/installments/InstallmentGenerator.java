package poly.installments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator {

    public List<Installment> generateRowsFor(Integer amount, LocalDate periodStart, LocalDate periodEnd) {
        // if first amount value is 3 or less
        if (amount < 4){
            return List.of(new Installment(amount, periodStart));
        }

        List<Installment> result = new ArrayList<>();
        int months = periodEnd.compareTo(periodStart)+1;
        int commonAmount = amount / months;

        while (commonAmount < 3){
            months--;
            commonAmount = amount / months;
        }

        for (int i = 0; i < months; i++) {
            result.add(new Installment(commonAmount, getNextMonthStart(periodStart, i)));
            amount -= commonAmount;
        }

        if (amount > 0){
            fillReverse(result, amount);
        }

        return result;
    }

    private LocalDate getNextMonthStart(LocalDate month, int plus){
        // if it is first month
        if (plus == 0){
            return month;
        }else {
            return month.plusMonths(plus).withDayOfMonth(1);
        }
    }

    private List<Installment> fillReverse(List<Installment> result, int amount){
        while (amount > 0) {
            // run list backwards, skip 0 index value
            for (int i = result.size() - 1; i >= 1; i--) {
                Installment old = result.get(i);
                Installment nw = new Installment(old.amount + 1, old.date);
                result.set(i, nw);

                amount--;
            }
        }
        return result;
    }

}
