package concurrent.examples;

import concurrent.Timer;

import java.util.ArrayList;
import java.util.List;

import static concurrent.examples.Common.*;

public class ParallelWithoutPool {

    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> doHardWork());

            thread.start();

            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n" + timer.getPassedTime());
    }

}
