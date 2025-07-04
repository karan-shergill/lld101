package Chess_V1.playerFactory;

public abstract class Player {
    private String name;
    private  boolean isWhiteSide;

    public Player(String name, boolean isWhiteSide) {
        this.name = name;
        this.isWhiteSide = isWhiteSide;
    }

    public String getName() {
        return name;
    }

    public boolean isWhiteSide() {
        return isWhiteSide;
    }
}
