package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {

    public BattleShip() {
        this(Orientation.EAST);
    }

    public BattleShip(Orientation ship_orientation) {
        super("Battleship", 'B', 4, ship_orientation);
    }
}
