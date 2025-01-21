package Classes;

public class AILogic {

    public int calculateBestMove(Game game, int depth, Player player) {
        return 0;
    }

    public int evaluateState(Game game, Player player) {
        return 0;
    }

    private int expectiMax(
            Game game,
            int maxDepth,
            int depth,
            int playersCount,
            boolean chanceNodes,
            Player maximizePlayer,
            int turn,
            int diceRoll
    ){
        if (game.isGameOver() || depth >= maxDepth)
            return evaluateState(game, maximizePlayer);


        return  0;
    }

}
