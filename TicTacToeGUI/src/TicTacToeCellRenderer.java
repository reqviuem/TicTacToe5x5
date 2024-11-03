import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TicTacToeCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 40));

        if (value.equals('X')) {
            setForeground(Color.BLACK);
        } else if (value.equals('O')) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
        }

        setText(value.toString());
        return this;
    }
}
