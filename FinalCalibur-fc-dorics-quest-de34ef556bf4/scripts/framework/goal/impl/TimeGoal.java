package scripts.framework.goal.impl;

import java.io.Serializable;

import org.tribot.api.Timing;

import scripts.framework.goal.Goal;

public class TimeGoal extends Goal implements Serializable
{
	private static final long serialVersionUID = -4676006881183854384L;
	
	private long startTime;
	private long timeAmount;
	
	public TimeGoal(long timeAmount)
	{
		this.startTime = Timing.currentTimeMillis();
		this.timeAmount = timeAmount;
	}

	@Override
	public boolean hasReached()
	{
		return Timing.timeFromMark(startTime) >= timeAmount;
	}

	@Override
	public String getCompletionMessage()
	{
		return "Goal Complete: Execute mission for " + Timing.msToString(timeAmount);
	}

	@Override
	public String getName()
	{
		return "Time goal: " + Timing.msToString(Timing.timeFromMark(startTime + timeAmount));
	}
	
	public String toString()
	{
		return "Time goal: " + Timing.msToString(timeAmount);
	}
	
	public void reset()
	{
		startTime = Timing.currentTimeMillis();
	}

}
