package scripts.framework.requirement;

import java.util.ArrayList;
import java.util.List;

import scripts.framework.mission.Mission;
import scripts.framework.script.FCMissionScript;

public abstract class Requirement
{
	protected List<Mission> missions = new ArrayList<Mission>(); //The list of missions that will satisfy the requirements
	protected boolean hasCheckedReqs;
	protected FCMissionScript script;
	
	public abstract void checkReqs();
	public abstract List<Mission> getReqMissions();
	
	public Requirement(FCMissionScript script)
	{
		this.script = script;
	}
	
	public List<Mission> getMissions()
	{
		return missions;
	}
	
	public boolean hasCheckedReqs()
	{
		return hasCheckedReqs;
	}
}
