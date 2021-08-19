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

    @Test
    public void getRowWidth() {
        model.startGame(model.getDeck(), false, 7, 5);
        assertEquals(11, model.getRowWidth(4));


    }

    @Test
    public void randometest() {
        /**
         * First line takes 12 white spaces, take 7 elements
         * Each line add one more element, at most 9 rows
         * Each row has one more element than previous one;
         * So if (line = 1) return 7, getRowWidth = row - 1 + 7
         * if row = 1, pyramid = [1][7]
         * if row = 2, pyramid = [2][8]
         * if row = 3, pyramid = [3][9]
         */
        assertEquals("            J♣  .   .   5♥  .   .   7♥\n" + // 12 -> 7 e -> 1
                "          9♠  10♦ .   Q♥  10♥ .   3♣  6♥\n" + //10 -> 8 e -> 2
                "        3♦  8♣  A♥  4♥  4♣  K♥  6♠  3♦  10♠\n" + // 9 e -> 3
                "      Q♦  5♥  J♠  9♦  9♠  7♦  3♠  8♣  4♠  5♣\n" + // 10 e -> 4
                "    A♥  K♣  3♠  8♥  A♠  9♥  3♣  Q♣  3♥  4♣  10♥\n" + // 11 e -> 5
                "  8♦  Q♦  K♦  2♠  A♠  6♣  8♠  A♦  3♥  6♣  2♠  7♣\n" + // 12 e -> 6
                "K♦  J♠  J♦  5♠  Q♣  8♠  10♠ 2♥  K♠  5♠  9♦  7♦  Q♠\n" + // 13 e -> 7 // 14 e -> 8 //15 e -> 9
                "Draw: K♥, 5♦, 10♣", "");
    }
}
