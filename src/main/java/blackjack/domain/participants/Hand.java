package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.game.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this(Collections.emptyList());
    }

    public Hand addCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new Hand(newCards);
    }

    public Score getScore() {
        int countOfAce = countAce();
        Score scoreOfCards = calculateMinimumScore();

        for (int i = 0; i < countOfAce; i++) {
            scoreOfCards = scoreOfCards.plusTenIfNotBusted();
        }
        return scoreOfCards;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(this::isAce)
                .count();
    }

    private boolean isAce(Card card) {
        return card.getDenomination() == Denomination.ACE;
    }

    private Score calculateMinimumScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(new Score(0), Score::sum);
    }

    public boolean isBust() {
        return getScore().isOverThanMax();
    }

    public boolean isBlackjack() {
        return getScore().isMax() && cards.size() == 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

}
