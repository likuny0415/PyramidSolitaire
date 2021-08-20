package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;


import java.io.InputStreamReader;
import java.util.Scanner;

public class PyramidSolitaire {
    public static void main(String[] args) {
        PyramidSolitaireModel model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
        PyramidSolitaireController controller = new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                System.out);
        controller.playGame(model,model.getDeck(),false,7,3);

//        Scanner scan = new Scanner(System.in);
//        int num1, num2;
//        String command = scan.next();
//        switch(command) {
//            case "+":
//                num1 = scan.nextInt();
//                num2 = scan.nextInt();
//
//                break;
//            case "mutiple":
//                num1 = scan.nextInt();
//                num2 = scan.nextInt();
//                int num3 = scan.nextInt();
//
//                break;
//            default:
//                System.out.print("Go away");
//        }
//
//
//    }
    }
}
