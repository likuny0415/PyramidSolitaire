import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PyramidSolitaireTextualViewTest {

    @Test
    public void toStringTest1() {
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        PyramidSolitaireTextualView v = new PyramidSolitaireTextualView(m);
        assertEquals(v.toString(), "");
    }

    @Test
    public void toStringTest2() {
        PyramidSolitaireModel m = new BasicPyramidSolitaire();
        m.startGame(m.getDeck(), false, 7, 4);
        PyramidSolitaireTextualView v = new PyramidSolitaireTextualView(m);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  5♥  6♥  7♥  8♥  \n" +
                "9♥  10♥  J♥  Q♥  K♥  A♦  2♦  \n" +
                "Draw: [3♦, 4♦, 5♦, 6♦]");
        m.remove(6,3, 6, 5);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  5♥  6♥  7♥  8♥  \n" +
                "9♥  10♥  J♥  .   K♥  .   2♦  \n" +
                "Draw: [3♦, 4♦, 5♦, 6♦]");
        m.remove(6,4);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  5♥  6♥  7♥  8♥  \n" +
                "9♥  10♥  J♥  .   .   .   2♦  \n" +
                "Draw: [3♦, 4♦, 5♦, 6♦]");
        m.remove(6,6,6,2);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  5♥  6♥  7♥  8♥  \n" +
                "9♥  10♥  .   .   .   .   .   \n" +
                "Draw: [3♦, 4♦, 5♦, 6♦]");
        m.remove(5,3,5,4);
        m.remove(5,2,5,5);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  .   .   .   .   \n" +
                "9♥  10♥  .   .   .   .   .   \n" +
                "Draw: [3♦, 4♦, 5♦, 6♦]");
        m.removeUsingDraw(1, 6,0);
        assertEquals(v.toString(), "            A♠  \n" +
                "          2♠  3♠  \n" +
                "        4♠  5♠  6♠  \n" +
                "      7♠  8♠  9♠  10♠  \n" +
                "    J♠  Q♠  K♠  A♥  2♥  \n" +
                "  3♥  4♥  .   .   .   .   \n" +
                ".   10♥  .   .   .   .   .   \n" +
                "Draw: [3♦, 5♦, 6♦, 7♦]");
        m.removeUsingDraw(0, 6,1);
        m.remove(4,2);
        m.discardDraw(1);
        m.removeUsingDraw(3, 5,1);
        m.removeUsingDraw(3, 5,0);
        m.removeUsingDraw(3, 4,4);
        m.removeUsingDraw(3, 4,3);
        m.discardDraw(3);
        m.removeUsingDraw(3, 4,1);
        m.removeUsingDraw(3, 4,0);
        m.removeUsingDraw(3, 3,3);
        m.removeUsingDraw(3, 3,2);
        m.removeUsingDraw(0, 3,1);
        m.removeUsingDraw(3, 3,0);
        m.removeUsingDraw(0, 2,2);
        m.removeUsingDraw(0, 2,1);
        m.removeUsingDraw(3, 2,0);
        m.removeUsingDraw(3, 1,1);
        m.removeUsingDraw(3, 1,0);
        m.removeUsingDraw(3, 0,0);
        assertEquals(v.toString(), "You win!");

    }

//    @Test
//    public void toStringTest3() {
//        PyramidSolitaireModel m = new BasicPyramidSolitaire();
//        m.startGame(m.getDeck(), true, 7, 4);
//        PyramidSolitaireTextualView v = new PyramidSolitaireTextualView(m);
//        assertEquals(v.toString(), "            2♦  \n" +
//                "          2♠  6♥  \n" +
//                "        6♠  5♣  4♦  \n" +
//                "      K♠  4♠  J♦  10♦  \n" +
//                "    A♦  A♣  3♥  4♥  J♠  \n" +
//                "  Q♠  8♣  3♦  3♠  J♥  5♦  \n" +
//                "9♥  7♠  3♣  10♠  6♦  8♠  2♣  \n" +
//                "Draw: [7♦, A♠, 9♦, 7♣]");
//    }
}
