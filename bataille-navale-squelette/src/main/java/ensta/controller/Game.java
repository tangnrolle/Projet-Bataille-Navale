package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.ai.PlayerAI;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {

			Board my_board = new Board("My Board");
			Board opponent_board = new Board("Opponent Board");

			List<AbstractShip> my_ships = createDefaultShips();
			List<AbstractShip> opponent_ships = createDefaultShips();

			this.player1 = new Player(my_board, opponent_board, my_ships);
			this.player2 = new PlayerAI(opponent_board, my_board, opponent_ships);

			player1.putShips();
			player2.putShips();

		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Hit hit;

		// main loop
		b1.print();
		boolean done;
		do {
			hit = null;

			while (hit == null) {
				hit = player1.sendHit(coords);
			}

			boolean strike = hit != Hit.MISS;
			b1.setHit(strike, coords);

			done = updateScore();
			b1.print();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

			// save();

			if (!done && !strike) {
				do {
					hit = null;
					while (hit == null) {
						hit = player2.sendHit(coords);
					}

					strike = hit != Hit.MISS;
					if (strike) {
						b1.print();
					}
					System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
					done = updateScore();

					if (!done) {
						// save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		// sin.close();
	}

	private void save() {
		// try {
		// // TODO bonus 2 : uncomment
		// // if (!SAVE_FILE.exists()) {
		// // SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
		// // }
		//
		// // TODO bonus 2 : serialize players
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	private boolean loadSave() {
		// if (SAVE_FILE.exists()) {
		// try {
		// // TODO bonus 2 : deserialize players
		//
		// return true;
		// } catch (IOException | ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		// }
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
			case MISS:
				msg = hit.toString();
				break;
			case STRIKE:
				msg = hit.toString();
				color = ColorUtil.Color.RED;
				break;
			default:
				msg = hit.toString() + " coulé";
				color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() + 1), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
