package Classes;

import Helper.Color;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
    }
    @Override
    public void makeMove(int diceRoll, Game game) {
    }

    public int decideBestMove(int diceRoll, Board board) {
        return 0;
    }
}
