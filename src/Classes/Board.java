package Classes;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int  SIZE=60;
    private List<Position> positions;

    public Board() {
        positions=new ArrayList<>();
        initializeBoard();
    }
    public  void initializeBoard(){
        for (int i = 0; i < SIZE; i++) {
            boolean isSafe = (i % 5 == 0);
            positions.add(new Position(i, isSafe));
        }
    }

    public boolean isSafePosition(int index) {
        Position position=getPositionAt(index);
        return position.isSafe();
    }
    public Position getPositionAt(int index) {
        if(index<0||index>=SIZE){
            throw new IllegalArgumentException("Invalid position index: " + index);
        }
        return  positions.get(index);
    }

    public boolean movePiece(Piece piece, int steps) {
        Position currentPosition = piece.getPosition();

        if (currentPosition == null) {
            throw new IllegalStateException("Piece is not on the board.");
        }
        if (!piece.canMove( steps,this)) return false;

        //we need owner player to get his own home path
        Player owner = piece.getOwner();
        //we need this three index variable to check if piece is in player home path
        int currentIndex = currentPosition.getIndex();
        int playerEndIndex = owner.getEndPosition().getIndex();
        int homeStartIndex = playerEndIndex - 5; // Start of home path (last 5 positions)
        //target index where we will put the piece
        int targetIndex = currentIndex + steps;
        if (isInHomePath(currentIndex, homeStartIndex, playerEndIndex)) {
            handleHomeMovement(piece, steps, currentIndex, playerEndIndex);
            return false;
        }
        if (isMoveExceedsEndPosition (targetIndex, playerEndIndex)) {
            return false;
        }
        //when piece it's not on home path so we handle normal move state
        Position targetPosition = getPositionAt(targetIndex);
        boolean extraTurn= handleTargetPosition(piece, currentPosition, targetPosition);
        return extraTurn;
    }


    private void handleHomeMovement(Piece piece, int steps, int currentIndex, int playerEndIndex) {
        int distanceToHome = playerEndIndex - currentIndex;
        if (steps == distanceToHome) {
            movePieceToHome(piece);
        } else if (steps < distanceToHome) {
           movePieceWithinHomePath(piece,currentIndex,steps);
        } else {
            System.out.println("Invalid move: Step count exceeds distance to home.");
        }
    }
    private void movePieceToHome(Piece piece) {
        System.out.println("Piece has reached home!");
        piece.setInHome(true);
        piece.getPosition().removePiece(piece);
    }

    private void movePieceWithinHomePath(Piece piece, int currentIndex, int steps) {
        int targetIndex = currentIndex + steps;
        Position targetPosition = getPositionAt(targetIndex);
        Position currentPosition = getPositionAt(currentIndex);
        handleTargetPosition(piece, currentPosition, targetPosition);
    }


    private boolean handleTargetPosition(Piece piece, Position currentPosition, Position targetPosition) {
        //handle opponentPiece existed state
        if (targetPosition.isBlockedBySinglePiece(piece)) {
           return piece.handleOpponentPiece(targetPosition);

        }if (targetPosition.isBlockedByMultiplePieces(piece)) {
            System.out.println("Target position is blocked by multiple pieces. Move is not possible.");
            return false;
        }
        //normal state
        piece.updatePosition(currentPosition, targetPosition);
        return false;
    }

    private boolean isInHomePath(int currentIndex, int homeStartIndex, int playerEndIndex) {
        return currentIndex >= homeStartIndex && currentIndex <= playerEndIndex;
    }

    private boolean isMoveExceedsEndPosition (int targetIndex, int playerEndIndex) {
        if (targetIndex > playerEndIndex) {
            System.out.println("Target position exceeds player's end position. Move is not possible.");
            return true;
        }
        return false;
    }
    public List<Piece> getPiecesAtPosition(int index) {
        return positions.get(index).getPieces();
    }

    public void printBoard(List<Player> players) {
    }
}
