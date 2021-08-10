import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class BasicPyramidSolitaireTest {
    PyramidSolitaireModel m = new BasicPyramidSolitaire();

    @Test
    public void getDeck() {
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> temp = m.getDeck();
        assertEquals(temp.size(), 52);
        assertEquals(temp.get(0), new Card(1, "♠"));
        assertEquals(temp.get(51), new Card(13, "♣"));
    }

    @Test
    public void startGame1() {
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        m.startGame(m.getDeck(), false, 7,2);
        assertEquals(m.getNumRows(), 7);
        assertEquals(m.getScore(), 185);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException0() {
        // invalid deck
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> temp = m.getDeck();
        temp.remove(0);
        temp.add(new Card(10));
        m.startGame(temp, true, 6, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException1() {
        // invalid deck
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(10));
        m.startGame(deck, true, 7, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException2() {
        // invalid rows
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(10));
        m.startGame(m.getDeck(), true, -1, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException3() {
        // invalid rows
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(10));
        m.startGame(m.getDeck(), true, 8, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException4() {
        // invalid number of draws cards
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(10));
        m.startGame(m.getDeck(), true, 5, -1);
    }

    @Test (expected = IllegalStateException.class)
    public void removeStateException() {
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        m.remove(1,1,2,2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeArgumentException1() {
        m.startGame(m.getDeck(), false, 7,2);
        // Card is not exposed
        m.remove(5,2,6,3);
    }

    @Test
    public void remove1() {
        m.startGame(m.getDeck(), false, 7,3);

    }

}
