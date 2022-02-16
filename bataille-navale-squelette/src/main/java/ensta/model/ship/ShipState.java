package ensta.model.ship;

import ensta.util.ColorUtil;

public class ShipState {

    private AbstractShip refShip;
    private boolean struck;

    public ShipState() {
        this(null);
    }

    public ShipState(AbstractShip refship) {
        this.refShip = refship;
        this.struck = false;
    }

    public AbstractShip getShip() {
        return refShip;
    }

    public void setStruck(boolean strike) {
        this.struck = strike;
    }

    public boolean isStruck() {
        return struck;
    }

    @Override
    public String toString() {
        if (this.struck) {
            if (this.refShip == null) {
                return ("x ");
            } else {
                return (String.valueOf(ColorUtil.colorize(refShip.getLabel(),
                        ColorUtil.Color.RED)) + " ");
            }
        }

        else {
            if (this.refShip == null) {
                return (". ");
            } else {
                return (String.valueOf(ColorUtil.colorize(refShip.getLabel(),
                        ColorUtil.Color.BLUE)) + " ");
            }
        }
    }

}
