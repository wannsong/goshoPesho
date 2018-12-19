package scripts.missions.cooksAsistant.tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.framework.task.Task;

public class Mill extends Task {
    private static final RSTile millDoor = new RSTile(3167, 3302);
    private static final RSTile mill = new RSTile(3165, 3306);
    private static final int STANDARD_LUMB_TELEPORT_ANIMATION = 4847;
    private static final int RUNEFEST_LUMB_TELEPORT_ANIMATION = 1696;

    @Override
    public void execute() {
        WebWalking.walkTo(millDoor);
        handleDoor();
        WebWalking.walkTo(mill);
        RSObject[] ladders = Objects.findNearest(10, "Ladder");
        if (ladders.length > 0){
            if (ladders[0] != null){
                DynamicClicking.clickRSObject(ladders[0], "Climb-up");
            }
        }
        RSObject[] ladders2 = Objects.findNearest(10, "Ladder");
        if (ladders2.length > 0){
            if (ladders2[0] != null){
                DynamicClicking.clickRSObject(ladders2[0], "Climb-up");
            }
        }
        RSItem[] grain = Inventory.find(1947);
        grain[0].click("Use");
        RSObject[] hopper = Objects.findNearest(10, "Hopper");
        if (hopper.length > 0) {
            RSObject target = hopper[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Use")) {
                        Camera.setCameraAngle(100);
                        General.sleep(50);
                    }
                }
            }
        }
        RSObject[] hopperControls = Objects.findNearest(10, "Hopper controls");
        if (hopperControls.length > 0) {
            RSObject target = hopperControls[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Operate")) {
                        Camera.setCameraAngle(100);
                        General.sleep(50);
                    }
                }
            }
        }
        RSObject[] ladders3 = Objects.findNearest(10, "Ladder");
        if (ladders3.length > 0){
            if (ladders3[0] != null){
                DynamicClicking.clickRSObject(ladders3[0], "Climb-down");
            }
        }
        RSObject[] ladders4 = Objects.findNearest(10, "Ladder");
        if (ladders4.length > 0){
            if (ladders4[0] != null){
                DynamicClicking.clickRSObject(ladders4[0], "Climb-down");
            }
        }
        RSObject[] flowerBin = Objects.findNearest(10, "Flour bin");
        if (flowerBin.length > 0) {
            RSObject target = flowerBin[0];
            if (target != null) {
                if (!target.isOnScreen()) {
                    Camera.setCameraAngle(0);
                    Camera.turnToTile(target.getPosition());
                } else {
                    if (target.click("Empty")) {
                        Camera.setCameraAngle(100);
                        General.sleep(50);
                    }
                }
            }
        }
        teleport();

    }

    private void handleDoor() {
        RSObject[] doors = Objects.findNearest(10, "Large door");
        if (doors.length > 0) {
            RSObject target = doors[0];
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
    }
    public void teleport() {
        if (Magic.selectSpell("Lumbridge Home teleport")) {
            if (Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    int animation = Player.getAnimation();
                    return animation == STANDARD_LUMB_TELEPORT_ANIMATION ||
                            animation == RUNEFEST_LUMB_TELEPORT_ANIMATION;
                }
            }, General.random(2000, 3000)))
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return Player.getAnimation() < 0;
                    }
                }, General.random(15000, 17000));
        }
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public String getStatus() {
        return "Otiwam w melnicata da bozq";
    }
}
