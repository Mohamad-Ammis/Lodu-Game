package Main;

import Classes.Board;
import Classes.Game;
import Classes.HumanPlayer;
import Classes.Player;
import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[]args){
        Player player1=new HumanPlayer("Mohamad", Color.RED);
        Player player2=new HumanPlayer("Ali", Color.BLUE);
        Player player3=new HumanPlayer("Fouad", Color.YELLOW);
        Player player4=new HumanPlayer("adnan", Color.GREEN);
        List<Player> playerList=new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        Game game=new Game(new Board(),playerList
        );
        game.startGame();
    }
}
