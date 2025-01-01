package Classes;

import Helper.Color;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(int diceRoll, Board board) {
    }

    public int decideBestMove(int diceRoll, Board board) {
        return 0;
    }
}
