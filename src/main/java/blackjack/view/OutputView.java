package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultType;
import blackjack.domain.participants.Score;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String OUTPUT_DISTRIBUTE_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장을 나누었습니다.";
    private static final String OUTPUT_DEALER_STATUS_MESSAGE = System.lineSeparator() + "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DELIMITER_BETWEEN_CARDS = ", ";
    private static final String DELIMITER = ": ";
    private static final String DEALER = "딜러";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과: ";

    public static void printInitialDistributionMessage(final Map<String, List<Card>> playerNameToCards) {
        final String playerNames = String.join(DELIMITER_BETWEEN_CARDS, playerNameToCards.keySet());
        System.out.printf((OUTPUT_DISTRIBUTE_MESSAGE) + System.lineSeparator(), DEALER, playerNames);
    }

    private static String convertCard(final Card card) {
        final String convertedSymbol = OutputViewSymbol.from(card.getSymbol())
                .getPrintSymbol();
        final String convertedShape = OutputViewShape.from(card.getShape())
                .getPrintShape();

        return convertedSymbol + convertedShape;
    }

    public static void printDealerCardDrawMessage(final int dealerDrawPoint) {
        System.out.printf(OUTPUT_DEALER_STATUS_MESSAGE + System.lineSeparator(), DEALER, dealerDrawPoint);
    }

    private static void printPlayerResult(final String name, final ResultType resultType) {
        System.out.println(name + DELIMITER + OutputViewResultType.from(resultType)
                .getPrintResultType());
    }

    public void printError(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printInitialCards(final Card dealerCard, final Map<String, List<Card>> playerNameToCards) {
        printInitialDistributionMessage(playerNameToCards);
        printInitialDealerCard(dealerCard);
        printInitialPlayerCard(playerNameToCards);
        System.out.println();
    }

    private void printInitialDealerCard(final Card card) {
        System.out.println(DEALER + DELIMITER + convertCard(card));
    }

    private void printInitialPlayerCard(final Map<String, List<Card>> playerNameToCards) {
        playerNameToCards.forEach((playerName, playerCards) ->
                System.out.println(playerName + CARD + DELIMITER + convertCards(playerCards)));
    }

    private String convertCards(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::convertCard)
                .collect(Collectors.joining(DELIMITER_BETWEEN_CARDS));
    }

    private void printPlayersResult(final Map<String, ResultType> playersResult) {
        playersResult.forEach(OutputView::printPlayerResult);
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<Card> playerCards) {
        final String cards = convertCards(playerCards);
        System.out.println(playerName + DELIMITER + cards);
    }

    public void printFinalStatusOfDealer(final List<Card> dealerCards, final int dealerScore) {
        final String cards = convertCards(dealerCards);
        System.out.println();
        System.out.println(DEALER + " " + CARD + DELIMITER + cards + RESULT + dealerScore);
    }

    public void printFinalStatusOfPlayers(final Map<Map<String, List<Card>>, Score> statusOfPlayers) {
        statusOfPlayers.forEach(this::printFinalStatusOfPlayer);
        System.out.println();
    }

    //수정
    private void printFinalStatusOfPlayer(final Map<String, List<Card>> playerNameToCards, final Score playerScore) {
        playerNameToCards.forEach((playerName, playerCards) ->
                System.out.println(playerName + DELIMITER + convertCards(playerCards) + RESULT + playerScore.getValue()));
    }

    public void printFinalResult(final Map<ResultType, Integer> dealerResult, final Map<String, ResultType> playerResult) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printPlayersResult(playerResult);
    }

    private void printDealerResult(final Map<ResultType, Integer> dealerResult) {
        System.out.print(DEALER + DELIMITER);
        printDealerResultByType(dealerResult, ResultType.WIN);
        printDealerResultByType(dealerResult, ResultType.TIE);
        printDealerResultByType(dealerResult, ResultType.LOSE);
        System.out.println();
    }

    private void printDealerResultByType(final Map<ResultType, Integer> dealerResult, final ResultType resultType) {
        if (dealerResult.containsKey(resultType)) {
            System.out.print(dealerResult.get(resultType) + OutputViewResultType.from(resultType)
                    .getPrintResultType());
        }
    }

}
