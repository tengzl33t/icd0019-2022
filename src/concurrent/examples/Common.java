package concurrent.examples;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Common {

    public static void waitTillAllDone(ExecutorService service) {
        service.shutdown();
        try {
            while (!service.isTerminated()) {
                service.awaitTermination(1L, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doEasyWork() {
        for (int i = 0; i < 10; i++) {
            sleep(100);
        }
    }

    public static void doHardWork() {
        Random r = new Random(0);

        IntStream.range(1, 2_000_000)
                .mapToObj(i -> r.nextInt(i))
                .sorted()
                .findFirst()
                .get();
    }

    public static void doWork() {
        doHardWork();
    }


}
