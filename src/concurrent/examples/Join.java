package concurrent.examples;

public class Join {

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> System.out.println(1);
        Runnable r2 = () -> System.out.println(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("done");
    }

}

