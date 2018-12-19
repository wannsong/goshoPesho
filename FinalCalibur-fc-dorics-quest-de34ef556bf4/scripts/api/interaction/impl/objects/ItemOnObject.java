package scripts.api.interaction.impl.objects;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import scripts.api.interaction.ObjectInteraction;

public class ItemOnObject extends ObjectInteraction
{
	private String itemName;
	private int itemId;
	
	public ItemOnObject(String action, String name, String itemName, int searchDistance)
	{
		super(action, name, searchDistance);
		this.itemName = itemName;
	}
	
	public ItemOnObject(String action, int id, String itemName, int searchDistance)
	{
		super(action, id, searchDistance);
		this.itemName = itemName;
	}
	
	public ItemOnObject(String action, RSObject object, int itemId)
	{
		super(action, object);
		this.itemId = itemId;
	}

	@Override
	protected boolean interact()
	{
		this.name = object.getDefinition().getName();
		RSItem[] items = itemName == null ? Inventory.find(itemId) : Inventory.find(itemName);
		
		if(items.length > 0)
		{
			if(itemName == null)
				itemName = items[0].getDefinition().getName();
			
			String upText = Game.getUptext();
			
			if(upText == null || !upText.contains(itemName))
			{
				if(items[0].click("Use"))
				{
					General.sleep(600, 800);
					
					upText = Game.getUptext();
					
					if(upText != null && upText.contains(itemName))
						return DynamicClicking.clickRSObject(object, "Use " + itemName + " -> " + name);
				}
			}
			else
			{
				return DynamicClicking.clickRSObject(object, "Use " + itemName + " -> " + name);
			}
		}
		
		return false;
	} 

}
