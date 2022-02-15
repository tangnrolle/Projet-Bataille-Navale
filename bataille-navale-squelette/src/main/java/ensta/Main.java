package ensta;

//import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.ship.Submarine;
import ensta.model.ship.Destroyer;
import ensta.model.ship.AbstractShip;
import ensta.model.Player;
import ensta.model.Coords;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
        // new Game().init().run();

        Board B1 = new Board("MyBoard");
        Board B2 = new Board("OpponentBoard");

        Submarine S1 = new Submarine();
        Destroyer D1 = new Destroyer();
        List<AbstractShip> L = new ArrayList<AbstractShip>();

        L.add(S1);
        L.add(D1);

        Coords C1 = new Coords(4, 1);
        if (B1.putShip(S1, C1))
            B1.print();

        Player P1 = new Player(B1, B2, L);
        P1.putShips();

    }

}
