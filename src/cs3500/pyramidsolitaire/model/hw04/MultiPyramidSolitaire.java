package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.GameStatus;

import java.util.*;

public class MultiPyramidSolitaire extends BasicPyramidSolitaire {

    public MultiPyramidSolitaire() {
        super();
    }

    @Override
    public List<Card> getDeck() {
        List<Card> deckOfCards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deckOfCards.add(new Card(i));
            deckOfCards.add(new Card(i));
        }
        return deckOfCards;
    }

    @Override
    public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw) throws IllegalArgumentException {
        if (deck.size() != 104 || noDuplicateCards(deck)) {
            throw new IllegalArgumentException("Invalid deck");
        } else if (numRows < 1 || numRows > 8) {
            throw new IllegalArgumentException("Invalid number of rows");
        } else if (numDraw < 0) {
            throw new IllegalArgumentException("Invalid number of draw cards");
        } else if (shuffle) {
            Collections.shuffle(deck);
        }
        this.numberOfRows = numRows;
        this.numberOfDrawCards = numDraw;
        this.deckOfCards = deck;
        this.gameStatus = GameStatus.RUNNING;
        this.pyramidCardsPile = new Card[numRows][getRowWidth(numRows)]; // need to be changed
        this.cardsInPyramidCanRemoved = new Boolean[numRows][getRowWidth(numRows)]; // need to be changed

        // initiate cardsInPyramidCanRemoved
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < i + 7; j++) {
                cardsInPyramidCanRemoved[i][j] = false;
            }
        }
        this.score = 0;
        // put cards in the pyramid
        makeTopHalf();
        makeBottomHalf();
        // put cards into the draw cards pile
        putCardsIntoDrawCardsPile();
        // expose the cards that can be removed
        exposeCards();
    }

    @Override
    public void exposeCards() {
        for (int i = 0; i < getRowWidth(numberOfRows); i++) {
            cardsInPyramidCanRemoved[numberOfRows - 1][i] = true;
        }
    }

    private void makeTopHalf() {
        for (int row = 0; row < (getNumRows() / 2); row++) {
            for (int col = 0; col < getRowWidth(row); col++) {
                // the 3 peaks of the pyramid
                if (row == 0 && col % (getNumRows() / 2) == 0) {
                    Card card = this.deckOfCards.remove(0);
                    pyramidCardsPile[row][col] = card;
                    score += card.getValue();
                } else if (row > 0 && col > 0 && this.pyramidCardsPile[row - 1][col - 1] != null) { // fills in cards
                    // supporting peaks on one side
                    Card card = this.deckOfCards.remove(0);
                    pyramidCardsPile[row][col] = card;
                    score += card.getValue();
                } else if (row > 0 && this.pyramidCardsPile[row - 1][col] != null) {
                    Card card = this.deckOfCards.remove(0);
                    pyramidCardsPile[row][col] = card;
                    score += card.getValue();
                } else { //not part of pyramid if not within conditions above
                    this.pyramidCardsPile[row][col] = null;
                }
            }
        }
    }

    private void makeBottomHalf() {
        for (int row = getNumRows() / 2; row < getNumRows(); row++) {
            for (int col = 0; col < getRowWidth(row); col++) {
                Card card = this.deckOfCards.remove(0);
                pyramidCardsPile[row][col] = card;
                score += card.getValue();
            }
        }
    }

    @Override
    protected boolean noDuplicateCards(List<Card> deck) {
        Map<Card, Integer> map = new HashMap<>();
        for (Card card : deck) {
            if (map.containsKey(card)) {
                map.put(card, map.get(card) + 1);
            } else {
                map.put(card, 1);
            }
        }
        for (Card c : map.keySet()) {
            if (map.get(c) != 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getRowWidth(int row) {
        if (gameStatus != GameStatus.RUNNING) {
            throw new IllegalStateException("Game has not started");
        }
        if (row < 0 || row > numberOfRows) {
            throw new IllegalArgumentException("Invalid row number");
        }
        return row + 7;
    }

}
