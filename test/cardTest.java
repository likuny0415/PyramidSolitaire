import cs3500.pyramidsolitaire.model.hw02.Card;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class cardTest {
    @Test
    public void cardConstructor() {
        Card c = new Card(1);
        assertEquals(c.getSuit(), "♠");
        assertEquals(c, new Card(2, "♠"));

        // test concat
        String t = "1";
        assertEquals("123", t.concat("23"));

        // toString, constructor just with int
        Card c1 = new Card(0);
        assertEquals(c.toString(), "2♠");
        assertEquals(c1.toString(), "A♠");

        // toString, with value and suit
        Card c2 = new Card(13, "♠");
        assertEquals(c2.toString(), "K♠");
    }

    @Test  (expected = IllegalArgumentException.class)
    public void testException1() {
        // invalid value
        Card exception = new Card(0, "♠");
    }

    @Test  (expected = IllegalArgumentException.class)
    public void testException2() {
        // invalid value
        Card exception = new Card(14, "♠");
    }

    @Test  (expected = IllegalArgumentException.class)
    public void testException3() {
        // invalid suit
        Card exception = new Card(10, "12");
    }

    @Test
    public void testException4() {
        // invalid suit
        Card c = new Card(10);
        assertEquals(c, new Card(11,"♠"));
    }
}
