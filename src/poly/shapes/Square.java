package poly.shapes;

public class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

//    public int getSide() {
//        return side;
//    }

    public double getArea() {
        return Math.pow(side, 2);
    }
}
