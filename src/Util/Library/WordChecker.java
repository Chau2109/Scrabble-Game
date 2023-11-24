package Util.Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WordChecker {
    private static final Map<String, WordResponse> words = new HashMap<>();

    static {
        try {
            InputStream resourceStream = Util.Library.WordChecker.class.getResourceAsStream("/collins_scrabble_words_2019.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] splitLine = line.split("\t");
                    if (splitLine.length == 2) {
                        words.put(splitLine[0], new WordResponse(splitLine[0], splitLine[1]));
                    }
                }
            }
        }
        catch(IOException exception){
            System.out.println("Could not load scrabble words: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public WordResponse isValidWord(String word) {
        if(word == null || word.isEmpty()) return null;
        return words.get(word.toUpperCase());
    }
}



