//package Controller.Test;
//
//import Controller.BoardController;
//import Controller.GameController;
//import Controller.LetterBagController;
//import Model.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class GameTest{
//
//    @Test
//
//    public void testGetPlayers(){
//
//        List<Player> players = new ArrayList<>();
//        Player player1 = new Player("Mike");
//        Player player2 = new Player("Jack");
//        players.add(player1);
//        players.add(player2);
//        Game game = new Game(players);
//
//        assertEquals(players,game.getPlayers());
//        assertEquals(players.size(),game.getPlayers().size());
//    }
//
//    @Test
//
//    public void testCurrentPlayer(){
//        Player currentPlayer;
//        List<Player> players = new ArrayList<>();
//        Player player1 = new Player("Mike");
//        Player player2 = new Player("Jack");
//        players.add(player1);
//        players.add(player2);
//        Game game = new Game(players);
//
//        game.setCurrentPlayer(player1);
//
//        assertEquals(game.getCurrentPlayer(),player1);
//        game.setCurrentPlayer(player2);
//        assertEquals(game.getCurrentPlayer(),player2);
//
//    }
//
//    @Test
//
//    public void testPlayerMethods(){
//
//        Player player1 = new Player("Mike");
//        player1.setScore(99);
//        List<LetterTile> rack = new ArrayList<>();
//        rack.add(new LetterTile("A"));
//        rack.add(new LetterTile("B"));
//        rack.add(new LetterTile("C"));
//        rack.add(new LetterTile("D"));
//        player1.setRack(rack);
//
//
//        assertEquals(player1.getId(),"Mike");
//        assertEquals(player1.getScore(),99);
//        assertEquals(player1.getRack().size(),rack.size());
//    }
//
//    @Test
//
//    public void testGetSquareType(){
//
//        Square square1 = new Square(SquareType.NORMAL,new Location(1,1));
//        assertEquals(SquareType.NORMAL,square1.getType());
//        assertNotEquals(SquareType.CENTER_SQUARE,square1.getType());
//
//    }
//
//    @Test
//
//    public void testGenerateBag(){
//
//        LetterBagController letterBagController = new LetterBagController();
//        assertEquals(letterBagController.generateLetterBag().getTileMap().size(),27);
//
//    }
//
//    @Test
//
//    public void testIsOutOfTiles(){
//
//        Map<String, Integer> tileMap = new HashMap<>();
//        LetterBag letterBag1 = new LetterBag(tileMap);
//        LetterBagController letterBagController = new LetterBagController();
//
//        LetterBag letterBag = letterBagController.generateLetterBag();
//
//        assertFalse(letterBagController.isOutOfTiles(letterBag));
//        assertTrue(letterBagController.isOutOfTiles(letterBag1));
//
//    }
//
//    @Test
//
//    public void testFullFillRackForPlayer(){
//
//        Player player = new Player("Mike");
//        List<LetterTile> rack = new ArrayList<>();
//        player.setRack(rack);
//        LetterBagController letterBagController = new LetterBagController();
//        LetterBag letterBag = letterBagController.generateLetterBag();
//
//        assertEquals(player.getRack().size(),0);
//        letterBagController.fullFillRackForPlayer(letterBag,player);
//        assertEquals(player.getRack().size(),7);
//    }
//
//    @Test
//
//    public void testGetWinner(){
//
//        List<Player> players = new ArrayList<>();
//        GameController gameController = new GameController();
//        Player player1 = new Player("Mike");
//        Player player2 = new Player("Jack");
//        player1.setScore(10);
//        player2.setScore(1);
//        players.add(player1);
//        players.add(player2);
//        Game game = new Game(players);
//
//        assertEquals(gameController.getWinner(game),player1);
//        assertNotEquals(gameController.getWinner(game),player2);
//    }
//
//    @Test
//
//    public void testSwitchPlayer(){
//
//        GameController gameController = new GameController();
//        List<Player> players = new ArrayList<>();
//        Player player1 = new Player("Jack");
//        Player player2 = new Player("Denis");
//        players.add(player1);
//        players.add(player2);
//        Game game = new Game(players);
//        game.setCurrentPlayer(player1);
//
//        gameController.switchPlayer(game);
//
//        assertEquals(game.getCurrentPlayer(),player2);
//        assertNotEquals(game.getCurrentPlayer(),player1);
//    }
//}
//
