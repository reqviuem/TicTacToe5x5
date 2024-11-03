import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TicTacToeView extends JFrame {
    private JTable table;
    private JButton restartButton;

    public TicTacToeView() {
        setTitle("Tic-Tac-Toe Game");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        table = new JTable(5, 5);
        table.setCellSelectionEnabled(false);
        table.setFocusable(true);
        table.setRowHeight(100);
        table.setFont(new Font("Arial", Font.PLAIN, 40));
        table.setTableHeader(null);
        table.setDefaultRenderer(Object.class, new TicTacToeCellRenderer());

        add(table, BorderLayout.CENTER);

        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));

        add(restartButton, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getRestartButton() {
        return restartButton;
    }
}
