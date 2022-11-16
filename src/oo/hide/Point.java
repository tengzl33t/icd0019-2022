package oo.hide;

import java.util.Objects;

public class Point {

    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point other)) {
            return false;
        }

        return Objects.equals(x, other.x)
                && Objects.equals(y, other.y);
    }
}
