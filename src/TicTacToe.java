/*
  _    _ _     _       _ _   _  _____
 | |  | | |   (_)     (_) | (_)/ ____|
 | |  | | |__  _ _ __  _| |_ _| (___
 | |  | | '_ \| | '_ \| | __| |\___ \
 | |__| | |_) | | | | | | |_| |____) |
  \____/|_.__/|_|_| |_|_|\__|_|_____/

This script made by Bryce Sheridan, https://github.com/UbinitiS
Please don't copy :) I'm trying to do good.
This is just a pretty simple TicTacToe Game that runs in the output window.
My third version will use a GUI instead. I've scripted this game so that way you can
easily add different winning combinations.

 */
// Keep it clean with the imports :)
import java.util.*;


public class TicTacToe {

    // ArrayList for storing already used moved from either Player or CPU positions.
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    public static boolean gameReset = false;
    public static int gameBoardLayout = 9; // This is how many spaces are in the available game board.
    public static Random rand = new Random();

    public static void main(String[] args) {

        // You can call this when ever you want the game to reset to the main menu. Just set it to true.
        gameReset = false;

        // This is the game board layout. The script really only uses data from [0][0] - [4][4] the rest is just for style
        char[][] gameBoard = {
                {' ','|',' ','|',' ', ' ', ' ', ' ', '1','|','2','|','3'},
                {'-','+','-','+','-', ' ', ' ', ' ', '-','+','-','+','-'},
                {' ','|',' ','|',' ', ' ', ' ', ' ', '4','|','5','|','6'},
                {'-','+','-','+','-', ' ', ' ', ' ', '-','+','-','+','-'},
                {' ','|',' ','|',' ', ' ', ' ', ' ', '7','|','8','|','9'}
        };
        clearOutput();
        System.out.println("How to play the game. Well it's really easy you see. Just type what number you want\n" +
                "compared to the game board down below. Oh, and getting all corners is a winner too!");
        System.out.println("Hit \"ENTER\" to start!");
        Scanner enterWait = new Scanner(System.in);
        enterWait.nextLine();
        clearOutput();
        // This is where the good stuff is. This is the main game function.
        Scanner scan = new Scanner(System.in);
        while (true) {

            // This if statement is called when the gameReset = true.
            if (gameReset) {
                Scanner enterRequired = new Scanner(System.in);
                enterRequired.nextLine();
                playerPositions.clear();
                cpuPositions.clear();
                main(null);
                break;
            }

            System.out.print("Enter your placement (1-9)");
            int playerPlacement = scan.nextInt();

            // Makes sure that the user picks a number between 1-gameBoardLayout
            while (playerPlacement > gameBoardLayout || playerPlacement < 1) {
                System.out.print("Please choose a value between 1 - " + gameBoardLayout + ". Please try again: ");
                playerPlacement = scan.nextInt();
            }

            // This while loop determines if the player or CPU already used that spot. If so it loops
            while (playerPositions.contains(playerPlacement) || cpuPositions.contains(playerPlacement)) {
                System.out.print("Oops! That place already is taken! Try again: ");
                playerPlacement = scan.nextInt();
            }
            playerPiece(gameBoard, playerPlacement, "player");

            int cpuPlacement = rand.nextInt(9) + 1;

            // This is the cpu Placement. It loops until it finds an open spot. If after 10 and it doesn't find it, it skips.
            boolean loopBreak = false;
            while (playerPositions.contains(cpuPlacement) || cpuPositions.contains(cpuPlacement)) {
                cpuPlacement = rand.nextInt(9) + 1;
                if (playerPositions.size() + cpuPositions.size() == 9) {
                    loopBreak = true;
                    break;
                }
            }
            clearOutput();
            // Only places the piece on the gameBoard if a spot is found.
            if (!loopBreak) {
                playerPiece(gameBoard, cpuPlacement, "cpu");
            }
            printGameBoard(gameBoard);
            System.out.println(checkWinner());

        }

    }


    // This method calculates if someone wins.
    public static String checkWinner() {

        // This is all the possible winning combinations, plus a custom one called Corners.
        List<List> winning = new ArrayList<List>();
        winning.add(Arrays.asList(1,2,3));
        winning.add(Arrays.asList(4,5,6));
        winning.add(Arrays.asList(7,8,9));
        winning.add(Arrays.asList(1,4,7));
        winning.add(Arrays.asList(2,5,8));
        winning.add(Arrays.asList(3,6,9));
        winning.add(Arrays.asList(1,5,9));
        winning.add(Arrays.asList(3,5,7));
        winning.add(Arrays.asList(1,3,7,9));

        // I split up the player and cpu and tie up because it wasn't working right the first time.
        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                gameReset = true;
                return "Congratulation's You Win\nHit enter to play again.";

            }
        }
        for (List l : winning) {
            if (cpuPositions.containsAll(l)) {
                gameReset = true;
                return "You lost!\nHit enter to play again.";

            }
        }
        if (playerPositions.size() + cpuPositions.size() == 9) {
            gameReset = true;
            return "Tie! Please play again\nHit enter to play again.";

        }
        return "";
    }


    // This method when called prints out the gameBoard using for loops.
    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {

            for (char c : row) {
                System.out.print(c);
            }

            System.out.println();

        }

    }

    // This method when called places the playerPiece on a char array between 1-9
    // called by playerPiece(gameBoard, POS, either player or cpu)
    public static void playerPiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }


        // Rewrote this switch statement.
        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
            default -> {
            }
        }

    }

    // This just clears the output window.
    public static void clearOutput() {

        for (int i = 0; i < 15; i++) {
            System.out.println();
        }

    }

}
