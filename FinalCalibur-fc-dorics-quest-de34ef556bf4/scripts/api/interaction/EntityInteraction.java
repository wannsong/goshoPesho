package scripts.api.interaction;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.DPathNavigator;

public abstract class EntityInteraction
{
	private final int DISTANCE_THRESHOLD = 9;
	
	protected String name;
	protected int id;
	protected String action;
	protected int searchDistance;
	protected Positionable position;
	private boolean canReach;
	private boolean checkPath;
	private boolean walkToPos = true;
	
	public EntityInteraction(String action, String name, int searchDistance)
	{
		this.action = action;
		this.name = name;
		this.searchDistance = searchDistance;
	}
	
	public EntityInteraction(String action, String name, int searchDistance, boolean checkPath)
	{
		this.action = action;
		this.name = name;
		this.searchDistance = searchDistance;
		this.checkPath = checkPath;
	}
	
	public EntityInteraction(String action, int id, int searchDistance)
	{
		this.action = action;
		this.id = id;
		this.searchDistance = searchDistance;
	}
	
	public EntityInteraction(String action, Positionable position)
	{
		this.action = action;
		this.position = position;
	}
	
	public boolean execute()
	{
		if(position == null)
			findEntity();
		if(position != null)
		{
			prepareInteraction();
			return interact();
		}
		
		return false;
	}
	
	protected abstract boolean interact();
	
	protected abstract void findEntity();
	
	protected void prepareInteraction()
	{	
		canReach = checkPath ? PathFinding.canReach(position, this instanceof ObjectInteraction) : true;
		
		if(!canReach || (Player.getPosition().distanceTo(position) >= DISTANCE_THRESHOLD && !Player.isMoving()))
			walkToPosition();
		
		if(!position.getPosition().isOnScreen())
		{
			Camera.turnToTile(position);
			
			if(!position.getPosition().isOnScreen())
				walkToPosition();
		}
	}
	
	private void walkToPosition()
	{
		if(!walkToPos)
			return;
		
		if(!canReach)
			new DPathNavigator().traverse(position);
		else
			Walking.blindWalkTo(position);		
	}
	
	public void setWalkToPos(boolean bool)
	{
		walkToPos = bool;
	}
	
	public void setCheckPath(boolean bool)
	{
		checkPath = bool;
	}
	
}
