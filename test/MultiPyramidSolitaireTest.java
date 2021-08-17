import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;

public class MultiPyramidSolitaireTest {
    PyramidSolitaireModel model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);

    @Test (expected = IllegalArgumentException.class)
    public void startGameException() {
        List<Card> deckOfCards = model.getDeck();
        deckOfCards.remove(0);
        model.startGame(deckOfCards, false, 7, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startGameException1() {
        List<Card> deckOfCards = model.getDeck();
        deckOfCards.remove(0);
        deckOfCards.add(new Card(10));
        assertEquals(deckOfCards.size(), 104);
        model.startGame(deckOfCards, false, 7, 3);
    }
}
