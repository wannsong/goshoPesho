package scripts.api.interaction;

import org.tribot.api2007.GroundItems;
import org.tribot.api2007.types.RSGroundItem;

public abstract class GroundItemInteraction extends EntityInteraction
{
	protected RSGroundItem groundItem;
	
	public GroundItemInteraction(String action, String name)
	{
		super(action, name, 10);
	}

	@Override
	protected void findEntity()
	{
		RSGroundItem[] items = GroundItems.findNearest(name);
		
		if(items.length > 0)
		{
			groundItem = items[0];
			position = groundItem;
		}
	}

}
