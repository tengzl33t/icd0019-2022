package intro;

public class Program {

    public static void main(String[] args) {

        System.out.println(asDecimal("11001101")); // 205

        System.out.println(asString(205));

        System.out.println(pow(5,0));
    }

    public static String asString(int input) {
        return Integer.toBinaryString(input);
    }

    public static int asDecimal(String input) {
        return Integer.parseInt(input, 2);
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.

        int result = 1;
        int pw_count = power;

        if (power == 0){
            return 1;
        }else{
            while (pw_count != 0){
                result = result * arg;

                pw_count--;
            }
            return result;
        }
    }
}
