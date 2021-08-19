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
        } else if (numRows < 0 || numRows > 7) {
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
        this.pyramidCardsPile = new Card[numRows][numRows + 6]; // need to be changed
        this.cardsInPyramidCanRemoved = new Boolean[numRows][numRows + 6]; // need to be changed

        // initiate cardsInPyramidCanRemoved
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <= i + 7; j++) {
                cardsInPyramidCanRemoved[i][j] = false;
            }
        }
        this.score = 0;
        // put cards in the pyramid
        putCardsIntoPyramid();
        // put cards into the draw cards pile
        putCardsIntoDrawCardsPile();
        // expose the cards that can be removed
        exposeCards();
        // Get score;
        calculateScoreInPyramid();
    }

    @Override
    protected void putCardsIntoPyramid() {
        
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
