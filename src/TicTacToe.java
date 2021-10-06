import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static boolean gameReset = false;

    public static void main(String[] args) {

        gameReset = false;
        char[][] gameBoard = {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };
        clearOutput();
        Scanner scan = new Scanner(System.in);
        System.out.println("1|2|3\n-+-+-\n4|5|6\n-+-+-\n7|8|9\n");
        System.out.println("How to play:\nObjective is to get 3 in a row of your piece, X. You can also win by filling all 4 corners.\nGood luck! Hit enter to play...");
        String randomSPH = scan.nextLine();
        clearOutput();
        while (true) {

            if (gameReset) {
                Scanner enterRequired = new Scanner(System.in);
                String randomString = enterRequired.nextLine();
                playerPositions.clear();
                cpuPositions.clear();
                main(null);
                break;
            }

            System.out.print("Enter your placement (1-9)");
            int playerPlacement = scan.nextInt();

            while (playerPositions.contains(playerPlacement) || cpuPositions.contains(playerPlacement)) {
                System.out.print("Oops! That place already is taken! Try again: ");
                playerPlacement = scan.nextInt();
            }
            playerPiece(gameBoard, playerPlacement, "player");

            Random rand = new Random();
            int cpuPlacement = rand.nextInt(9) + 1;

            int loopTrys = 0;
            boolean loopBreak = false;
            while (playerPositions.contains(cpuPlacement) || cpuPositions.contains(cpuPlacement)) {
                cpuPlacement = rand.nextInt(9) + 1;
                loopTrys += 1;
                if (loopTrys == 10) {
                    loopBreak = true;
                    break;
                }
            }
            clearOutput();
            if (!loopBreak) {
                playerPiece(gameBoard, cpuPlacement, "cpu");
            }
            printGameBoard(gameBoard);
            System.out.println(checkWinner());

        }

    }

    public static String checkWinner() {

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

    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {

            for (char c : row) {
                System.out.print(c);
            }

            System.out.println();

        }

    }

    public static void playerPiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }

    }

    public static void clearOutput() {

        for (int i = 0; i < 15; i++) {
            System.out.println();
        }

    }

}
