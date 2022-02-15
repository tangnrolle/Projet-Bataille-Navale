package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {

    public Submarine() {
        this(Orientation.EAST);
    }

    public Submarine(Orientation ship_orientation) {
        super("Submarine", 'S', 3, ship_orientation);
    }
}
