package Classes;

import java.util.List;

public class Position {
    private int index;
    private boolean isSafe;
    private List<Piece> pieces;

    public Position(int index, boolean isSafe) {
    }

    public int getIndex() {
        return index;
    }

    public boolean isSafe() {
        return false;
    }

    public void addPiece(Piece piece) {
    }

    public void removePiece(Piece piece) {
    }

    public boolean isOccupied() {
        return false;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public Position copy(){
        return new Position(this.index, this.isSafe);
    }
}
