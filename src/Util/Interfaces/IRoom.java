package Util.Interfaces;

import java.util.List;

public interface IRoom {

	public IBoard getBoard();
	
	public int getId();
	
	public List<IPlayer> getPlayers();
}
