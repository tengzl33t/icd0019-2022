package concurrent.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static concurrent.examples.Common.sleep;

public class ResultFromPool {

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable<Integer> c1 = () -> square(2);
        Callable<Integer> c2 = () -> square(4);

        Future<Integer> f1 = pool.submit(c1);
        Future<Integer> f2 = pool.submit(c2);

        System.out.println(f1);

        System.out.println(f1.get() + f2.get());

        System.out.println(f1);

        pool.shutdown();
    }

    private static int square(int argument) {

        sleep(1000);

        return argument * argument;
    }


}
