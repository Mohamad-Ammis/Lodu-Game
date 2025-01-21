package Classes;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private int index;
    private boolean isSafe;
    private List<Piece> pieces;

    public Position(int index, boolean isSafe) {
        this.index=index;
        this.isSafe=isSafe;
        this.pieces=new ArrayList<>();
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isSafe() {
        return this.isSafe;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public void removePiece(Piece piece) {
    this.pieces.remove(piece);
    }

    public boolean isEmpty() {
        return this.pieces.isEmpty();
    }

    public List<Piece> getPieces() {
        return this.pieces;
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
