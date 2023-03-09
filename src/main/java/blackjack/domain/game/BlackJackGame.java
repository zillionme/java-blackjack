package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Score;
import blackjack.view.DrawCommand;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    private BlackJackGame(final GameParticipants gameParticipants, final Deck deck) {
        this.gameParticipants = gameParticipants;
        this.deck = deck;
    }

    public static BlackJackGame of(final List<String> playerNames, final Deck deck) {
        return new BlackJackGame(new GameParticipants(Players.from(playerNames)), deck);
    }

    public void distributeInitialCards() {
        gameParticipants.distributeInitialCards(deck);
    }

    public Card findDealerInitialCard() {
        return gameParticipants.findDealerCard()
                .get(0);
    }

    public Map<String, List<Card>> findPlayerNameToCards() {
        return gameParticipants.findPlayerNameToCards();
    }

    public List<String> findPlayerNames() {
        return gameParticipants.findPlayerNames();
    }


    public boolean isPlayerDrawable(final String playerName) {
        return gameParticipants.isPlayerDrawable(playerName);
    }

    public void drawCardOfPlayerByName(final String playerName, final DrawCommand drawCommand) {
        if (drawCommand == DrawCommand.DRAW) {
            gameParticipants.drawCardOfPlayerByName(playerName, deck);
        }
    }

    public List<Card> findCardsOfPlayerByName(final String playerName) {
        return gameParticipants.findCardsOfPlayerByName(playerName);
    }

    public int findDealerDrawPoint() {
        return gameParticipants.findDealerDrawPoint();
    }

    public int findDealerDrawCount() {
        return gameParticipants.findDealerDrawCount(deck);
    }

    public List<Card> findDealerCard() {
        return gameParticipants.findDealerCard();
    }

    public Score findDealerScore() {
        return gameParticipants.findDealerScore();
    }

    //플레이어 이름-카드-점수 출력 수정본1: 문제점 결과를 플레이어마다 따로 출력함.
    public Score findScoreOfPlayerByName(final String playerName) {
        return gameParticipants.findScoreOfPlayerByName(playerName);
    }

    public ResultOfGame findResultOfGame() {
        return gameParticipants.findResultOfGame();
    }

}
