package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.Card;
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
        String renderModel = renderModel(model);
        return renderModel;
    }

    private String renderModel(PyramidSolitaireModel model) {
        int numRows = model.getNumRows();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i != 0) {
                sb.append("\n");
            }
            for (int p = numRows - i - 1; p > 0; p--) {
                sb.append("  ");
            }
            for (int j = 0; j <= i; j++) {
                if (model.getCardAt(i,j) == null) {
                    sb.append(". ");
                } else {
                    sb.append(model.getCardAt(i,j));
                }
                sb.append("  ");
            }

        }
        sb.append("\n");
        sb.append("Draw: ");
        sb.append(model.getDrawCards());
        return sb.toString();
    }
}