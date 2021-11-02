package cpsc2150.connectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard {
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
     * @Correspondence this = Map<Character, List<BoardPosition>> board;
     */


    private Map<Character, List<BoardPosition>> board;

    /**
     * @param rowNum the number of rows the board will have
     * @param colNum the number of columns the board will have
     * @param winNum the number in a row needed to win the game
     * @post GameBoardMem = an empty game board
     */
    public GameBoardMem(int rowNum, int colNum, int winNum) {
        ROWSIZE = rowNum;
        COLSIZE = colNum;
        TOWIN = winNum;

        board = new HashMap<>();
    }

    public int getNumRows() { return ROWSIZE; }

    public int getNumColumns() { return COLSIZE; }

    public int getNumToWin() { return TOWIN; }

    public void placeToken(char p, int c) {

        BoardPosition pos;
        int rowSize = getNumRows();

        if (!board.containsKey(p)) {
            board.put(p, new ArrayList<>());
        }

        for (int i = 0; i < rowSize; i++) {
            pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' ') {
                board.get(p).add(pos);
                return;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> i: board.entrySet())
            if (i.getValue().contains(pos)) return i.getKey();
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (board.containsKey(player)) {
            if (board.get(player).contains(pos)) return true;
        }
        return false;
    }
}
