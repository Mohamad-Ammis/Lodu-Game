package Classes;

import java.util.List;

public class Position {
    private int index;
    private boolean isSafe;
    private List<Piece> pieces;

    public Position(int index, boolean isSafe) {
    }

    public int getIndex() {
        return 0;
    }

    public boolean isSafe() {
        return false;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public void removePiece(Piece piece) {
    }

    public boolean isOccupied() {
        return false;
    }

    public List<Piece> getPieces() {
        return null;
    }
    private int  blockedOpponentPiecesCount(Piece piece) {
        int count=0;
        if (!this.isSafe() && !this.getPieces().isEmpty()) {
            for (Piece opponentPiece : this.getPieces()) {
                if (piece.isOpponentPiece(opponentPiece)) {
                    count++;
                }
            }
        }
        return count;
    }
     public boolean isBlockedBySinglePiece(Piece piece){
        return this.blockedOpponentPiecesCount(piece)==1;
    }
     public boolean isBlockedByMultiplePieces(Piece piece){
        return this.blockedOpponentPiecesCount(piece)>=2;
    }

}
