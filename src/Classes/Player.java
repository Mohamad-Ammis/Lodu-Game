package Classes;

import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected String name;
    protected Color color;
    protected int consecutiveSixes;
    protected Position startPosition;
    protected Position endPosition;

    public Player(String name, Color color, Position startPosition, Position endPosition) {
    this.name=name;
    this.color=color;
    this.startPosition=startPosition;
    this.endPosition=endPosition;
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

    public abstract boolean makeMove(int diceRoll, Board board) ;

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

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }


    public abstract Player copy();
}
