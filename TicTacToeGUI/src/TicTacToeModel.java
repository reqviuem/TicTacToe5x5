import javax.swing.table.AbstractTableModel;

public class TicTacToeModel extends AbstractTableModel {
    public native int getLENGTH();
    public native void initializeBoard();
    public native char getCell(int row, int col);
    public native boolean makeMove(int row, int col);
    public native boolean checkWin();
    public native boolean isXTurn();
    public native boolean checkForDraw();
    public native void resetBoard();

    public TicTacToeModel() {
        initializeBoard();
    }

    @Override
    public int getRowCount() {
        return getLENGTH();
    }

    @Override
    public int getColumnCount() {
        return getLENGTH();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getCell(rowIndex,columnIndex);
    }
    public void reset() {
        resetBoard();
        fireTableDataChanged();
    }
}