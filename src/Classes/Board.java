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
            if (piece.getOwner().getColor() == player.getColor()){
                return piece;
            }
        }
        return null;
    }

    public boolean movePiece(Piece piece, int steps) {
        Position currentPosition = piece.getPosition();

        if (currentPosition == null) {
            throw new IllegalStateException("Piece is not on the board.");
        }
        if (!piece.canMove( steps)) return false;

        //we need owner player to get his own home path
        Player owner = piece.getOwner();
        //we need this three index variable to check if piece is in player home path
        int currentIndex = currentPosition.getIndex();
        int playerEndIndex = owner.getEndPosition().getIndex();
        int homeStartIndex = playerEndIndex - 5; // Start of home path (last 5 positions)
        //target index where we will put the piece
        int targetIndex = currentIndex + steps;
        if (piece.isInHomePath()) {
            handleHomeMovement(piece, steps, currentIndex, playerEndIndex);
            return false;
        }
        if (piece.isMoveExceedsEndPosition (targetIndex)) {
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
       if(piece.isStart()&&(targetPosition.getIndex()==6)){
           piece.setInPlay(true);
           piece.setStart(false);
           return false;
       }
        piece.setInPlay(true);
        piece.setStart(false);
        piece.setInHome(false);
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
                newPos.addPiece(newPiece);
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

