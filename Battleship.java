import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;


public class Battleship {

    boolean isPlayerOneTurn = true;
    boolean gameOver = false;
    static int playerOneHits = 7;
    static int playerTwoHits = 7;
    static int boardSize = 0;
    static String[] playerOneShips;
    static String[] playerTwoShips;

    public static char[][] initBoard(int n) {
        char[][] output = new char [n][n];
        for (int row = 0; row < n; row++) {

            for (int column = 0; column < n; column++) {
                output[row][column] = "~".charAt(0);
            }
        }

        return output;
    }

    public static void printBoard(int playerTurn, char[][] board,
        int hitsLeft) {
        // YOUR CODE BELOW: prints the board and player information
        //  (as in the example output)

        if (playerTurn == 1) {
            System.out.println("Player 1 (" + hitsLeft + " hits left):");

            for (int row = 0; row < board.length; row++) {

                for (int column = 0; column < board.length; column++) {
                    if (column < board.length - 1){
                        System.out.print(String.valueOf(board[row][column]) +
                            " ");
                    } else {
                        System.out.print(String.valueOf(board[row][column]));
                    }

                }
                System.out.print("\n");
            }

            System.out.print("Enter missile location: ");
            Scanner guessScanner = new Scanner(System.in);
            String guess = guessScanner.nextLine();

            int shipHit = fireMissile(board, guess, playerTwoShips, hitsLeft);
            if (shipHit == 1) {
                System.out.println("Hit!");
                hitsLeft--;
                playerOneHits--;
            } else if (shipHit == 0) {
                System.out.println("Miss!");
            } else {
                System.out.println("target(" + guess
                    + ") has already been chosen!");
            }
            System.out.println("Player 1 (" + hitsLeft + " hits left):");
            for (int row = 0; row < board.length; row++) {

                for (int column = 0; column < board.length; column++) {
                    if (column < board.length - 1){
                        System.out.print(String.valueOf(board[row][column]) +
                            " ");
                    } else {
                        System.out.print(String.valueOf(board[row][column]));
                    }

                }
                System.out.print("\n");
            }
        } else {
            //make exact copy as above
            System.out.println("Player 2 (" + hitsLeft + " hits left):");

            for (int row = 0; row < board.length; row++) {

                for (int column = 0; column < board.length; column++) {
                    if (column < board.length - 1){
                        System.out.print(String.valueOf(board[row][column]) +
                            " ");
                    } else {
                        System.out.print(String.valueOf(board[row][column]));
                    }

                }
                System.out.print("\n");
            }

            System.out.print("Enter missile location: ");
            Scanner guessScanner = new Scanner(System.in);
            String guess = guessScanner.nextLine();

            int shipHit = fireMissile(board, guess, playerOneShips, hitsLeft);
            if (shipHit == 1) {
                System.out.println("Hit!");
                hitsLeft--;
                playerTwoHits--;
            } else if (shipHit == 0) {
                System.out.println("Miss!");
            } else {
                System.out.println("target(" + guess
                    + ") has already been chosen!");
            }
            System.out.println("Player 2 (" + hitsLeft + " hits left):");
            for (int row = 0; row < board.length; row++) {

                for (int column = 0; column < board.length; column++) {
                    if (column < board.length - 1){
                        System.out.print(String.valueOf(board[row][column]) +
                            " ");
                    } else {
                        System.out.print(String.valueOf(board[row][column]));
                    }

                }
                System.out.print("\n");
            }
        }
    }

    public static int fireMissile(char[][] board, String target,
        String[] shipLocations, int hitsLeft) {
        // YOUR CODE BELOW: updates the board given the missile
        //  target and returns if a ship is hit
        int[] location = convertLocation(target);

        if (isShip(target, shipLocations)) {


            if (String.valueOf(board[location[0]][location[1]]).equals("~")) {
                board[location[0]][location[1]] = "X".charAt(0);
                return 1;
            } else {

                return 2;
            }
        } else {
            board[location[0]][location[1]] = "O".charAt(0);
        }
        return 0;
    }

    public static boolean isShip(String target, String[] shipLocations) {
        // YOUR CODE BELOW: returns if location is ship
         for (String s: shipLocations) {
            if (target.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int[] convertLocation(String coordinate) {
        // YOUR CODE BELOW: returns the integer indices corresponding
        //  with the coordinate string
        int[] output = {Character.getNumericValue(coordinate.charAt(0)) - 10,
            Character.getNumericValue(coordinate.charAt(1)) - 1};

        return output;
    }

    public static void main(String[] args) {
        int fileInd = (args.length > 0) ? Integer.parseInt(args[0])
            : new Random().nextInt(4);

        String filename = "game" + fileInd + ".txt";



        try {
            Scanner fileReader = new Scanner(new File(filename));
            Scanner inputScanner = new Scanner(System.in);
            // YOUR CODE BELOW: carries out the execution of the game
            //  using the methods defined above


            boardSize = Integer.parseInt(fileReader.nextLine());
            char[][] board1 = initBoard(boardSize);
            char[][] board2 = initBoard(boardSize);
            playerOneShips = fileReader.nextLine().split("\\s+");
            playerTwoShips = fileReader.nextLine().split("\\s+");
            playerOneHits = playerOneShips.length;
            playerTwoHits = playerTwoShips.length;


            while (playerOneHits > 0 || playerTwoHits > 0 ) {
                printBoard(1, board1, playerOneHits);



                System.out.println("\n----------\n");

                if (playerOneHits == 0) {
                    break;
                }

                printBoard(2, board2, playerTwoHits);

                System.out.println("\n----------\n");
            }
            if (playerOneHits == 0) {
                System.out.println("The winner is Player 1");
            } else {
                System.out.println("The winner is Player 2");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Make sure that " + filename
                + " is in the current directory!");
        }
    }
}