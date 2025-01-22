package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winnerPlayer;
    private int currentPlayerIndex;
    private final int maxConsecutiveSixes=3;
    private Scanner input=new Scanner(System.in);


    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        currentPlayerIndex=0;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
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
        //variable to check extra turn when player eat opponent piece
        boolean extraTurn=false;
        int diceRoll = rollDice();
       extraTurn= currentPlayer.makeMove(diceRoll, this);
        handleConsecutiveSixes(currentPlayer,diceRoll);
        while (diceRoll ==6||extraTurn) {
            currentPlayer.incrementConsecutiveSixes();
            if (currentPlayer.canPlayAgain()) {
                System.out.println("Congratulations " + currentPlayer.getName() + " gets another turn!");
                System.out.println("Press any button to roll dice ");
                input.nextLine();
                diceRoll = rollDice();
               extraTurn= currentPlayer.makeMove(diceRoll, this);
                handleConsecutiveSixes(currentPlayer,diceRoll);
            } else {
                break;
            }
        }
    }
    public void handleConsecutiveSixes(Player player,int diceRoll){
        if(diceRoll!=6){
            player.resetConsecutiveSixes();
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
            if(player.allPiecesInHome()){
                winnerPlayer=player;
                endGame();
                return true;
            }
        }
            return  false;
    }

    public double calcProbability(int diceRoll){
        return (1.0 / 6.0);
    }

    public void endGame() {
        System.out.println("Game Over!");
        System.out.println("Congratulations! Player " + winnerPlayer.getName() + " has won the game!");
        System.exit(0);
    }

    public void notifyPlayers() {
        System.out.println("Notifying all players about the game status...");
    }

    public Game copy(){
        List<Player> newPlayers = new ArrayList<>();
        for (Player player: this.players){
            newPlayers.add(player.copy());
        }
        return new Game(this.board.copy(), newPlayers);
    }

    public List<Game> getNextStates(int diceRoll, Player player){
        List<Game> states = new ArrayList<>();
        for (Piece piece : player.getPieces()){
            System.out.println(diceRoll);
            if (piece.canMove(diceRoll)){
                Game newGame = this.copy();
                Piece newPiece = newGame.board.getFirstPieceAt(player, piece.getPosition().getIndex());
                newGame.board.movePiece(newPiece, diceRoll);
                states.add(newGame);
            }
        }
        return states;
    }
    public Board getBoard(){
        return this.board;
    }

}
