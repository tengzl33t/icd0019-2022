package concurrent.examples;

public class SyncGlobal {

    private static final Object LOCK = new Object();

    private static int a = 1;
    private static int b = 0;

    public static void main(String[] args) {

        synchronized (LOCK) {
            int tmp = a;

            a = b;

            b = tmp;
        }

        System.out.println(a);
        System.out.println(b);
    }
}

