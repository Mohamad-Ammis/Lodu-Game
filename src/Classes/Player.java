package Classes;

import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private String name;
    private Color color;
    private List<Piece> pieces;
    private int consecutiveSixes;
    private Position startPosition;
    private Position endPosition;

    public Player(String name, Color color, Position startPosition, Position endPosition) {
    this.name=name;
    this.color=color;
    this.startPosition=startPosition;
    this.endPosition=endPosition;
    consecutiveSixes=0;
    }

    public List<Position> getPiecesPositions() {
        return null;
    }

    public void resetConsecutiveSixes() {
    }

    public void incrementConsecutiveSixes() {
        this.consecutiveSixes++;
    }

    public boolean canPlayAgain() {
        return this.consecutiveSixes<3;
    }

    public void makeMove(int diceRoll, Board board) {
    }

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

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

}
