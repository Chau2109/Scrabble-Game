package Util.Library;

public class WordResponse {
    public WordResponse(String word, String description){
        this.word = word;
        this.description = description;
    }

    public String getWord(){
        return this.word;
    }
    private String word;

    public String getDescription(){
        return this.description;
    }
    private String description;

    public String toString(){
        return this.getWord() + ": " + this.getDescription();
    }
}
