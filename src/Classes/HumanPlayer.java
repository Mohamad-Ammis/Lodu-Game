package Classes;

import Helper.Color;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
    }

    @Override
    public boolean makeMove(int diceRoll, Game game) {
        Piece selectedPiece=Helper.choosePieceToMove(game.getPlayers().get(game.getCurrentPlayerIndex()),diceRoll);
        if(selectedPiece!=null){

       return game.getBoard().movePiece(selectedPiece,diceRoll);
        }
        return false;
    }
    @Override
    public Player copy(){
        HumanPlayer player = new HumanPlayer(this.name, this.color, this.startPosition, this.endPosition);
        player.consecutiveSixes = this.consecutiveSixes;
        return player;
    }
}
