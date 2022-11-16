package collections.benchmark;

import oo.hide.Timer;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Runner {

    @Test
    public void benchmarkDifferentImplementations() {

        Set<Integer> set1 = new MySet();
        Set<Integer> set2 = new TreeSet<>();
        Set<Integer> set3 = new HashSet<>();


        // add 30000 elements to set
        extracted(set1);
        extracted(set2);
        extracted(set3);
    }

    private void extracted(Set<Integer> set1) {
        Timer timer = new Timer();

        for (int i = 0; i < 30000; i++){
            set1.add(i);
        }

        System.out.println(timer.getPassedTime());
    }

}
