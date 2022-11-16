package concurrent.examples;

import concurrent.Timer;

import java.util.stream.IntStream;

public class Streams {

    public static void main(String[] args) {

        Timer timer = new Timer();

        long count = IntStream.range(0, 200_000)
                .parallel()
                .filter(n -> isPrime(n))
                .count();

        System.out.println(count);

        System.out.println(timer.getPassedTime());

    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        return !IntStream
                .rangeClosed(2, number / 2)
                .anyMatch(i -> number % i == 0);
    }

}

