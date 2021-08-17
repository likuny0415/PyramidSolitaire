package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.GameStatus;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.util.*;

public abstract class AbstractPyramidSolitaire implements PyramidSolitaireModel<Card> {

    protected Integer score;
    protected Integer numberOfRows;
    protected Integer numberOfDrawCards;
    protected Card[][] pyramidCardsPile;
    protected List<Card> drawCardsPile;
    protected List<Card> deckOfCards;
    protected Boolean[][] cardsInPyramidCanRemoved;
    protected GameStatus gameStatus;


    public AbstractPyramidSolitaire() {
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


    protected void calculateScoreInPyramid() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <= i; j++) {
                score += pyramidCardsPile[i][j].getValue();
            }
        }
    }

    protected void exposeCards() {
        for (int i =0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfRows; j++) {
                if (i == numberOfRows - 1) {
                    cardsInPyramidCanRemoved[i][j] = true;
                }
            }
        }
    }

    protected void putCardsIntoDrawCardsPile() {
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
    protected boolean noDuplicateCards(List<Card> deck) {
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
        isValidGame();
        validCardPosition(row1, card1);
        validCardPosition(row2, card2);
        Card c1 = getCardAt(row1, card1);
        Card c2 = getCardAt(row2, card2);
        int sum = sumOfTwoCard(c1, c2);
        if (sum != 13) {
            throw new IllegalArgumentException("Sum is not 13, can't be removed");
        } else {
            this.score -= 13;
            changeCardStatus(row1, card1);
            changeCardStatus(row2, card2);
        }
    }

    protected void changeCardStatus(int row, int card) {
        pyramidCardsPile[row][card] = null;
        cardsInPyramidCanRemoved[row][card] = false;
        changeCardStatusAround(row, card);
    }

    protected void changeCardStatusAround(int row, int card) {
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

    protected int sumOfTwoCard(Card c1, Card c2) {
        return c1.getValue() + c2.getValue();
    }

    protected void validCardPosition(int row, int card) {
        if (row < 0 || row > pyramidCardsPile.length) {
            throw new IllegalArgumentException("Invalid card position, row out of bound");
        }
        if (card < 0 || card > getRowWidth(row)) {
            throw new IllegalArgumentException("Invalid card position, card out of bound");
        }
        if (pyramidCardsPile[row][card] == null) {
            throw new IllegalArgumentException("This position is null, removed or error");
        }
        if (cardsInPyramidCanRemoved[row][card] == false) {
            throw new IllegalArgumentException("This card is not exposed, can't be removed");
        }
    }


    @Override
    public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
        isValidGame();
        validCardPosition(row, card);
        Card temp = getCardAt(row,card);
        int sum = temp.getValue();
        if (sum != 13) {
            throw new IllegalArgumentException("This card is not K, can't be removed");
        } else {
            changeCardStatus(row, card);
            this.score -=13;
        }
    }

    protected void isValidGame() {
        if (gameStatus == GameStatus.NOT_STARTED) {
            throw new IllegalStateException("Game has not yet started");
        }
    }

    @Override
    public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalArgumentException, IllegalStateException {
        isValidGame();
        validCardPosition(row, card);
        isValidDrawCard(drawIndex);

        Card drawCard = getDrawCards().get(drawIndex);
        Card cardInPyramid = getCardAt(row, card);
        int sum = drawCard.getValue() + cardInPyramid.getValue();
        if (sum != 13) {
            throw new IllegalArgumentException("Invalid card remove, choose another draw card or card in pyramid");
        } else {
            changeCardStatus(row, card);
            changeDrawCardInPile(drawIndex);
            this.score -= cardInPyramid.getValue();
        }
    }

    protected void changeDrawCardInPile(int drawIndex) {
        drawCardsPile.remove(drawIndex);
        drawCardsPile.add(deckOfCards.remove(0));
    }

    protected void isValidDrawCard(int drawIndex) {
        if (drawIndex < 0 || drawIndex > getNumDraw()) {
            throw new IllegalArgumentException("Invalid draw card position, choose another one");
        }
    }

    @Override
    public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
        isValidGame();
        isValidDrawCard(drawIndex);
        changeDrawCardInPile(drawIndex);
    }

    @Override
    public int getNumRows() {
        return this.numberOfRows;
    }

    @Override
    public int getNumDraw() {
        List<Card> temp = getDrawCards();
        return temp.size();
    }

    @Override
    public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {
        isValidGame();
        if (row < 0 || row > numberOfRows) {
            throw new IllegalArgumentException("Invalid row, out of bound");
        }
        return row + 1;
    }

    @Override
    public boolean isGameOver() throws IllegalStateException {
        isValidGame();
        if (getScore() == 0) {
            return true;
        }
        if (getNumDraw() == 0 || deckOfCards == null) {
            return true;
        }
        return false;
    }

    @Override
    public int getScore() throws IllegalStateException {
        isValidGame();
        return this.score;
    }

    @Override
    public Card getCardAt(int row, int card) throws IllegalArgumentException, IllegalStateException {
        isValidGame();
        if (row < 0 || row > numberOfRows || card < 0 || card > getRowWidth(row)) {
            throw new IllegalArgumentException("Invalid card position");
        }
        return pyramidCardsPile[row][card];
    }

    @Override
    public List<Card> getDrawCards() throws IllegalStateException {
        isValidGame();
        return drawCardsPile;
    }
}
