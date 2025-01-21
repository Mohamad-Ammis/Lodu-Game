package Main;

import Classes.*;
import Helper.Color;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[]args){
        Player player1=new HumanPlayer("Mohamad", Color.RED,new Position(0,false),new Position(60,false));
//        Player player2=new HumanPlayer("Ali", Color.BLUE,new Position(15,false),new Position(60,false));
//        Player player3=new HumanPlayer("Fouad", Color.YELLOW,new Position(30,false),new Position(60,false));
//        Player player4=new HumanPlayer("adnan", Color.GREEN,new Position(45,false),new Position(60,false));
        List<Player> playerList=new ArrayList<>();
        playerList.add(player1);
//        playerList.add(player2);
//        playerList.add(player3);
//        playerList.add(player4);

        Game game=new Game(new Board(),playerList
        );
        List<Game> games = game.getNextStates(6, player1);
        System.out.println(games.size());
    }
}
