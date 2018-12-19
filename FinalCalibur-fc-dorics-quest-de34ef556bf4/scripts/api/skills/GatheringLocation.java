package scripts.api.skills;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;

import scripts.api.banking.FCBanking;
import scripts.api.generic.FCConditions;

public abstract class GatheringLocation implements Comparable<GatheringLocation>
{	
	private final int DEPOSIT_BOX_SEARCH_DISTANCE = 10;
	
	protected Positionable centerTile;
	protected RSArea area;
	
	public abstract String getName();
	public abstract Positionable centerTile();
	public abstract boolean goTo();
	public abstract boolean isDepositBox();
	public abstract boolean goToBank();
	public abstract boolean hasRequirements();
	public abstract int getRadius();
	
	public GatheringLocation()
	{
		centerTile = centerTile();
		area = getArea();
	}
	
	public RSArea getArea()
	{
		if(area == null && (centerTile != null || centerTile() != null))
			area = new RSArea(centerTile == null ? centerTile() : centerTile, getRadius());
		
		return area;
	}
	
	public Positionable getCenterTile()
	{
		return centerTile;
	}
	
	public boolean isInBank()
	{
		return !isDepositBox() ? Banking.isInBank() : FCBanking.isNearDepositBox(DEPOSIT_BOX_SEARCH_DISTANCE);
	}
	
	public boolean isBankScreenOpen()
	{
		return !isDepositBox() ? Banking.isBankScreenOpen() : Banking.isDepositBoxOpen();
	}
	
	public boolean openBank()
	{
		return !isDepositBox() ? Banking.openBank() && Timing.waitCondition(FCConditions.BANK_LOADED_CONDITION, 3500) 
				: FCBanking.openDepositBox(DEPOSIT_BOX_SEARCH_DISTANCE);
	}
	
	public int compareTo(GatheringLocation other)
	{
		return Player.getPosition().distanceTo(centerTile.getPosition()) - Player.getPosition().distanceTo(other.getCenterTile().getPosition());
	}
	
	public String toString()
	{
		return getName();
	}
}
