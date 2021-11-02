package cpsc2150.connectX;

public class GameBoard extends AbsGameBoard {
    /**
     * @invariant A token cannot be placed out of bounds
     * @invariant A token cannot be added to an already full column
     * @invariant A token cannot have an empty space below it
     * @invariant <0,0> is the bottom left of the board and the top right of the board is <ROWSIZE - 1, COLSIZE - 1>
     * @invariant The the game board size is ROWSIZE*COLSIZE
     *
     *
     * @Correspondence rowNum = ROWSIZE
     * @Correspondence colNum = COLSIZE
     * @Correspondence winNum = TOWIN
     */

    private char board[][];

    /**
     * @param rowNum the number of rows the board will have
     * @param colNum the number of columns the board will have
     * @param winNum the number in a row needed to win the game
     * @post GameBoard = a game board with size[rowNum][colNum] with each position initialized to ' '
     */
    public GameBoard(int rowNum, int colNum, int winNum) {
        ROWSIZE = rowNum;
        COLSIZE = colNum;
        TOWIN = winNum;

        board = new char[ROWSIZE][COLSIZE];
        for (int i = 0; i < ROWSIZE; i++) {
            for (int j = 0; j < COLSIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public int getNumRows() {
        return ROWSIZE;
    }

    public int getNumColumns() {
        return COLSIZE;
    }

    public int getNumToWin() {
        return TOWIN;
    }

    public void placeToken(char p, int c) {

        // Find the first blank row in the column to place the token
        for (int i = 0; i < ROWSIZE; i++) {
            if (board[i][c] == ' ') {
                board[i][c] = p;
                break;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) { return board[pos.getRow()][pos.getColumn()]; }
}