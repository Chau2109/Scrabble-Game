package Model;

public class Board {

    private Square[][] squares;

    public Board(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(int x, int y){
        if (x < 0 || x >= Constants.BOARD_SIZE || y < 0 || y >= Constants.BOARD_SIZE) return null;
        return this.squares[x][y];
    }

}
