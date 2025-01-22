package Classes;

import java.util.*;

public  class  Helper {
    static Scanner input=new Scanner(System.in);
    public static Piece choosePieceToMove(Player player,int steps){
        Piece[]pieces=player.getPieces();
        Set<Piece> validPieces=new HashSet<>();

        for (Piece piece:pieces){
            int targetIndex=piece.getPosition().getIndex()+steps;
            if(!piece.isMoveExceedsEndPosition(targetIndex)&&!piece.isStart()&&!piece.isHome()&&!piece.isInHomePath()){
                validPieces.add(piece);
            }
             if (piece.isInHomePath()&&(targetIndex<piece.getOwner().getEndPosition().getIndex())){
            validPieces.add(piece);
            }
             if(piece.canMove(steps)){
                validPieces.add(piece);
            }
        }
        Piece[]validPiecesArray=validPieces.toArray(new Piece[validPieces.size()]);
        //if we have pieces to move -> choose one of them
        if(validPiecesArray.length!=0){
        System.out.println("you have "+validPiecesArray.length+" valid pieces to move\nEnter number of piece you want to move");
            for (int i = 0; i <validPiecesArray.length ; i++) {
                System.out.println((i+1)+" - for move piece of index "+validPiecesArray[i].getPosition().getIndex());
            }
            int selectedPieceIndex=input.nextInt();
            if(selectedPieceIndex<0||selectedPieceIndex>validPiecesArray.length){
                System.out.println("invalid piece selected");
                System.exit(0);
            }
            return validPiecesArray[selectedPieceIndex-1];
        }
        return null;
    }
}

