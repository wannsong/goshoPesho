package scripts.api.skills.mining.data.locations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import scripts.api.skills.mining.data.RockType;
import scripts.api.skills.mining.data.locations.impl.AlKharidMine;
import scripts.api.skills.mining.data.locations.impl.BarbVillageMine;
import scripts.api.skills.mining.data.locations.impl.LumbridgeTrainingMine;
import scripts.api.skills.mining.data.locations.impl.RimmingtonMine;
import scripts.api.skills.mining.data.locations.impl.SouthwestLumbridgeSwampMine;
import scripts.api.skills.mining.data.locations.impl.VarrockEastMine;
import scripts.api.skills.mining.data.locations.impl.VarrockWestMine;

public class MiningLocHandler implements Serializable
{
	private static final long serialVersionUID = -453927077523842893L;

	public static final List<MiningLocation> LOCATIONS = new ArrayList<>(Arrays.asList(new VarrockEastMine(), new VarrockWestMine(),
			new BarbVillageMine(), new AlKharidMine(), new LumbridgeTrainingMine(), new RimmingtonMine(), new SouthwestLumbridgeSwampMine()));
	
	public List<CustomMiningLocation> customLocations = new ArrayList<>();
	
	public List<MiningLocation> getAppropriateLocations(RockType... rocks)
	{
		List<MiningLocation> appropriateLocs = new ArrayList<>();
		
		for(MiningLocation loc : LOCATIONS)
		{
			if(loc.contains(rocks) && loc.hasRequirements())
				appropriateLocs.add(loc);
		}
		
		for(CustomMiningLocation custom : customLocations)
		{
			if(custom.contains(rocks))
				appropriateLocs.add(custom);
		}
		
		Collections.sort(appropriateLocs);
		
		return appropriateLocs;
	}
}
