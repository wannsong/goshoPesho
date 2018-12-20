package scripts.missions.cooksAsistant.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.framework.task.Task;

public class Chickens extends Task {
    private static final RSTile goGate = new RSTile(3238, 3295);
    private static final RSTile goChicken = new RSTile(3230, 3299);
    @Override
    public void execute() {
        WebWalking.walkTo(goGate);
        RSObject[] gate = Objects.findNearest(10, "Gate");
        if (gate.length > 0) {
            RSObject target = gate[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Open")) {
                        Camera.setCameraAngle(100);
                        General.sleep(50);
                    }
                }
            }
        }
        WebWalking.walkTo(goChicken);
        RSObject[] eggs = Objects.findNearest(10, "Egg");
        if (eggs.length > 0) {
            RSObject target = eggs[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Take")) {
                        Camera.setCameraAngle(100);
                        General.sleep(50);
                    }
                }
            }
        }

    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public String getStatus() {
        return "otiwam pri kokoshkite";
    }
}
