package scripts.api.interaction;

import org.tribot.api2007.Players;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSPlayer;

public abstract class PlayerInteraction extends EntityInteraction
{
	protected RSPlayer player;
	
	public PlayerInteraction(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}

	@Override
	protected void findEntity()
	{
		RSPlayer[] players = Players.findNearest(Filters.Players.nameEquals(name));
		
		if(players.length > 0)
		{
			player = players[0];
			position = player;
		}
	}

}
