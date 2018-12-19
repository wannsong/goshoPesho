package scripts.api.interaction.impl.npcs;

import org.tribot.api.DynamicClicking;

import scripts.api.interaction.NpcInteraction;

public class ClickNpc extends NpcInteraction
{
	public ClickNpc(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}
	
	public ClickNpc(String action, int id, int searchDistance)
	{
		super(action, id, searchDistance);
	}
	
	@Override
	protected boolean interact()
	{
		return DynamicClicking.clickRSNPC(npc, action);
	}

}
