package Classes;

import Helper.Color;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
    }
    @Override
    public boolean makeMove(int diceRoll, Board board) {
        return false;
    }

    public int decideBestMove(int diceRoll, Board board) {
        return 0;
    }
}
