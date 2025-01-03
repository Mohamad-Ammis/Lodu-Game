package Classes;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winnerPlayer;
    private int currentPlayerIndex;
    private int maxConsecutiveSixes=3;
    private Scanner input=new Scanner(System.in);


    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        currentPlayerIndex=0;
    }

    public void startGame() {
        System.out.println("Game is started");
        while (!isGameOver()) {
            playTurn();
            board.printBoard(players);
        }
    }

    private void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("It's " + currentPlayer.getName() + "'s turn!");
        System.out.println("Press any button to roll dice / (E to exit)");
        String status = input.nextLine();
        if (status.equals("E")) {
            System.out.println("Exiting the game...");
            input.close();
            System.exit(0);
        }
        handleDiceRoll(currentPlayer);
        switchTurn();
    }

    private void handleDiceRoll(Player currentPlayer) {
        int diceRoll = rollDice();
        currentPlayer.makeMove(diceRoll, board);
        while (diceRoll == 6) {
            currentPlayer.incrementConsecutiveSixes();
            if (currentPlayer.canPlayAgain()) {
                System.out.println("Congratulations " + currentPlayer.getName() + " gets another turn!");
                System.out.println("Press any button to roll dice ");
                input.nextLine();
                diceRoll = rollDice();
                currentPlayer.makeMove(diceRoll, board);
            } else {
                break;
            }
        }
    }


    public int rollDice() {
        int diceRoll = (int) (Math.random() * 6) + 1;
        System.out.println("Rolled: " + diceRoll);
        return diceRoll;
    }
    public void switchTurn() {
        if(currentPlayerIndex==players.size()-1){
            currentPlayerIndex=0;
        }
        else {
            currentPlayerIndex++;
        }

    }
    public boolean isGameOver() {
        for (Player player :players){
            if(player.allPiecesInHome(board)){
                winnerPlayer=player;
                endGame();
                return true;
            }
        }
            return  false;
    }

    public void endGame() {
        System.out.println("Game Over!");
        System.out.println("Congratulations! Player " + winnerPlayer.getName() + " has won the game!");
        System.exit(0);
    }

    public void notifyPlayers() {
        System.out.println("Notifying all players about the game status...");
    }
}
