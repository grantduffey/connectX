package cpsc2150.connectX;

public class BoardPosition {
    private int row;
    private int col;

    /**
     * @param myRow the number of rows
     * @param myCol the number of columns
     * @post row = myRow, col = myCol
     */
    public BoardPosition(int myRow, int myCol) {
        row = myRow;
        col = myCol;
    }

    /**
     * @return row
     * @post getRow = row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return col
     * @post getColumn = col
     */
    public int getColumn() {
        return col;
    }

    /**
     * @param obj the object being compared
     * @return true if the both board positions are equal, false otherwise
     * @post equals = true if the board positions are equal
     *       equals = false if the board positions are different
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof BoardPosition) {
            BoardPosition pos = (BoardPosition) obj;

            if (col == pos.col && row == pos.row) return true;
        }

        return false;
    }

    /**
     * @return string of row and col formatted as "<row,col>"
     * @post toString = properly formatted string of row and col
     */
    @Override
    public String toString() {
        String string = "<";
        string = string + row + ", " + col + ">";
        return string;
    }
}