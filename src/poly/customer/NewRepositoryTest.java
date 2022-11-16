package poly.customer;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;

public class NewRepositoryTest {

    private CustomerRepository repository = new CustomerRepository();

    @Test
    public void canAddCustomers() {
        String randomId = UUID.randomUUID().toString();

        RegularCustomer customer = new RegularCustomer(
                randomId, "David", 0, LocalDate.now());

        repository.save(customer);

        assertThat(repository.getCustomerById(randomId).get(), is(customer));
    }

    @Test
    public void canDeleteCustomers() {
        RegularCustomer customer = new RegularCustomer(
                "c4", "David", 0, LocalDate.now());

        repository.save(customer);

        repository.remove("c4");

        assertFalse(repository.getCustomerById("c4").isPresent());
    }

    @Test
    public void canUpdateCustomers() {

        RegularCustomer customer = new RegularCustomer(
                "c4", "David", 0, LocalDate.now());

        repository.save(customer);

        int countBefore = repository.getCustomerCount();

        repository.save(customer);

        assertThat(repository.getCustomerCount(), is(countBefore));
        assertThat(repository.getCustomerById("c4").get(), is(customer));
    }

    @Test
    public void customerIsChangedOnlyWhenSaved() {
        String randomId = UUID.randomUUID().toString();

        repository.save(new RegularCustomer(
                randomId, "David", 0, LocalDate.now())); // записывает кастомера в лист и в файл

        AbstractCustomer customer = repository.getCustomerById(randomId).get(); // обновляет лист и получает кастомера

        assertThat(customer.getBonusPoints(), is(0)); // у него 0 бонусов

        customer.collectBonusPointsFrom(new Order(200, LocalDate.now())); // создает кастомеру В ЛИСТЕ ордер

        assertThat(customer.getBonusPoints(), is(not(0)));

        AbstractCustomer loaded = repository.getCustomerById(randomId).get(); // переписывает ЛИСТ данными из ФАЙЛА и получает кастомера по ИД

        System.out.println(customer);
        System.out.println(loaded);

        assertThat(loaded.getBonusPoints(), is(0)); // у него 0, потому что 
    }

    @Test
    public void repositoryShouldNotUseStaticFields() {
        List<Field> fieldsNotAllowed = Arrays.stream(CustomerRepository.class.getDeclaredFields())
                .filter(field -> isStatic(field.getModifiers()))
                .filter(field -> !"FILE_PATH".equals(field.getName()))
                .toList();

        assertThat(fieldsNotAllowed, is(empty()));
    }

    private boolean isStatic(int modifierValue) {
        return (modifierValue & Modifier.STATIC) > 0;
    }
}
