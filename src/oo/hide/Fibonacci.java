package oo.hide;

public class Fibonacci {
    int f0 = 0;
    int f1 = 1;

    public int nextValue() {
        int result = f0;
        int sum = f1 + f0;

        f0 = f1;
        f1 = sum;

        return result;
    }

}
