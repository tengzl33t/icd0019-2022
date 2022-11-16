package bonus.dna;

import generics.pair.Pair;

import java.util.*;
import java.util.stream.Stream;

public class SequenceFinder {

    public final int minSequenceLength;
    public final int maxCapLength;

    public SequenceFinder(int minSequenceLength, int maxCapLength) {
        this.minSequenceLength = minSequenceLength;
        this.maxCapLength = maxCapLength;
    }

    public Set<String> findMatchingSubsequences(String firstSequence, String secondSequence) {

        List<String> A = List.of(firstSequence.split(""));
        List<String> B = List.of(secondSequence.split(""));

        for(int i = 0; i< A.size(); i++){
            System.out.println("A: " + A.get(i) + " B: " + B.get(i));
        }

        return null;
    }


}
