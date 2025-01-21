package Classes;

import Helper.Color;

public class Piece {
    private Position currentPosition;
    private Color color;
    private Player owner;
    private boolean inPlay;
    private boolean isHome;
    private boolean isStart;

    public Piece(Color color,Player owner) {
    this.color=color;
    this.owner=owner;
    this.inPlay=false;
    this.isHome=false;
    this.isStart=true;
    }



    public Position getPosition() {
        return this.currentPosition;
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
        return color;
    }

    public boolean isStart() {
        return false;
    }
    public Player getOwner() {
        return owner;
    }
    public void setInHome(boolean isHome) {
        this.isHome = isHome;
    }

    public boolean isHome() {
        return this.isHome;
    }
    boolean isOpponentPiece(Piece opponentPiece) {
        return !opponentPiece.getOwner().equals(this.getOwner());
    }
    public void resetToStart(Position startPosition) {
        this.currentPosition = startPosition;
        this.isStart = true;
        this.inPlay = false;
        this.isHome=false;
        startPosition.addPiece(this);
        System.out.println("Piece has been reset to the start position.");
    }

    public Piece copy(Position position){
        Piece piece = new Piece(this.color, this.owner);

        piece.setPosition(position);

        return piece;
    }

}
