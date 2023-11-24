package Model;

import java.util.Arrays;

public enum SquareType {
    NORMAL,
    TRIPLE_WORD,
    DOUBLE_WORD,
    CENTER_SQUARE,
    TRIPLE_LETTER,
    DOUBLE_LETTER;

    public static SquareType getTypeByLocation(Location location){
        if(location.toString() == "8-H") {
            return CENTER_SQUARE;
        }
        if(Arrays.asList("1-A","1-H","1-O","8-A","8-O","15-A","15-H","15-O").contains(location.toString()))
            return TRIPLE_WORD;
        if(Arrays.asList("2-F","2-J","6-B","6-F","6-J","6-N","10-B","10-F","10-J","10-N","14-F","14-J").contains(location.toString()))
            return TRIPLE_LETTER;
        if(Arrays.asList("2-B","2-N","3-C","3-M","4-D","4-L","5-E","5-K","11-E","11-K","12-D","12-L","13-C","13-M","14-B","14-N").contains(location.toString()))
            return DOUBLE_WORD;
        if(Arrays.asList("1-D","1-L","3-G","3-I","4-A","4-H","4-O","7-C","7-G","7-I","7-M","8-D","8-L","9-C","9-G","9","-I","9-M","12-A","12-H","12-O","13-G","13-I","15-D","15-L").contains(location.toString()))
            return DOUBLE_LETTER;
        return NORMAL;

    }
}
