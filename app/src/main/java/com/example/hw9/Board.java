package com.example.hw9;

import java.util.Random;

public class Board {

    private static final Random RANDOM = new Random();
    private char[][] mElements;
    private int mHeight;
    private int mWidth;
    private char currentPlayer;
    private boolean ended;

    public Board() {
        mElements = new char[mHeight][mWidth];
        newGame();
    }

    public boolean isEnded() {
        return ended;
    }

    public char play(int x, int y) {
        for (int i=0; i<mHeight; i++){
            for (int j=0; j<mWidth; j++){
                if (!ended && mElements[i][j] == ' '){
                    mElements[i][j] = currentPlayer;
                    changePlayer();
                }
            }
        }
        return checkEnd();
    }
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    public char getElt(int x, int y) {
        return mElements[x][y];
    }

    public void newGame() {
        for (int i = 0; i  < mHeight; i++) {
            for (int j=0; j<mWidth; j++) {
                mElements[i][j] = ' ';
            }
        }

        currentPlayer = 'X';
        ended = false;
    }

    public char checkEnd() {
        for (int i = 0; i < 3; i++) {
            // check horizontal
            if (getElt(i, 0) != ' ' &&
                    getElt(i, 0) == getElt(i, 1)  &&
                    getElt(i, 1) == getElt(i, 2)) {
                ended = true;
                return getElt(i, 0);
            }

            // check vertical
            if (getElt(0, i) != ' ' &&
                    getElt(0, i) == getElt(1, i)  &&
                    getElt(1, i) == getElt(2, i)) {
                ended = true;
                return getElt(0, i);
            }
        }

        if (getElt(0, 0) != ' '  &&
                getElt(0, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(2, 2)) {
            ended = true;
            return getElt(0, 0);
        }

        if (getElt(2, 0) != ' '  &&
                getElt(2, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(0, 2)) {
            ended = true;
            return getElt(2, 0);
        }

        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++){
                if (mElements[i][j] == ' ')
                    return ' ';
        }
        }

        return 'T';
    }

    public char computer() {
        if (!ended) {
            int i= -1;
            int j =-1;

            do {
                i = RANDOM.nextInt(9);
            } while (mElements[i][j] != ' ');

            mElements[i][j] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

}
