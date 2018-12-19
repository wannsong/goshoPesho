package scripts.api.skills.mining.data.locations;

import java.io.Serializable;
import java.util.List;

import scripts.api.skills.GatheringLocation;
import scripts.api.skills.mining.data.RockType;

public abstract class MiningLocation extends GatheringLocation implements Serializable
{	
	protected static final long serialVersionUID = 7175276070017336643L;
	
	protected List<RockType> supportedRocks = getRockTypes();
	
	public abstract List<RockType> getRockTypes();
	
	public boolean contains(RockType... rocks)
	{
		for(RockType r : rocks)
			if(supportedRocks.contains(r))
				return true;
		
		return false;
	}
}
