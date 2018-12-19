package scripts.fcdoricsquest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.framework.mission.Mission;
import scripts.framework.paint.FCPaintable;
import scripts.framework.script.FCMissionScript;
import scripts.missions.cooksAsistant.CooksAssistantQuest;

@ScriptManifest(
		authors     = { 
		    "Final Calibur",
		}, 
		category    = "Quests", 
		name        = "FC Doric's Quest", 
		version     = 0.2, 
		description = "Completes Doric's Quest for you. Start anywhere.", 
		gameMode    = 1)

public class FCDoricsQuestScript extends FCMissionScript implements FCPaintable, Painting, Starting, Ending
{

	@Override
	protected Queue<Mission> getMissions()
	{
		return new LinkedList<>(Arrays.asList(new CooksAssistantQuest(this)));
	}

	@Override
	protected String[] scriptSpecificPaint()
	{
		return new String[]{};
	}

}
