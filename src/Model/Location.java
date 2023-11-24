package Model;

import Util.Exception.WrongCoordinateException;

public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        if(row >= 0 && row <= Constants.BOARD_SIZE / 2 && col >= 0 && col <= Constants.BOARD_SIZE / 2 ){
            this.row = row;
            this.col = col;
        }
    }

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }

    // board location : 1-A -> 15-O
    public int getBRow(){
        return this.row + 1;
    }

    public String getBCol(){
        return String.valueOf((char) (65 + col));
    }

    @Override
    public String toString() {
        return String.valueOf(getBRow()) + '-' + getBCol();
    }
}


