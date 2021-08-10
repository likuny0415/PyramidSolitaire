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
        m.startGame(m.getDeck(), false, 6, 4);
        PyramidSolitaireTextualView v = new PyramidSolitaireTextualView(m);
        assertEquals(v.toString(), "");
    }
}
