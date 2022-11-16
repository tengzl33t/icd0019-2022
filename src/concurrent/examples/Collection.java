package concurrent.examples;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        Runnable r1 = () -> {
            list.add(1);
        };

        Runnable r2 = () -> {
            try {

                for (Integer integer : list) {}

            } catch (Exception e) {
                System.out.println(e);
            }
        };

        for (int i = 0; i < 1000; i++) {
            new Thread(r1).start();
            new Thread(r2).start();
        }

        System.out.println(list.size());
    }


}
