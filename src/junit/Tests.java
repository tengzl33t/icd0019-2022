package junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void equalityExamples() {
        assertEquals(1, 1);
        assertNotEquals(1, 2);

        int x2 = 1;
        int y2 = 1;
        assertEquals(x2, y2);

        int x = 128;
        int y = 128;
        assertEquals(x, y);

        assertSame("abc", "abc");
        assertSame("abc", "a" + "bc");

        String a = "a";
        assertNotSame("abc", a + "bc");
        assertEquals("abc", a + "bc");
    }

    @Test
    public void assertThatAndAssertEqualsExample() {
//        1 + 2 on 3
//        1 + 2 ei ole 4
//        new int[] {1, 2, 3} on new int[] {1, 2, 3}
//        new int[] {1, 2, 3} ei ole new int[] {1, 2}

        assertEquals(1 + 2, 3);
        assertThat(1 + 2 , is(3));
        assertThat(1 + 2 , is(not(4)));
        assertThat(1 + 2 , is(equalTo(3)));

        assertThat(new int[] {1, 2, 3}, is(new int[] {1, 2, 3}));
        assertThat(new int[] {1, 2, 3} , is(not(new int[] {1, 2})));
    }

    @Test
    public void findsSpecialNumbers() {
        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));

        assertTrue(Code.isSpecial(11));
        assertFalse(Code.isSpecial(15));

        assertTrue(Code.isSpecial(36));
        assertFalse(Code.isSpecial(37));
    }

    @Test
    public void findsLongestStreak() {
        assertEquals(Code.longestStreak(""), 0);
        assertEquals(Code.longestStreak("a"), 1);
        assertEquals(Code.longestStreak("abbcccaaaad"), 4);
        // other test cases for longestStreak() method
    }

    @Test
    public void findsModeFromCharactersInString() {
        assertEquals(Code.getCharacterCount("aaabbbccc", 'a'), 3);
        assertEquals(Code.getCharacterCount("aaabbbccc", 'b'), 3);
        assertEquals(Code.getCharacterCount("abcgfryhvbaabc", 'b'), 3);
        assertEquals(Code.getCharacterCount("aagfrbbbaqcaaa", 'a'), 6);

        assertThat(Code.mode("cbbc"), is('c'));
        assertThat(Code.mode("abcb"), is('b'));
        assertNull(Code.mode(""));
        assertNull(Code.mode(null));

        // other test cases for mode() method
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(100, 0, 3, 100, 0, 4, 562, 4)),
                is(arrayOf(100, 0, 3, 4, 562)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2)), is(6));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }

}
