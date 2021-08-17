package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

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
    }

    @Override
    public boolean noDuplicateCards(List<Card> deck) {
        Map<Card, Integer> map = new HashMap<>();
        for (Card card : deck) {
            if (map.containsKey(card)) {
                map.getOrDefault(card, 1 + map.get(card));
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
}
