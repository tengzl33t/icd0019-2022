package concurrent.examples;

import static concurrent.examples.Common.sleep;

public class Interrupt {

    public static void main(String[] args) {

        Runnable r1 = () -> {
            for (int i = 0; i < 5; i++) {

                System.out.print(i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        };

        Thread t1 = new Thread(r1);

        t1.start();

        sleep(1500);

        t1.interrupt();

        System.out.println("done");
    }

}

