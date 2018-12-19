package scripts.api.banking.listening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tribot.api2007.Banking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

public class FCBankObserver extends Thread
{
	private final int CYCLE_TIME = 1000;
	
	public boolean isRunning = true;
	
	private List<RSItem> bankCache =  new ArrayList<>();
	
	public FCBankObserver()
	{
		start();
	}
	
	public void run()
	{
		while(isRunning)
		{
			try
			{
				if(Banking.isBankScreenOpen())
				{
					bankCache.clear();
					bankCache.addAll(Arrays.asList(Banking.getAll()));
				}
				
				sleep(CYCLE_TIME);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean containsItem(int id, int amt)
	{
		for(RSItem i : bankCache)
		{
			if(i.getID() == id && i.getStack() >= amt)
				return true;
		}
		
		return false;
	}
	
	public boolean containsItem(String name, int amt)
	{
		for(RSItem i : bankCache)
		{
			RSItemDefinition def = i.getDefinition();
			
			if(def != null && def.getName().equals(name) && i.getStack() >= amt)
				return true;
		}
		
		return false;
	}
	
	public RSItem[] getItemArray()
	{
		return bankCache.toArray(new RSItem[bankCache.size()]);
	}
	
}
