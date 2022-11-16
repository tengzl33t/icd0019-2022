package oo.hide;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

public class PointSetTests {

    @Test
    public void pointSetKeepsTrackOfPoints() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));
        set.add(new Point(1, 2));


        assertThat(set.size(), is(3));

        set.add(new Point(2, 1));

        assertThat(set.size(), is(3));

        assertTrue(set.contains(new Point(1, 1)));
        assertTrue(set.contains(new Point(1, 2)));
        assertFalse(set.contains(new Point(1, 3)));

        assertThat(set.toString(), is("(1, 1), (2, 1), (1, 2)"));
    }

    @Test
    public void pointSetSupportsEqualityTesting() {
        assertThat(getSet(), is(getSet()));

//        System.out.println("getSet with 1 point (1, 1): " + getSet(new Point(1, 1)));
//        System.out.println("getSet without points: " + Arrays.toString(getSet().getArray()));
        assertThat(getSet(new Point(1, 1)), is(not(getSet())));

        assertThat(getSet(new Point(1, 1)),
                is(not(getSet(new Point(1, 2)))));

        assertThat(getSet(new Point(1, 1), new Point(1, 2)),
                is(getSet(new Point(1, 2), new Point(1, 1))));
    }

    @Test
    public void pointSetSupportsSubtractingAnotherSet() {
        PointSet a = getSet(new Point(1, 1), new Point(1, 2));
        PointSet b = getSet(new Point(1, 1), new Point(1, 3));

        PointSet remainder = a.subtract(b);

        assertThat(a, is(getSet(new Point(1, 1), new Point(1, 2))));

        assertThat(remainder, is(getSet(new Point(1, 2))));
    }

    @Test
    public void pointSetSupportsIntersectionOperation() {
        PointSet a = getSet(new Point(1, 1), new Point(1, 2));
        PointSet b = getSet(new Point(1, 1), new Point(1, 3));

        PointSet intersection = a.intersect(b);

        assertThat(a, is(getSet(new Point(1, 1), new Point(1, 2))));

        assertThat(intersection, is(getSet(new Point(1, 1))));
    }

    @Test
    public void setGrowsWhenThereIsNoMoreRoom() {
        PointSet set = new PointSet(2);
//        System.out.println(Arrays.toString(set.getSet()));
//        System.out.println(set.getSet().length);

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));

        assertThat(getInternalArray(set).length, is(2));

        set.add(new Point(3, 1));

        assertThat(getInternalArray(set).length, is(4));
    }

    private PointSet getSet(Point... points) {
        PointSet set = new PointSet();
        for (Point point : points) {
            set.add(point);
        }

        return set;
    }

    @SuppressWarnings("PMD")
    private Point[] getInternalArray(PointSet set) {

        Field[] fields = set.getClass().getDeclaredFields();

        List<Field> integerArrayFields = Arrays.stream(fields)
                .filter(field -> field.getType()
                        .equals(Point[].class))
                .toList();

        if (integerArrayFields.isEmpty()) {
            fail("PointSet should have a field of type Point[]");
        }

        if (integerArrayFields.size() > 1) {
            fail("PointSet should have just one field of type Point[]");
        }

        integerArrayFields.get(0).setAccessible(true);

        try {
            return (Point[]) integerArrayFields.get(0).get(set);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
