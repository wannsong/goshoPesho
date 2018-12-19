package scripts.api.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.api.generic.FCConditions;

public class FCItemList extends ArrayList<FCItem>
{
	private static final long serialVersionUID = -5556404383763797322L;

	public FCItemList(FCItem... items)
	{
		addAll(Arrays.asList(items));
	}
	
	public boolean hasListInInv()
	{
		for(FCItem item : this)
			if(item.getWithdrawAmt() > 0)
				return false;
		
		return true;
	}
	
	public int getTotalInInventory()
	{
		int total = 0;
		for(FCItem item : this)
			total += item.getInvCount(false);
		
		return total;
	}
	
	public int getTotalInvSpaceNeeded()
	{
		int total = 0;
		for(FCItem item : this)
			total += item.isStackable() ? 1 : item.getAmt();
		
		return total;
	}
	
	public int getRemainingCountNeeded()
	{
		int count = 0;
		for(FCItem item : this)
		{
			final int INV_COUNT = item.getInvCount(false);
			final int NEEDED_AMT = item.getRequiredInvSpace();
			
			if(INV_COUNT < NEEDED_AMT)
			{
				count += NEEDED_AMT - INV_COUNT;
			}
		}
		
		return count;
	}
	
	public FCItem find(int id, String name)
	{
		for(FCItem item : this)
			if(item.equals(id, name))
				return item;
		
		return null;
	}
	
	public int getErroneousItemsInInv()
	{
		int count = 0;
		List<String[]> checked = new ArrayList<>();
		
		for(RSItem i : Inventory.getAll())
		{
			FCItem item = find(i.getID(), i.getDefinition().getName());
			if(item == null)
				count++;
			else if(item.getInvCount(false) > item.getRequiredInvSpace() && !checked.contains(item.getNames()))
			{
				count++;
				checked.add(item.getNames());
			}
		}
		
		return count;
	}
	
	public boolean withdraw()
	{
		for(int index = 0; index < size(); index++)
		{
			FCItem i = get(index);
			
			if(i.getInvCount(i.isStackable()) >= i.getAmt())
				continue;
			
			final int INV_COUNT = Inventory.getAll().length;
			final int WITHDRAW_AMT = i.getWithdrawAmt();
			final boolean WITHDRAW_ALL = !i.isStackable() && (INV_COUNT + WITHDRAW_AMT > 28 || (index == size() - 1) && i.getAmt() > 1);
			
			if(!i.withdraw(WITHDRAW_ALL ? 0 : WITHDRAW_AMT))
				return false;
			else
				Timing.waitCondition(FCConditions.inventoryChanged(INV_COUNT), 3500);
		}
		
		return true;
	}
	
}
