package cs3500.pyramidsolitaire.model.hw02;

import java.util.*;

// A basic implementation of Pyramid Solitaire
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card>{

    private Integer score;
    private Integer numberOfRows;
    private Integer numberOfDrawCards;
    private Card[][] pyramidCardsPile;
    private List<Card> drawCardsPile;
    private List<Card> deckOfCards;
    private Boolean[][] cardsInPyramidCanRemoved;
    private GameStatus gameStatus;


    public BasicPyramidSolitaire() {
        score = 0;
        numberOfRows = -1;
        numberOfDrawCards = -1;
        gameStatus = GameStatus.NOT_STARTED;
        this.drawCardsPile = new ArrayList<>();

    }

    @Override
    public List<Card> getDeck() {
        List<Card> deckOfCards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deckOfCards.add(new Card(i));
        }
        return deckOfCards;
    }

    @Override
    public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw) throws IllegalArgumentException {
        if (deck.size() != 52 || noDuplicateCards(deck)) {
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
        this.pyramidCardsPile = new Card[numRows][numRows];
        this.cardsInPyramidCanRemoved = new Boolean[numRows][numRows];
        // initiate cardsInPyramidCanRemoved
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <= i; j++) {
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


    private void calculateScoreInPyramid() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <= i; j++) {
                score += pyramidCardsPile[i][j].getValue();
            }
        }
    }

    private void exposeCards() {
        for (int i =0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfRows; j++) {
                if (i == numberOfRows - 1) {
                    cardsInPyramidCanRemoved[i][j] = true;
                }
            }
        }
    }

    private void putCardsIntoDrawCardsPile() {
        while (numberOfDrawCards > 0) {
            drawCardsPile.add(deckOfCards.remove(0));
            numberOfDrawCards--;
        }
    }

    public void putCardsIntoPyramid() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <= i; j++) {
                pyramidCardsPile[i][j] = deckOfCards.remove(0);
            }
        }
    }

    /**
     * Check whether the deck contain duplicate cards.
     * @param deck deck of cards
     * @return false if contain duplicate cards, true otherwise
     */
    private boolean noDuplicateCards(List<Card> deck) {
        Set<Card> set = new HashSet<>();
        for (Card c : deck) {
            if (set.contains(c)) {
                return true;
            } else {
                set.add(c);
            }
        }
        return false;
    }

    @Override
    public void remove(int row1, int card1, int row2, int card2) throws IllegalArgumentException, IllegalStateException {
        if (gameStatus == GameStatus.NOT_STARTED) {
            throw new IllegalStateException("The game has only started yet, start the game first!");
        }
        validCardPosition(row1, card1);
        validCardPosition(row2, card2);
        Card c1 = getCardAt(row1, card1);
        Card c2 = getCardAt(row2, card2);
        int sum = sumOfTwoCard(c1, c2);
        if (sum != 13) {
            throw new IllegalArgumentException("Sum is not 13, can't be removed");
        } else {
            changeCardStatus(row1, card1);
            changeCardStatus(row2, card2);
        }
    }

    private void changeCardStatus(int row, int card) {
        pyramidCardsPile[row][card] = null;
        changeCardStatusAround(row, card);
    }

    private void changeCardStatusAround(int row, int card) {
        // if card left is true, then row - 1, card - 1 is exposed
        if (card != 0) {
            if (cardsInPyramidCanRemoved[row][card - 1] == true) {
                cardsInPyramidCanRemoved[row - 1][card - 1] = true;
            }
        }
        // is card right is true, then row - 1, card is exposed;
        if (card != getRowWidth(row) - 1) {
            if (cardsInPyramidCanRemoved[row][card + 1] == true) {
                cardsInPyramidCanRemoved[row - 1][card] = true;
            }
        }
    }


    private int sumOfTwoCard(Card c1, Card c2) {
        return c1.getValue() + c2.getValue();
    }

    private void validCardPosition(int row, int card) {
        if (row < 0 || row > pyramidCardsPile.length) {
            throw new IllegalArgumentException("Invalid card position, row out of bound");
        }
        if (card < 0 || card > getRowWidth(row)) {
            throw new IllegalArgumentException("Invalid card position, card out of bound");
        }
        if (cardsInPyramidCanRemoved[row][card] == false) {
            throw new IllegalArgumentException("This card is not exposed, can't be removed");
        }
    }


    @Override
    public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public int getNumRows() {
        return this.numberOfRows;
    }

    @Override
    public int getNumDraw() {
        return this.numberOfDrawCards;
    }

    @Override
    public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {
        if (gameStatus == GameStatus.NOT_STARTED) {
            throw new IllegalStateException("Game has not yet started");
        } else if (row < 0 || row > numberOfRows) {
            throw new IllegalArgumentException("Invalid row, out of bound");
        }
        return row + 1;
    }

    @Override
    public boolean isGameOver() throws IllegalStateException {
        return false;
    }

    @Override
    public int getScore() throws IllegalStateException {
        return this.score;
    }

    @Override
    public Card getCardAt(int row, int card) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public List<Card> getDrawCards() throws IllegalStateException {
        return null;
    }
}