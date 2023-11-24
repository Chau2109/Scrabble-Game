package Model;

import java.util.Map;

public class LetterBag {

    private Map<String, Integer> tileMap;

    public LetterBag(Map<String, Integer> tileMap) {
        this.tileMap = tileMap;
    }

    public Map<String, Integer> getTileMap() {
        return tileMap;
    }
}
