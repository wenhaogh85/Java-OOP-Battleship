package myBattleship;

import java.util.*;

/**
 * This class gets the player's coordinate and bombs.
 */
public class Player {

    private int coordinate[][];
    private int bombs;

    /**
     * This player constructor is a no argument constructor.
     * The 'this' method calls another player constructor
     * that initialises the player's bombs and coordinate.
     */
    public Player() {
        this(15);
    }

    /**
     * This player constructor initialises the player's bombs and coordinate.
     *
     * @param bombs initialises the number of bombs left
     * if the player's coordinate hit or miss the ship.
     */
    public Player(int bombs) {
        coordinate = new int[1][2];
        this.bombs = bombs;
    }

    /**
     * This method prompts the player to enter their coordinate by entering the row and column.
     *<p>
     * This method then minus the row and column of the player's coordinate by 1
     * because arrays index starts from zero, thus it needs to be align
     * to prevent confusion to players when the map of the sea is printed.
     */
    public void enterCoordinate() {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter location (row column): ");
        int row = input.nextInt();
        int column = input.nextInt();

        coordinate[0][0] = row - 1;
        coordinate[0][1] = column - 1;
    }

    /**
     * This method gets the player's coordinate as the game progresses.
     *
     * @return int[][] of the player's coordinate as the game progresses.
     */
    public int[][] getCoordinate() {
        return coordinate;
    }

    /**
     * This method decreases the player's bombs 
     * if the player's coordinate miss or hit the ship.
     */
    public void decreaseBombs() {
        bombs = bombs - 1;
    }

    /**
     * This method gets the player's bombs left as the game progresses.
     *
     * @return int of the player's bombs left as the game progresses.
     */
    public int getBombsLeft() {
        return bombs;
    }

}