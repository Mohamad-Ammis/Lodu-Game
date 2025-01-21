package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Helper.*;

public class Board {
    private final  int SIZE=60;
    public List<Position> positions;

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

    public Piece getFirstPieceAt(Player player, int index){
        Position position = getPositionAt(index);
        for (Piece piece : position.getPieces()){
            if (piece.getOwner().color == player.color){
                return piece;
            }
        }
        return null;
    }

    public void movePiece(Piece piece, int steps) {
        Position currentPosition = piece.getPosition();

        if (currentPosition == null) {
            throw new IllegalStateException("Piece is not on the board.");
        }
        if (!canPieceMove(piece, steps)) return;

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
            return;
        }
        if (isMoveExceedsEndPosition (targetIndex, playerEndIndex)) {
            return;
        }
        //when piece it's not on home path so we handle normal move state
        Position targetPosition = getPositionAt(targetIndex);
        handleTargetPosition(piece, currentPosition, targetPosition);
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


    private void handleTargetPosition(Piece piece, Position currentPosition, Position targetPosition) {
        //handle opponentPiece existed state
        if (isBlockedBySinglePiece(targetPosition,piece)) {
            handleOpponentPiece(targetPosition,piece);
            return;
        }if (isBlockedByMultiplePieces(targetPosition,piece)) {
            System.out.println("Target position is blocked by multiple pieces. Move is not possible.");
            return;
        }
        //normal state
        updatePiecePosition(piece, currentPosition, targetPosition);
    }
    private int blockedOpponentPiecesCount(Position targetPosition, Piece piece) {
        int count=0;
        if (!targetPosition.isSafe() && !targetPosition.getPieces().isEmpty()) {
            for (Piece opponentPiece : targetPosition.getPieces()) {
                if (piece.isOpponentPiece(opponentPiece)) {
                    count++;
                }
            }
        }
        return count;
    }
    public boolean isBlockedBySinglePiece(Position targetPosition, Piece piece){
        return blockedOpponentPiecesCount(targetPosition, piece)==1;
    }
    public boolean isBlockedByMultiplePieces(Position targetPosition, Piece piece){
        return blockedOpponentPiecesCount(targetPosition, piece)>=2;
    }

    private void handleOpponentPiece(Position targetPosition,Piece piece) {
            //first get piece,get owner start position to reset it ,remove piece from current position,then reset it to start
            Piece opponentPiece = targetPosition.getPieces().get(0);
        if (piece.isOpponentPiece(opponentPiece)) {
            Position opponentStart = opponentPiece.getOwner().getStartPosition();
            Position opponentPiecePosition=opponentPiece.getPosition();
            opponentPiecePosition.removePiece(opponentPiece);
            opponentPiece.resetToStart(opponentStart);
            System.out.println("Opponent piece reset to start position.");
        }
    }

    public boolean canPieceMove(Piece piece, int steps) {
        if (piece.isStart() && steps != 6) {
            System.out.println("Piece cannot be moved from the start position unless you roll a 6.");
            return false;
        }
        if (piece.isHome()) {
            System.out.println("Piece cannot be moved from the home.");
            return false;
        }
        return true;
    }

    private void updatePiecePosition(Piece piece, Position currentPosition, Position targetPosition) {
        currentPosition.removePiece(piece);
        targetPosition.addPiece(piece);
        piece.setPosition(targetPosition);
        piece.setInPlay(true);
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

    public Board copy() {
        Board clonedBoard = new Board();

        // Clear the initialized positions in the cloned board
        clonedBoard.positions.clear();

        // Clone each position and add it to the cloned board
        for (Position position : this.positions) {
            Position newPos = position.copy();
            for (Piece piece: position.getPieces()){
                Piece newPiece = piece.copy(position);
                position.addPiece(newPiece);
            }
            clonedBoard.positions.add(newPos);
        }

        return clonedBoard;
    }


    public void printBoard() {
        // Initialize an empty board
        String[] board = new String[225]; // 15x15 grid
        Arrays.fill(board, "   "); // Initialize with empty spaces

        // Mark safe positions
        for (int safe : Constant.SAFE_POSITIONS) {
            double[] coords = Constant.COORDINATES_MAP.get(safe);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = " S "; // Safe position
        }

        // Mark base positions for P1 and P2
        for (int i = 0; i < Constant.BASE_POSITIONS.get("P1").length; i++) {
            double[] coords = Constant.COORDINATES_MAP.get(Constant.BASE_POSITIONS.get("P1")[i]);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = "P1 "; // Base positions for P1
        }
        for (int i = 0; i < Constant.BASE_POSITIONS.get("P2").length; i++) {
            double[] coords = Constant.COORDINATES_MAP.get(Constant.BASE_POSITIONS.get("P2")[i]);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = "P2 "; // Base positions for P2
        }

        // Mark home entrance positions for P1 and P2
        for (int i = 0; i < Constant.HOME_ENTRANCE.get("P1").length; i++) {
            double[] coords = Constant.COORDINATES_MAP.get(Constant.HOME_ENTRANCE.get("P1")[i]);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = "[ ]"; // Home entrance for P1
        }
        for (int i = 0; i < Constant.HOME_ENTRANCE.get("P2").length; i++) {
            double[] coords = Constant.COORDINATES_MAP.get(Constant.HOME_ENTRANCE.get("P2")[i]);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = "[ ]"; // Home entrance for P2
        }

        // Mark turning points for P1 and P2
        for (String player : Constant.TURNING_POINTS.keySet()) {
            int turningPoint = Constant.TURNING_POINTS.get(player);
            double[] coords = Constant.COORDINATES_MAP.get(turningPoint);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = " T "; // Turning point
        }
        // Mark home positions for P1 and P2
        for (String player : Constant.HOME_POSITIONS.keySet()) {
            int homePosition = Constant.HOME_POSITIONS.get(player);
            double[] coords = Constant.COORDINATES_MAP.get(homePosition);
            int index = getIndex((int) coords[0], (int) coords[1]);
            board[index] = " H "; // Home position
        }

        // Mark the walkable positions with '#', but avoid overwriting 'S' (safe positions)
        for (int i = 0; i < Constant.PATH_POSITIONS.size(); i++) {
            double[] coords = Constant.COORDINATES_MAP.get(Constant.PATH_POSITIONS.keySet().toArray()[i]);
            int index = getIndex((int) coords[0], (int) coords[1]);

            // Check if the position already has an 'S' (safe position)
            if (!board[index].equals(" S ") && !board[index].equals(" T ")) {  // Only set '#' if the position is not a safe spot
                board[index] = " # ";
            }
        }

        // Print the board with spacing
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(board[i * 15 + j]);
            }
            System.out.println();
        }
    }

    // Convert (x, y) coordinates to board index
    private int getIndex(int x, int y) {
        return (y * 15) + x;
    }

}

