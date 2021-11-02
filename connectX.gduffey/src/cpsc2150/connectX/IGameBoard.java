package cpsc2150.connectX;

/**
 * This interface tracks the information that forms the game board and what actions can be done with it
 * Defines: ROWSIZE: Z
 *          COLSIZE: Z
 *          TOWIN: Z
 *          MAXROWSIZE: Z
 *          MINROWSIZE: Z
 *          MAXCOLSIZE: Z
 *          MINCOLSIZE Z
 *          MAXTOWIN: Z
 *          MINTOWIN: Z
 *
 * Constraints: MINROWSIZE <= ROWSIZE <= MAXROWSIZE;
 *              MINCOLSIZE <= COLSIZE <= MAXCOLSIZE;
 *              MINTOWIN <= TOWIN <= MAXTOWIN.
 *              TOWIN <= ROWSIZE;
 *              TOWIN <= COLSIZE;
 *              MAXROWSIZE = 100;
 *              MAXCOLSIZE = 100;
 *              MAXTOWIN = 25;
 *              MINROWSIZE = 3;
 *              MINCOLSIZE = 3;
 *              MINTOWIN = 3;
 *
 * Initialization Ensures: A game board of size ROWSIZE * COLSIZE with each position empty
 */

public interface IGameBoard {

    public static final int MAXROWSIZE = 100;
    public static final int MAXCOLSIZE = 100;
    public static final int MAXTOWIN = 25;
    public static final int MINROWSIZE = 3;
    public static final int MINCOLSIZE = 3;
    public static final int MINTOWIN = 3;

    /**
     * @return the number of row
     * @post getNumRows = number of rows
     */
    public int getNumRows();

    /**
     * @return the number of columns
     * @post getNumColumns = number of columns
     */
    public int getNumColumns();

    /**
     * @return the number of tokens needed in a row to win
     * @post getNumToWin = number of tokens needed in a row to win
     */
    public int getNumToWin();

    /**
     * @param c the column the player chose
     * @pre 0 <= c < COLSIZE
     * @return true if any row in that column is empty, false if every row contains a player character
     * @post checkIfFree = true if any row in the selected column is empty
     */
    default boolean checkIfFree(int c) {

        int rowSize = getNumRows();
        BoardPosition pos;
        for (int i = 0; i < rowSize; i++) {
            pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' ') return true;
        }
        return false;
    }

    /**
     * @param c the column the player chose
     * @pre 0 <= c < COLSIZE
     *      c = the last column played
     * @return true if the current player has won the game, false if the player has not won the game
     * @post checkForWin = true if the player won at the top non-empty row in column c
     *       checkForWin = false if the player has not won at the top non-empty row in column c
     */
    default boolean checkForWin(int c) {
        // Find the topmost row in c with a non-blank character

        int rowSize = getNumRows();
        BoardPosition pos;


        int myRow = rowSize - 1;
        for (int i = rowSize - 1; i >= 0; i--) {
            pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) != ' ') {
                myRow = i;
                break;
            }
        }

        pos = new BoardPosition(myRow, c);

        char player = whatsAtPos(pos);

        if (checkHorizWin(pos, player)) return true;
        if (checkVertWin(pos, player)) return true;
        if (checkDiagWin(pos, player)) return true;
        return false;
    }

    /**
     * @param p the player's token
     * @param c the column the player chose
     * @pre checkIfFree == true
     *      c is in bounds
     *      0 <= c < COLSIZE
     *      p == 'X' | p == 'O'
     * @post the player token has been placed in board at the top non-empty row in column c
     */
    public void placeToken(char p, int c);

    /**
     * @param pos the position on the board
     * @param p the player's token
     * @pre the player has placed a token on the board, pos is in bounds, and pos = the last token placed
     * @return true if the the player's token appears TOWIN times in a row, and false otherwise
     * @post checkHorizWin = true if the the player's token appears TOWIN times in a row
     *       checkHorizWin = false if the the player's token does not appear TOWIN times in a row
     */
    default boolean checkHorizWin(BoardPosition pos, char p) {
        int inARow = 0;
        int colSize = getNumColumns();
        int winNum = getNumToWin();
        BoardPosition currPos;


        for (int i = pos.getColumn(); i < colSize; i++) {
            currPos = new BoardPosition(pos.getRow(), i);
            if (isPlayerAtPos(currPos, p)) inARow++;
            else {
                break;
            }
            if (inARow == winNum) return true;
        }

        inARow--;
        for (int i = pos.getColumn(); i >= 0; i--) {
            currPos = new BoardPosition(pos.getRow(), i);
            if (isPlayerAtPos(currPos, p)) inARow++;
            else {
                break;
            }
            if (inARow == winNum) return true;
        }
        return false;
    }

    /**
     * @param pos the position on the board
     * @param p the player's token
     * @pre the player has placed a token on the board, pos is in bounds, and pos = the last token placed
     * @return true if the the player's token appears TOWIN times in a row, and false otherwise
     * @post checkVertWin = true if the the player's token appears TOWIN times in a row
     *       checkVertWin = false if the the player's token does not appear TOWIN times in a row
     */
    default boolean checkVertWin(BoardPosition pos, char p) {
        int inARow = 0;
        int rowSize = getNumRows();
        int winNum = getNumToWin();
        BoardPosition currPos;


        for (int i = pos.getRow(); i < rowSize; i++) {
            currPos = new BoardPosition(i, pos.getColumn());
            if (isPlayerAtPos(currPos, p)) inARow++;
            else {
                break;
            }
            if (inARow == winNum) return true;
        }

        inARow--;
        for (int i = pos.getRow(); i >= 0; i--) {
            currPos = new BoardPosition(i, pos.getColumn());
            if (isPlayerAtPos(currPos, p)) inARow++;
            else {
                break;
            }
            if (inARow == winNum) return true;
        }
        return false;
    }

    /**
     * @param pos the position on the board
     * @param p the player's token
     * @pre the player has placed a token on the board, pos is in bounds, and pos = the last token placed
     * @return true if the the player's token appears TOWIN times in a row, and false otherwise
     * @post checkDiagWin = true if the the player's token appears TOWIN times in a row
     *       checkDiagWin = false if the the player's token does not appear TOWIN times in a row
     */
    default boolean checkDiagWin(BoardPosition pos, char p) {
        int inARow = 0;
        int rowSize = getNumRows();
        int colSize = getNumColumns();
        int winNum = getNumToWin();
        BoardPosition currPos = new BoardPosition(pos.getRow(), pos.getColumn());

        // Check the diagonal down and to the left
        while (isPlayerAtPos(currPos, p)) {
            inARow++;
            if (inARow == winNum) return true;
            currPos = new BoardPosition((currPos.getRow() - 1), (currPos.getColumn() - 1));
            if (currPos.getRow() < 0 || currPos.getColumn() < 0) break;
        }

        currPos = pos;
        inARow--;

        // Check the diagonal up and to the right
        while (isPlayerAtPos(currPos, p)) {
            inARow++;
            if (inARow == winNum) return true;
            currPos = new BoardPosition((currPos.getRow() + 1), (currPos.getColumn() + 1));
            if (currPos.getRow() > (rowSize - 1) || currPos.getColumn() > (colSize - 1)) break;
        }

        // Reset to original board position
        currPos = pos;
        inARow = 0;

        // Check the diagonal down and to the right
        while (isPlayerAtPos(currPos, p)) {
            inARow++;
            if (inARow == winNum) return true;
            currPos = new BoardPosition((currPos.getRow() - 1), (currPos.getColumn() + 1));
            if (currPos.getRow() < 0 || currPos.getColumn() > (colSize - 1)) break;
        }

        currPos = pos;
        inARow--;

        // Check the diagonal up and to the left
        while (isPlayerAtPos(currPos, p)) {
            inARow++;
            if (inARow == winNum) return true;
            currPos = new BoardPosition((currPos.getRow() + 1), (currPos.getColumn() - 1));
            if (currPos.getRow() > (rowSize - 1) || currPos.getColumn() < 0) break;
        }

        return false;
    }

    /**
     * @param pos the position on the board
     * @pre pos is in bounds
     * @return the char at pos
     * @post whatsAtPos = the character at pos
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @param pos the position on the board
     * @param player the player's token character
     * @pre pos is in bounds
     * @return true if player character is at pos, and false if player character is not at pos
     * @post isPlayerAtPos = true if the player character is at that position
     *       isPlayerAtPos = false if the player character is not at that position
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (whatsAtPos(pos) == player) return true;
        else return false;
    }

    /**
     * @pre there are no wins on the board
     * @return true if every position on the board has been taken, and no win condition has been met, otherwise false
     * @post checkTie = true if every position on the board has been filled
     *       checkTie = false if every position on the board has not been filled
     */
    default boolean checkTie() {
        int rowSize = getNumRows();
        int colSize = getNumColumns();
        BoardPosition pos;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                pos = new BoardPosition(i, j);
                if (whatsAtPos(pos) == ' ') return false;
            }
        }
        return true;
    }
}


