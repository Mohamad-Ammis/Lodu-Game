package Classes;

import Helper.Color;

public class Piece {
    private Position currentPosition;
    private Color color;
    private boolean inPlay;
    private boolean isHome;
    private boolean isStart;

    public Piece(Color color) {
    }

    public Position getPosition() {
        return null;
    }

    public void move(int diceRoll, Board board) {
    }

    public boolean isAtHome(Board board) {
        return false;
    }

    public boolean canMove(int diceRoll, Board board) {
        return false;
    }

    public void setInPlay(boolean inPlay) {
    }

    public void setStart(boolean isStart) {
    }

    public void setPosition(Position position) {
    }

    public Color getColor() {
        return null;
    }

    public boolean isStart() {
        return false;
    }
}
