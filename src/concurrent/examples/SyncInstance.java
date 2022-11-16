package concurrent.examples;

public class SyncInstance {

    private int x = 1;
    private int y = 0;

    public static void main(String[] args) {
        new SyncInstance().swapVariables();
    }

    private void swapVariables() {
        synchronized (this) {
            int tmp = x;

            x = y;

            y = tmp;
        }
    }

    private synchronized void swapVariables2() {
        int tmp = x;

        x = y;

        y = tmp;
    }

}

