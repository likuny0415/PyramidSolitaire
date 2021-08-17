package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

public class PyramidSolitaireCreator {

    public enum GameType {
        BASIC, RELAXED, MULTIPYRAMID;
    }

    /**
     * Factory method that assign different models.
     * @param gameType the type of the game
     * @return different types of model
     */
    public static PyramidSolitaireModel create(GameType gameType) {
        if (gameType == GameType.BASIC) {
            return new BasicPyramidSolitaire();
        } else if (gameType == GameType.RELAXED) {
            return new RelaxedPyramidSolitaire();
        } else if (gameType == GameType.MULTIPYRAMID) {
            return new MultiPyramidSolitaire();
        }
        return null;
    }
}
