package Classes;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final  int SIZE=60;
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



    public void movePiece(Piece piece, int steps) {
        Position currentPosition = piece.getPosition();

        if (currentPosition == null) {
            throw new IllegalStateException("Piece is not on the board.");
        }
        //we need owner player to get his own home path
        Player owner = piece.getOwner();
        //we need this three index variable to check if piece is in player home path
        int currentIndex = currentPosition.getIndex();
        int playerEndIndex = owner.getEndPosition().getIndex();
        int homeStartIndex = playerEndIndex - 4; // Start of home path (last 5 positions)
        //target index where we will put the piece
        int targetIndex = currentIndex + steps;
        //when this condition true so we are on path home so exactly one number is only accepted to play
        if (currentIndex >= homeStartIndex && currentIndex <= playerEndIndex) {
            //handle steps number if it's the exactly  number is only accepted to reach home
            handleHomeMovement(piece, steps, currentIndex, playerEndIndex);
            return;
        }
        if (targetIndex > playerEndIndex) {
            System.out.println("Target position exceeds player's end position. Move is not possible.");
            return;
        }
        //when piece it's not on home path so we handle normal move state
        Position targetPosition = getPositionAt(targetIndex);
        handleTargetPosition(piece, currentPosition, targetPosition);
    }

    private void handleHomeMovement(Piece piece, int steps, int currentIndex, int playerEndIndex) {
        int distanceToHome = playerEndIndex - currentIndex;
        if (steps == distanceToHome) {
            System.out.println("Piece has reached home!");
            piece.setInHome(true);
            piece.getPosition().removePiece(piece);
        } else if (steps > distanceToHome) {
            System.out.println("Invalid move: Step count exceeds distance to home.");
        } else {
            System.out.println("Invalid move: You need exactly " + distanceToHome + " steps to reach home.");
        }
    }

    private void handleTargetPosition(Piece piece, Position currentPosition, Position targetPosition) {
        //handle opponentPiece existed state, when the condition true so there is opponentPiece in position
        if (!targetPosition.isSafe() && !targetPosition.getPieces().isEmpty()) {
            //check if pieces is only one , if its one reset it to home , else it will be as wall
            if (targetPosition.getPieces().size() == 1) {
                //get piece
                Piece opponentPiece = targetPosition.getPieces().get(0);
                //get player start position index
                Position opponentStart = opponentPiece.getOwner().getStartPosition();
                //reset to start
                opponentStart.addPiece(opponentPiece);
                opponentPiece.resetToStart(opponentStart);
            } else {
                System.out.println("Target position is blocked by multiple pieces. Move is not possible.");
                return;
            }
        }
        currentPosition.removePiece(piece);
        targetPosition.addPiece(piece);
        piece.setPosition(targetPosition);
    }
    public List<Piece> getPiecesAtPosition(int index) {
        return positions.get(index).getPieces();
    }

    public void printBoard(List<Player> players) {
    }
}
