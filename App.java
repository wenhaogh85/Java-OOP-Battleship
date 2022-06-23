package myBattleship;

import java.util.*;

/**
 * This class is where the main program is stored.
 */
public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println
        (
            "Choose a stage by entering the number:\n" +
            "+------------+----------------+\n" +
            "|   Stage    | Ships | Number |\n" +
            "+------------+----------------+\n" +
            "|Beginner    |   30  |    1   |\n" +
            "+------------+----------------+\n" +
            "|Intermdiate |   20  |    2   |\n" +
            "+------------+----------------+\n" +
            "|Advance     |   10  |    3   |\n" +
            "+------------+----------------+\n"
        );

        System.out.print("Enter number: ");
        int number = input.nextInt();
        int totalShips = 0;

        switch (number) {

            case 1:
                totalShips = 30;
                break;

            case 2:
                totalShips = 20;
                break;

            case 3:
                totalShips = 10;
                break;

            default:
                System.out.println("Invalid input!!");
                break;
        }

        //initialises game object
        Game gameObject = new Game();
        gameObject.createShips(totalShips);

        //to test for logic error in the program
        // for (Ship eachShip: gameObject.getShipList()) {
        //     eachShip.printCoordinates();
        // }

        //store player's miss coordinates
        int[][] oldCoordinate = new int[1][2];
        int[][] missList = new int[gameObject.getPlayer().getBombsLeft()][2];
        int row = 0;

        //starts game for the player to play
        while (true) {

            gameObject.getSea().printMap();

            //displays player status to player
            System.out.println
            (
                "--------------------------------------------------------------\n" +
                "Ships bombed: " + gameObject.getTotalShipsBombed() + "\n" +
                "Bombs left: " + gameObject.getPlayer().getBombsLeft()
            );

            if (gameObject.getTotalShipsBombed() == 5 || gameObject.getPlayer().getBombsLeft() == 0) {
                break;

            } else {

                gameObject.getPlayer().enterCoordinate();

                //temporary stores the player's coordinate and ship's coordinates
                int[][] playerCoordinate = gameObject.getPlayer().getCoordinate();
                int[][] shipCoordinates = new int[3][2];

                boolean playerBombedShip = false;
                int shipNumber = 0;

                if (gameObject.checkInputOutOfRange(playerCoordinate) != true) {

                    for (int index = 0; index < gameObject.getShipList().size(); index++) {

                        shipCoordinates = gameObject.getShipList().get(index).getCoordinates();

                        if (gameObject.checkDuplicate(playerCoordinate, shipCoordinates) == true) {
                            playerBombedShip = true;
                            shipNumber = index;
                            break;
                        }
                    }//end of for loop

                    if (playerBombedShip == true) {

                        //checks if ship have been bombed or not
                        if (gameObject.getShipList().get(shipNumber).getIsBombed() == false) {
                            System.out.println
                            (
                                "\n***************** You have found the ship :) *****************\n"
                            );
                            gameObject.increaseTotalShipsBombed();
                            gameObject.getPlayer().decreaseBombs();
                            gameObject.getShipList().get(shipNumber).isBombed();
                            gameObject.getSea().updateMap(shipCoordinates, 'O');

                        } else {
                            System.out.println
                            (
                                "\n****** You cannot bombed the same ship more than once!! ******\n"
                            );
                        }

                    } else {

                        /*add the player 1st coordinate into the missList to check for 
                        duplicate missed coordinates*/
                        if (row == 0) {

                            missList[row][0] = playerCoordinate[0][0];
                            missList[row][1] = playerCoordinate[0][1];

                            row = row + 1;

                            System.out.println
                            (
                                "\n******************* You missed the ship :'( ******************\n"
                            );

                            gameObject.getPlayer().decreaseBombs();
                            gameObject.getSea().updateMap(gameObject.getPlayer().getCoordinate(), 'X');

                        } else {
                            
                            /*if the row is more than 1, starts looping through the 
                            missed coordinates in the missList*/
                            boolean missDuplicate = false;

                            for (int n_row = 0; n_row < missList.length; n_row++) {

                                oldCoordinate[0][0] = missList[n_row][0];
                                oldCoordinate[0][1] = missList[n_row][1];

                                if (gameObject.checkDuplicate(playerCoordinate, oldCoordinate) == true) {
                                    missDuplicate = true;
                                    break;
                                }
                            }//end of for loop

                            if (missDuplicate == true) {

                                System.out.println
                                (
                                    "\n*********** You have searched this location before ***********\n"
                                );

                            } else {
                                System.out.println
                                (
                                    "\n******************* You missed the ship :'( ******************\n"
                                );

                                /*add the player's missed coordinates to the missList if
                                the missed coordinate is not a duplicate*/
                                missList[row][0] = playerCoordinate[0][0];
                                missList[row][1] = playerCoordinate[0][1];

                                row = row + 1;

                                gameObject.getPlayer().decreaseBombs();
                                gameObject.getSea().updateMap(gameObject.getPlayer().getCoordinate(), 'X');
                            }
                        }
                    }

                } else {
                    System.out.println
                    (
                        "\n*************** Coordinate are out of range!! ****************\n"
                    );
                }
            }

        }//end of while loop

        System.out.println
        (
            "\n========================= Game Over ==========================\n"
        );

        //shows player the ending message when the player win or lose
        int totalShipsBombed = gameObject.getTotalShipsBombed();
        int bombsLeft = 15 - gameObject.getPlayer().getBombsLeft();
        
        if (totalShipsBombed == 5) {

            if (bombsLeft >= 13 && bombsLeft <= 15) {
                System.out.println("You are a novice.");

            } else if (bombsLeft >= 10 && bombsLeft <= 12) {
                System.out.println("Not too bad.");

            } else if (bombsLeft < 10) {
                System.out.println("You have talent!!");
            }

        } else {
            System.out.println("You've no luck today, try again.");
        }

        System.out.println("\nThank you for playing battleship.");
    }
}