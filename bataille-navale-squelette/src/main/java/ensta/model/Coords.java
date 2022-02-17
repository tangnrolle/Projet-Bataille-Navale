package ensta.model;

import java.util.Random;

public class Coords {
    int x;
    int iy;

    public Coords(Coords coords) {
        this.x = coords.x;
        this.iy = coords.iy;
    }

    public Coords() {
        this.x = 0;
        this.iy = 0;
    }

    public Coords(int x, int iy) {
        this.x = x;
        this.iy = iy;
    }

    public int getX() {
        return x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public void setY(int j) {
        this.iy = j;
    }

    public int getY() {
        return iy;
    }

    public void setCoords(Coords res) {
        this.x = res.x;
        this.iy = res.iy;
    }

    public boolean isInBoard(int size) {
        return (x >= 0 && x < size && iy >= 0 && iy < size);
    }

    public static Coords randomCoords(int size) {

        Random random = new Random();
        Coords coords = new Coords();

        coords.setX(random.nextInt(size));
        coords.setY(random.nextInt(size));

        return coords;
    }

}
