package cs3500.pyramidsolitaire.view;


import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.io.IOException;

public class PyramidSolitaireTextualView implements PyramidSolitaireView {
    private final PyramidSolitaireModel<?> model;
    private Appendable ap;
    // ... any other fields you need

    public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
        this.model = model;
    }

    public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
        if (model == null) {
            throw new IllegalArgumentException("Invalid model");
        }
        this.model = model;
        this.ap = ap;
    }

    @Override
    public void render() throws IOException {
        ap.append(toString());
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
        for (Object c : model.getDrawCards()) {
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");
        sb.append("Scores: ");
        sb.append(model.getScore());
        return sb.toString();
    }


}