package Model;

public class PlayerInput {
    private Alignment alignment;
    private Location coordinate;
    private String word;

    public PlayerInput(Alignment alignment, Location coordinate, String word) {
        this.alignment = alignment;
        this.coordinate = coordinate;
        this.word = word;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Location getCoordinate() {
        return coordinate;
    }


    public String getWord() {
        return word;
    }
}
