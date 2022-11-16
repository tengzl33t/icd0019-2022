package collections.simulator;

public enum HandType {

    HIGH_CARD, ONE_PAIR, TWO_PAIRS, TRIPS, STRAIGHT,
    FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH

    // TODO: как и ONE PAIR комбинации считаются слева направо
    // TODO: FULL_HOUSE должен содержать трипл и пару
    // TODO: TWO_PAIRS между парами не должно быть промежутков, оставшаяся карта будет с краю.
    // TODO: TRIPS вероятно невошедшие тоже с краю будут.

    // TODO: в порядке: FOUR_OF_A_KIND
}
