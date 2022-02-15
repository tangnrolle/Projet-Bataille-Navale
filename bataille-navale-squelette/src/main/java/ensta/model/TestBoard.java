package ensta.model;

import ensta.model.ship.Submarine;
import ensta.util.Orientation;

public class TestBoard {
    public static void main(String args[]) {

        Board B1 = new Board("MyBoard");
        Submarine S1 = new Submarine(Orientation.EAST);
        Coords C1 = new Coords(2, 3);
        B1.putShip(S1, C1);
        B1.print();
    }

}