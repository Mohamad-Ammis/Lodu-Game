package Classes;
import Helper.Color;

import java.util.*;

public class AILogic {

    public int calculateBestMove(Game game, int depth, Player player) {
        return 0;
    }

    public double evaluateState(Game game, Player player) {
        return 0;
    }

    private Double expectiMax(
            Game game,
            int depth,
            Player maximizePlayer,
            int diceRoll
    ){
        return maxMove(game, maximizePlayer, diceRoll, depth);
    }

    private double maxMove(Game game, Player player, int diceRoll, int depth){
        double maxValue = Double.MIN_VALUE;
        List<Game> games = game.getNextStates(diceRoll, player);
        for (Game state : games){
            double value = chanceMove(state, player, diceRoll, depth - 1);
            maxValue = Math.max(value, maxValue);
        }
        return maxValue;
    }

    private double minMove(Game game, Player player, int diceRoll, int depth){
        double minValue = Double.MAX_VALUE;
        List<Game> games = game.getNextStates(diceRoll, player);
        for (Game state : games){
            double value = chanceMove(state, player, diceRoll, depth - 1);
            minValue = Math.min(value, minValue);
        }
        return minValue;
    }

    private double chanceMove(Game game, Player player, int diceRoll, int depth){
        if ((depth == 0) || game.isGameOver()){
            return evaluateState(game, player);
        }
        double expectedValue = 0;
        for (int i = 1; i <= 6; i++){
            double value = 0;
            if (player.color == Color.RED){
                value = maxMove(game, game.players.get(1), i, depth);
            }
            else{
                value = minMove(game, game.players.get(0), i, depth);
            }
            expectedValue += game.calcProbability(i) * value;
        }
        return expectedValue;
    }
}
