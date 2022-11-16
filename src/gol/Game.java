package gol;

import java.util.Arrays;

public class Game {

    private boolean[][] dataMatrix  = new boolean[15][20];

    public void markAlive(int row, int col) {
        dataMatrix[row][col] = true;
    }

    public boolean isAlive(int row, int col) {
        try {
            return dataMatrix[row][col];
        }catch (ArrayIndexOutOfBoundsException ignored){}

        return false;
    }

    public void toggle(int row, int col) {
        dataMatrix[row][col] = !dataMatrix[row][col];
    }

    public Integer getNeighbourCount(int row, int col) {

        int result = 0;

        // instead of {-1, 0, 1} array
        for (int num0 = -1; num0 <= 1; num0++) {
            for (int num1 = -1; num1 <= 1; num1++) {
                if (num0 == 0 && num1 == 0){
                    continue;
                }
                if (isAlive(row+num0, col+num1)){
                    result += 1;
                }
            }
        }

        return result;
    }

    public void nextFrame() {

        // clone to compare with initial matrix
        boolean[][] innerMatrix = Arrays.stream(dataMatrix).map(boolean[]::clone).toArray(boolean[][]::new);

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 20; col++) {
                innerMatrix[row][col] = nextState(dataMatrix[row][col], getNeighbourCount(row, col));
            }
        }
        dataMatrix = innerMatrix;
    }

    public void clear() {
        dataMatrix  = new boolean[15][20];
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        // if alive and have 2 or 3 neighbours OR if dead and have 3 neighbours
        // it survives OR became alive
        return isLiving && (neighborCount == 2 || neighborCount == 3) || !isLiving && neighborCount == 3;
    }
}
