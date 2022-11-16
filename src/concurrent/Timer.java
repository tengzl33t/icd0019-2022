package concurrent;

public class Timer {

    private Long start = System.currentTimeMillis();

    public String getPassedTime() {
        double passedMills = System.currentTimeMillis() - start;
        return String.format("%s sek", passedMills / 1000);
    }
}
