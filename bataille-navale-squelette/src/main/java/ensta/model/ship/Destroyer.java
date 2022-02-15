package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {

    public Destroyer() {
        this(Orientation.EAST);
    }

    public Destroyer(Orientation ship_orientation) {
        super("Destroyer", 'D', 2, ship_orientation);
    }

}
