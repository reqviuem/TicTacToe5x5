#ifndef PROJECT_UTP_LIBRARY_TICTACTOEBOARDSTATE_H
#define PROJECT_UTP_LIBRARY_TICTACTOEBOARDSTATE_H
#pragma once

#include <array>

struct TicTacToeBoardState {
    static inline int currentPlayer = 1;
    const static inline int LENGTH = 5;
    static inline std::array<std::array<int, LENGTH>, LENGTH> board = {{
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0}
                                                                       }};
public:

    static  void initializeBoard();

    static const std::array<std::array<int, LENGTH>, LENGTH> &getBoard();

    static auto getLength() -> int;

    static int getCell(int row, int col);

    static bool isCellEmpty(int row, int col);

    static void setCell(int id, int row, int col);

    static  int checkWin();
    static  void resetBoard();
    static  bool isXTurn();
    static void switchPlayer();
};


inline bool TicTacToeBoardState::isXTurn() {
    return currentPlayer == 1;
}

inline void TicTacToeBoardState::initializeBoard() {

    for (auto &row: board) {
        row.fill(0);
    }
    currentPlayer = 1;
}

inline void TicTacToeBoardState::setCell(int id, int row, int col) {
    board[row][col] = id;
}

inline bool TicTacToeBoardState::isCellEmpty(int row, int col) {
    return board[row][col] == 0;
}

inline int TicTacToeBoardState::getCell(int row, int col) {
    return board[row][col];
}


inline const std::array<std::array<int, TicTacToeBoardState::LENGTH>, TicTacToeBoardState::LENGTH> &
TicTacToeBoardState::getBoard() {
    return board;
}

inline auto TicTacToeBoardState::getLength() -> int {
    return board.size();
}

inline int TicTacToeBoardState::checkWin() {

    for (int i = 0; i < LENGTH; ++i) {
        if ((board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2] &&
             board[i][2] == board[i][3] && board[i][3] == board[i][4])) {
            return board[i][0];
        }
        if ((board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i] &&
             board[2][i] == board[3][i] && board[3][i] == board[4][i])) {
            return board[0][i];
        }
    }


    if ((board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2] &&
         board[2][2] == board[3][3] && board[3][3] == board[4][4])) {
        return board[0][0];
    }
    if ((board[0][4] != 0 && board[0][4] == board[1][3] && board[1][3] == board[2][2] &&
         board[2][2] == board[3][1] && board[3][1] == board[4][0])) {
        return board[0][4];
    }

    return 0;
}

inline void TicTacToeBoardState::resetBoard() {
    for (auto &row: board) {
        row.fill(0);
    }
    currentPlayer = 1;
}

inline void TicTacToeBoardState::switchPlayer() {
    currentPlayer = (currentPlayer == 1) ? 2 : 1;
}

#endif // PROJECT_UTP_LIBRARY_TICTACTOEBOARDSTATE_H