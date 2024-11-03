import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToeController {
    private TicTacToeModel model;
    private TicTacToeView view;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        view.getTable().setModel(model);
        addEventListeners();
    }

    private void addEventListeners() {
        view.getRestartButton().addActionListener(e -> {
            model.reset();
            model.fireTableDataChanged();
        });

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (model.makeMove(view.getTable().rowAtPoint(e.getPoint()), view.getTable().columnAtPoint(e.getPoint()))) {
                    model.fireTableDataChanged();
                    if (model.checkWin()) {
                        JOptionPane.showMessageDialog(view, "Player " + (model.isXTurn() ? "O" : "X") + " wins!");
                        model.reset();
                    } else if (model.checkForDraw()) {
                        JOptionPane.showMessageDialog(view, "Draw!");
                        model.reset();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(view, "Invalid Selection!");
                }
            }
        });
        view.getTable().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (model.makeMove(view.getTable().getSelectedRow(), view.getTable().getSelectedColumn())) {
                        model.fireTableDataChanged();
                        if (model.checkWin()) {
                            JOptionPane.showMessageDialog(view, "Player " + (model.isXTurn() ? "O" : "X") + " wins!");
                            model.reset();
                        } else if (model.checkForDraw()) {
                            JOptionPane.showMessageDialog(view, "Draw!");
                            model.reset();
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Invalid Selection!");
                    }
                }
            }
        });

    }
}