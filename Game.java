package myBattleship;

import java.util.*;

/**
 * This class initialises the Sea class, Player class, and Ship class
 * in the main program in the App class.
 */
public class Game {
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private Sea seaObject = new Sea();
    private Player playerObject = new Player();
    private int totalShipsBombed = 0;

    /**
     * This game constructor is a no argument constructor.
     */
    public Game() {

    }

    /**
     * This method generates each new ship's object coordinates based on total ships.
     * <p>
     * This method then adds each new ship's object to the shipList if it passed
     * a series of checks such as out of range of the map of the sea
     * and if the new ship's object coordinates 
     * is a duplicate with other ship's object coordinates in the shipList
     * by looping through each old ship's coordinates in the shipList and comparing 
     * the new ship's object coordinates and each old ship's coordinates one at a time.
     *
     * @param totalShips is used to let the method 
     * know how many ship's object coordinates to generate.
     */
    public void createShips(int totalShips) {

        for (int shipNumber = 1; shipNumber <= totalShips; shipNumber++) {

            Ship shipObject = new Ship();
            shipObject.setCoordinates(seaObject.getRows(), seaObject.getColumns());

            boolean duplicate = false;

            if (checkOutOfRange(shipObject.getCoordinates()) != true) {

                int[][] newShipCoordinates = shipObject.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < shipList.size(); index++) {

                        int[][] oldShipCoordinates = shipList.get(index).getCoordinates();

                        if (checkDuplicate(newShipCoordinates, oldShipCoordinates) == true) {
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end of 2nd for loop

                    if (duplicate != true) {
                        shipList.add(shipObject);
                    }

                } else {
                    shipList.add(shipObject);
                }

            } else {
                shipNumber--;
            }
        }//end of 1st for loop
    }//end of createShips method

    /**
     * This methods checks if the new ship's object coordinates generated
     * is out of range of the map of the sea by looping through
     * the ship's object coordinates based on the ship's object length.
     *
     * @param coordinates are needed when checking
     * if the new ship's object coordinates are out of range of the map of the sea.
     */
    public boolean checkOutOfRange(int[][] coordinates) {

        int maxRow = seaObject.getRows() - 1;
        int maxColumn = seaObject.getColumns() - 1;

        boolean outOfRange = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > maxRow || coordinates[row][1] > maxColumn) {
                outOfRange = true;
                break;
            }
        }

        return outOfRange;
    }

    /**
     * This method checks if the new ship's object coordinates
     * is a duplicate with old ship's object coordinates
     * by looping through row by row and column by column.
     * <p>
     * This method can also be used to check if the player's entered coordinate
     * is a duplicate with the a ship's object coordinates because
     * if it is a duplicate, that means the player's entered coordinate
     * have hit the ship's object coordinates.
     *
     * @param coordinate1 checks if there is a duplicate coordinate
     *                    by looping through row by row and column by column.
     * @param coordinate2 checks if there is a duplicate coordinate
     *                    by looping through row by row and column by column.
     * @return boolean    of whether there is a duplicate among 
     *                    the 2 coordinate given.
     *                    If there is a duplicate, returns true, else return false.
     */
    public boolean checkDuplicate(int[][] coordinate1, int[][] coordinate2) {

        boolean duplicate = false;
        for (int row_a = 0; row_a < coordinate1.length; row_a++) {
            for (int row_b = 0; row_b < coordinate2.length; row_b++) {

                if ((coordinate1[row_a][0] == coordinate2[row_b][0]) 
                        && (coordinate1[row_a][1] == coordinate2[row_b][1])) {

                    duplicate = true;
                    break;
                }
            }//end of 2nd for loop

            if (duplicate == true) {
                break;
            }
        }//end of 1st for loop

        return duplicate;
    }

    /**
     * This method gets the ship list to access each ship object
     * by using the ship list index in a loop as the game progresses.
     * Once the each ship object can be access, the ship's object is manipulated
     * by using the ship's class methods.
     * 
     * @return Arraylist<Ship> to get the the ship list to
     * access each ship object by using the ship list index in a loop
     * as the game progresses.
     */
    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    /**
     * This methods gets the player object to use the player's class methods
     * as the game progresses.
     *
     * @return Player to use the player's class methods as the game progresses.
     * @see    Player
     */
    public Player getPlayer() {
        return playerObject;
    }

    /**
     * This method increases the total ships bombed by 1
     * if the player entered coordinate hits the ship.
     */
    public void increaseTotalShipsBombed() {
        totalShipsBombed = totalShipsBombed + 1;
    }

    /**
     * This method gets the total ships bombed
     * to display to the player if the player have bombed the ship.
     * <p>
     * This methods also controls the flow of the game
     * by knowing when to end the game as the game progresses.
     *
     * @return int of the totalShipsBombed to display
     * to the player if the player have bombed the ship
     * and to control the flow of the game as the game progresses.
     */
    public int getTotalShipsBombed() {
        return totalShipsBombed;
    }

    /**
     * This method gets the sea object to use the sea's class methods
     *
     * @return Sea to get the sea object to use the sea's class methods.
     * @see    Sea
     */
    public Sea getSea() {
        return seaObject;
    }

    /**
     * This method checks if the player's entered coordinate
     * is out of range of the map of the sea or
     * if the player entered coordinate consist of negative number(s).
     *
     * @param coordinate is used check if the player's entered coordinate
     *                   is out of range of the map of the sea or
     *                   if the player entered coordinate 
     *                   consist of negative number(s).
     * @return boolean   value of true if the player's entered coordinate
     *                   is out of range of map of the sea or
     *                   if the player entered coordinate
     *                   consist of negative number(s),
     *                   else return false.
     */
    public boolean checkInputOutOfRange(int[][] coordinate) {

        int maxRow = seaObject.getRows() - 1;
        int maxColumn = seaObject.getColumns() - 1;

        boolean InputOutOfRange = false;
        for (int row = 0; row < coordinate.length; row++) {
            if ((coordinate[row][0] > maxRow || coordinate[row][1] > maxColumn) 
                    || (coordinate[row][0] < 0 || coordinate[row][1] < 0)) {

                InputOutOfRange = true;
                break;
            }
        }

        return InputOutOfRange;
    }

}