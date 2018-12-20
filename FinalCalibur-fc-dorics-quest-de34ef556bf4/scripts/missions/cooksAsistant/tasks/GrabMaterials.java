package scripts.missions.cooksAsistant.tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.api.interaction.GroundItemInteraction;
import scripts.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.framework.task.Task;
import scripts.missions.cooksAsistant.CooksAssistantQuest;

import static org.tribot.api.General.sleep;

public class GrabMaterials extends Task
{
	private static final long serialVersionUID = 8860378162856920333L;






	@Override
	public void execute() {

		General.println("tyrsq pot");
		GroundItemInteraction pot = new PickUpGroundItem("Pot");
		pot.execute();
		RSObject[] doors = Objects.findNearest(20, "Trapdoor");
		for (int i = 0; i < doors.length; i++) {
			if (doors[i] != null) {
				if (doors[i].isClickable()) {
					DynamicClicking.clickRSObject(doors[i], "Climb-down");
					sleep(750, 1000);
					break;
				}
			}
		}
		General.println("Otiwam pri kofata");
		RSTile bucketGo = new RSTile(3216, 9624);
		WebWalking.walkTo(bucketGo);
		RSObject[] bucket = Objects.findNearest(10, "Bucket");
		for (int i = 0; i < bucket.length; i++) {
			if (doors[0] != null) {
				if (doors[0].isClickable()) {
					DynamicClicking.clickRSObject(doors[i], "Take");
					sleep(750, 1000);
					break;
				}
			}
		}
		General.println("wzimam kofata");
		GroundItemInteraction bucketPick = new PickUpGroundItem("Bucket");
		bucketPick.execute();
		RSTile ladderGo = new RSTile(3209, 9617);
		WebWalking.walkTo(ladderGo);
		RSObject[] ladders = Objects.findNearest(10, "Ladder");
		if (ladders.length > 0) {
			RSObject target = ladders[0];
			if (target != null) {
				if (!target.isOnScreen()) {
					Camera.setCameraAngle(0);
					Camera.turnToTile(target.getPosition());
				} else {
					if (target.click("Climb-up")) {
						Camera.setCameraAngle(100);
						General.sleep(50);
					}
				}
			}
		}
	}



	@Override
	public boolean shouldExecute()
	{
		return true;
	}

	@Override
	public String getStatus()
	{
		return "Grab materials";
	}
	


}
