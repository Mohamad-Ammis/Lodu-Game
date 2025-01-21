package Classes;

import Helper.Color;

public class Piece {
    private Position currentPosition;
    private Color color;
    private Player owner;
    private boolean inPlay;
    private boolean isHome;
    private boolean isStart;
    public Piece(){

    }
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
        return this.isHome;
    }

    boolean canMove(int steps, Board board) {
        if (this.isStart() && steps != 6) {
            System.out.println("Piece cannot be moved from the start position unless you roll a 6.");
            return false;
        }
        if (this.isHome()) {
            System.out.println("Piece cannot be moved from the home.");
            return false;
        }
        return true;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay=inPlay;
    }

    public void setStart(boolean isStart) {
    this.isStart=isStart;

    }

    public void setPosition(Position position) {
    this.currentPosition=position;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isStart() {
        return this.isStart;
    }
    public Player getOwner() {
        return this.owner;
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
    void updatePosition(Position currentPosition, Position targetPosition) {
        currentPosition.removePiece(this);
        targetPosition.addPiece(this);
        this.setPosition(targetPosition);
        this.setInPlay(true);
    }
     boolean handleOpponentPiece(Position targetPosition) {
        //first get piece,get owner start position to reset it ,remove piece from current position,then reset it to start
        Piece opponentPiece = targetPosition.getPieces().get(0);
        if (this.isOpponentPiece(opponentPiece)) {
            Position opponentStart = opponentPiece.getOwner().getStartPosition();
            Position opponentPiecePosition=opponentPiece.getPosition();
            opponentPiecePosition.removePiece(opponentPiece);
            opponentPiece.resetToStart(opponentStart);
            System.out.println("Opponent piece reset to start position.");
            return true;
        }
        return false;
    }


}
