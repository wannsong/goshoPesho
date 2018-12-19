package scripts.api.interaction.impl.npcs;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;
import org.tribot.api2007.NPCChat;

import scripts.api.generic.FCConditions;
import scripts.api.interaction.NpcInteraction;

public class NpcDialogue extends NpcInteraction
{	
	private final int FAILURE_THRESHOLD = 3;
	
	private int[] options;
	private int optionIndex;
	private int failedAttempts;
	
	public NpcDialogue(String action, String name, int searchDistance, int... options)
	{
		super(action, name, searchDistance);
		this.options = options;
	}

	@Override
	protected boolean interact()
	{	
		String chatName = NPCChat.getName();
		
		if((chatName == null || !chatName.equals(npc.getName())) && !DynamicClicking.clickRSNPC(npc, action)
				|| !Timing.waitCondition(FCConditions.IN_DIALOGUE_CONDITION, 4000))
			return false;
		
		boolean success = false;
		while(FCConditions.IN_DIALOGUE_CONDITION.active()
				&& failedAttempts < FAILURE_THRESHOLD && Game.getGameState() == 30)
		{
			if(NPCChat.getSelectOptionInterface() != null)
			{
				String[] dialogueOptions = NPCChat.getOptions();
				
				if(options.length > 0 && dialogueOptions != null && optionIndex < options.length && dialogueOptions.length > options[optionIndex])
				{
					success = NPCChat.selectOption(dialogueOptions[options[optionIndex]], true);
					optionIndex++;
				}
				else if(optionIndex >= options.length && options.length > 0 && dialogueOptions != null)
				{
					success = NPCChat.selectOption(dialogueOptions[dialogueOptions.length - 1], true);
				}
			}
			else
			{
				success = NPCChat.clickContinue(true);
			}
			
			failedAttempts += !success ? 1 : 0;
			General.sleep(600, 750);
		}
		
		return success && NPCChat.getClickContinueInterface() == null && NPCChat.getSelectOptionInterface() == null;
	}
}
