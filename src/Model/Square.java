package Model;

public class Square {
    private SquareType type;
    private LetterTile tile;
    private Location location;

    public Square(SquareType type, Location location) {
        this.type = type;
        this.location = location;
    }

    public void setTile(LetterTile tile) {
        this.tile = tile;
    }

    public LetterTile getTile() {
        return tile;
    }

    public SquareType getType() {
        return type;
    }
}
