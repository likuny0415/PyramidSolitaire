package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

public class Card {

    private final Integer value; // value range: 1 - K by toString();
    private final String suit;

    public Card(Integer value, String suit) {
        if (value <= 0 || value > 13) {
            throw new IllegalArgumentException("Invalid value of card");
        }
        if (suit != "♣" && suit != "♦" && suit != "♥" && suit != "♠") {
            throw new IllegalArgumentException("Invalid suit of card");
        }
        this.value = value;
        this.suit = suit;
    }

    /**
     * An convenient way to represent card.
     * @param value
     */
    public Card(Integer value) {
        Integer cardValue = (value % 13) + 1; // card range: 1 - K, e.g. value=0->cardValue=1 value=12->cardValue=13(K)
        String suitValue = suitByValue(value / 13); // 0:0-12->spade | 1:13-25->heart | 2:26-38->diamond | 3:39-51:club
        this.value = cardValue;
        this.suit = suitValue;
    }

    //Get corresponding suit by its given value
    private String suitByValue(Integer value) {
        if (value == 0) {
            return "♠";
        } else if (value == 1) {
            return "♥";
        } else if (value == 2) {
            return "♦";
        } else if (value == 3) {
            return "♣";
        }
        return "";
    }

    public Integer getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String temp = valueToDeckRepresentation(this.getValue());
        return temp.concat(this.getSuit());
    }

    private String valueToDeckRepresentation(Integer value) {
        if (value > 1 && value <= 10) {
            return Integer.toString(value);
        } else if (value == 1) {
            return "A";
        } else if (value == 11) {
            return "J";
        } else if (value == 12) {
            return "Q";
        } else if (value == 13) {
            return "K";
        }
        return "";
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Card)) {
            return false;
        }
        Card newCard = (Card) that;
        return newCard.getValue() == this.getValue() && newCard.getSuit() == this.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSuit(),this.getValue());
    }
}
