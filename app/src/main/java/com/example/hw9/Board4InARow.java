package com.example.hw9;

public class Board4InARow {
    private int mRed;
    private int mBlue;
    private int[][] mGrid;
    private boolean mPlayerOne;
    private boolean mPlayerTwo;


    public Board4InARow(int[][] grid) {
        mGrid = grid;
    }

    public int getRed() {
        return mRed;
    }

    public void setRed(int red) {
        mRed = red;
    }

    public int getBlue() {
        return mBlue;
    }

    public void setBlue(int blue) {
        mBlue = blue;
    }

    public int[][] getGrid() {
        return mGrid;
    }

    public void setGrid(int[][] grid) {
        mGrid = grid;
    }

    public boolean isPlayerOne() {
        return mPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        mPlayerOne = playerOne;
    }

    public boolean isPlayerTwo() {
        return mPlayerTwo;
    }

    public void setPlayerTwo(boolean playerTwo) {
        mPlayerTwo = playerTwo;
    }
}
