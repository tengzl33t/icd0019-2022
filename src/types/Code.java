package types;

import java.util.*;

public class Code {

    public static void main(String[] args) {

        // Testing part
//        System.out.println(sum(numbers)); // 11
//        System.out.println(sum(new int[] {1, 2, 3})); // 6
//        System.out.println(average(new int[] {1, 2})); // 1.5
//        System.out.println(minimumElement(new int[] {1, 2})); // 1
//        System.out.println(minimumElement(new int[] {})); // null
//        System.out.println(asString(new int[] { 1, 3, -2, 9 })); // "1, 3, -2, 9"
//        System.out.println(mode("abb")); // b
//        System.out.println(mode("")); // null
//        System.out.println(mode("abccbca")); // c
//        System.out.println(squareDigits("2")); //4
//        System.out.println(squareDigits("a2b")); //a4b
//        System.out.println(squareDigits("a9b2")); //a81b4
        System.out.println(isolatedSquareCount());
    }

    public static int sum(int[] numbers) {
        int result = 0;
        for (int i : numbers) {
            result += i;
        }
        return result;
    }


    public static double average(int[] numbers) {
        int summa = 0;
        for (int i : numbers) {
            summa += i;
        }

        int numCount = numbers.length;
        return Float.valueOf(summa) / numCount;
    }

    public static Integer minimumElement(int[] integers) {

        if (integers.length == 0) {
            return null;
        }

        int minValue = integers[0];

        for (int i : integers){
            if (i < minValue){
                minValue = i;
            }
        }

        return minValue;

    }

    public static String asString(int[] elements) {
        String result = "";
        for (int i : elements){
            result += i;
            if (i != elements[elements.length - 1]) {
                result += ", ";
            }
        }
        return result;
    }

    public static Character mode(String input) {

        // Goes a little hard, so i made some comments here to not forget what is goin on.

        if (input.toCharArray().length == 0){
            return null;
        }

        HashMap<String, Integer> valuesDict = new HashMap<>(); // Dictionary

        int max = 0;
        char maxChar = Character.MIN_VALUE; // starting position for char, like "" in string

        for (char i : input.toCharArray()){
            String iString = Character.toString(i);
            if (valuesDict.containsKey(iString)){
                int newKeyValue = valuesDict.get(iString) + 1;  // add +1 to count if record is found in dict

                valuesDict.put(iString, newKeyValue);

                if(newKeyValue > max){ // Increase until max is not less than newKeyValue
                    max = newKeyValue;
                    maxChar = i; // changes with max variable, at finish goes to return
                }


            }else {
                valuesDict.put(iString, 1); // creates new record in dict if not found
            }
        }

        return maxChar;
    }

    public static String squareDigits(String s) {

        String result = "";
        for (char i : s.toCharArray()) {
            if (Character.isDigit(i)){
                int iInt = Integer.parseInt(Character.toString(i));
                result += iInt*iInt;
            }else{
                result += i;
            }
        }
        return result;
    }

    private static boolean verticalPossibility(ArrayList<HashMap<Integer, boolean[]>> listOfMaps, int rowCounter, int column){
        // check values vertically

        boolean result;
        int prevLine = rowCounter - 1;
        int nextLine = rowCounter + 1;
        boolean checkUp;
        boolean checkUpRight;
        boolean checkUpLeft;

        boolean checkDown;
        boolean checkDownRight;
        boolean checkDownLeft;

        try {
            checkUp = !listOfMaps.get(prevLine).get(column)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkUp = true;
        }

        try {
            checkUpRight = !listOfMaps.get(prevLine).get(column + 1)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkUpRight = true;
        }

        try {
            checkUpLeft = !listOfMaps.get(prevLine).get(column - 1)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkUpLeft = true;
        }

        try {
            checkDown = !listOfMaps.get(nextLine).get(column)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkDown = true;
        }

        try {
            checkDownRight = !listOfMaps.get(nextLine).get(column + 1)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkDownRight = true;
        }

        try {
            checkDownLeft = !listOfMaps.get(nextLine).get(column - 1)[0];
        }catch (Exception IndexOutOfBoundsException){
            checkDownLeft = true;
        }

        result = checkUp && checkUpRight && checkUpLeft && checkDown && checkDownRight && checkDownLeft;


        return result;
    }

    private static ArrayList<HashMap<Integer, boolean[]>> horizontalPossibility(boolean[][] matrix){
        ArrayList<HashMap<Integer, boolean[]>> listOfMaps = new ArrayList<>();

        for (boolean[] row : matrix){
            HashMap<Integer, boolean[]> rowDict = new HashMap<>();

            for (int i = 0; i < matrix.length; i++) {

                boolean possibility = false;

                if (i == 0){ // check for 1st value
                    if (row[i] && !row[i+1]) {
                        possibility = true;
                    }

                }else if (i == matrix.length - 1){ // check if last value
                    if (row[i] && !row[i-1]) {
                        possibility = true;
                    }

                }else{ // for values between first and last
                    if (row[i] && !row[i-1] && !row[i+1]) {
                        possibility = true;
                    }
                }

                boolean[] parameters = {row[i], possibility};
                rowDict.put(i, parameters);

            }

            listOfMaps.add(rowDict);

        }
        return listOfMaps;
    }

    private static int possibilities(ArrayList<HashMap<Integer, boolean[]>> listOfMaps, int lastIndex){
        int isolatedCount = 0;
        int rowCounter = 0;

        for (HashMap<Integer, boolean[]> row : listOfMaps){ // goes thru line horizontally

            for (Map.Entry<Integer, boolean[]> entry : row.entrySet()) {
                Integer key = entry.getKey();
                boolean[] value = entry.getValue();


                boolean commonCheck = value[0] && value[1]; // common check if its True and if any more Trues are near on row

                boolean moreCheck = verticalPossibility(listOfMaps, rowCounter, key); // check vertically

                if (rowCounter == 0) { // if it's first line

                    if (commonCheck && moreCheck) {
                        isolatedCount ++;
                    }

                }else if (rowCounter == lastIndex) { // if last line

                    if (commonCheck && moreCheck) {
                        isolatedCount ++;
                    }
                }else{
                    if (commonCheck && moreCheck) {
                        isolatedCount ++;
                    }
                }

            }

            rowCounter ++; // counts rows
        }

        return isolatedCount;
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();
        printMatrix(matrix);


        // list of maps with rows
        /*
        listOfMaps [{0: [], 1: [],...}, {}, {},....]
         */

        ArrayList<HashMap<Integer, boolean[]>> listOfMaps = horizontalPossibility(matrix);

        int lastIndex = matrix.length - 1;

        return possibilities(listOfMaps, lastIndex);
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
