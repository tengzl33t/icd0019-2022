package bonus.dna;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class SequenceFinderTest {

    @Test
    public void findsSimpleSequences() {

        int minSubsequenceLength = 2;
        int maxErrorsBetween = 0;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences("--12--", "...12");

        System.out.println(found);

        assertThat(found, contains("12"));
    }

    @Test
    public void findsBrokenSequences() {

        int minSubsequenceLength = 2;
        int maxErrorsBetween = 1;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences("--12345--", "...12845.");

        assertThat(found, contains("12345"));
    }

    @Test
    public void findsMatchingSequences() {

        var generated = new TestDataGenerator().generate();

        String firstSequence = generated.getFirst();
        String secondSequence = generated.getSecond();
        Set<String> subsequencesToBeFound = generated.getSubsequences();

        int minSubsequenceLength = 15;
        int maxErrorsBetween = 3;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences(firstSequence, secondSequence);

        assertThat(found, equalTo(subsequencesToBeFound));
    }
}
