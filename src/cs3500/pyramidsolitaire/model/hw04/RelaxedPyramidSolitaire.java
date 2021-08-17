package cs3500.pyramidsolitaire.model.hw04;

public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaire {

    RelaxedPyramidSolitaire() {
        super();
    }

    @Override
    protected void exposeCards() {
        for (int i =0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfRows; j++) {
                this.cardsInPyramidCanRemoved[i][j] = true;
            }
        }
    }
}
