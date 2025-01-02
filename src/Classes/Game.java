package Classes;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private int maxConsecutiveSixes=3;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        currentPlayerIndex=0;
    }

    public void startGame() {
        Scanner input=new Scanner(System.in);
        System.out.println("Game is started");
        while(!isGameOver()){
        Player currentPlayer=players.get(currentPlayerIndex);
        System.out.println("It's " + currentPlayer.getName() + "'s turn!");
            System.out.println("press any button to roll dice / ( E to exit ) ");
        String status=input.nextLine();
            if (status.equals("E")) {
                System.exit(0);
            } else {
                int diceRoll = rollDice();
                currentPlayer.makeMove(diceRoll, board);
                while (diceRoll == 6) {
                currentPlayer.incrementConsecutiveSixes();
                    if (currentPlayer.canPlayAgain()) {
                        System.out.println("Congratulations "+currentPlayer.getName()+ " get more turn!");
                        System.out.println("press any button to roll dice ");
                        input.nextLine();
                        diceRoll = rollDice();
                        currentPlayer.makeMove(diceRoll, board);
                    } else {
                        break;
                    }
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                switchTurn();
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
        return false;
    }

    public void endGame() {
    }

    public void notifyPlayers() {
    }
}
