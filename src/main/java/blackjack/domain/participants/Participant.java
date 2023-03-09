package blackjack.domain.participants;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class Participant {

    // 상속의 문제점
    // 캡슐화가 깨진다
    // final을 메서으에 받지 않으면 상속 받아서 자기 마음대로 바꿀 수 있다.
    // 추상클래스 만든이유?
    // 조합
    // 같은 메서드 두번 호출하는 것은 느슨한 결합
    // 깊이가 깊어진다
    // 준팍 pr 댓글로 상속과 조합

    private final CardPocket cardPocket;

    protected Participant() {
        cardPocket = new CardPocket();
    }

    public void drawCard(final Card card) {
        cardPocket.addCard(card);
    }

    public Score currentScore() {
        return cardPocket.getScore();
    }

    public List<Card> getCards() {
        return cardPocket.getPossessedCards();
    }

    public boolean isBusted() {
        return cardPocket.isBusted();
    }

    public abstract boolean isDrawable();

}
