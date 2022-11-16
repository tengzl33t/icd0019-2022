package concurrent.examples;

public class Interference {

    public static void main(String[] args) throws InterruptedException {
        new Interference().run();
    }

    private int c = 0;

    private void run() throws InterruptedException {

        Runnable r1 = () -> {
            for (int i = 0; i < 10000; i++) {
                c++;
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 10000; i++) {
                c--;
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c);
    }

}
