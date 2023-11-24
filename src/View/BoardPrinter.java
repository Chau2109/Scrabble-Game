package View;

import Model.*;

public class BoardPrinter implements UserInterface{

    public static String generateBoard(Board board){
        StringBuilder builder = new StringBuilder();

        builder.append("   ");
        for (int x = 0; x < Constants.BOARD_SIZE; x++){
            builder.append("  " + ((char) (65 + x)) + "  ");
        }
        builder.append("\n");

        builder.append("    ┌");

        for(int x = 0; x <Constants.BOARD_SIZE-1; x++){
            builder.append("────┐");
        }
        builder.append("────┐");
        builder.append("\n");
        for(int x = 0; x < Constants.BOARD_SIZE; x++){
            builder.append(" " + (x < 9? " " : "") + (x+1) + " ");
            builder.append("│");
            for (int y = 0; y < Constants.BOARD_SIZE; y++){
                Square square = board.getSquare(x,y);
                switch (square.getType()){
                    case CENTER_SQUARE:
                        builder.append(ANSI.YELLOW_BACKGROUND);
                        break;
                    case TRIPLE_LETTER:
                        builder.append(ANSI.BLUE_BACKGROUND_BRIGHT);
                        break;
                    case TRIPLE_WORD:
                        builder.append(ANSI.RED_BACKGROUND);
                        break;
                    case DOUBLE_LETTER:
                        builder.append(ANSI.BLUE_BACKGROUND);
                        break;
                    case DOUBLE_WORD:
                        builder.append(ANSI.RED_BACKGROUND_BRIGHT);
                    default:
                }
                LetterTile tile = square.getTile();
                builder.append("  " + (tile != null ? tile.getValue() : " ") + " ");
                builder.append(ANSI.RESET);
                builder.append("│");
            }
            builder.append("\n    ");
            if(x < Constants.BOARD_SIZE-1){
                builder.append("├");
            }else{
                builder.append("└");
            }
            for(int z = 0; z < Constants.BOARD_SIZE; z++){
                if(z == Constants.BOARD_SIZE - 1 && z < Constants.BOARD_SIZE - 1){
                    builder.append("────┘");
                }
                else if (z == Constants.BOARD_SIZE - 1 && z == Constants.BOARD_SIZE - 1){
                    builder.append("────┘");
                }
                else if (z < Constants.BOARD_SIZE - 1 && z==Constants.BOARD_SIZE - 1){
                    builder.append("────┤");
                }else{

                    builder.append("────┤");
                }
            }
            builder.append("\n");
        }
        builder.append(ANSI.RESET);
        return builder.toString();
    }

    @Override
    public void printState(Board board) {
        System.out.println(generateBoard(board));
    }

    @Override
    public void informInputRow() {
        System.out.println("Please input ROW of character (1 - 15): ");
    }

    @Override
    public void informInputCol() {
        System.out.println("Please input COLUMN of character (A - O): ");
    }

    @Override
    public void informInputDir() {
        System.out.println("Please input DIRECTION of the word (horizontal/vertical): ");
    }

    @Override
    public void informInputWord() {
        System.out.println("Please input your LETTER TILE: ");
    }

    @Override
    public void informWaiting() {
        System.out.println("Please waiting for another player's turn!");
    }

    @Override
    public void informYourTurn() {
        System.out.println("It's your turn! You have " + Constants.TIME_BOX + " seconds to make move, or you will " +
                "lose your turn.");
    }

    @Override
    public void informInvalidMove() {
        System.out.println("Invalid move, you loose your turn!");
    }

    @Override
    public void informFinishMove(int scoreEarn) {
        System.out.println("You finish your move and earn " + scoreEarn + " points");
    }

    @Override
    public void informWinner(String playerId) {
        System.out.println("Player " + playerId + " is the winner!");
    }

    @Override
    public void informWin() {
        System.out.println("You win! congratulation.");
    }

    @Override
    public void informLose() {
        System.out.println("Don't be sad! practice make perfect");
    }
    @Override
    public void printRack(String rackData) {
        System.out.println("Here is all your tiles: " + rackData);
    }

    @Override
    public void printMenu() {
        System.out.println("What is your choice, input a NUMBER (1-4) ? \n1 Start the game\n2 Submit move\n3 Replace tile\n4 Skip ");
    }

    @Override
    public void informInvalidChoice() {
        System.out.println("The choice is invalid" );
    }

    @Override
    public void informChangeTiles(LetterTile tiles) {
        System.out.println("The tiles have been changed is" + tiles);
    }

    @Override
    public void informTenSecLeft() {
        System.out.println("You only have 10 seconds left, hurry up!");
    }

    @Override
    public void informTimeUp() {
        System.out.println("Time up! You loose this turn.");
    }

    @Override
    public void systemMessage(String command) {
        System.out.println(command);
    }

    @Override
    public void connectConfirmation() {
        System.out.println("Connection successfully!");
    }

    @Override
    public void informScore(String data) {
        System.out.println("Your score: " + data);
    }
}
