package scripts.framework.goal.impl;

import java.io.Serializable;

import scripts.framework.goal.Goal;

public class ResourceGoal extends Goal implements Serializable
{
	private static final long serialVersionUID = -84774096753207451L;
	
	private int[] ids;
	private int currentAmount;
	private int targetAmount;
	private String itemString;
	
	public ResourceGoal(int targetAmount, int... ids)
	{
		this.ids = ids;
		this.targetAmount = targetAmount;
		itemString = getItemString();
	}
	
	@Override
	public boolean hasReached()
	{
		return currentAmount >= targetAmount;
	}
	
	public void update(int count)
	{
		currentAmount += count;
	}
	
	public int[] getIds()
	{
		return ids;
	}

	@Override
	public String getCompletionMessage()
	{
		return "Goal Complete: Collect " + targetAmount + " of " + itemString;
	}

	@Override
	public String getName()
	{
		return  itemString + " x " + targetAmount + " (" + currentAmount + ")";
	}
	
	public String toString()
	{
		return itemString + " x " + targetAmount;
	}
	
	private String getItemString()
	{
		String str = ids.length == 1 ? "Item[" : "Items[";
		
		for(int i = 0; i < ids.length; i++)
			str += i == ids.length - 1 ? ids[i] + "]" : ids[i] + ",";
			
		return str;
	}
	
	public boolean containsId(int id)
	{
		for(int i : ids)
			if(i == id)
				return true;
		
		return false;
	}
	
}
