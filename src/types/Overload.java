package types;

public class Overload {

    public static void main(String[] args) {

        long xlong = -9999999999L;
        long ylong = 9999999999L;
        System.out.println(add(xlong, ylong)); // add method with long

        int x = 5;
        int y = -5;
        System.out.println(add(x, y)); // add method with int

        String strx = "5";
        String stry = "-5";
        System.out.println(add(strx, stry));
    }

    public static long add(long x, long y) {
        System.out.println("Adding longs");
        return x + y;
    }

    public static int add(int x, int y) {
        System.out.println("Adding integers");
        return x + y;
    }

    public static long add(String x, String y) {
        System.out.println("Adding numbers from strings");
        return Long.parseLong(x) + Long.parseLong(y);
    }

}
