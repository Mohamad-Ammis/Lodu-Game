package Classes;

import Helper.Color;

public class ComputerPlayer extends Player {
    AILogic solver;

    public ComputerPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
        this.solver = new AILogic();
    }
    @Override
    public boolean makeMove(int diceRoll, Game game) {
        int bestMove = solver.calculateBestMove(game, 3, this, diceRoll);
        if (bestMove == -1){
            return false;
        }
        else{
            game.getBoard().movePiece(this.pieces[bestMove], diceRoll);
            return true;
        }
    }

    public int decideBestMove(int diceRoll, Board board) {
        return 0;
    }

    @Override
    public Player copy(){
        ComputerPlayer player =  new ComputerPlayer(this.name, this.color, this.startPosition, this.endPosition);
        player.consecutiveSixes = this.consecutiveSixes;
        return player;
    }
}
