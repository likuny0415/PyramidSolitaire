package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

public class PyramidSolitaireTextualView {
    private final PyramidSolitaireModel<?> model;
    // ... any other fields you need

    public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        if (model.getNumRows() == - 1) {
            return "";
        } else if (model.isGameOver()) {
            if (model.getScore() == 0) {
                return "You win!";
            } else {
                return "Game over. Score: " + model.getScore();
            }
        }
        return " j k q r p";
    }
}