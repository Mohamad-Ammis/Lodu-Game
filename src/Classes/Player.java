package Classes;

import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    public String name;
    public Color color;
    public Piece[] pieces;
    public int consecutiveSixes;
    public Position startPosition;
    public Position endPosition;

    public Player(String name, Color color, Position startPosition, Position endPosition) {
    this.name=name;
    this.color=color;
    this.startPosition=startPosition;
    this.endPosition=endPosition;
    this.pieces=new Piece[4] ;
        for (int i = 0; i < 4; i++) {
            pieces[i]=new Piece(color,this,startPosition);
        }
    consecutiveSixes=0;
    }

    public void resetConsecutiveSixes() {
        this.consecutiveSixes=0;
    }

    public void incrementConsecutiveSixes() {
        this.consecutiveSixes++;
    }

    public boolean canPlayAgain() {
        return this.consecutiveSixes<3;
    }

    public abstract boolean makeMove(int diceRoll, Game game) ;

    public boolean allPiecesInHome(Board board) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Piece[] getPieces() {
    return pieces;
    }

    public void setPieces(List<Piece> pieces) {

    }
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    @Override
    public boolean equals(Object obj) {
        // التحقق من أن الكائن الذي تتم مقارنته ليس null وأنه من نوع Player
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        // تحويل الكائن إلى نوع Player
        Player player = (Player) obj;

        // مقارنة الحقول
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (color != player.color) return false;
        if (consecutiveSixes != player.consecutiveSixes) return false;
        if (startPosition != null ? !startPosition.equals(player.startPosition) : player.startPosition != null)
            return false;
        if (endPosition != null ? !endPosition.equals(player.endPosition) : player.endPosition != null) return false;

        // مقارنة المصفوفة pieces
        if (pieces != null && player.pieces != null) {
            if (pieces.length != player.pieces.length) return false;
            for (int i = 0; i < pieces.length; i++) {
                if (!pieces[i].equals(player.pieces[i])) return false;
            }
        } else if (pieces != player.pieces) { // التعامل مع حالة أن المصفوفة نفسها null
            return false;
        }

        return true;
    }

    public abstract Player copy();
    public int getBaseIndex(Piece piece) {
        int baseStartIndex = (this.getColor() == Color.R) ? 0 : 15; // Example for Player 1 and Player 2
        int pieceIndex=findPieceIndex(piece);
        if (pieceIndex == -1) {
            throw new IllegalArgumentException("Piece not found in player's base.");
        }
        return baseStartIndex + pieceIndex;
    }
    private int findPieceIndex(Piece targetPiece) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals(targetPiece)) {
                return i;
            }
        }
        return -1; // Return -1 if the piece is not found
    }

}
