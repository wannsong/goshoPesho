package scripts.api.generic;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.types.RSMenuNode;
import org.tribot.api2007.types.RSObject;

public class FCFilters
{
	public static Filter<RSObject> isReachable()
	{
		return new Filter<RSObject>()
		{
			@Override
			public boolean accept(RSObject o)
			{
				return PathFinding.canReach(o, true);
			}
		};
	}
	
	public static Filter<RSMenuNode> optionContains(String option)
	{
		return new Filter<RSMenuNode>()
		{
			@Override
			public boolean accept(RSMenuNode node)
			{
				return node.getAction().contains(option);
			}
			
		};
	}
	
}
