package oo.hide;

public class Counter {
    public int start;
    public int step;
    public int currentValue = 0;

    public Counter(int start, int step) {
        this.start = start;
        this.step = step;
    }

    public int nextValue() {
        if(currentValue == 0){
            currentValue += start;
        }else{
            currentValue += step;
        }
        return currentValue;
    }
}
