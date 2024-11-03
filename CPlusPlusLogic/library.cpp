#include "TicTacToeBoardState.h"
#include "TicTacToeModel.h"

JNIEXPORT jint JNICALL Java_TicTacToeModel_getLENGTH
        (JNIEnv *, jobject) {
    return TicTacToeBoardState::getLength();
}

JNIEXPORT jboolean JNICALL Java_TicTacToeModel_checkWin
        (JNIEnv *, jobject) {
    return TicTacToeBoardState::checkWin() ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_TicTacToeModel_resetBoard
        (JNIEnv *, jobject) {
    TicTacToeBoardState::resetBoard();
}

JNIEXPORT jchar JNICALL Java_TicTacToeModel_getCell
        (JNIEnv *, jobject, jint row, jint col ){
    int val = TicTacToeBoardState::getCell(row,col);
    if(val==1){
        return 'X';
    }
    else if(val==2){
        return 'O';
    }
    else{
        return ' ';
    }

}

JNIEXPORT jboolean JNICALL Java_TicTacToeModel_isXTurn(JNIEnv *, jobject) {
    return TicTacToeBoardState::isXTurn() ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_TicTacToeModel_checkForDraw
        (JNIEnv *, jobject) {

    int boardSize = TicTacToeBoardState::getLength();
    const auto &boardState = TicTacToeBoardState::getBoard();


    for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
            if (boardState[row][col] == 0) {
                return JNI_FALSE;
            }
        }
    }
    TicTacToeBoardState::currentPlayer = 1;
    return JNI_TRUE;

}
JNIEXPORT jboolean JNICALL Java_TicTacToeModel_makeMove(JNIEnv *env, jobject obj, jint row, jint col) {
    if (TicTacToeBoardState::isCellEmpty(row, col)) {
        int playerId = TicTacToeBoardState::isXTurn() ? 1 : 2;
        TicTacToeBoardState::setCell(playerId, row, col);

        TicTacToeBoardState::switchPlayer();
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}
JNIEXPORT void JNICALL Java_TicTacToeModel_initializeBoard
        (JNIEnv *, jobject){
    TicTacToeBoardState::initializeBoard();
}