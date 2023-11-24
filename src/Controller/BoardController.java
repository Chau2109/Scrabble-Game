package Controller;
import Model.Board;
import Model.Constants;
import Model.LetterTile;
import Model.Square;
import Model.Location;
import Model.SquareType;

public class BoardController  {
    public static Board generateGameBoard(int boardSize) {
        Square[][] squares = new Square[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Location location = new Location(i, j);
                SquareType type = SquareType.getTypeByLocation(location);
                squares[i][j] = new Square(SquareType.NORMAL,location);
            }
        }

        squares[7][7] = new Square(SquareType.CENTER_SQUARE,new Location(7,7));

        squares[0][0] = new Square(SquareType.TRIPLE_WORD,new Location(0,0));
        squares[0][7] = new Square(SquareType.TRIPLE_WORD, new Location(0,7));
        squares[0][14] = new Square(SquareType.TRIPLE_WORD, new Location(0,14));
        squares[7][0] = new Square(SquareType.TRIPLE_WORD, new Location(7,0));
        squares[7][14] = new Square(SquareType.TRIPLE_WORD, new Location(7,14));
        squares[14][0] = new Square(SquareType.TRIPLE_WORD, new Location(14,0));
        squares[14][7] = new Square(SquareType.TRIPLE_WORD, new Location(14,7));
        squares[14][14] = new Square(SquareType.TRIPLE_WORD, new Location(14,14));


        squares[0][3] = new Square(SquareType.DOUBLE_LETTER,new Location(0,3));
        squares[0][11] = new Square(SquareType.DOUBLE_LETTER, new Location(0,11));
        squares[2][6] = new Square(SquareType.DOUBLE_LETTER, new Location(2,6));
        squares[2][8] = new Square(SquareType.DOUBLE_LETTER, new Location(2,8));
        squares[3][0] = new Square(SquareType.DOUBLE_LETTER, new Location(3,0));
        squares[3][7] = new Square(SquareType.DOUBLE_LETTER, new Location(3,7));
        squares[3][14] = new Square(SquareType.DOUBLE_LETTER, new Location(3,14));
        squares[6][2] = new Square(SquareType.DOUBLE_LETTER, new Location(6,2));
        squares[6][6] = new Square(SquareType.DOUBLE_LETTER, new Location(6,6));
        squares[6][8] = new Square(SquareType.DOUBLE_LETTER, new Location(6,8));
        squares[6][12] = new Square(SquareType.DOUBLE_LETTER, new Location(6,12));
        squares[7][3] = new Square(SquareType.DOUBLE_LETTER, new Location(7,3));
        squares[7][11] = new Square(SquareType.DOUBLE_LETTER, new Location(7,11));
        squares[8][2] = new Square(SquareType.DOUBLE_LETTER, new Location(8,2));
        squares[8][6] = new Square(SquareType.DOUBLE_LETTER, new Location(8,6));
        squares[8][8] = new Square(SquareType.DOUBLE_LETTER, new Location(8,8));
        squares[8][12] = new Square(SquareType.DOUBLE_LETTER, new Location(8,12));
        squares[11][0] = new Square(SquareType.DOUBLE_LETTER, new Location(11,0));
        squares[11][7] = new Square(SquareType.DOUBLE_LETTER, new Location(11,7));
        squares[11][14] = new Square(SquareType.DOUBLE_LETTER, new Location(11,14));
        squares[12][6] = new Square(SquareType.DOUBLE_LETTER, new Location(12,6));
        squares[12][8] = new Square(SquareType.DOUBLE_LETTER, new Location(12,8));
        squares[14][3] = new Square(SquareType.DOUBLE_LETTER, new Location(14,3));
        squares[14][11] = new Square(SquareType.DOUBLE_LETTER, new Location(14,11));

        //for the top left corner
        for (int i = 1; i < 5; i++){
            squares[i][i] = new Square(SquareType.DOUBLE_WORD, new Location(i,i));
        }


        //bottom right corner
        for (int i = 13; i > 9; i--){
            squares[i][i] = new Square(SquareType.DOUBLE_WORD,new Location(i,i));
        }

        squares[13][1] = new Square(SquareType.DOUBLE_WORD, new Location(13,1));
        squares[12][2] = new Square(SquareType.DOUBLE_WORD, new Location(12,2));
        squares[11][3] = new Square(SquareType.DOUBLE_WORD, new Location(11,3));
        squares[10][4] = new Square(SquareType.DOUBLE_WORD, new Location(10,4));

        squares[1][13] = new Square(SquareType.DOUBLE_WORD, new Location(1,13));
        squares[2][12] = new Square(SquareType.DOUBLE_WORD, new Location(2,12));
        squares[3][11] = new Square(SquareType.DOUBLE_WORD, new Location(3,11));
        squares[4][10] = new Square(SquareType.DOUBLE_WORD,new Location(4,10));

        squares[1][5] = new Square(SquareType.TRIPLE_LETTER,new Location(1,5));
        squares[1][9] = new Square(SquareType.TRIPLE_LETTER, new Location(1,9));
        squares[5][1] = new Square(SquareType.TRIPLE_LETTER, new Location(5,1));
        squares[5][5] = new Square(SquareType.TRIPLE_LETTER, new Location(5,5));
        squares[5][9] = new Square(SquareType.TRIPLE_LETTER, new Location(5,9));
        squares[5][13] = new Square(SquareType.TRIPLE_LETTER, new Location(5,13));
        squares[9][1] = new Square(SquareType.TRIPLE_LETTER, new Location(9,1));
        squares[9][5] = new Square(SquareType.TRIPLE_LETTER, new Location(9,5));
        squares[9][9] = new Square(SquareType.TRIPLE_LETTER, new Location(9,9));
        squares[9][13] = new Square(SquareType.TRIPLE_LETTER, new Location(9,13));
        squares[13][5] = new Square(SquareType.TRIPLE_LETTER, new Location(13,5));
        squares[13][9] = new Square(SquareType.TRIPLE_LETTER, new Location(13,9));
        return new Board(squares);
    }


    public String toString(Board board){
        String boardString = "";
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                Square square = board.getSquares()[i][j];
                if(square.getTile() != null){
                    boardString += square.getTile().getValue();
                }else{
                    boardString += "-";
                }
                if(j == Constants.BOARD_SIZE - 1){
                    boardString += ";";
                }else{
                    boardString += ",";
                }
            }
        }
        return boardString;
    }

    public Board toBoard(String boardString){
        Board board = generateGameBoard(Constants.BOARD_SIZE);
        String[] lines = boardString.split(";");
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            String[] values = lines[i].split(",");
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (!values[j].equals("-")){
                    board.getSquares()[i][j].setTile(new LetterTile(values[j]));
                }
            }
        }
        return board;
    }
    public boolean isEmptyBoard(Board board){
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (board.getSquares()[i][j].getTile() != null){
                    return false;
                }
            }
        }
        return true;
    }
    public Board clone(Board board){
        Square[][] squares = new Square[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                squares[i][j] = board.getSquares()[i][j];
            }
        }
        return new Board(squares);
    }
}
