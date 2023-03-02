package blackjack.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {
    private final CardPocket cardPocketUnderDrawPoint = generateUnderDrawPoint();
    private final CardPocket cardPocketOverDrawPoint = generateOverDrawPoint();


    @Test
    void 딜러의_카드가_16_이하의_점수라면_드로우_합니다() {
        final Dealer dealer = new Dealer(cardPocketUnderDrawPoint);

        assertThat(dealer.isDrawable())
                .isTrue();
    }

    @Test
    void 딜러의_카드가_17_이상의_점수라면_스테이_합니다() {
        final Dealer dealer = new Dealer(cardPocketOverDrawPoint);

        assertThat(dealer.isDrawable())
                .isFalse();
    }

    private CardPocket generateUnderDrawPoint() {
        final List<Card> cardsUnderDrawPoint = List.of(
                new Card(Shape.CLOVER, Symbol.TWO),
                new Card(Shape.HEART, Symbol.EIGHT));
        return new CardPocket(cardsUnderDrawPoint);
    }

    private CardPocket generateOverDrawPoint() {
        final List<Card> cardsOverDrawPoint = List.of(
                new Card(Shape.CLOVER, Symbol.ACE),
                new Card(Shape.HEART, Symbol.KING));
        return new CardPocket(cardsOverDrawPoint);
    }
}
