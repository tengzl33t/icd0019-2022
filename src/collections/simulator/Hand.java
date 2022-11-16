package collections.simulator;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand implements Iterable<Card> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    private List<Card> getSorted(){
        List<Card> result = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.mapping(x -> x, Collectors.toList())))
                .entrySet()
                .stream().sorted(Comparator.comparing(c -> -c.getValue().size()))
                .flatMap(x -> x.getValue().stream())
                .toList();

//        System.out.println(result);

        return result;
    }

    private HandType checkOnePair() {
        if (cards.size() >= 2) {
            Card card0 = getSorted().get(0);
            Card card1 = getSorted().get(1);

            for (int i = 3; i < cards.size(); i++) {
                if (card0.compareTo(getSorted().get(i)) == 0 || card1.compareTo(getSorted().get(i)) == 0){
                    return null;
                }
                if (getSorted().get(i).compareTo(getSorted().get(i-1)) == 0){
                    return null;
                }
            }

            if (card0.compareTo(card1) == 0){
                return HandType.ONE_PAIR;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    private HandType checkTwoPairs() {

        if (cards.size() >= 4) {
            Card card0 = getSorted().get(0);
            Card card1 = getSorted().get(1);
            Card card2 = getSorted().get(2);
            Card card3 = getSorted().get(3);

            if (cards.size() == 5){
                Card card4 = getSorted().get(4);

                if (card4.compareTo(card0) == 0 || card4.compareTo(card2) == 0){
                    return null;
                }
            }

            if (card0.compareTo(card1) == 0 && card2.compareTo(card3) == 0){
                return HandType.TWO_PAIRS;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    private HandType checkTrips() {
        if (cards.size() >= 3) {
            Card card0 = getSorted().get(0);
            Card card1 = getSorted().get(1);
            Card card2 = getSorted().get(2);

            if (card0.compareTo(card1) == 0 && card0.compareTo(card2) == 0){
                return HandType.TRIPS;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    private HandType checkFourOfAKind() {
        if (getSorted().size() >= 4) {
            Card card0 = getSorted().get(0);
            Card card1 = getSorted().get(1);
            Card card2 = getSorted().get(2);
            Card card3 = getSorted().get(3);

            if (card0.compareTo(card1) == 0 && card0.compareTo(card2) == 0 && card0.compareTo(card3) == 0){
                return HandType.FOUR_OF_A_KIND;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public HandType getHandType() {
        if (checkFlush() == HandType.STRAIGHT_FLUSH){
            return HandType.STRAIGHT_FLUSH;
        }else if (checkFourOfAKind() != null){
            return HandType.FOUR_OF_A_KIND;
        }else if (checkFullHouse() != null){
            return HandType.FULL_HOUSE;
        }else if (checkFlush() == HandType.FLUSH){
            return HandType.FLUSH;
        }else if (checkStraight() != null){
            return HandType.STRAIGHT;
        }else if (checkTrips() != null){
            return HandType.TRIPS;
        }else if (checkTwoPairs() != null){
            return HandType.TWO_PAIRS;
        }else if (checkOnePair() != null){
            return HandType.ONE_PAIR;
        }else{
            return HandType.HIGH_CARD;
        }

    }

    private HandType checkFullHouse(){
        if (cards.size() == 5) {
            Card card0 = getSorted().get(0);
            Card card1 = getSorted().get(1);
            Card card2 = getSorted().get(2);
            Card card3 = getSorted().get(3);
            Card card4 = getSorted().get(4);

            if (card0.compareTo(card1) == 0 && card0.compareTo(card2) == 0
                    && card3.compareTo(card4) == 0){
                return HandType.FULL_HOUSE;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    private HandType checkFlush(){
        if (cards.size() == 5) {
            Card.CardSuit suit = cards.get(0).getSuit();

            for (Card card : cards) {
                if (suit != card.getSuit()) {
                    return null;
                }
            }

            // if it's STRAIGHT at the same time
            if (checkStraight() == HandType.STRAIGHT) {
                return HandType.STRAIGHT_FLUSH;
            }

            return HandType.FLUSH;
        }else{
            return null;
        }
    }

    private HandType checkStraight(){
        if (cards.size() == 5) {
            int cardsLastIndex = cards.size() - 1;

            List<Card.CardValue> cardsList = cards.stream()
                    .map(Card::getValue).toList();
            Set<Card.CardValue> cardsSet = new HashSet<>(cardsList);


            if (cardsList.size() != cardsSet.size()) {
                return null;
            }

            // if first or last is A
            if (cards.get(0).getValue() == Card.CardValue.A || cards.get(cardsLastIndex).getValue() == Card.CardValue.A) {
                if (!(Math.abs(cards.get(0).compareTo(cards.get(cardsLastIndex))) == 9 || Math.abs(cards.get(0).compareTo(cards.get(cardsLastIndex))) == 4)) {
                    return null;
                }
            } else {

                if (!(Math.abs(cards.get(0).compareTo(cards.get(cardsLastIndex))) == 4)) {
                    return null;
                }
            }

            for (int i = 1; i < cardsLastIndex; i++) {
                Card card = cards.get(i);
                Card prevCard = cards.get(i - 1);


                // "A" is exception, because it can be high and low rank
                if (card.getValue() == Card.CardValue.A && i != cards.size() - 1) {
                    Card nextCard = cards.get(i + 1);

                    // if card before and after "A" have different ranks
                    if (Math.abs(prevCard.compareTo(nextCard)) == 11) {
                        return null;
                    }
                }

                // if prev card is not lower or higher by one
                if (!(Math.abs(prevCard.compareTo(card)) == 12 || Math.abs(prevCard.compareTo(card)) == 1)) {
                    return null;
                }
            }
            return HandType.STRAIGHT;
        }else{
            return null;
        }
    }


    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
