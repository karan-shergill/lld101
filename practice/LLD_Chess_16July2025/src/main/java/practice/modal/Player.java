package practice.modal;

public abstract class Player {
    private String name;
    private boolean whitePiece;

    public Player(String name, boolean whilePiece) {
        this.name = name;
        this.whitePiece = whilePiece;
    }

    public String getName() {
        return name;
    }

    public boolean isWhitePiece() {
        return whitePiece;
    }

    abstract public boolean playMove(Move move, Board board);
}
