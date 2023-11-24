package Model;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private String name;
    private List<LetterTile> rack;
    private int score;

    public Player(String id) {
        this.id = id;
        this.rack = new ArrayList<>();
        this.score = 0;
    }

    public String getId() {
        return id;
    }

    public List<LetterTile> getRack() {
        return rack;
    }

    public void setRack(List<LetterTile> rack) {
        this.rack = rack;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBRack(){
        String bRack = "";
        for (int i = 0; i < Constants.LIMIT_TILE_PER_ROUND; i++) {
            bRack += rack.get(i).getValue() + " : " + rack.get(i).getScore();
            if(i == Constants.LIMIT_TILE_PER_ROUND - 1){
                bRack += ";";
            }else{
                bRack += ", ";
            }
        }
        return bRack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
