package scripts.framework.goal.impl;

import java.io.Serializable;

import scripts.framework.goal.Goal;

public class InfiniteGoal extends Goal implements Serializable
{
	private static final long serialVersionUID = -3174768588445160687L;

	@Override
	public boolean hasReached()
	{
		return false;
	}

	@Override
	public String getCompletionMessage()
	{
		return "This will never be reached";
	}

	@Override
	public String getName()
	{
		return "Infinite";
	}
	
	public String toString()
	{
		return getName();
	}
}
