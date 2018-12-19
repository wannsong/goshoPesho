package scripts.missions.cooksAsistant.tasks;

import org.tribot.api.General;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSTile;
import scripts.api.interaction.impl.npcs.NpcDialogue;
import scripts.framework.task.Task;

public class CooksDialog extends Task
{
	private static final long serialVersionUID = -2799862046107080194L;
	
	private final Positionable COOKS_TITLE = new RSTile(3160, 3160, 0);
	private final int DISTANCE_THRESHOLD = 2;

	@Override
	public void execute()
	{
			WebWalking.walkTo(COOKS_TITLE);
			if(new NpcDialogue("Talk-to", "Cook", 10, 0, 0, 0).execute())
				General.sleep(600, 1800);

	}

	@Override
	public boolean shouldExecute()
	{
		return true;
	}

	@Override
	public String getStatus()
	{
		return "CooksDialog";
	}

}
