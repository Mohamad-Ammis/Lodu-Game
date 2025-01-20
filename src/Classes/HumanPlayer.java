package Classes;

import Helper.Color;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Color color, Position startPosition, Position endPosition) {
        super(name, color,startPosition,endPosition);
    }

    @Override
    public void makeMove(int diceRoll, Board board) {
    }
}
