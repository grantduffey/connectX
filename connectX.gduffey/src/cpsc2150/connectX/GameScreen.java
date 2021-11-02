package cpsc2150.connectX;

import java.util.Scanner;
public class GameScreen {
    private static int turn = 0;
    private static int numPlayers = 0;
    private static int minPlayers = 2;
    private static int playerCharSize = 10;
    private static int rowNum = 0;
    private static int colNum = 0;
    private static int winNum = 0;
    private static char whichGame;
    private static char playerChar[];
    private static boolean replayCondition = true;

    public static void main(String[] args) {

        AbsGameBoard gb = newGame();
        Scanner input = new Scanner(System.in);

        while(replayCondition) {
            System.out.println(gb.toString());

            char player = ' ';
            int col = 0;
            boolean validInput = false;
            player = playerChar[turn];

            // Repeat until the player's input is a valid choice
            while(!validInput) {
                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                col = input.nextInt();
                if (isValid(col, gb.getNumColumns())) {
                    if (gb.checkIfFree(col)) validInput = true;
                    else {
                        System.out.println("Column is full");
                        validInput = false;
                    }
                }
                else validInput = false;
            }

            gb.placeToken(player, col);

            // Determine if the game has ended
            if (gb.checkForWin(col)) {
                if (checkForReplay(true, gb)) {
                    gb = newGame();
                }
                continue;
            }
            else {
                if (gb.checkTie()) {
                    if (checkForReplay(false, gb)) {
                        gb = newGame();
                    }
                    continue;
                }
            }
            turn = (turn + 1) % numPlayers;
        }
    }

    /**
     * @param column the column the player chose
     * @return true if 0 <= column < maxCol, else return false
     * @post isValid = true if 0 <= column < COLSIZE
     *       isValid = false if column < 0 or column > maxCol
     */
    private static boolean isValid(int column, int colSize){

        if (column < 0) {
            System.out.println("Column cannot be less than 0");
            return false;
        }
        if (column > (colSize - 1)) {
            System.out.println("Column cannot be greater than " + (colSize - 1));
            return false;
        }
        return true;
    }

    /**
     * @param didPlayerWin the result of checkForWin()
     * @post display a message telling the player the result of the game
     */
    private static void printResult(boolean didPlayerWin) {
        char player = ' ';
        player = playerChar[turn];

        if (didPlayerWin) {
            System.out.println("Player " + player + " Won!");
        }
        else {
            System.out.println("The game has ended in a tie.");
        }
    }

    /**
     * @param didPlayerWin = whether or not a player has won.
     * @param gb = the current game board the player's are playing on
     * @post if doReplay == 'Y', then replayCondition = true
     *       if doReplay == 'y', then replayCondition = true
     *       else replayCondition = false
     */
    private static boolean checkForReplay(boolean didPlayerWin, AbsGameBoard gb) {
        char doReplay = ' ';
        boolean validInput = false;
        Scanner input = new Scanner(System.in);

        // Print the final board and the result of the game
        System.out.println(gb.toString());
        printResult(didPlayerWin);

        // Repeat until the user inputs a valid answer
        while (!validInput) {
            System.out.println("Would you like to replay again? Y/N");
            doReplay = input.next().charAt(0);
            if (doReplay == 'y' || doReplay == 'Y' || doReplay == 'n' || doReplay == 'N') {
                validInput = true;
            }
        }

        turn = 0;
        if (doReplay == 'y' || doReplay == 'Y') {
            replayCondition = true;
            return true;
        }
        else {
            replayCondition = false;
            return false;
        }
    }

    /**
     * @return A properly initialized game board with the player's specifications
     * @post newGame() = A properly initialized instance of either GameBoard or GameBoardMem with
     *       the player's specifications
     */
    private static AbsGameBoard newGame() {
        Scanner input = new Scanner(System.in);
        playerChar = new char[playerCharSize];
        boolean validInput = true;
        numPlayers = 0;

        // Ensure there is a proper amount of players
        while (numPlayers < minPlayers || numPlayers > playerCharSize) {
            System.out.println("How many players?");
            numPlayers = input.nextInt();
            if (numPlayers < minPlayers) System.out.println("Must be at least " + minPlayers + " players");
            if (numPlayers > playerCharSize) System.out.println("Must be " + playerCharSize + " players or fewer");
        }


        int count = 0;

        // Ensure each player has a unique character
        while (validInput) {

            System.out.println("Enter the character to represent player " + (count + 1));
            playerChar[count] = input.next().charAt(0);
            playerChar[count] = Character.toUpperCase(playerChar[count]);

            for (int j = 0; j < count; j++) {
                if (playerChar[count] == playerChar[j]) {
                    System.out.println(playerChar[count] + " is already taken as a player token!");
                    count--;
                }
            }

            count++;
            if (count > numPlayers - 1) validInput = false;
        }

        // Create an instance of IGameBoard to access max and min row, column, and win condition sizes
        IGameBoard gb = new GameBoard(0, 0, 0);

        rowNum = 0;
        // Ensure a valid number of rows
        while (rowNum < gb.MINROWSIZE || rowNum > gb.MAXROWSIZE) {
            System.out.println("How many rows should be on this board?");
            rowNum = input.nextInt();
            if (rowNum < gb.MINROWSIZE) System.out.println("Number of rows must be " + gb.MINROWSIZE + " or greater");
            if (rowNum > gb.MAXROWSIZE) System.out.println("Number of rows must be " + gb.MAXROWSIZE + " or less");
        }

        colNum = 0;
        //Ensure a valid number of columns
        while (colNum < gb.MINCOLSIZE || colNum > gb.MAXCOLSIZE) {
            System.out.println("How many columns should be on this board?");
            colNum = input.nextInt();
            if (colNum < gb.MINCOLSIZE) System.out.println("Number of columns must be " + gb.MINCOLSIZE + " or greater");
            if (colNum > gb.MAXCOLSIZE) System.out.println("Number of columns must be " + gb.MAXCOLSIZE + " or less");
        }

        winNum = 0;
        // Ensure a valid number in a row to win
        while (winNum < gb.MINTOWIN || winNum > gb.MAXTOWIN || (winNum > rowNum && winNum > colNum)) {
            System.out.println("How many in a row to win?");
            winNum = input.nextInt();

            if (winNum < gb.MINTOWIN)
                System.out.println("The number in a row to win must be " + gb.MINTOWIN + " or greater");
            if (winNum > gb.MAXTOWIN)
                System.out.println("The number in a row to win must be " + gb.MAXTOWIN + " or less");
            else if (winNum > rowNum && winNum > colNum)
                System.out.println("The number in a row to win must be less than or equal to the number of columns, "
                        + colNum + ", or less than or equal to the number of rows, " + rowNum);
        }

        // Determine which implementation of the game board should be used
        while (true) {
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            whichGame = input.next().charAt(0);
            whichGame = Character.toUpperCase(whichGame);
            if (whichGame == 'F' || whichGame == 'M') {
                if (whichGame == 'F') return new GameBoard(rowNum, colNum, winNum);
                else return new GameBoardMem(rowNum, colNum, winNum);
            }
            else System.out.println("Please enter F or M");
        }
    }
}