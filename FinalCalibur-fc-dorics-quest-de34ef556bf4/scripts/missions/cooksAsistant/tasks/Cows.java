package scripts.missions.cooksAsistant.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.framework.task.Task;

import static org.tribot.api.General.sleep;

public class Cows extends Task
{
    private static final long serialVersionUID = 8860378162856920333L;
    private static final  RSTile cows = new RSTile(3253, 3272);
    private static final RSTile goGate = new RSTile(3251, 3266);
    private static final RSTile wheatGate = new RSTile(3162, 3288);
    private static final RSTile wheat = new RSTile(3162, 3292);
    public void execute() {
        WebWalking.walkTo(goGate);

        checkGate();
        WebWalking.walkTo(cows);
        sleep(750, 1000);
        RSObject[] cow = Objects.findNearest(10, "Dairy cow");
        General.println("tyrsq Kravi");
        while (cow.length == 0) {
            cow = Objects.findNearest(10, "Dairy cow");
        }
        if (cow.length > 0){
            RSObject target = cow[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Milk")) {
                        Camera.setCameraAngle(100);
                        sleep(50);
                    }
                }
            }
        }
        General.println("Nqma kravi");
        WebWalking.walkTo(wheatGate);
        checkGate();
        WebWalking.walkTo(wheat);
        RSObject[] wheats = Objects.findNearest(10, "Wheat");
        if (wheats.length > 0) {
            RSObject target = wheats[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Pick")) {
                        Camera.setCameraAngle(100);
                        sleep(50);
                    }
                }
            }
        }
    }



    private void checkGate(){
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
                        sleep(50);
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
        return "Otiwam da duq krawite";
    }
}
