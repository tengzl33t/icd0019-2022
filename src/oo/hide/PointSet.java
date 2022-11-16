package oo.hide;

public class PointSet {
    public int capacity;
    private Point[] pointArray;

    public PointSet(int capacity) {
        this.capacity = capacity;
        pointArray = new Point[capacity];

    }

    public PointSet() {
        pointArray = new Point[1];
    }

    public boolean contains(Point point){
        if (pointArray.length > 0) {
            for (Point p : pointArray) {
                if (point.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(Point point) {
        int index = 0;

//        System.out.println(Arrays.toString(pointArray));
        if (!contains(point)){

            if (pointArray.length == this.size()) { // if array is full
                Point[] result = new Point[pointArray.length * 2];

                for (Point p : pointArray) {
                    result[index] = p;
                    index++;
                }

                result[index] = point;

                pointArray = result;
            }else{ // if it has available space
                for (Point p : pointArray) {
                    if (p == null){
                        pointArray[index] = point;
                        break;
                    }
                    index++;
                }
            }
        }
    }

    public int size() {
        int counter = 0;
        for (Point p : pointArray){
            if (p != null){
                counter++;
            }
        }
        return counter;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet();

        for (Point p : pointArray){
            if (!other.contains(p)){
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        int counter = 0;
        for (Point p : pointArray){
            if (p == null){
                break;
            }
            if (counter > 0 && counter < this.size()){
                result += ", ";
            }
            result += p.toString();
            counter++;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PointSet)){
            return false;
        }

        PointSet other = (PointSet) obj;

        if (other.size() != this.size()){
            return false;
        }

        for (Point p : other.pointArray){
            if (p == null){
                continue;
            }
            if (!this.contains(p)){
                return false;
            }
        }
        return true;
    }

    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet();

        for (Point p : pointArray){
            if (other.contains(p)){
                result.add(p);
            }
        }
        return result;
    }
}
