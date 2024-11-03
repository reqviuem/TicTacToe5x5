import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeBoardTest {

    static {
        System.loadLibrary("libCPlusPlus");
    }

    private TicTacToeModel model;

    @BeforeEach
    public void setUp() {
        model = new TicTacToeModel();
        model.resetBoard();
    }

    @Test
    public void testBoardInitialization() {
        for (int row = 0; row < model.getLENGTH(); row++) {
            for (int col = 0; col < model.getLENGTH(); col++) {
                assertEquals(' ', model.getCell(row, col));
            }
        }
        assertTrue(model.isXTurn());
    }

    @Test
    public void testMakeMove() {
        assertAll(
                () ->assertTrue(model.makeMove(0, 0)),
                () ->assertEquals('X', model.getCell(0, 0) )
        );
        assertAll(
                () ->assertTrue(model.makeMove(0, 1)),
                () ->assertFalse(model.makeMove(0, 0))
        );
    }

    @Test
    public void testSwitchPlayer() {
        model.makeMove(0, 0);
        assertFalse(model.isXTurn());

        model.makeMove(0, 1);
        assertTrue(model.isXTurn());
    }

    @ParameterizedTest(name = "Test Horizontal Win for row {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testHorizontalWinConditionForX(int row) {

        for (int col = 0; col < model.getLENGTH(); col++) {
            model.makeMove(row, col);
            if (col < model.getLENGTH() - 1) {
                model.makeMove((row + 1) % model.getLENGTH(), col);
            }
        }
        assertAll(
                () -> assertTrue(model.checkWin()),
                () -> assertFalse(model.isXTurn())
        );


    }

    @ParameterizedTest(name = "Test Vertical Win for col {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testVerticalWinConditionForX(int col) {

        for (int row = 0; row < model.getLENGTH(); row++) {
            model.makeMove(row, col);
            if (row < model.getLENGTH() - 1) {
                model.makeMove(row, (col + 1) % model.getLENGTH());
            }
        }
        assertAll(
                () -> assertTrue(model.checkWin()),
                () -> assertFalse(model.isXTurn())
        );
    }

    @ParameterizedTest(name = "Test Horizontal Win for row {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testHorizontalWinConditionForO(int targetRow) {
        int otherRow = (targetRow + 1) % 5;
        for (int col = 0; col < 5; col++) {
            if(col==4) {
                model.makeMove(otherRow + 1, col);
            } else {
                model.makeMove(otherRow, col);
            }
            model.makeMove(targetRow, col);
        }

        assertAll(
                () -> assertTrue(model.checkWin()),
                () -> assertTrue(model.isXTurn())
        );
    }

    @ParameterizedTest(name = "Test Vertical Win for col {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testVerticalWinConditionForO(int col) {
        final int LENGTH = model.getLENGTH();

        for (int row = 0; row < LENGTH; row++) {
            int otherCol = (col + 1) % 5;
            if(row==4) {
                model.makeMove(row, otherCol+1);
            } else {
                model.makeMove(row, otherCol);
            }
            model.makeMove(row, col);

        }
        assertAll(
                () -> assertTrue(model.checkWin()),
                () -> assertTrue(model.isXTurn())
        );
    }

    @ParameterizedTest(name = "Test Diagonal Win for  {0}")
    @ValueSource(chars = {'X' , 'O' })
    public void checkDiagonalWinConditionForX_O(char step) {
        if (step == 'X') {
            for (int i = 0; i < 5; i++) {
                    model.makeMove(i, i);
                    if (i==4){
                        break;
                    }
                    model.makeMove(i+1, i);
            }
            assertAll(
                    () -> assertTrue(model.checkWin()),
                    () -> assertFalse(model.isXTurn())
            );
        }
        if (step == 'O') {
            for (int i = 0; i < 5; i++) {
                if (i < 4){
                    model.makeMove(i+1, i);
                    model.makeMove(i, i);
                }
                else if (i==4){
                    model.makeMove(i-2, i-1);
                    model.makeMove(i, i);
                    break;
                }
            }
            assertAll(
                    () -> assertTrue(model.checkWin()),
                    () -> assertTrue(model.isXTurn())
            );
        }

    }

    @ParameterizedTest(name = "Test Diagonal Win for  {0}")
    @ValueSource(chars = {'X' , 'O' })
    public void checkAntiDiagonalWinConditionForX_O(char step) {
        if (step == 'X') {
            int col=0;
            for (int row =4 ; row>=0;row--){
                model.makeMove(row, col);
                col++;
                if (row==0){
                    break;
                }
                model.makeMove(row, col);
            }
            assertAll(
                    () -> assertTrue(model.checkWin()),
                    () -> assertFalse(model.isXTurn())
            );
        }
        if (step == 'O') {
            int col=0;
            for (int row =4 ; row>=0;row--){
                if (row>0){
                    col++;
                    model.makeMove(row, col);
                    model.makeMove(row, col-1);
                }
                else {
                    model.makeMove(row, col-1);
                    model.makeMove(row, col);
                }

            }
            assertAll(
                    () -> assertTrue(model.checkWin()),
                    () -> assertTrue(model.isXTurn())
            );
        }
    }

    @Test
    public void testCheckDraw() {
        int[][] moves = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 4},
                {1, 0}, {1, 1}, {1, 3}, {2, 4}, {1, 2},
                {2, 0}, {2, 1}, {2, 3}, {3, 4}, {2, 2},
                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {4, 4},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {0, 4}
        };

        for (int[] move : moves) {
            assertTrue(model.makeMove(move[0], move[1]));
        }

        assertTrue(model.checkForDraw());
        assertFalse(model.checkWin());
    }

    @Test
    public void testResetBoard() {
        model.makeMove(0, 0);
        model.resetBoard();
        for (int row = 0; row < model.getLENGTH(); row++) {
            for (int col = 0; col < model.getLENGTH(); col++) {
                assertEquals(' ', model.getCell(row, col));
            }
        }
        assertTrue(model.isXTurn());
    }
}
