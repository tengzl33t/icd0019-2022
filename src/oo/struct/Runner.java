package oo.struct;

import org.junit.Test;

public class Runner {

    @Test
    public void coordinatesAsArrays() {

        int[][] trianglePoints = {{1, 1, 0}, {5, 1, 0}, {3, 7, 1}};

        for (int[] each : trianglePoints) {
            System.out.println(each[2]);
        }
    }

    @Test
    public void coordinatesAsObjects() {
        Point3D pointSet0 = new Point3D(1, 1, 0);
        Point3D pointSet1 = new Point3D(5, 1, 0);
        Point3D pointSet2 = new Point3D(3, 7, 1);
        Point3D[] trianglePoints = {pointSet0, pointSet1, pointSet2};

        for (Point3D each : trianglePoints) {
            System.out.println(each.getZ());
        }
    }
}
