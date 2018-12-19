package scripts.framework.mission;

import scripts.framework.goal.GoalList;

public interface Mission
{
	public boolean hasReachedEndingCondition();
	
	public String getMissionName();
	
	public String getCurrentTaskName();
	
	public String getEndingMessage();
	
	public GoalList getGoals();
	
	public String[] getMissionSpecificPaint();
	
	public void execute();
}
