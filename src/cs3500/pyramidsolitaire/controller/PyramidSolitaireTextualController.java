package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PyramidSolitaireTextualController implements PyramidSolitaireController{
    private Appendable ap; // out
    private Readable rd; // in
    private boolean isGameOver;


    public PyramidSolitaireTextualController(Readable rd, Appendable ap) {
        if (rd == null || ap == null) {
            throw new IllegalArgumentException("Invalid readable or appendable");
        }
        this.rd = rd;
        this.ap = ap;
        this.isGameOver = false;
    }

    /**
     * The primary method for beginning and playing a game.
     *
     * @param <K> the type of cards used by the model.
     * @param model The game of solitaire to be played
     * @param deck The deck of cards to be used
     * @param shuffle Whether to shuffle the deck or not
     * @param numRows How many rows should be in the pyramid
     * @param numDraw How many draw cards should be visible
     * @throws IllegalArgumentException if the model or deck is null
     * @throws IllegalStateException if the game cannot be started,
     *          or if the controller cannot interact with the player.
     */
    @Override
    public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle, int numRows, int numDraw) {
        checkExceptions(model, deck);
        // start the game
        try {
            model.startGame(deck, shuffle, numRows, numDraw);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new IllegalStateException("Null deck or model || IllArgument exception");
        }

        while (!isGameOver) {
            displayView(model);
            handleCommands(model);
        }

        try {
            ap.append("Game over. Score: " + Integer.toString(model.getScore()));
        } catch (IOException io) {
            System.out.println("IO Exception");
        }

    }

    private <K> void checkExceptions(PyramidSolitaireModel<K> model, List<K> deck) {
        if (model == null) {
            throw new IllegalArgumentException("Model is null");
        }
        if (deck == null) {
            throw new IllegalArgumentException("Deck is null");
        }
    }

    private <K> void handleCommands(PyramidSolitaireModel<K> model) {
        Scanner scanner = new Scanner(this.rd);
        String command = scanner.next();

//        try {
            switch (command) {
                case "q":
                case "Q":
                    isGameOver = true;
                    break;
                case "rm1":
                    int row1 = scanner.nextInt() - 1;
                    int card1 = scanner.nextInt() - 1;
                    model.remove(row1, card1);
                    break;
                case "rm2":
                    row1 = scanner.nextInt() -1;
                    card1 = scanner.nextInt() - 1;
                    int row2 = scanner.nextInt() - 1;
                    int card2 = scanner.nextInt() - 1;
                    model.remove(row1, card1, row2, card2);
                    break;
                case "rmwd":
                    int drawIndex = scanner.nextInt() - 1;
                    row1 = scanner.nextInt() - 1;
                    card1 = scanner.nextInt() - 1;
                    model.removeUsingDraw(drawIndex, row1, card1);
                    break;
                case "dd":
                    drawIndex = scanner.nextInt() - 1;
                    model.discardDraw(drawIndex);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
//        } catch (IllegalArgumentException | IllegalStateException ex) {
//            System.out.println("Invalid move. Play again. " + ex);
//        }
    }

    private <K> void displayView(PyramidSolitaireModel<K> model) {
        PyramidSolitaireView view = new PyramidSolitaireTextualView(model, ap);
        try {
            view.render();
            ap.append("\n");
        } catch (IOException ex){
            System.out.println("IO exception");
        }
    }
}
