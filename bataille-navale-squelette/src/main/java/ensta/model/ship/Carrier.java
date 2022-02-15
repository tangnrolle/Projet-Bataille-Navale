package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {

    public Carrier() {
        this(Orientation.EAST);
    }

    public Carrier(Orientation ship_orientation) {
        super("Carrier", 'C', 5, ship_orientation);
    }
}
