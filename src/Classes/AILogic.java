package Classes;
import Helper.Color;

import java.util.*;

public class AILogic {

    public int calculateBestMove(Game game, int depth, Player player) {
        return 0;
    }

    public double evaluateState(Game game, Player player) {
        double score=0.0;

        for(Piece piece:player.getPieces()){
            Position position=piece.getPosition();
            if(piece.isHome()){
                score+=1.0;
            } else if (game.getBoard().isSafePosition(position.getIndex())) {
                score+=0.75;
            } else {
                int homeIndex=player.getEndPosition().getIndex();
                int currentIndex= position.getIndex();
                int distanceToHome=homeIndex-currentIndex;
                if(distanceToHome>0){
                    score+=1.0/distanceToHome;
                }
            }
        }
        return score*100;
    }

    private Double expectaMax(
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
            if (player.getColor() == Color.R){
                value = maxMove(game, game.getPlayers().get(1), i, depth);
            }
            else{
                value = minMove(game, game.players.get(0), i, depth);
            }
            expectedValue += game.calcProbability(i) * value;
        }
        return expectedValue;
    }
}
