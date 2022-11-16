package junit;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Code {
    public static void main(String[] args) {
        int[] arr = {5, 5, 5};
        System.out.println(getValueFromList(arr, 5));

    }

    public static boolean isSpecial(int candidate) {
        return candidate % 11 < 4;
    }

    public static int longestStreak(String inputString) {
        int index = 0;
        int counter = 0;
        int max = 0;

        char[] charArray = inputString.toCharArray();

        for (char c : charArray){

            if (index > 0){
                char previousChar = (char) Array.get(charArray, index-1); // get previous char

                if (previousChar == c){
                    counter++; // if streak continues
                }else{
                    counter = 1; // if new streak starts
                }

            }else{
                counter++; // if first char in list
            }

            if (counter > max){
                max = counter; // change max to current maximum value
            }

            index++;
        }
        return max;
    }

    public static Character mode(String inputString) {
        if (inputString != null) {
            if (inputString.toCharArray().length == 0) {
                return null;
            }
        }else{
            return null;
        }

        HashMap<String, Integer> valuesDict = new HashMap<>(); // Dictionary

        int max = 0;
        char maxChar = Character.MIN_VALUE; // starting position for char, like "" in string

        for (char i : inputString.toCharArray()){
            String iString = Character.toString(i);
            if (!valuesDict.containsKey(iString)){
                int chrCount = getCharacterCount(inputString, i);

                valuesDict.put(iString, chrCount); // creates new record in dict if not found

                if (chrCount > max) {
                    max = chrCount;
                    maxChar = i;
                }

            }
        }

        return maxChar;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {

        if (allCharacters.toCharArray().length == 0){
            return 0;
        }

        int counter = 0;

        for (char c : allCharacters.toCharArray()){
            if (c == targetCharacter){
                counter++;
            }
        }

        return counter;
    }


    public static int[] removeDuplicates(int[] integers) {
        int[] result = {};

        for (int i : integers) {
            if (!ifValueInList(result, i)){
                result = appendValueToList(result, i);
            }
        }

        return result;
    }

    private static int getValueFromList(int[] integers, int index){
        int ind = 0;

        for (int i : integers) {
            if (index == ind){
                return i;
            }
            ind++;
        }

        return 0;
    }

    private static int[] appendValueToList(int[] integers, int value){
        int ind = 0;
        int listNewLength = integers.length + 1;
        int[] result = new int[listNewLength];

        for (int i : integers){
            result[ind] = i;
            ind++;
        }

        result[ind] = value;
        return result;
    }

    private static boolean ifValueInList(int[] integers, int value){
        for (int i : integers){
            if (i == value){
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] noDupList = removeDuplicates(integers);
        int result = 0;

        for (int i : noDupList){
            result += i;
        }
        return result;
    }

}
