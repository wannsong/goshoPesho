package scripts.missions.cooksAsistant;

import org.tribot.api2007.Game;
import scripts.framework.goal.GoalList;
import scripts.framework.quest.QuestScriptManager;
import scripts.framework.script.FCMissionScript;
import scripts.framework.task.Task;
import scripts.missions.cooksAsistant.tasks.*;

import java.util.Arrays;
import java.util.LinkedList;

public class CooksAssistantQuest extends QuestScriptManager
{
	public static final int QUEST_PROGRESS_SETTING = 31;

	public CooksAssistantQuest(FCMissionScript fcScript)
	{
		super(fcScript);
	}
	
	@Override
	public boolean hasReachedEndingCondition()
	{
		return Game.getSetting(QUEST_PROGRESS_SETTING) == 100;
	}

	@Override
	public String getMissionName()
	{
		return "Cooks Assistant";
	}
	
	@Override
	public String getEndingMessage()
	{
		return "Cooks Assistant has been completed.";
	}

	@Override
	public GoalList getGoals()
	{
		return null;
	}

	@Override
	public void execute()
	{
		executeTasks();
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(
				new Cows(), new Chickens(), new Mill(), new ReturnQuest()));
	}

	
	public String toString()
	{
		return getMissionName();
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[0];
	}

}
