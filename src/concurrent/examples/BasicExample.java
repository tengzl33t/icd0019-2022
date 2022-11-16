package concurrent.examples;

public class BasicExample {

    public static void main(String[] args) {

        new MyThread(1).start();
        new MyThread(2).start();
        new MyThread(3).start();

        System.out.println("done");
    }

    private static class MyThread extends Thread {

        private Integer number;

        public MyThread(Integer number) {
            this.number = number;
        }

        @Override
        public void run() {
            System.out.println(
                    String.format("Thread %d is running", number));
        }
    }
}

