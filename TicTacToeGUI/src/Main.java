import javax.swing.*;
public class Main {
    static {
        System.loadLibrary("libCPlusPlus");
    }

    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        new TicTacToeController(model, view);
        view.setVisible(true);
    }
}
