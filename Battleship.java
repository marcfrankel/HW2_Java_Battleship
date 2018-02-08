import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class Battleship {

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
                        System.out.print(String.valueOf(board[row][column]) + " ");
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
        return 0;
    }

    public static boolean isShip(String target, String[] shipLocations) {
        // YOUR CODE BELOW: returns if location is ship
        return false;
    }

    public static int[] convertLocation(String coordinate) {
        // YOUR CODE BELOW: returns the integer indices corresponding
        //  with the coordinate string
        return null;
    }

    public static void main(String[] args) {
        int fileInd = (args.length > 0) ? Integer.parseInt(args[0])
            : new Random().nextInt(4);

        String filename = "game" + fileInd + ".txt";
        boolean isPlayerOneTurn = true;
        boolean gameOver = false;
        int playerOneTurns = 7;
        int playerTwoTurns = 7;
        int boardSize = 0;


        try {
            Scanner fileReader = new Scanner(new File(filename));
            Scanner inputScanner = new Scanner(System.in);
            // YOUR CODE BELOW: carries out the execution of the game
            //  using the methods defined above


            boardSize = Integer.parseInt(fileReader.nextLine());
            char[][] board = initBoard(boardSize);



            printBoard(1, board, playerOneTurns);


        } catch (FileNotFoundException e) {
            System.out.println("Make sure that " + filename
                + " is in the current directory!");
        }
    }
}