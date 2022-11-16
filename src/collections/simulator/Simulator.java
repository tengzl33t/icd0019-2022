package collections.simulator;

import java.util.*;
import static collections.simulator.Helpers.getHandWithRandomSuit;

public class Simulator {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private double iterations;

    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    private Hand getRandomHand() {
        try {
            String[] arr = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

            String randomValues = "";
            for (int i = 0; i < 5; i++) {
                int randomNum = new Random().nextInt(arr.length);
                randomValues += arr[randomNum];
            }

            return getHandWithRandomSuit(randomValues);

        }catch (IllegalArgumentException e){
            return getRandomHand();
        }
    }

//    private Hand getRandomHand() {
//        Hand hand = new Hand();
//        ArrayList<Card> cards = new ArrayList<>();
//        while (cards.size() < 5){
//            Random random = new Random();
//            Card.CardValue[] values = Card.CardValue.values();
//            Card.CardValue value = values[random.nextInt(values.length)];
//            Card.CardSuit[] suits = Card.CardSuit.values();
//            Card.CardSuit suit = suits[random.nextInt(suits.length)];
//            Card card = new Card(value, suit);
//            if (cards.contains(card)) {
//                continue;
//            }
//            hand.addCard(card);
//            cards.add(card);
//        }
//        return hand;
//    }


    public Map<HandType, Double> calculateProbabilities() {


        Map<HandType, Double> result = new HashMap<>();

        for (HandType value : HandType.values()) {
            result.put(value, 0.0);
        }

        int successIterations = 0;

        for (int i = 0; i < iterations; i++) {
            Hand random = getRandomHand();
            System.out.println("HAND: " + random + " TYPE: " + random.getHandType());
            result.put(random.getHandType(), result.get(random.getHandType())+1);

//            System.out.println("hand: " + random + " type: " + random.getHandType());
//            System.out.println(i);
        }

        for (HandType handType : result.keySet()) {
            double resultPercent = (result.get(handType) / iterations) * 100;
            result.put(handType, resultPercent);
        }

//        Hand random = getRandomHand();
//        System.out.println(random + " " + random.getHandType());

//
        return result;

    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        throw new RuntimeException("not implemented yet");
    }

}
