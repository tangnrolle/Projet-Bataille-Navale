package ensta.controller;

import ensta.ai.BattleShipsAI;
import ensta.model.Board;
import ensta.model.ship.Submarine;
import ensta.model.ship.Destroyer;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.Coords;
import ensta.model.Hit;

public class TestGame {

    private static void sleepfor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Board b1 = new Board("MyBoard");
        b1.print();

        Submarine s1 = new Submarine();
        Submarine s2 = new Submarine();
        Destroyer d1 = new Destroyer();
        BattleShip ba1 = new BattleShip();
        Carrier c1 = new Carrier();
        AbstractShip[] all_ships = { s1, s2, d1, ba1, c1 };

        BattleShipsAI ai = new BattleShipsAI(b1, b1);
        ai.putShips(all_ships);

        int sunk_ships = 0;
        int end_game = all_ships.length;
        System.out.println(end_game);

        Coords init_hit = Coords.randomCoords(b1.getSize());

        do {

            Hit hit = ai.sendHit(init_hit);
            System.out.println("Résultat de la frappe : " + hit.toString());
            b1.print();

            if (hit.getValue() > 0) {
                sunk_ships++;
            }

            System.out.println("=============================================");
            sleepfor(1200);

        } while (sunk_ships < end_game);

        System.out.println("Fin de partie.");
        return;
    }

}
