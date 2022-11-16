package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    private Entry createEntry(String[] item){
        Double amountFix = Double.valueOf(item[5].replace(",", "."));

        Entry entry = new Entry();
        entry.setDate(LocalDate.parse(item[0], formatter));
        entry.setState(item[1]);
        entry.setProductId(item[2]);
        entry.setCategory(item[3]);
        entry.setAmount(amountFix);

        return entry;
    }

    public List<Entry> getEntries() {

        // reads lines form the file and creates entry objects for each line.

        try {
            List<String> records = Files.readAllLines(Paths.get(FILE_PATH));

            List<Entry> entries = records.stream().skip(1).
                    map(line -> line.split("\t")).map(this::createEntry).toList();

            return entries;
        }catch (IOException e){
            throw new RuntimeException("file not found");
        }

    }

}
