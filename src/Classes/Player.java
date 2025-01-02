package Classes;

import Helper.Color;
import java.util.List;

public abstract class Player {
    private String name;
    private Color color;
    private List<Piece> pieces;
    private int consecutiveSixes;

    public Player(String name, Color color) {
    this.name=name;
    this.color=color;
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

}
