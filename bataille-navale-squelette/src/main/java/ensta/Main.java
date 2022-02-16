package ensta;

//import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.ship.Submarine;
import ensta.model.ship.Destroyer;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.Player;
import ensta.model.Coords;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
        // new Game().init().run();

        Board B1 = new Board("MyBoard");
        Board B2 = new Board("OpponentBoard");

        Coords C = new Coords(2, 3);

        Submarine S1 = new Submarine();
        Submarine S2 = new Submarine();
        Destroyer D1 = new Destroyer();
        BattleShip Ba1 = new BattleShip();
        Carrier C1 = new Carrier();
        List<AbstractShip> L = new ArrayList<AbstractShip>();

        L.add(S1);
        L.add(D1);
        L.add(S2);
        L.add(Ba1);
        L.add(C1);

        Player P1 = new Player(B1, B2, L);
        P1.putShips();
        B1.sendHit(C);
        B1.print();

        // B1.putShip(S1, C);
        // B1.print();

    }

}
