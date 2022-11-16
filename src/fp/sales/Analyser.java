package fp.sales;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {

        Double result = repository.getEntries().stream().mapToDouble(Entry::getAmount).sum();
        return result;
    }

    public Double getSalesByCategory(String category) {
        Double result = repository.getEntries().stream().filter(c -> c.getCategory().equals(category)).mapToDouble(Entry::getAmount).sum();
        return result;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        Double result = repository.getEntries().stream().filter(c -> c.getDate().isAfter(start) && c.getDate().isBefore(end)).mapToDouble(Entry::getAmount).sum();
        return result;
    }

    public String mostExpensiveItems() {
        var result = repository.getEntries().stream().sorted(Collections.reverseOrder(Comparator.comparing(Entry::getAmount))).
                limit(3).sorted(Comparator.comparing(Entry::getProductId)).map(Entry::getProductId).collect(Collectors.joining (", "));

        return result;
    }

    public String statesWithBiggestSales() {

        var groups = repository.getEntries().stream().collect(Collectors.groupingBy(Entry::getState, Collectors.summingDouble(Entry::getAmount))).entrySet();
        String result = groups.stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                map(Map.Entry::getKey).limit(3).collect(Collectors.joining (", "));

        return result;
    }
}
