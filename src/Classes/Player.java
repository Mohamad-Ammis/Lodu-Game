package Classes;

import Helper.Color;
import java.util.List;

public abstract class Player {
    private String name;
    private Color color;
    private List<Piece> pieces;
    private int consecutiveSixes;

    public Player(String name, Color color) {

    }

    public List<Position> getPiecesPositions() {
        return null;
    }

    public void resetConsecutiveSixes() {
    }

    public void incrementConsecutiveSixes() {
    }

    public boolean canPlayAgain() {
        return false;
    }

    public void makeMove(int diceRoll, Board board) {
    }

    public boolean allPiecesInHome(Board board) {
        return false;
    }
}
