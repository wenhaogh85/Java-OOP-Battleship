package myBattleship;

import java.util.ArrayList;

/**
 * This class is the driver test program for Sea class
 * to test if there is any logic error in the program
 * before finalising the final program.
 */
public class SeaTest {
    public static void main(String[] args) {

        ArrayList<Ship> shipList = new ArrayList<Ship>();
        Sea seaObject = new Sea();

        for (int shipNumber = 1; shipNumber <= 30; shipNumber++) {

            Ship shipObject = new Ship();
            shipObject.setCoordinates(seaObject.getRows(), seaObject.getColumns());
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("\nshipNumber: " + shipNumber + " -> ");
            shipObject.printCoordinates();

            boolean duplicate = false;

            if (checkOutOfRange(shipObject.getCoordinates(), seaObject.getRows(), seaObject.getColumns()) != true) {

                int[][] temp1 = shipObject.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < shipList.size(); index++) {

                        int[][] temp2 = shipList.get(index).getCoordinates();

                        if (checkDuplicate(temp1, temp2) == true) {
                            System.out.println("Duplicate match with shipNumber: " + (index + 1) + "\n");
                            seaObject.printMap();
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end 2nd for loop

                    if (duplicate != true) {
                        System.out.println("Add new ship to shipList: " + shipNumber + "\n");
                        shipList.add(shipObject);
                        seaObject.updateMap(shipObject.getCoordinates(), 'O');
                        seaObject.printMap();
                    }

                } else {
                    System.out.println("Add new ship to shipList: " + shipNumber + "\n");
                    shipList.add(shipObject);
                    seaObject.updateMap(shipObject.getCoordinates(), 'O');
                    seaObject.printMap();
                }

            } else {
                System.out.println("*** Ship coordinate is out of range!! ***\n");
                seaObject.printMap();
                shipNumber--;
            }
        }//end 1st for loop

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("\nNumber of ships: " + shipList.size());
        seaObject.printMap();
    }

    public static boolean checkOutOfRange(int[][] coordinates, int maxRow, int maxColumn) {

        maxRow = maxRow - 1;
        maxColumn = maxColumn - 1;

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > maxRow || coordinates[row][1] > maxColumn) {
                outOfBound = true;
                break;
            }
        }

        return outOfBound;
    }

    public static boolean checkDuplicate(int[][] array1, int[][] array2) {

        boolean duplicate = false;
        for (int row_a = 0; row_a < array1.length; row_a++) {
            for (int row_b = 0; row_b < array2.length; row_b++) {

                if ((array1[row_a][0] == array2[row_b][0]) && (array1[row_a][1] == array2[row_b][1])) {
                    System.out.println("Duplicate detected!!");
                    System.out.println("Duplicate value: (" + array1[row_a][0] + ", " + array1[row_a][1] + ")");
                    duplicate = true;
                    break;
                }
            }//end 2nd for loop

            if (duplicate == true) {
                break;
            }
        }//end 1st for loop

        return duplicate;
    }
}