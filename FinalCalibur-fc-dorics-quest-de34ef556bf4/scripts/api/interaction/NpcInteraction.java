package scripts.api.interaction;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSNPC;

public abstract class NpcInteraction extends EntityInteraction
{
	protected RSNPC npc;
	
	public NpcInteraction(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}

	public NpcInteraction(String action, int id, int searchDistance)
	{
		super(action, id, searchDistance);
	}

	@Override
	protected abstract boolean interact();

	@Override
	protected void findEntity()
	{
		RSNPC[] npcs = null;
		
		if(id <= 0)
			npcs = NPCs.findNearest(Filters.NPCs.nameEquals(name));
		else
			npcs = NPCs.findNearest(id);
		
		if(npcs.length > 0)
		{
			npc = npcs[0];
			position = npc;
		}
	}

}
