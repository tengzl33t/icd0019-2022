package concurrent.examples;

import concurrent.Timer;

import java.util.concurrent.*;
import static concurrent.examples.Common.doHardWork;
import static concurrent.examples.Common.waitTillAllDone;

public class ParallelWithPool {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(8);

        Timer timer = new Timer();

        for (int i = 0; i < 40; i++) {
            pool.submit(() -> doHardWork());
        }

        waitTillAllDone(pool);

        System.out.println("\n" + timer.getPassedTime());

    }


}
