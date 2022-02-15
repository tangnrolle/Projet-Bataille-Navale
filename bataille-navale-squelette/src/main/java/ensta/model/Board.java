package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.model.Coords;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	String name;
	private Character[][] ships;
	boolean[][] hits;

	public Board() {
	}

	public Board(String my_name, int my_size) {
		size = my_size;
		name = my_name;
		ships = new Character[size][size];
		hits = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ships[i][j] = '.';
			}
		}

	}

	public Board(String my_name) {
		this(my_name, DEFAULT_SIZE);
	}

	public void print() {

		System.out.println("\nNavires:");

		String col_idx = "  ";
		String row;

		for (int k = 0; k < size; k++) {
			int col = k + 65;
			col_idx = col_idx + (char) col + " ";
		}

		System.out.println(" " + col_idx);

		for (int i = 0; i < size; i++) {
			row = "";
			row = row + (i + 1) + " ";
			for (int j = 0; j < size; j++) {
				row = row + ships[i][j] + " ";
			}
			System.out.println(row);
		}

		System.out.println("\nFrappes:");

		col_idx = "  ";

		for (int k = 0; k < size; k++) {
			int col = k + 65;
			col_idx = col_idx + (char) col + " ";
		}

		System.out.println(" " + col_idx);

		for (int i = 0; i < size; i++) {
			row = "";
			row = row + (i + 1) + " ";
			for (int j = 0; j < size; j++) {
				if (hits[i][j] == true)
					row = row + "X ";
				else
					row = row + ". ";
			}
			System.out.println(row);
		}

	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;

		} else if (o == Orientation.NORTH) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	@Override
	public int getSize() {
		return (size);
	}

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {

		if (canPutShip(ship, coords) == true) {

			Orientation o = ship.getOrientation();

			for (int i = 0; i < ship.getLength(); ++i) {

				if (o == Orientation.SOUTH || o == Orientation.NORTH) {

					ships[coords.getX() - 1 + o.getIncrement() * i][coords.getY() - 1] = ship.getLabel();
				}

				if (o == Orientation.EAST || o == Orientation.WEST) {

					ships[coords.getX() - 1][coords.getY() - 1 + o.getIncrement() * i] = ship.getLabel();
				}
			}

			return true;

		}
		return false;
	}

	@Override
	public boolean hasShip(Coords coords) {

		if (coords.isInBoard(size)) {
			if (ships[coords.getX()][coords.getY()] == '.') {
				return false;
			}
			return true;
		}
		// throw error
		return false;
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		hits[coords.getX()][coords.getY()] = hit;
	}

	@Override
	public Boolean getHit(Coords coords) {
		return hits[coords.getX()][coords.getY()];
	}

	@Override
	public Hit sendHit(Coords res) {
		return Hit.MISS;
	}
}
