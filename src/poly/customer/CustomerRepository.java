package poly.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private Map<String, AbstractCustomer> customers;

    public CustomerRepository() {
        customers = initializeCustomers();
    }

    private Map<String, AbstractCustomer> initializeCustomers(){
        try {
            List<String> records = Files.readAllLines(Paths.get(FILE_PATH));

            return records.stream().
                    map(line -> line.split(";")).map(this::createCustomer).collect(Collectors.toMap(AbstractCustomer::getId, Function.identity()));

        }catch (IOException e){
            throw new RuntimeException("file not found");
        }
    }

    private AbstractCustomer createCustomer(String[] item){
        String type = item[0];
        String id = item[1];
        String name = item[2];
        int bonusPoints = Integer.parseInt(item[3]);
        LocalDate date = null;


        if (item.length == 5){
            date = LocalDate.parse(item[4]);
        }

        if (type.equals("GOLD")){
            return new GoldCustomer(id, name, bonusPoints, date);
        }else if (type.equals("REGULAR")){
            return new RegularCustomer(id, name, bonusPoints, date);
        }

        throw new RuntimeException("Error in createCustomer()!");
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        customers = initializeCustomers();

        if (customers.containsKey(id)){
            return Optional.of(customers.get(id));
        }
        return Optional.empty();
    }



    public void remove(String id) {
        customers.remove(id);
        overwriteFile();
    }

    private void overwriteFile(){
        String result = "";

        List<AbstractCustomer> values = customers.values().stream().sorted(Comparator.comparing(AbstractCustomer::getId)).toList();

        for (AbstractCustomer value : values) {
            String type = value instanceof GoldCustomer ? "GOLD" : "REGULAR";
            String date = value.getLastOrderDate() != null ? String.valueOf(value.getLastOrderDate()) : "";
            result += type + ";" + value.getId() + ";" + value.getName() + ";" + value.getBonusPoints() + ";" + date + "\n";
        }

        try {
            Files.writeString(Paths.get(FILE_PATH), result);
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }

    }

    public void save(AbstractCustomer customer) {
        customers.put(customer.getId(), customer);
        overwriteFile();
    }

    public int getCustomerCount() {
        return customers.size();
    }
}
