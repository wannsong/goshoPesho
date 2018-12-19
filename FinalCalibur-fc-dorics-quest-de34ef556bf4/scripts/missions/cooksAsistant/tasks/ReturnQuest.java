package scripts.missions.cooksAsistant.tasks;

import org.tribot.api2007.NPCChat;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSTile;
import scripts.framework.task.Task;

public class ReturnQuest extends Task {
    private static final RSTile walkToCook = new RSTile(3209, 3213);
    @Override
    public void execute() {
        WebWalking.walkTo(walkToCook);
        NPCChat chat = new NPCChat();
    }


    @Override
    public boolean shouldExecute() {
        return false;
    }

    @Override
    public String getStatus() {
        return null;
    }

}
