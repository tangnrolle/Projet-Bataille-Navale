package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;
import ensta.util.ColorUtil.Color;
import ensta.model.Coords;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private String name;
	private ShipState[][] ships;
	private Boolean[][] hits;

	public String getName() {
		return name;
	}

	public ShipState[][] getShips() {
		return ships;
	}

	public Boolean[][] getHits() {
		return hits;
	}

	public Board() {
	}

	public Board(String my_name, int my_size) {
		size = my_size;
		name = my_name;
		ships = new ShipState[size][size];
		hits = new Boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ships[i][j] = new ShipState();
			}
		}

	}

	public Board(String my_name) {
		this(my_name, DEFAULT_SIZE);
	}

	public void print() {

		System.out.println("\nNavires:");

		String col_idx = "";
		String row;
		int max_indent = String.valueOf(size).length();
		int indent;

		for (int t = 0; t < max_indent; t++)
			col_idx = col_idx + " ";

		for (int k = 0; k < size; k++) {
			int col = k + 65;
			col_idx = col_idx + (char) col + " ";
		}

		System.out.println(" " + col_idx);

		for (int i = 0; i < size; i++) {
			row = "";
			indent = max_indent - String.valueOf(i + 1).length();
			row = row + (i + 1);

			for (int k = 0; k <= indent; k++)
				row = row + " ";

			for (int j = 0; j < size; j++) {
				row = row + ships[j][i].toString();
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
			indent = max_indent - String.valueOf(i + 1).length();
			row = row + (i + 1);

			for (int k = 0; k <= indent; k++)
				row = row + " ";

			for (int j = 0; j < size; j++) {
				if (hits[j][i] == null)
					row = row + ". ";
				else if (hits[j][i] == false)
					row = row + "X ";
				else if (hits[j][i] == true)
					row = row + ColorUtil.colorize("X ", ColorUtil.Color.RED);
			}
			System.out.println(row);
		}
		System.out.println();
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() > this.size) {
				System.out.println("Ce bateau sort de la grille ! Recommencez svp :\n");
				return false;

			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() > this.size) {
				System.out.println("Ce bateau sort de la grille ! Recommencez svp :\n");
				return false;
			}
			dy = 1;

		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				System.out.println("Ce bateau sort de la grille ! Recommencez svp :\n");
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				System.out.println("Ce bateau sort de la grille ! Recommencez svp :\n");
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				System.out.println("Vous ne pouvez pas superposer les bateaux ! Recommencez svp :\n");
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

			if (o == Orientation.EAST || o == Orientation.WEST) {

				for (int i = 0; i < ship.getLength(); ++i) {

					ships[coords.getX() + o.getIncrement() * i][coords.getY()] = new ShipState(ship);

				}
			}

			else if (o == Orientation.SOUTH || o == Orientation.NORTH) {

				for (int i = 0; i < ship.getLength(); ++i) {

					ships[coords.getX()][coords.getY() + o.getIncrement() * i] = new ShipState(ship);

				}
			}

			return true;

		}
		return false;
	}

	@Override
	public boolean hasShip(Coords coords) {

		if (coords.isInBoard(size)) {
			if (ships[coords.getX()][coords.getY()].getShip() == null) {
				return false;
			}
			return true;
		}
		System.err.println("Coordonnées hors de la grille");
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

	public Hit sendHit(Coords res) {
		if (res.isInBoard(size)) {

			ShipState hitpoint = ships[res.getX()][res.getY()];

			if (!hitpoint.isStruck()) {

				hitpoint.setStruck(true);

				if (!hasShip(res)) {

					return Hit.MISS;

				} else {

					hitpoint.getShip().addStrike();

					if (hitpoint.getShip().isSunk()) {

						return Hit.valueOf(hitpoint.getShip().getName().toUpperCase());

					} else {

						return Hit.STRIKE;
					}
				}
			}

			System.out.println("Cette case a déjà été frappée. Recommencez svp : ");
			return null;

		}
		System.out.println("Coordonnées hors de la grille. Recommencez svp : ");
		return null;
	}

}
