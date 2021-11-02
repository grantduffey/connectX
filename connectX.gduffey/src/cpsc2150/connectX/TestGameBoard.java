package cpsc2150.connectX;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestGameBoard {

    public TestGameBoard(){}

    private IGameBoard GameBoardFactory(int row, int col, int win) {
        return new GameBoard(row, col, win);
    }

    @Test
    public void test_Constructor_Max(){
        int row = 100;
        int col = 100;
        int win = 25;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_Constructor_Min(){
        int row = 3;
        int col = 3;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_Constructor_Rect(){
        int row = 4;
        int col = 3;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_CheckIfFree_Empty() {

        int row = 5;
        int col = 5;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        assertTrue(gb.checkIfFree(1));
    }

    @Test
    public void test_CheckIfFree_Full() {

        int row = 5;
        int col = 5;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        int temp = 1;
        for (int i = 0; i < row; i++) {
            if (temp == 1) {
                gb.placeToken('X', 4);
            }
            else {
                gb.placeToken('O', 4);
            }
            temp = (temp + 1) % 2;
        }

        assertFalse(gb.checkIfFree(4));
    }

    @Test
    public void test_CheckIfFree_Partially_Full() {

        int row = 5;
        int col = 5;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        int temp = 1;
        for (int i = 0; i < row/2; i++) {
            if (temp == 1) {
                gb.placeToken('X', 4);
            }
            else {
                gb.placeToken('O', 4);
            }
            temp = (temp + 1) % 2;
        }

        assertTrue(gb.checkIfFree(0));
    }

    @Test
    public void test_checkHorizWin_One_Token() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);

        assertFalse(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void test_checkHorizWin_Win_Last_Token_End() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(0, 3);

        assertTrue(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void test_checkHorizWin_Win_Last_Token_Middle() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 1);

        BoardPosition pos = new BoardPosition(0, 3);

        assertTrue(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void test_checkHorizWin_Four_Not_Sequential() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);

        BoardPosition pos = new BoardPosition(0, 3);

        assertFalse(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void test_checkVertWin_One_Token() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);

        assertFalse(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void test_checkVertWin_Win_Last_Token_Top() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(3, 0);

        assertTrue(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void test_checkVertWin_Four_Not_Sequential() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 1);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(4, 3);

        assertFalse(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void test_checkVertWin_Four_Not_Same_Char() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        BoardPosition pos = new BoardPosition(4, 3);

        assertFalse(gb.checkVertWin(pos, 'O'));
    }

    @Test
    public void test_checkDiagWin_One_Token(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0, 2);

        assertFalse(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Last_Token_Bottom_Right(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 4);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(0, 3);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Last_Token_Top_Left(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(3, 0);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Token_In_Middle_Down_Right_Diag(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(1, 2);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Last_Token_Bottom_Left(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);

        BoardPosition pos = new BoardPosition(0, 1);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Last_Token_Top_Right(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 4);

        BoardPosition pos = new BoardPosition(3, 4);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkDiagWin_Win_Token_In_Middle_Down_Left_Diag(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(1, 2);

        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void test_checkTie_Full_Board_No_Wins(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 3);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 2);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);

        assertTrue(gb.checkTie());
    }

    @Test
    public void test_checkTie_One_Position_Open(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 3);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 2);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);

        assertFalse(gb.checkTie());
    }

    @Test
    public void test_checkTie_Empty_Board(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        assertFalse(gb.checkTie());
    }

    @Test
    public void test_checkTie_Half_Full_Board() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);

        assertFalse(gb.checkTie());
    }

    @Test
    public void test_whatsAtPos_Empty() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        BoardPosition pos = new BoardPosition(0,0);

        assertEquals(gb.whatsAtPos(pos), ' ');
    }

    @Test
    public void test_whatsAtPos_One_Token() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 1);

        BoardPosition pos = new BoardPosition(0,1);

        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void test_whatsAtPos_Bottom_Right_Bound() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);


        BoardPosition pos = new BoardPosition(0,4);

        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void test_whatsAtPos_Bottom_Left_Bound() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);

        BoardPosition pos = new BoardPosition(0,0);

        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void test_whatsAtPos_Top_Right_Bound() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);

        BoardPosition pos = new BoardPosition(4,4);

        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void test_isPlayerAtPos_Empty() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        BoardPosition pos = new BoardPosition(0,0);

        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void test_whatsAtPos_Filled_Middle_Column() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(0,2);

        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void test_whatsAtPos_Filled_Top_Left_Boundary() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(0,4);

        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void test_isPlayerAtPos_Bottom_Right_Wrong_Character() {
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);

        BoardPosition pos = new BoardPosition(0,4);

        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void test_isPlayerAtPos_Top_Right_Boundary_Rect() {
        int row = 3;
        int col = 4;
        int win = 3;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(2,3);

        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void test_placeToken_Empty_Board(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        myBoard[0][2] ='X';
        gb.placeToken('X', 2);

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_placeToken_Partially_Full(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        myBoard[0][0] ='X';
        gb.placeToken('X', 0);
        myBoard[1][0] ='O';
        gb.placeToken('O', 0);
        myBoard[2][0] ='X';
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_placeToken_Bottom_Left_Boundary(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        myBoard[0][0] ='X';
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_placeToken_Top_Right_Boundary(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        myBoard[0][4] ='X';
        gb.placeToken('X', 4);
        myBoard[1][4] ='O';
        gb.placeToken('O', 4);
        myBoard[2][4] ='X';
        gb.placeToken('X', 4);
        myBoard[3][4] ='O';
        gb.placeToken('O', 4);
        myBoard[4][4] ='X';
        gb.placeToken('X', 4);

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    @Test
    public void test_placeToken_Top_Left_Boundary(){
        int row = 5;
        int col = 5;
        int win = 4;
        IGameBoard gb = this.GameBoardFactory(row, col, win);

        char myBoard[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                myBoard[i][j] = ' ';
            }
        }

        myBoard[0][0] ='X';
        gb.placeToken('X', 0);
        myBoard[1][0] ='O';
        gb.placeToken('O', 0);
        myBoard[2][0] ='X';
        gb.placeToken('X', 0);
        myBoard[3][0] ='O';
        gb.placeToken('O', 0);
        myBoard[4][0] ='X';
        gb.placeToken('X', 0);

        assertEquals(gb.toString(), drawBoard(myBoard));
    }

    private String drawBoard(char[][] board) {
        String printBoard = "|";
        for (int i = 0; i < board[0].length; ++i) {
            if (i < 10) printBoard = printBoard + " " + i + "|";
            if (i >= 10) printBoard = printBoard + i + "|";
        }

        for (int i = (board.length-1); i >= 0; --i) {
            printBoard = printBoard + "\n|";
            for (int j = 0; j < board[0].length; ++j) {
                printBoard = printBoard + board[i][j] + " |";
            }
        }
        return printBoard;
    }
}
