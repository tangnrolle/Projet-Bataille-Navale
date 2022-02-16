package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {

    private Character label;
    private String name;
    private int length;
    private Orientation orientation;
    private int strikeCount;

    public AbstractShip(String ship_name, Character ship_label, int ship_length, Orientation ship_orientation) {
        name = ship_name;
        label = ship_label;
        length = ship_length;
        orientation = ship_orientation;
        strikeCount = 0;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation new_orientation) {
        this.orientation = new_orientation;
    }

    public boolean isSunk() {
        if (strikeCount >= length)
            return true;
        else
            return false;
    }

    public String getName() {
        return name;
    }

    public Character getLabel() {
        return label;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public void addStrike() {
        this.strikeCount++;
    }

}
