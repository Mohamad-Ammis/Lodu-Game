package Classes;

import Helper.Color;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
    }
    @Override
    public boolean makeMove(int diceRoll, Game game) {
        return false;
    }

    public int decideBestMove(int diceRoll, Board board) {
        return 0;
    }

    @Override
    public Player copy(){
        ComputerPlayer player =  new ComputerPlayer(this.getName(), this.getColor(), this.getStartPosition(), this.getEndPosition());
        player.consecutiveSixes = this.consecutiveSixes;
        return player;
    }
}
