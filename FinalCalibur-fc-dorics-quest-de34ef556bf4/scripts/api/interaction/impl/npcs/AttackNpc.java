package scripts.api.interaction.impl.npcs;

import org.tribot.api.DynamicClicking;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSNPC;

import scripts.api.interaction.NpcInteraction;

public class AttackNpc extends NpcInteraction
{
	private final Filter<RSNPC> NPC_FILTER = npcFilter();
	
	public AttackNpc(String action, String name, int searchDistance)
	{
		super(action, name, searchDistance);
	}

	@Override
	protected boolean interact()
	{
		return DynamicClicking.clickRSNPC(npc, action);
	}
	
	@Override
	protected void findEntity()
	{
		RSNPC[] npcs = NPCs.findNearest(Filters.NPCs.nameEquals(name).combine(NPC_FILTER, false));
		
		if(npcs.length > 0)
		{
			npc = npcs[0];
			position = npc;
		}
	}
	
	private Filter<RSNPC> npcFilter()
	{
		return new Filter<RSNPC>()
		{
			@Override
			public boolean accept(RSNPC n)
			{
				return !n.isInCombat() && n.getInteractingCharacter() == null
							&& PathFinding.canReach(n, false);
			}		
		};		
	}

}
