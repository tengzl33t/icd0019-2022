package fp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Numbers {

    private List<Integer> numbers = Arrays.asList(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {

        List<Integer> oddNumbers = numbers.stream().
                filter(n -> n % 2 != 0)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {
        List<Integer> result = numbers.stream().
                filter(n -> n % 2 != 0).filter(n -> n > 10)
                .toList();

        System.out.println(result);
    }

    @Test
    public void findsSquaredOddNumbers() {
        List<Integer> result = numbers.stream().
                filter(n -> n % 2 != 0).map(x -> x*x)
                .toList();

        System.out.println(result);
    }

    @Test
    public void findsSumOfOddNumbers() {

        Integer result = numbers.stream().
                filter(n -> n % 2 != 0).mapToInt(x -> x).sum();

        System.out.println(result);
    }

}
