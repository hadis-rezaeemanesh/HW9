package com.example.hw9;

public class Board4InARow {

    private int[][] mGrid;
    private int turn = 1;
    private char player = 'R';
    private boolean winner = false;


    public Board4InARow(int[][] grid) {
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                grid[row][col] = ' ';
            }
        }
    }

    public int[][] getGrid() {
        for (int row = 0; row < mGrid.length; row++){
            for (int col = 0; col < mGrid[0].length; col++){
                mGrid[row][col] = ' ';
            }
        }
        return mGrid;
    }

    public void setGrid(int[][] grid) {
        mGrid = grid;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public static boolean validate(int column, char[][] grid){
        //valid column?
        if (column < 0 || column > grid[0].length){
            return false;
        }

        //full column?
        if (grid[0][column] != ' '){
            return false;
        }

        return true;
    }
    public static boolean isWinner(char player, char[][] grid){
        //check for 4 across
        for(int row = 0; row<grid.length; row++){
            for (int col = 0;col < grid[0].length - 3;col++){
                if (grid[row][col] == player   &&
                        grid[row][col+1] == player &&
                        grid[row][col+2] == player &&
                        grid[row][col+3] == player){
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length; col++){
                if (grid[row][col] == player   &&
                        grid[row+1][col] == player &&
                        grid[row+2][col] == player &&
                        grid[row+3][col] == player){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < grid.length; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == player   &&
                        grid[row-1][col+1] == player &&
                        grid[row-2][col+2] == player &&
                        grid[row-3][col+3] == player){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == player   &&
                        grid[row+1][col+1] == player &&
                        grid[row+2][col+2] == player &&
                        grid[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }
}
