package scripts.framework.requirement.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;

import scripts.api.generic.FCConditions;
import scripts.framework.requirement.Requirement;
import scripts.framework.script.FCMissionScript;

public abstract class ItemRequirement extends Requirement
{
	protected List<ReqItem> reqItems = new ArrayList<>(Arrays.asList(getReqItems()));
	private boolean hasCheckedInv;
	private boolean hasCheckedEquipment;
	private boolean hasCheckedBank;
	
	public abstract ReqItem[] getReqItems();
	
	public ItemRequirement(FCMissionScript script)
	{
		super(script);
		General.println("CHECKING FOR ITEM REQUIREMENT");
	}
	
	@Override
	public void checkReqs()
	{
		if(Login.getLoginState() == STATE.INGAME)
		{
			if(!hasCheckedInv && !reqItems.isEmpty())
			{
				checkReqs(Inventory.getAll());
				hasCheckedInv = true;
			}
			else if(!hasCheckedEquipment && !reqItems.isEmpty())
			{
				checkReqs(Equipment.getItems());
				hasCheckedEquipment = true;
			}
			else if(!hasCheckedBank && !reqItems.isEmpty())
				checkBank();
			else
			{	
				addPreReqs();
				hasCheckedReqs = true;
			}
		}
	}
	
	private void checkBank()
	{
		if(!Banking.isInBank())
			WebWalking.walkToBank(FCConditions.IN_BANK_CONDITION, 600);
		else
		{
			RSItem[] cache = script.BANK_OBSERVER.getItemArray();
			
			if(Banking.isBankScreenOpen() || cache.length > 0)
			{
				checkReqs(cache.length > 0 ? cache : Banking.getAll());
				hasCheckedBank = true;
			}
			else
			{
				if(Banking.openBank())
					Timing.waitCondition(FCConditions.BANK_LOADED_CONDITION, 4500);
			}
		}
	}
	
	private void checkReqs(RSItem[] items)
	{
		for(ReqItem req : reqItems)
			req.check(items);
		
		checkForReqs();
	}
	
	private void checkForReqs()
	{
		for(int i = reqItems.size() - 1; i >= 0; i--)
		{
			ReqItem req = reqItems.get(i);
			
			if(req.isSatisfied())
				reqItems.remove(i);
		}
	}
	
	private void addPreReqs()
	{
		for(ReqItem req : reqItems)
		{
			General.println("Player does not have item requirement: (" + req.getId() + " x " + req.getAmt() + ")");
			missions.addAll(Arrays.asList(req.getPreReqMissions()));
		}
	}

}
