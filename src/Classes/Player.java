package Classes;

import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private String name;
    private Color color;
    private Piece[] pieces;
    int consecutiveSixes;
    private Position startPosition;
    private Position endPosition;

    public Player(String name, Color color, Position startPosition, Position endPosition) {
    this.name=name;
    this.color=color;
    this.startPosition=startPosition;
    this.endPosition=endPosition;
    this.pieces=new Piece[4] ;
        for (int i = 0; i < 4; i++) {
            pieces[i]=new Piece();
            pieces[i].setPosition(startPosition);
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


    public abstract Player copy();
}
