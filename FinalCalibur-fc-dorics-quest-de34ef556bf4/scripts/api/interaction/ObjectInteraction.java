package scripts.api.interaction;

import org.tribot.api2007.Objects;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSObject;

public abstract class ObjectInteraction extends EntityInteraction
{
	protected RSObject object;
	
	public ObjectInteraction(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}
	
	public ObjectInteraction(String action, int id, int searchDistance)
	{
		super(action, id, searchDistance);
	}

	public ObjectInteraction(String action, RSObject object)
	{
		super(action, object);
		this.object = object;
	}

	public ObjectInteraction(String action, String name, int searchDistance, boolean checkPath)
	{
		super(action, name, searchDistance, checkPath);
	}

	@Override
	protected void findEntity()
	{
		RSObject[] objects = null;
		if(id <= 0)
			objects = Objects.findNearest(searchDistance, Filters.Objects.nameEquals(name));
		else
			objects = Objects.findNearest(searchDistance, Filters.Objects.idEquals(id));
		
		if(objects.length > 0)
		{
			object = objects[0];
			position = object;
		}
	}

}
