package Model;

public enum Alignment {
    HORIZONTAL,
    VERTICAL;

    public static Alignment fromValue(String value){
        switch (value.toUpperCase()){
            case "HORIZONTAL":
                return HORIZONTAL;
            case "VERTICAL":
            default:
                return VERTICAL;
        }
    }
}
