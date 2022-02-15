package ensta;

//import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.ship.*;
import ensta.util.*;
import ensta.model.Coords;

public class Main {

    public static void main(String args[]) {
        // new Game().init().run();

        Board B1 = new Board("MyBoard");
        Submarine S1 = new Submarine(Orientation.EAST);
        Coords C1 = new Coords(2, 3);
        if (B1.putShip(S1, C1))
            B1.print();
    }

}
