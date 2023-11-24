package Model;

public class LetterTile {
    private String value;
    private int score;

    public LetterTile(String value) {
        if(value.matches("^[A-Z]")){
            this.value = value;
            switch (value){
                case "A":
                case "E":
                case "I":
                case "L":
                case "N":
                case "O":
                case "Q":
                case "R":
                case "S":
                case "T":
                case "U":
                case "Z":
                    this.score = 1;
                    break;
                case "B":
                case "C":
                case "M":
                case "P":
                    this.score = 3;
                    break;
                case "D":
                case "G":
                    this.score = 2;
                    break;
                case "F":
                case "H":
                case "V":
                case "W":
                case "Y":
                    this.score = 4;
                    break;
                case "J":
                case "X":
                    this.score = 8;
                    break;
                case "K":
                    this.score = 5;
                    break;
                case " ":
                    this.score = 0;
                    break;
            }
        }
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}
