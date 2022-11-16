package exceptions.validate;

public class MinimumElement {

    public static Integer minimumElement(int[] integers) {
//        if (integers == null || integers.length == 0) {
//            return null;
//        }

        try {
            int minimumElement = integers[0];

            for (int current : integers) {
                if (current < minimumElement) {
                    minimumElement = current;
                }
            }

            return minimumElement;
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

}
