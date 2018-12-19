package scripts.api.interaction.impl.objects;

import org.tribot.api.Clicking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.util.ThreadSettings;

import scripts.api.interaction.ObjectInteraction;

public class ClickObject extends ObjectInteraction
{
	private boolean rightClick;
	
	public ClickObject(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}
	
	public ClickObject(String action, int id, int searchDistance)
	{
		super(action, id, searchDistance);
	}
	
	public ClickObject(String action, String name, int searchDistance, boolean checkPath, boolean rightClick)
	{
		super(action, name, searchDistance, checkPath);
		this.rightClick = rightClick;
	}
	
	public ClickObject(String action, RSObject object)
	{
		super(action, object);
	}

	@Override
	protected boolean interact()
	{
		ThreadSettings.get().setClickingAPIUseDynamic(true);
		
		if(rightClick)
			ThreadSettings.get().setAlwaysRightClick(true);
		
		boolean result = Clicking.click(action, object);
		
		ThreadSettings.get().setClickingAPIUseDynamic(false);
		ThreadSettings.get().setAlwaysRightClick(false);
		
		return result;
	}

}
