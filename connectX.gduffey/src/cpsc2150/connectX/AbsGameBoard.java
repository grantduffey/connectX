package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {

    public static int ROWSIZE = 0;
    public static int COLSIZE = 0;
    public static int TOWIN = 0;

    /**
     * @return the contents of board printed out in a particular format
     * @post toString = a properly formatted representation of the board
     */
    @Override
    public String toString() {
        String printBoard = "|";
        for (int i = 0; i < COLSIZE; ++i) {
            if (i < 10) printBoard = printBoard + " " + i + "|";
            if (i >= 10) printBoard = printBoard + i + "|";
        }

        BoardPosition pos;
        for (int i = (ROWSIZE-1); i >= 0; --i) {
            printBoard = printBoard + "\n|";
            for (int j = 0; j < COLSIZE; ++j) {
                pos = new BoardPosition(i, j);
                printBoard = printBoard + whatsAtPos(pos) + " |";
            }
        }
        return printBoard;
    }

}
