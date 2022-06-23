package myBattleship;

import java.util.*;

/**
 * This class prints the map of the sea or updates the map of the sea
 * if the player's coordinate miss or hit the ship.
 */

public class Sea {

    private char[][] map;
    private int rows;
    private int columns;
    private char tile;

    /**
     * This sea constructor is a no argument constuctor.
     * The 'this' method used calls another ship constructor
     * that initialises the sea's rows, columns, tile, and map of the sea.
     */
    public Sea() {
        this(10, 30, '.');
    }

    /**
     * This sea constructor initialises sea rows, columns, tile and map of the sea.
     *
     * @param rows    initialises the sea's rows.
     * @param columns initialises the sea's columns.
     * @param tile    initialises the sea's tile.
     */
    public Sea(int rows, int columns, char tile) {

        this.rows = rows;
        this.columns = columns;
        this.tile = tile;
        map = new char[rows][columns];

        /*since cannot fill up 2D array directly, loops through the 2D array
        and fills up 1D array row by row in the 2D array*/
        for (int row_n = 0; row_n < map.length; row_n++) {
            Arrays.fill(map[row_n], this.tile);
        }
    }

    /**
     * This method gets the sea's rows to check if the player's entered coordinate 
     * and the ship's coordinates generated are out of range of the map of the sea.
     *
     * @return int of the rows of the map of the sea.
     */

    public int getRows() {
        return rows;
    }

    /**
     * This method gets the sea's columns to check if the player's entered coordinate
     * and the ship's coordinates generated are out of range of the map of the sea.
     *
     * @return int of the columns of the map of the sea.
     */

    public int getColumns() {
        return columns;
    }

    /**
    * This method updates the map of the sea 
    * if the player's coordinates hit or miss the ship.
    *
    * @param coordinates gets the coordinate(s) and updates the map of the sea.
    * @param symbol      updates the specific tile of the map of the sea
    *                    if the player hit the ship (symbol is 'O') 
    *                    or if the player miss the ship (symbol is 'X').
    */
    public void updateMap(int[][] coordinates, char symbol) {

        //loops number according to the nummber of rows that the of the 2D coordinates
        for (int row = 0; row < coordinates.length; row++) {

            int column = 0;

            //extracts the coorindates into a temporary row and column
            int tempRow = coordinates[row][column];
            int tempColumn = coordinates[row][column + 1];

            map[tempRow][tempColumn] = symbol;
        }
    }

    /**
     * This method prints the updated map of the sea for the player
     * so that the player can see the previous coordinates that the player had entered.
     * <p>
     * This method was also used to check for logic errors visually
     * in the initial development of the program and test drive programs.
     *
     * @see SeaTest
     */
    public void printMap() {

        //aligns top label for 1st part
        System.out.print("   ");

        //loops the value in tenth places 3 times
        for (int value = 1; value <= 3; value++) {
            for (int n_value = 1; n_value <= 10; n_value++) {
                if (n_value == 10) {
                    System.out.print(value + " ");
                } else {
                    System.out.print("  ");
                }
            }//end of 2nd for loop
        }//end of 1st for loop

        //aligns top label for 2nd part
        System.out.println();
        System.out.print("   ");

        //prints a series of values from 0 to 9 three times
        for (int value = 1; value <= 3; value++) {
            for (int n_value = 1; n_value <= 10; n_value++) {
                if (n_value == 10) {
                    System.out.print("0 ");
                } else {
                    System.out.print(n_value + " ");
                }
            }//end of 2nd for loop
        }//end of 1st for loop

        //aligns side labels
        System.out.println();

        int sideLabel = 1;
        //loops thorugh 2D array and prints each element
        for (int row = 0; row < map.length; row++) {

            if (sideLabel >= 10) {
                System.out.print(sideLabel + " ");
            } else {
                System.out.print(" " + sideLabel + " ");
            }

            //loops through the 2D array and prints each element
            for (int column = 0; column < map[row].length; column++) {
                System.out.print(map[row][column] + " ");
            }//end of 2nd for loop

            System.out.println();
            sideLabel++;
        }//end of 1st for loop
    }

}