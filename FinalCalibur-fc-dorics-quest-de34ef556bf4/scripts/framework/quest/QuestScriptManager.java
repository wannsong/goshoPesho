package scripts.framework.quest;

import scripts.framework.mission.Mission;
import scripts.framework.mission.MissionManager;
import scripts.framework.script.FCMissionScript;

import java.util.ArrayList;
import java.util.List;

public abstract class QuestScriptManager extends MissionManager implements Mission
{
	private List<Mission> preReqMissions = new ArrayList<Mission>();
	
	public QuestScriptManager(FCMissionScript fcScript)
	{
		super(fcScript);
	}


	@Override
	public String getCurrentTaskName()
	{
		return currentTask == null ? "null" : currentTask.getStatus();
	}
	
	@Override
	public void execute()
	{		
		executeTasks();
	}

	
	public List<Mission> getPreReqMissions()
	{
		return preReqMissions;
	}
}
