package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {
        LinkedList<List<String>> result = new LinkedList<>();

        for (char character : input.toCharArray()){

            String symbol = String.valueOf(character);

            if (result.isEmpty()){
                result.add(new LinkedList<>(List.of(symbol)));

            } else if (result.getLast().contains(symbol)){
                result.getLast().add(symbol);

            }else{
                result.add(new LinkedList<>(List.of(symbol)));
            }
        }

        return result;
    }


}
