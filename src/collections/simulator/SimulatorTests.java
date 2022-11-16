package collections.simulator;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Map;

import static collections.simulator.Helpers.getSuitedHand;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimulatorTests {


    @Test
    public void calculatesProbabilitiesUsingSimulation() {

//        Simulator simulator = new Simulator(3e6);
        Simulator simulator = new Simulator(100000);

        Map<HandType, Double> map = simulator.calculateProbabilities();

        // https://en.wikipedia.org/wiki/Poker_probability#Frequency_of_5-card_poker_hands

        System.out.println(map);

        assertThat(map.get(HandType.HIGH_CARD), is(closeTo(50.118)));
        assertThat(map.get(HandType.ONE_PAIR), is(closeTo(42.257)));
        assertThat(map.get(HandType.TWO_PAIRS), is(closeTo(4.754)));
        assertThat(map.get(HandType.TRIPS), is(closeTo(2.113)));
    }

    @Test
    public void calculatesWinningsOddsForHoldEmHand() {

        Hand hand1 = getSuitedHand("9h9s");
        Hand hand2 = getSuitedHand("AdKc");

        Simulator simulator = new Simulator(6e4);

        double winningOdds = simulator.getWinningOdds(hand1, hand2);

        // https://www.cardplayer.com/poker-tools/odds-calculator/texas-holdem

        assertThat(winningOdds, Matchers.closeTo(55.53, 0.4));
    }

    private Matcher<Double> closeTo(double value) {
        double precision = 0.1;

        return Matchers.closeTo(value, precision);
    }
}
