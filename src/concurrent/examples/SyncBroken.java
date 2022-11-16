package concurrent.examples;

public class SyncBroken {

    private static int a = 1;
    private static int b = 0;

    public static void main(String[] args) {

        int tmp = a;

        a = b;

        b = tmp;

        System.out.println(a);
        System.out.println(b);
    }

}

