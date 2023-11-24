package Controller;

import Model.Constants;
import Model.LetterBag;
import Model.LetterTile;
import Model.Player;
import Util.Exception.NotOwnedTileException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LetterBagController {
    public LetterBag generateLetterBag() {
        Map<String, Integer> tiles = new HashMap<>();
        tiles.put("A", 9);
        tiles.put("B", 2);
        tiles.put("C", 2);
        tiles.put("D", 4);
        tiles.put("E", 12);
        tiles.put("F", 2);
        tiles.put("G", 2);
        tiles.put("H", 2);
        tiles.put("I", 8);
        tiles.put("J", 2);
        tiles.put("K", 2);
        tiles.put("L", 4);
        tiles.put("M", 2);
        tiles.put("N", 6);
        tiles.put("O", 8);
        tiles.put("P", 2);
        tiles.put("Q", 1);
        tiles.put("R", 6);
        tiles.put("S", 4);
        tiles.put("T", 6);
        tiles.put("U", 4);
        tiles.put("V", 2);
        tiles.put("W", 2);
        tiles.put("X", 1);
        tiles.put("Y", 2);
        tiles.put("Z", 1);
        tiles.put(" ",2);
        return new LetterBag(tiles);
    }

    public boolean isOutOfTiles(LetterBag letterBag) {
        for (Integer value: letterBag.getTileMap().values()) {
            if(value > 0)
                return false;
        }
        return true;
    }

    public void fullFillRackForPlayer(LetterBag letterBag, Player player){
        int numberOfTiles = Constants.LIMIT_TILE_PER_ROUND - player.getRack().size();
        List<LetterTile> tiles = new ArrayList<>();
        if(numberOfTiles <= Constants.LIMIT_TILE_PER_ROUND && !isOutOfTiles(letterBag)){
            while(tiles.size() < numberOfTiles && !isOutOfTiles(letterBag)){
                String randomValue = String.valueOf((char) getRandomNumber((int) 'A', (int) 'Z'));
                Integer availability = letterBag.getTileMap().get(randomValue);
                if(availability > 0){
                    tiles.add(new LetterTile(randomValue));
                    letterBag.getTileMap().put(randomValue, availability - 1);
                }
            }
        }
        tiles.addAll(player.getRack());
        player.setRack(tiles);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void changeTiles(LetterBag bag, Player player, List<String> changeTiles) throws NotOwnedTileException {
        List<String> invalidTiles = new ArrayList<>(changeTiles);
        List<LetterTile> cloneRack = new ArrayList<>(player.getRack());
        for (LetterTile tile: cloneRack) {
            invalidTiles.remove(tile.getValue());
        }
        if (!invalidTiles.isEmpty()){
            throw new NotOwnedTileException(String.join(",", invalidTiles));
        }
        cloneRack = player.getRack().stream().filter(t -> !changeTiles.contains(t.getValue())).collect(Collectors.toList());
        player.setRack(cloneRack);
        fullFillRackForPlayer(bag, player);
        pushBackToRack(bag, changeTiles);
    }

    private void pushBackToRack(LetterBag bag, List<String> changeTiles) {
        for (String tile: changeTiles) {
            bag.getTileMap().put(tile, bag.getTileMap().get(tile) + 1);
        }
    }
}
