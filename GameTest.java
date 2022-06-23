package myBattleship;

/**
 * This class is the driver test program for Game class
 * to test if there is any logic error in the program
 * before finalising the final program.
 */
public class GameTest {
    public static void main(String[] args) {
        Game gameObject = new Game();

        int totalShips = 10;

        gameObject.createShips(totalShips);
        
        for (Ship eachShip: gameObject.getShipList()) {
            gameObject.getSea().updateMap(eachShip.getCoordinates(), 'O');
        }

        gameObject.getSea().printMap();

        System.out.println("--------------------------------------------------------------\n");
        System.out.println("All ship coordinates | orientation | bomb status");
        System.out.println("--------------------------------------------------------------");
        for (Ship eachShip: gameObject.getShipList()) {
            eachShip.printCoordinates();
        }
    }
}