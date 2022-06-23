package myBattleship;

import java.util.Random;

/**
 * This class sets the ship's coordinates, length, orientation (horizontal)
 * and also sets the initial status of the ship if is bombed to false.
 */
public class Ship {
    private int[][] coordinates;
    private int length;
    private boolean horizontal;
    private boolean bombed;

    /**
     * This ship constructor is a no argument constructor.
     * The 'this' method used calls another ship constructor
     * that initialises the ship's length, orientation and bombed status.
     * Then it sets the coordinates of the ship.
     */
    public Ship() {
        this(3, false);
    }

    /**
     * This ship constructor initialises the ship's length, orientation and bombed status.
     *
     * @param length initialises the ship's length.
     * @param bombed initialises the ship's bombed status.
     */
    public Ship(int length, boolean bombed) {
        this.length = length;
        this.bombed = bombed;
        coordinates = new int[length][2];
        setOrientation();
    }

    /**
     * This method randomly sets the ship's orientation 
     * to either a horizontal or vertical position.
     *
     * @return boolean of whether the ship's orienation 
     * will be horizontal (if it is true) 
     * or not horizontal (also known as vertical) (if it is false).
     */
    public boolean setOrientation() {

        //initialises random variable
        Random random = new Random();

        horizontal = random.nextBoolean();

        return horizontal;
    }

    /**
     * This method randomly sets the ship's coordinates 
     * by randomly setting the ship's starting point
     * and then generates the other coordinates of the ship
     * according to the ship's length and orientation.
     *
     * @param seaRows    is needed for the ship to 
     *                   generate the coordinates accordingly
     * @param seaColumns is needed for the ship to 
     *                   generate the coordinates accordingly
     */
    public void setCoordinates(int seaRows, int seaColumns) {

        //initialises random variable
        Random random = new Random();

        /*generates random ship row and ship column as starting point 
        to generate the ship's coordinate*/
        int minRow = 0;
        int maxRow = seaRows - 1;
        //sets the ship's row range between 0 to maximum sea rows
        int shipRow = minRow + random.nextInt((maxRow - minRow) + 1);

        int minColumn = 0;
        int maxColumn = seaColumns - 1;
        //sets the ship's column range between 0 to maximum sea columns
        int shipColumn = minColumn + random.nextInt((maxColumn - minColumn) + 1);

        /*starts generating the ship's coordinates 
        by using shipRow and shipColumn as starting point*/
        int increment = 0;
        for (int row = 0; row < length; row++) {

            //if horizontal position is true, the ship column value is changed only
            if (horizontal == true) {
                coordinates[row][0] = shipRow;
                coordinates[row][1] = shipColumn + increment;

            //if horizontal position is false, the ship row value is changed only
            } else {
                coordinates[row][0] = shipRow + increment;
                coordinates[row][1] = shipColumn;
            }

            increment++;
        }//end of for loop
    }

    /**
     * This method gets a ship's current coordinates.
     *
     * @return int[][] of the ships coordinates stored.
     */
    public int[][] getCoordinates() {
        return coordinates;
    }

    /**
     * This method sets the ship's bombed status from false to true 
     * if it is hit by the player's coordinate.
     */
    public void isBombed() {
        bombed = true;
    }

    /**
     * This method gets the ship's bombed status.
     *
     * @return boolean of the ship's bombed status.
     */
    public boolean getIsBombed() {
        return bombed;
    }

    /**
     * This method prints the ship's coordinates to check for logic errors.
     * in the initial development of the program and test drive programs.
     *
     * @see ShipTest
     * @see SeaTest
     */
    public void printCoordinates() {

        int count = 1;
        for (int row = 0; row < coordinates.length; row++) {

            System.out.print("(");

            for (int column = 0; column < coordinates[row].length; column++) {

                System.out.print(coordinates[row][column]);

                if (column == 0) {
                    System.out.print(",");
                }
            }//end of 2nd for loop
            System.out.print(") ");

            if (count % 5 == 0) {
                System.out.println();
            }
            count++;
        }//end of 1st for loop
        System.out.println("| horizontal: " + horizontal + " | bombed: " + bombed);
    }

}